package instructions.thumb.format6

import instructions.thumb.ThumbInstruction

abstract class Format6 extends ThumbInstruction {
  val rd, word8: Int
  override val operands: List[String] = regNameFormat(List(rd))
  override val addrOperands: List[String] = List(addrFormat(List("r15", immValueFormat(word8))))
}

object Format6 {
  def apply(rd: String, word8: String): Format6 = {
    val args: Array[Int] = Array(rd,word8).map(a => Integer.parseInt(a,2))
    LDR(args(0),args(1))
  }
}
