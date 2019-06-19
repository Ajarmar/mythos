package instructions.thumb.format7

import instructions.thumb.ThumbInstruction

abstract class Format7 extends ThumbInstruction {
  val ro, rb, rd: Byte
}

object Format7 {
  def apply(l: String, b: String, ro: String, rb: String, rd: String): Format7 = {
    val args: Array[Byte] = Array(ro,rb,rd).map(a => Integer.parseInt(a,2).toByte)
    l match {
      case "0" => b match {
        case "0" => STR(args(0),args(1),args(2))
        case "1" => STRB(args(0),args(1),args(2))
      }
      case "1" => b match {
        case "0" => LDR(args(0),args(1),args(2))
        case "1" => LDRB(args(0),args(1),args(2))
      }
    }
  }
}
