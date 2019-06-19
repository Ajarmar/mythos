package instructions.thumb.format10

import instructions.thumb.ThumbInstruction

abstract class Format10 extends ThumbInstruction {
  val offset5, rb, rd: Byte
}

object Format10 {
  def apply(l: String, offset5: String, rb: String, rd: String): Format10 = {
    val args: Array[Byte] = Array(offset5,rb,rd).map(a => Integer.parseInt(a,2).toByte)
    l match {
      case "0" => STRH(args(0),args(1),args(2))
      case "1" => LDRH(args(0),args(1),args(2))
    }
  }
}
