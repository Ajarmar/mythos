package instructions.thumb.format7

import instructions.thumb.ThumbInstruction

abstract class Format7 extends ThumbInstruction {
  val ro, rb, rd: String
  override val operands: List[String] = regNameFormat(List(rd))
  override val addrOperands: List[String] = List(addrFormat(List(rb,ro)))
}

object Format7 {
  def apply(l: String, b: String, ro: String, rb: String, rd: String): Format7 = l match {
    case "0" => b match {
      case "0" => STR(ro,rb,rd)
      case "1" => STRB(ro,rb,rd)
    }
    case "1" => b match {
      case "0" => LDR(ro,rb,rd)
      case "1" => LDRB(ro,rb,rd)
    }
  }
}
