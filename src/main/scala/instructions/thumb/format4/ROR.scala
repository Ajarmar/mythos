package instructions.thumb.format4

case class ROR(rs: String, rd: String) extends Format4 {
  override val mnemonic: String = "ror"
}