package instructions.thumb.format8

case class LDSB(ro: Int, rb: Int, rd: Int) extends Format8 {
  override val mnemonic: String = "ldsb"
}
