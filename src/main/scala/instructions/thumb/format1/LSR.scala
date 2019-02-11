package instructions.thumb.format1

import instructions.thumb.ThumbInstruction

case class LSR(offset5: String, rs: String, rd: String) extends ThumbInstruction {
  override val mnemonic: String = "lsr"
  override val operands: List[String] = regNameFormat(List(rd,rs)) ++ List(immValueFormat(offset5))
  override val addrOperands: List[String] = _
  override val regListOperands: List[String] = _
}