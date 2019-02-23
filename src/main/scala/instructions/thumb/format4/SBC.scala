package instructions.thumb.format4

case class SBC(rs: Int, rd: Int) extends Format4 {
  override val mnemonic: String = "sbc"
}
