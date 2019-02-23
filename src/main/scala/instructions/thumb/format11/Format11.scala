package instructions.thumb.format11

import instructions.thumb.ThumbInstruction

abstract class Format11 extends ThumbInstruction {
  val rd, word8: Int
  override val operands: List[String] = regNameFormat(List(rd))
  override val addrOperands: List[String] = List(addrFormat(List("r13", immValueFormat(word8))))
}

object Format11 {
  def apply(l: String, rd: String, word8: String): Format11 = {
    val args: Array[Int] = Array(rd,word8).map(a => Integer.parseInt(a,2))
    l match {
      case "0" => STR(args(0),args(1))
      case "1" => LDR(args(0),args(1))
    }
  }
}
