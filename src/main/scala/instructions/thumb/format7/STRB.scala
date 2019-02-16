package instructions.thumb.format7

case class STRB(ro: String, rb: String, rd: String) extends Format7 {
  override val mnemonic: String = "strb"
}
