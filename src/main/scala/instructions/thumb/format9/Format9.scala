package instructions.thumb.format9

import instructions.thumb.ThumbInstruction

abstract class Format9 extends ThumbInstruction {
  val offset5, rb, rd: String
  override val operands: List[String] = regNameFormat(List(rd))
  override val addrOperands: List[String] = List(addrFormat(regNameFormat(List(rb)) ++ List(immValueFormat(offset5))))
}

object Format9 {
  def apply(b: String, l: String, offset5: String, rb: String, rd: String): Format9 = b match {
    case "0" => l match {
      case "0" => STR(offset5,rb,rd)
      case "1" => LDR(offset5,rb,rd)
    }
    case "1" => l match {
      case "0" => STRB(offset5,rb,rd)
      case "1" => LDRB(offset5,rb,rd)
    }
  }
}
