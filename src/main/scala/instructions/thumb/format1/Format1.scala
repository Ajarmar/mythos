package instructions.thumb.format1

import instructions.thumb.ThumbInstruction

abstract class Format1 extends ThumbInstruction {
  val offset5, rs, rd: String
  override val operands: List[String] = regNameFormat(List(rd,rs)) ++ List(immValueFormat(offset5))
}

object Format1 {
  def apply(op: String, offset5: String, rs: String, rd: String): Format1 = op match {
    case "00" => LSL(offset5,rs,rd)
    case "01" => LSR(offset5,rs,rd)
    case "10" => ASR(offset5,rs,rd)
  }
}