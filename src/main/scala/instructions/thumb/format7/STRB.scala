package instructions.thumb.format7

case class STRB(ro: Int, rb: Int, rd: Int) extends Format7 {
  override val mnemonic: String = "strb"
}
