package instructions.thumb.format4

case class LSR(rs: String, rd: String) extends Format4 {
  override val mnemonic: String = "lsr"
}
