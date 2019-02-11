package instructions.thumb.format2

import instructions.thumb.ThumbInstruction

abstract class Format2 extends ThumbInstruction {
  val i, rn_offset3, rs, rd: String
  override val operands: List[String] =
    regNameFormat(List(rd,rs)) ++ (if (i.matches("0")) regNameFormat(List(rn_offset3)) else List(immValueFormat(rn_offset3)))
}

object Format2 {
  def apply(i: String, op: String, rn_offset3: String, rs: String, rd: String): Format2 = op match {
    case "0" => ADD(i,rn_offset3,rs,rd)
    case "1" => SUB(i,rn_offset3,rs,rd)
  }
}