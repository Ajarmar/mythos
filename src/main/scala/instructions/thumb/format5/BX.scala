package instructions.thumb.format5

case class BX(h1: Int, h2: Int, rs_hs: Int, rd_hd: Int) extends Format5 {
  override val mnemonic: String = "bx"
  override val operands: List[String] = regNameFormat(List(
    if (h2 == 1) rs_hs+8 else rs_hs))
}
