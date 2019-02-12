package instructions.thumb.format4

case class SBC(rs: String, rd: String) extends Format4 {
  override val mnemonic: String = "sbc"
}
