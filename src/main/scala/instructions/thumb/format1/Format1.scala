package instructions.thumb.format1

import instructions.thumb.ThumbInstruction

abstract class Format1 extends ThumbInstruction {
  val rd, rs, offset5: String
  override val operands: List[String] = regNameFormat(List(rd,rs)) ++ List(immValueFormat(offset5))
  override val addrOperands: List[String] = List()
  override val regListOperands: List[String] = List()
}

object Format1 {
  def apply(op: String, offset5: String, rs: String, rd: String): ThumbInstruction = op match {
    case "00" => LSL(offset5,rs,rd)
    case "01" => LSR(offset5,rs,rd)
    case "10" => ASR(offset5,rs,rd)
  }
}