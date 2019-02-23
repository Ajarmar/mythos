package instructions.thumb.format8

case class STRH(ro: Int, rb: Int, rd: Int) extends Format8 {
  override val mnemonic: String = "strh"
}
