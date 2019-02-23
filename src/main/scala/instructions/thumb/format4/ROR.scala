package instructions.thumb.format4

case class ROR(rs: Int, rd: Int) extends Format4 {
  override val mnemonic: String = "ror"
}
