package simulator

import java.nio.{BufferUnderflowException, ByteBuffer}

import instructions.Instruction
import instructions.thumb.{ThumbError, ThumbInstruction}

import scala.collection.mutable
import scala.collection.immutable

class Simulator(rom: ByteBuffer) {

  val localInstructions: immutable.Map[Int,Instruction] = makeInstructions(thumb = true,0,4000)

  def getInstructionString(offset: Int): String = {
    makeInstruction(thumb = true,offset).disassembled()
  }

  def getROMInstructionStrings: List[(String,String)] = {
    localInstructions.toSeq.sortWith(_._1 < _._1).map(i => ("0x0" + (i._1 + 0x08000000).toHexString,i._2.disassembled())).toList
  }

  private def makeInstructions(thumb: Boolean, start: Int, end: Int): immutable.Map[Int, Instruction] = {
    var instructions: mutable.Map[Int,Instruction] = mutable.Map()
    rom.position(start)
    while (rom.position < end) {
      val addr = rom.position()
      val instr = makeInstruction(thumb)
      instr match {
        case t: ThumbError => if (t.error == ThumbError.OutOfBounds) return instructions.toMap
        case _: ThumbInstruction =>
      }
      instructions += (addr -> instr)
    }
    instructions.toMap
  }

  private def makeInstruction(thumb: Boolean): Instruction = {
    try {
      if (thumb) {
        ThumbInstruction(Integer.toBinaryString(rom.getShort() & 0xffff | 0x10000).substring(1))
      } else {
        ThumbInstruction(Integer.toBinaryString(rom.getShort() & 0xffff | 0x10000).substring(1)) //TODO change to arm
      }
    } catch {
      case _: BufferUnderflowException => if (thumb) new ThumbError(ThumbError.OutOfBounds) else new ThumbError(ThumbError.OutOfBounds) // TODO change to arm
    }
  }

  private def makeInstruction(thumb: Boolean, offset: Int): Instruction = {
    try {
      if (thumb) {
        ThumbInstruction(Integer.toBinaryString(rom.getShort(offset) & 0xffff | 0x10000).substring(1))
      } else {
        ThumbInstruction(Integer.toBinaryString(rom.getShort(offset) & 0xffff | 0x10000).substring(1)) //TODO change to arm
      }
    } catch {
      case _: IndexOutOfBoundsException => if (thumb) new ThumbError(ThumbError.OutOfBounds) else new ThumbError(ThumbError.OutOfBounds) // TODO change to arm
    }
  }
}
