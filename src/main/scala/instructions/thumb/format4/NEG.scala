package instructions.thumb.format4

case class NEG(rs: String, rd: String) extends Format4 {
  override val mnemonic: String = "neg"
}
