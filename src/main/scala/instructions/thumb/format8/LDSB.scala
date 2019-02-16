package instructions.thumb.format8

case class LDSB(ro: String, rb: String, rd: String) extends Format8 {
  override val mnemonic: String = "ldsb"
}
