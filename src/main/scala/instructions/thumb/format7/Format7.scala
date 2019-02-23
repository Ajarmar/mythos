package instructions.thumb.format7

import instructions.thumb.ThumbInstruction

abstract class Format7 extends ThumbInstruction {
  val ro, rb, rd: Int
  override val operands: List[String] = regNameFormat(List(rd))
  override val addrOperands: List[String] = List(addrFormat(regNameFormat(List(rb,ro))))
}

object Format7 {
  def apply(l: String, b: String, ro: String, rb: String, rd: String): Format7 = {
    val args: Array[Int] = Array(ro,rb,rd).map(a => Integer.parseInt(a,2))
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
