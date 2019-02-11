package instructions.thumb.format3

case class SUB(rd: String, offset8: String) extends Format3 {
  override val mnemonic: String = "sub"
}
