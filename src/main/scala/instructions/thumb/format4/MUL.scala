package instructions.thumb.format4

case class MUL(rs: Int, rd: Int) extends Format4 {
  override val mnemonic: String = "mul"
}
