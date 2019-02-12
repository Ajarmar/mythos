package instructions.thumb.format6

import instructions.thumb.ThumbInstruction

abstract class Format6 extends ThumbInstruction {
  val rd, word8: String
  override val operands: List[String] = regNameFormat(List(rd))
  override val addrOperands: List[String] = List("r15", immValueFormat(word8))
}

object Format6 {
  def apply(rd: String, word8: String): Format6 = LDR(rd,word8)
}
