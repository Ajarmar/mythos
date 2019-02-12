package instructions.thumb

import instructions.Instruction
import instructions.thumb.format1.Format1
import instructions.thumb.format2.Format2
import instructions.thumb.format3.Format3
import instructions.thumb.format4.Format4
import instructions.thumb.format5.Format5
import instructions.thumb.format6.Format6

abstract class ThumbInstruction extends Instruction {
  val mnemonic: String
  val operands: List[String] = List()
  val addrOperands: List[String] = List()
  val regListOperands: List[String] = List()

  val regNameFormat: List[String] => List[String] = (regs: List[String]) =>
    regs.map(reg => "r".concat(Integer.parseInt(reg,2).toString))
  val immValueFormat: String => String = (imm: String) => "#0x".concat(Integer.parseInt(imm,2).toHexString)
  val addrFormat: List[String] => String = (ops: List[String]) => "[" + ops.mkString(", ") + "]"
  val regListFormat: String => String = (regs: String) =>
    "{" + regListFormatRec(regs.replaceAll("0*$","").toList.reverse,0).replaceAll("^,","") + "}"
  val specialFormatting: Option[(Int => String) => String] = None

  private def regListFormatRec(regs: List[Char], consecutive: Int): String = {
    if (regs.isEmpty) return ""
    if (regs.head.equals('1')) {
      if (consecutive == 0) {
        ",r" + (16-regs.length).toString + regListFormatRec(regs.tail,consecutive+1)
      } else {
        if (regs.lengthCompare(1) == 0) {
          return "-r" + (16-regs.length).toString
        }
        if (consecutive == 1) {
          "-" + regListFormatRec(regs.tail,consecutive+1)
        } else {
          regListFormatRec(regs.tail,consecutive+1)
        }
      }
    } else {
      if (consecutive > 1) {
        "r" + (15-regs.length).toString + regListFormatRec(regs.tail,0)
      } else {
        regListFormatRec(regs.tail,0)
      }
    }
  }

  def disassembled(): String = {
    (operands ::: addrOperands ::: regListOperands).mkString(", ")
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
  }
}