package instructions.thumb

import instructions.Instruction
import instructions.thumb.format1.Format1
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
import instructions.thumb.format2.Format2
import instructions.thumb.format3.Format3
import instructions.thumb.format4.Format4
import instructions.thumb.format5.Format5
import instructions.thumb.format6.Format6
import instructions.thumb.format7.Format7
import instructions.thumb.format8.Format8
import instructions.thumb.format9.Format9

abstract class ThumbInstruction extends Instruction {
  // Values for instruction displaying
  val mnemonic: String = ""
  val operands: List[String] = List()
  val addrOperands: List[String] = List()
  val regListOperands: List[String] = List()

  val regNameFormat: List[Int] => List[String] = (regs: List[Int]) => regs.map(reg => "r".concat(reg.toString))
  val immValueFormat: Int => String = (imm: Int) => "#0x".concat(imm.toHexString)
  val addrFormat: List[String] => String = (ops: List[String]) => "[" + ops.mkString(", ") + "]"
  val regListFormat: List[Int] => String = (regs: List[Int]) =>
    "{" + regListFormatRec(regs,0).replaceAll(",$","") + "}"
  val specialFormatting: Option[(Int => String) => String] = None

  private def regListFormatRec(regs: List[Int], consecutive: Int): String = {
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

  def disassembled(): String = {
    mnemonic + " " + (operands ::: addrOperands ::: regListOperands).mkString(", ")
  }
}

object ThumbInstruction {
  def apply(binary: String): ThumbInstruction = binary match {
    case THUMB.Format1(op, offset5, rs, rd) => Format1(op, offset5, rs, rd)
    case THUMB.Format2(i, op, rn_offset3, rs, rd) => Format2(i, op, rn_offset3, rs, rd)
    case THUMB.Format3(op, rd, offset8) => Format3(op, rd, offset8)
    case THUMB.Format4(op, rs, rd) => Format4(op, rs, rd)
    case THUMB.Format5(op, h1, h2, rs_hs, rd_hd) => Format5(op, h1, h2, rs_hs, rd_hd)
    case THUMB.Format6(rd, word8) => Format6(rd, word8)
    case THUMB.Format7(l, b, ro, rb, rd) => Format7(l, b, ro, rb, rd)
    case THUMB.Format8(h, s, ro, rb, rd) => Format8(h, s, ro, rb, rd)
    case THUMB.Format9(b, l, offset5, rb, rd) => Format9(b, l, offset5, rb, rd)
    case THUMB.Format10(l, offset5, rb, rd) => Format10(l, offset5, rb, rd)
    case THUMB.Format11(l, rd, word8) => Format11(l, rd, word8)
    case THUMB.Format12(sp,rd,word8) => Format12(sp,rd,word8)
    case THUMB.Format13(s,sword7) => Format13(s,sword7)
    case THUMB.Format14(l,r,rlist) => Format14(l,r,rlist)
    case THUMB.Format15(l,rb,rlist) => Format15(l,rb,rlist)
    case THUMB.Format17(value8) => Format17(value8)
    case THUMB.Format16(cond,soffset8) => Format16(cond,soffset8)
    case THUMB.Format18(offset11) => Format18(offset11)
    case THUMB.Format19(h,offset) => Format19(h,offset)
    case _ => new ThumbError()
  }
}