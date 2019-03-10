package instructions.thumb.format9

import instructions.thumb.ThumbInstruction

abstract class Format9 extends ThumbInstruction {
  val offset5, rb, rd: Int
  override val operands: List[String] = regNameFormat(List(rd))
  override val addrOperands: List[String] =
    List(addrFormat(regNameFormat(List(rb)) ++ (if (offset5 == 0) None else List(immValueFormat(offset5)))))
}

object Format9 {
  def apply(b: String, l: String, offset5: String, rb: String, rd: String): Format9 = {
    val args: Array[Int] = Array(offset5,rb,rd).map(a => Integer.parseInt(a,2))
    b match {
      case "0" => l match {
        case "0" => STR(args(0),args(1),args(2))
        case "1" => LDR(args(0),args(1),args(2))
      }
      case "1" => l match {
        case "0" => STRB(args(0),args(1),args(2))
        case "1" => LDRB(args(0),args(1),args(2))
      }
    }
  }
}
