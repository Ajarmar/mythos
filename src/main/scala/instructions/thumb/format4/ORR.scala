package instructions.thumb.format4

case class ORR(rs: String, rd: String) extends Format4 {
  override val mnemonic: String = "orr"
}
