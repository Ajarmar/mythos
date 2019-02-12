package instructions.thumb.format4

case class MUL(rs: String, rd: String) extends Format4 {
  override val mnemonic: String = "mul"
}
