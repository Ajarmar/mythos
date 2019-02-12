package instructions.thumb.format5

case class MOV(h1: String, h2: String, rs_hs: String, rd_hd: String) extends Format5 {
  override val mnemonic: String = "mov"
}
