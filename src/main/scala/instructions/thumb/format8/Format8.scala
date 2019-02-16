package instructions.thumb.format8

import instructions.thumb.ThumbInstruction

abstract class Format8 extends ThumbInstruction {
  val ro, rb, rd: String
  override val operands: List[String] = regNameFormat(List(rd))
  override val addrOperands: List[String] = List(addrFormat(List(rb,ro)))
}

object Format8 {
  def apply(h: String, s: String, ro: String, rb: String, rd: String): Format8 = h match {
    case "0" => s match {
      case "0" => STRH(ro,rb,rd)
      case "1" => LDSB(ro,rb,rd)
    }
    case "1" => s match {
      case "0" => LDRH(ro,rb,rd)
      case "1" => LDSH(ro,rb,rd)
    }
  }
}
