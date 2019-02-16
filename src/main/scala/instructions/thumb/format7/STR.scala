package instructions.thumb.format7

case class STR(ro: String, rb: String, rd: String) extends Format7 {
  override val mnemonic: String = "str"
}
