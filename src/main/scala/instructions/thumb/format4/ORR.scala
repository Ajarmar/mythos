package instructions.thumb.format4

case class ORR(rs: Int, rd: Int) extends Format4 {
  override val mnemonic: String = "orr"
}
