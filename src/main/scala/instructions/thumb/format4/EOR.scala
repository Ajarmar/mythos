package instructions.thumb.format4

case class EOR(rs: Int, rd: Int) extends Format4 {
  override val mnemonic: String = "eor"
}
