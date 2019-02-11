package instructions.thumb.format1

import instructions.thumb.ThumbInstruction

abstract class Format1(offset5: String, rs: String, rd: String) extends ThumbInstruction {
  override val operands: List[String] = regNameFormat(List(rd,rs)) ++ List(immValueFormat(offset5))
  override val addrOperands: List[String] = List()
  override val regListOperands: List[String] = List()
}
