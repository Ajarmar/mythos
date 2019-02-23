package instructions.thumb.format4

case class TST(rs: Int, rd: Int) extends Format4 {
  override val mnemonic: String = "tst"
}
