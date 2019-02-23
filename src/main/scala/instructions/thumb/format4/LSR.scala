package instructions.thumb.format4

case class LSR(rs: Int, rd: Int) extends Format4 {
  override val mnemonic: String = "lsr"
}
