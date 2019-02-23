package instructions.thumb.format5

case class CMP(h1: Int, h2: Int, rs_hs: Int, rd_hd: Int) extends Format5 {
  override val mnemonic: String = "cmp"
}
