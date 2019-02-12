package instructions.thumb.format4

case class AND(rs: String, rd: String) extends Format4 {
  override val mnemonic: String = "and"
}
