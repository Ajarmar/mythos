package instructions.thumb.format3

import instructions.thumb.ThumbInstruction

abstract class Format3 extends ThumbInstruction {
  val rd, offset8: String
  override val operands: List[String] = regNameFormat(List(rd)) ++ List(immValueFormat(offset8))
}

object Format3 {
  def apply(op: String, rd: String, offset8: String): Format3 = op match {
    case "00" => MOV(rd,offset8)
    case "01" => CMP(rd,offset8)
    case "10" => ADD(rd,offset8)
    case "11" => SUB(rd,offset8)
  }
}