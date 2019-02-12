package instructions.thumb.format5

import instructions.thumb.ThumbInstruction

abstract class Format5 extends ThumbInstruction {
  val h1, h2, rs_hs, rd_hd: String
  override val operands: List[String] = regNameFormat(List(
    if (h1.matches("1")) "1".concat(rd_hd) else rd_hd,
    if (h2.matches("1")) "1".concat(rs_hs) else rs_hs))
}

object Format5 {
  def apply(op: String, h1: String, h2: String, rs_hs: String, rd_hd: String): Format5 = op match {
    case "00" => ADD(h1,h2,rs_hs,rd_hd)
    case "01" => CMP(h1,h2,rs_hs,rd_hd)
    case "10" => MOV(h1,h2,rs_hs,rd_hd)
    case "11" => BX(h1,h2,rs_hs,rd_hd)
  }
}
