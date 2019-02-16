package instructions.thumb.format11

import instructions.thumb.ThumbInstruction

abstract class Format11 extends ThumbInstruction {
  val rd, word8: String
  override val operands: List[String] = regNameFormat(List(rd))
  override val addrOperands: List[String] = List(addrFormat(List("r13", immValueFormat(word8))))
}

object Format11 {
  def apply(l: String, rd: String, word8: String): Format11 = l match {
    case "0" => STR(rd, word8)
    case "1" => LDR(rd, word8)
  }
}
