package instructions.thumb.format4

case class TST(rs: String, rd: String) extends Format4 {
  override val mnemonic: String = "tst"
}