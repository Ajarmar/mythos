package instructions.thumb.format8

import instructions.thumb.ThumbInstruction

abstract class Format8 extends ThumbInstruction {
  val ro, rb, rd: Int
  override val operands: List[String] = regNameFormat(List(rd))
  override val addrOperands: List[String] = List(addrFormat(regNameFormat(List(rb,ro))))
}

object Format8 {
  def apply(h: String, s: String, ro: String, rb: String, rd: String): Format8 = {
    val args: Array[Int] = Array(ro,rb,rd).map(a => Integer.parseInt(a,2))
    h match {
      case "0" => s match {
        case "0" => STRH(args(0),args(1),args(2))
        case "1" => LDSB(args(0),args(1),args(2))
      }
      case "1" => s match {
        case "0" => LDRH(args(0),args(1),args(2))
        case "1" => LDSH(args(0),args(1),args(2))
      }
    }
  }
}
