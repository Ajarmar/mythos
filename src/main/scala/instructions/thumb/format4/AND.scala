package instructions.thumb.format4

case class AND(rs: Int, rd: Int) extends Format4 {
  override val mnemonic: String = "and"
}
