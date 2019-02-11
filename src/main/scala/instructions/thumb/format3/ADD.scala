package instructions.thumb.format3

case class ADD(rd: String, offset8: String) extends Format3 {
  override val mnemonic: String = "add"
}
