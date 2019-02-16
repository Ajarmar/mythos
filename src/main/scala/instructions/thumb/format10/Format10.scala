package instructions.thumb.format10

import instructions.thumb.ThumbInstruction

abstract class Format10 extends ThumbInstruction {
  val offset5, rb, rd: String
  override val operands: List[String] = regNameFormat(List(rd))
  override val addrOperands: List[String] = List(addrFormat(List(rb, immValueFormat(offset5))))
}

object Format10 {
  def apply(l: String, offset5: String, rb: String, rd: String): Format10 = l match {
    case "0" => STRH(offset5, rb, rd)
    case "1" => LDRH(offset5, rb, rd)
  }
}
