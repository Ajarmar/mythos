package instructions.thumb.format5

case class BX(h1: String, h2: String, rs_hs: String, rd_hd: String) extends Format5 {
  override val mnemonic: String = "bx"
  override val operands: List[String] = regNameFormat(List(
    if (h2.matches("1")) "1".concat(rs_hs) else rs_hs))
}
