package instructions.thumb.format11

import instructions.thumb.ThumbInstruction

abstract class Format11 extends ThumbInstruction {
  val rd, word8: Short
}

object Format11 {
  def apply(l: String, rd: String, word8: String): Format11 = {
    val args: Array[Short] = Array(rd,word8).map(a => Integer.parseInt(a,2).toShort)
    l match {
      case "0" => STR(args(0),args(1))
      case "1" => LDR(args(0),args(1))
    }
  }
}
