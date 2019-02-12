package instructions.thumb.format4

case class EOR(rs: String, rd: String) extends Format4 {
  override val mnemonic: String = "eor"
}
