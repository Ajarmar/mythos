package simulator

import java.nio.{BufferUnderflowException, ByteBuffer}

import instructions.Instruction
import instructions.thumb.format1._
import instructions.thumb.format10.Format10
import instructions.thumb.format11.Format11
import instructions.thumb.format12.Format12
import instructions.thumb.format13.Format13
import instructions.thumb.format14.Format14
import instructions.thumb.format15.Format15
import instructions.thumb.format16.Format16
import instructions.thumb.format17.Format17
import instructions.thumb.format18.Format18
import instructions.thumb.format19.Format19
import instructions.thumb.format2._
import instructions.thumb.format3._
import instructions.thumb.format4.{ADC, AND, Format4}
import instructions.thumb.format5.Format5
import instructions.thumb.format6.Format6
import instructions.thumb.format7.Format7
import instructions.thumb.format8.Format8
import instructions.thumb.format9.Format9
import instructions.thumb.{ThumbError, ThumbInstruction}

import scala.collection.mutable

class Simulator(rom: ByteBuffer) {

  val localInstructions: mutable.Map[Int,Instruction] = makeInstructions(thumb = true,0,4000)

  def getInstructionString(offset: Int): String = {
    instructionToText(makeInstruction(thumb = true,offset),offset)
  }

  def getROMInstructionStrings: List[(String,String)] = {
    localInstructions
      .toSeq
      .sortWith(_._1 < _._1)
      .map(i => ("0x0" + (i._1 + 0x08000000).toHexString,instructionToText(i._2,i._1)))
      .toList
  }

  private def makeInstructions(thumb: Boolean, start: Int, end: Int): mutable.Map[Int, Instruction] = {
    var instructions: mutable.Map[Int,Instruction] = mutable.Map()
    var statsMap: mutable.Map[String,Int] = mutable.Map()

    rom.position(start)
    while (rom.position < end) {
      val addr = rom.position()
      val instr = makeInstruction(thumb)
      //statsMap += (instr.getClass.toString.split('.').last -> (statsMap.getOrElse(instr.getClass.toString.split('.').last,0) + 1))
      instr match {
        case t: ThumbError => if (t.error == ThumbError.OutOfBounds) return instructions
        case _: ThumbInstruction => instructions += (addr -> instr)
      }
    }
    //val totalInstrs: Double = statsMap.values.sum
    //statsMap.foreach(i => println(i._1 + ": " + 100*i._2/totalInstrs + "%"))
    instructions
  }

  private def makeInstruction(thumb: Boolean): Instruction = {
    try {
      if (thumb) {
        //ThumbInstruction(rom.getShort())
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
        //ThumbInstruction(rom.getShort(offset))
        ThumbInstruction(Integer.toBinaryString(rom.getShort(offset) & 0xffff | 0x10000).substring(1))
      } else {
        ThumbInstruction(Integer.toBinaryString(rom.getShort(offset) & 0xffff | 0x10000).substring(1)) //TODO change to arm
      }
    } catch {
      case _: IndexOutOfBoundsException => if (thumb) new ThumbError(ThumbError.OutOfBounds) else new ThumbError(ThumbError.OutOfBounds) // TODO change to arm
    }
  }

  private def instructionToText(instr: Instruction, addr: Int): String = {
    instr match {
      case f1: Format1 => f1 match {
        case instructions.thumb.format1.ASR(offset5, rs, rd) =>
          "asr" + "     " + reg2Imm1(rd,rs,offset5)
        case instructions.thumb.format1.LSL(offset5, rs, rd) =>
          "lsl" + "     " + reg2Imm1(rd,rs,offset5)
        case instructions.thumb.format1.LSR(offset5, rs, rd) =>
          "lsr" + "     " + reg2Imm1(rd,rs,offset5)
      }
      case f2: Format2 => f2 match {
        case instructions.thumb.format2.ADD(i, rn_offset3, rs, rd) =>
          "add" + "     " + reg2ImmOrReg1(i,rd,rs,rn_offset3)
        case instructions.thumb.format2.SUB(i, rn_offset3, rs, rd) =>
          "sub" + "     " + reg2ImmOrReg1(i,rd,rs,rn_offset3)
      }
      case f3: Format3 => f3 match {
        case instructions.thumb.format3.ADD(rd, offset8) =>
          "add" + "     " + reg1Imm1(rd,offset8)
        case instructions.thumb.format3.CMP(rd, offset8) =>
          "cmp" + "     " + reg1Imm1(rd,offset8)
        case instructions.thumb.format3.MOV(rd, offset8) =>
          "mov" + "     " + reg1Imm1(rd,offset8)
        case instructions.thumb.format3.SUB(rd, offset8) =>
          "sub" + "     " + reg1Imm1(rd,offset8)
      }
      case f4: Format4 => f4 match {
        case instructions.thumb.format4.ADC(rs, rd) =>
          "adc" + "     " + reg2(rd,rs)
        case instructions.thumb.format4.AND(rs, rd) =>
          "and" + "     " + reg2(rd,rs)
        case instructions.thumb.format4.ASR(rs, rd) =>
          "asr" + "     " + reg2(rd,rs)
        case instructions.thumb.format4.BIC(rs, rd) =>
          "bic" + "     " + reg2(rd,rs)
        case instructions.thumb.format4.CMN(rs, rd) =>
          "cmn" + "     " + reg2(rd,rs)
        case instructions.thumb.format4.CMP(rs, rd) =>
          "cmp" + "     " + reg2(rd,rs)
        case instructions.thumb.format4.EOR(rs, rd) =>
          "eor" + "     " + reg2(rd,rs)
        case instructions.thumb.format4.LSL(rs, rd) =>
          "lsl" + "     " + reg2(rd,rs)
        case instructions.thumb.format4.LSR(rs, rd) =>
          "lsr" + "     " + reg2(rd,rs)
        case instructions.thumb.format4.MUL(rs, rd) =>
          "mul" + "     " + reg2(rd,rs)
        case instructions.thumb.format4.MVN(rs, rd) =>
          "mvn" + "     " + reg2(rd,rs)
        case instructions.thumb.format4.NEG(rs, rd) =>
          "neg" + "     " + reg2(rd,rs)
        case instructions.thumb.format4.ORR(rs, rd) =>
          "orr" + "     " + reg2(rd,rs)
        case instructions.thumb.format4.ROR(rs, rd) =>
          "ror" + "     " + reg2(rd,rs)
        case instructions.thumb.format4.SBC(rs, rd) =>
          "sbc" + "     " + reg2(rd,rs)
        case instructions.thumb.format4.TST(rs, rd) =>
          "tst" + "     " + reg2(rd,rs)
      }
      case f5: Format5 => f5 match {
        case instructions.thumb.format5.ADD(h1, h2, rs_hs, rd_hd) =>
          "add" + "     " + reg2HiLo(h1,h2,rd_hd,rs_hs)
        case instructions.thumb.format5.BX(_, h2, rs_hs, _) =>
          "bx" + "      " + reg1HiLo(h2,rs_hs)
        case instructions.thumb.format5.CMP(h1, h2, rs_hs, rd_hd) =>
          "cmp" + "     " + reg2HiLo(h1,h2,rd_hd,rs_hs)
        case instructions.thumb.format5.MOV(h1, h2, rs_hs, rd_hd) =>
          "mov" + "     " + reg2HiLo(h1,h2,rd_hd,rs_hs)
      }
      case f6: Format6 => f6 match {
        case instructions.thumb.format6.LDR(rd, word8) =>
          "ldr" + "     " + reg1PCRelative1(rd,word8,addr)
      }
      case f7: Format7 => f7 match {
        case instructions.thumb.format7.LDR(ro, rb, rd) =>
          "ldr" + "     " + reg1AddrReg1(rd,rb,ro)
        case instructions.thumb.format7.LDRB(ro, rb, rd) =>
          "ldrb" + "    " + reg1AddrReg1(rd,rb,ro)
        case instructions.thumb.format7.STR(ro, rb, rd) =>
          "str" + "     " + reg1AddrReg1(rd,rb,ro)
        case instructions.thumb.format7.STRB(ro, rb, rd) =>
          "strb" + "    " + reg1AddrReg1(rd,rb,ro)
      }
      case f8: Format8 => f8 match {
        case instructions.thumb.format8.LDRH(ro, rb, rd) =>
          "ldrh" + "    " + reg1AddrReg1(rd,rb,ro)
        case instructions.thumb.format8.LDSB(ro, rb, rd) =>
          "ldsb" + "    " + reg1AddrReg1(rd,rb,ro)
        case instructions.thumb.format8.LDSH(ro, rb, rd) =>
          "ldsh" + "    " + reg1AddrReg1(rd,rb,ro)
        case instructions.thumb.format8.STRH(ro, rb, rd) =>
          "strh" + "    " + reg1AddrReg1(rd,rb,ro)
      }
      case f9: Format9 => f9 match {
        case instructions.thumb.format9.LDR(offset5, rb, rd) =>
          "ldr" + "     " + reg1AddrImm1(rd,rb,offset5,2)
        case instructions.thumb.format9.LDRB(offset5, rb, rd) =>
          "ldrb" + "    " + reg1AddrImm1(rd,rb,offset5,0)
        case instructions.thumb.format9.STR(offset5, rb, rd) =>
          "str" + "     " + reg1AddrImm1(rd,rb,offset5,2)
        case instructions.thumb.format9.STRB(offset5, rb, rd) =>
          "strb" + "    " + reg1AddrImm1(rd,rb,offset5,0)
      }
      case f10: Format10 => f10 match {
        case instructions.thumb.format10.LDRH(offset5, rb, rd) =>
          "ldrh" + "    " + reg1AddrImm1(rd,rb,offset5,1)
        case instructions.thumb.format10.STRH(offset5, rb, rd) =>
          "strh" + "    " + reg1AddrImm1(rd,rb,offset5,1)
      }
      case f11: Format11 => f11 match {
        case instructions.thumb.format11.LDR(rd, word8) =>
          "ldr" + "     " + reg1SPRelative1(rd,word8)
        case instructions.thumb.format11.STR(rd, word8) =>
          "str" + "     " + reg1SPRelative1(rd,word8)
      }
      case f12: Format12 => f12 match {
        case instructions.thumb.format12.ADD(sp,rd,word8) =>
          "add" + "     " + reg1PCorSP1Imm1(sp,rd,word8)
      }
      case f13: Format13 => f13 match {
        case instructions.thumb.format13.ADD(s,sword7) =>
          "add" + "     " + sp1SignedImm1(s,sword7)
      }
      case f14: Format14 => f14 match {
        case instructions.thumb.format14.POP(r,rlist) =>
          "pop" + "     " + regList1(rlist,0,r)
        case instructions.thumb.format14.PUSH(r,rlist) =>
          "push" + "    " + regList1(rlist,r,0)
      }
      case f15: Format15 => f15 match {
        case instructions.thumb.format15.LDMIA(rb,rlist) =>
          "ldmia" + "   " + "r" + rb + "!, " + regList1(rlist,0,0)
        case instructions.thumb.format15.STMIA(rb,rlist) =>
          "stmia" + "   " + "r" + rb + "!, " + regList1(rlist,0,0)
      }
      case f16: Format16 => f16 match {
        case instructions.thumb.format16.BCC(soffset8) =>
          "bcc" + "     " + branchTarget(soffset8,addr)
        case instructions.thumb.format16.BCS(soffset8) =>
          "bcs" + "     " + branchTarget(soffset8,addr)
        case instructions.thumb.format16.BEQ(soffset8) =>
          "beq" + "     " + branchTarget(soffset8,addr)
        case instructions.thumb.format16.BGE(soffset8) =>
          "bge" + "     " + branchTarget(soffset8,addr)
        case instructions.thumb.format16.BGT(soffset8) =>
          "bgt" + "     " + branchTarget(soffset8,addr)
        case instructions.thumb.format16.BHI(soffset8) =>
          "bhi" + "     " + branchTarget(soffset8,addr)
        case instructions.thumb.format16.BLE(soffset8) =>
          "ble" + "     " + branchTarget(soffset8,addr)
        case instructions.thumb.format16.BLS(soffset8) =>
          "bls" + "     " + branchTarget(soffset8,addr)
        case instructions.thumb.format16.BLT(soffset8) =>
          "blt" + "     " + branchTarget(soffset8,addr)
        case instructions.thumb.format16.BMI(soffset8) =>
          "bmi" + "     " + branchTarget(soffset8,addr)
        case instructions.thumb.format16.BNE(soffset8) =>
          "bne" + "     " + branchTarget(soffset8,addr)
        case instructions.thumb.format16.BPL(soffset8) =>
          "bpl" + "     " + branchTarget(soffset8,addr)
        case instructions.thumb.format16.BVC(soffset8) =>
          "bvc" + "     " + branchTarget(soffset8,addr)
        case instructions.thumb.format16.BVS(soffset8) =>
          "bvs" + "     " + branchTarget(soffset8,addr)
      }
      case f17: Format17 => f17 match {
        case instructions.thumb.format17.SWI(value8) =>
          "swi" + "     " + "#0x0" + (0x08000000 | value8).toHexString
      }
      case f18: Format18 => f18 match {
        case instructions.thumb.format18.B(offset11) =>
          "b" + "       " + "#0x0" + (0x08000000 | (addr + 4 + ((offset11 << 20) >> 20))).toHexString
      }
      case f19: Format19 => f19 match {
        case instructions.thumb.format19.BL(h,offset) =>
          "bl" + "      " + branchLinkTarget(h,offset,addr)
      }
      case _ => "-"
    }
  }

  val reg2Imm1: (Byte,Byte,Byte) => String = (r1,r2,imm) => {
    "r" + r1 + ", r" + r2 + ", #0x" + imm.toHexString
  }
  val reg2ImmOrReg1: (Byte,Byte,Byte,Byte) => String = (i,r1,r2,immOrReg) => {
    if (i == 1) "r" + r1 + ", r" + r2 + ", #0x" + immOrReg.toHexString
    else "r" + r1 + ", r" + r2 + ", r" + immOrReg
  }
  val reg1Imm1: (Short,Short) => String = (r,imm) => {
    "r" + r + ", #0x" + imm.toHexString
  }
  val reg2: (Byte,Byte) => String = (r1,r2) => {
    "r" + r1 + ", r" + r2
  }
  val reg2HiLo: (Byte,Byte,Byte,Byte) => String = (h1,h2,r1,r2) => {
    if (h1 == 0)
      if (h2 == 0) "---"
      else "r" + r1 + ", r" + (r2 + 8)
    else {
      if (h2 == 0) "r" + (r1 + 8) + ", r" + r2
      else "r" + (r1 + 8) + ", r" + (r2 + 8)
    }
  }
  val reg1HiLo: (Byte,Byte) => String = (h,r) => {
    if (h == 0) "r" + r
    else "r" + (r + 8)
  }
  val reg1PCRelative1: (Short,Short,Int) => String = (r,offset,addr) => {
    "r" + r + ", =#0x" + rom.getInt(addr+4+(offset << 2)).toHexString
  }
  val reg1AddrReg1: (Byte,Byte,Byte) => String = (r1,r2,r3) => {
    "r" + r1 + ", [r" + r2 + ", r" + r3 + "]"
  }
  val reg1AddrImm1: (Byte,Byte,Byte,Byte) => String = (r1,r2,imm,shift) => {
    "r" + r1 + ", [r" + r2 + ", #0x" + (imm << shift).toHexString + "]"
  }
  val reg1SPRelative1: (Short,Short) => String = (r,imm) => {
    "r" + r + ", [SP, #0x" + (imm << 2).toHexString + "]"
  }
  val reg1PCorSP1Imm1: (Short,Short,Short) => String = (sp,r,imm) => {
    if (sp == 0) "r" + r + ", PC, #0x" + (imm << 2).toHexString
    else "r" + r + ", SP, #0x" + (imm << 2).toHexString
  }
  val sp1SignedImm1: (Byte,Byte) => String = (sign,imm) => {
    if (sign == 0) "SP, #0x" + (imm << 2).toHexString
    else "SP, #-0x" + (imm << 2).toHexString
  }
  val branchTarget: (Byte,Int) => String = (signedOffset,addr) => {
    "#0x0" + (0x08000000 | (addr + 4 + (signedOffset << 0))).toHexString
  }
  val branchLinkTarget: (Short,Short,Int) => String = (h,offset,addr) => {
    if (h == 0) "#0x0" + (0x08000000 | (addr + 4 + offset + ((rom.getShort(addr+2) & 0x7FF) << 1))).toHexString
    else ""
  }
  val regList1: (List[Byte],Byte,Byte) => String = (rlist: List[Byte],r14,r15) => {
    if (r14 == 1) "{" + regListFormatRec(rlist,0).replaceAll(",$","") + ",r14}"
    else {
      if (r15 == 1) "{" + regListFormatRec(rlist,0).replaceAll(",$","") + ",r15}"
      else "{" + regListFormatRec(rlist,0).replaceAll(",$","") + "}"
    }
  }

  private def regListFormatRec(regs: List[Byte], consecutive: Byte): String = {
    if (regs.isEmpty) return ""
    val zipped = regs.zip(regs.tail)
    if (zipped.isEmpty) return "r" + regs.head
    consecutive match {
      case 0 => "r" + zipped.head._1 +
        (if (zipped.head._2 == zipped.head._1 + 1) "-" + regListFormatRec(regs.tail,1)
        else "," + regListFormatRec(regs.tail,0))
      case _ =>
        if (zipped.head._2 == zipped.head._1 + 1) regListFormatRec(regs.tail,1)
        else "r" + zipped.head._1 + "," + regListFormatRec(regs.tail,0)
    }
  }
}
