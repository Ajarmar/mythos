package instructions.thumb.format3

case class MOV(rd: String, offset8: String) extends Format3 {
  override val mnemonic: String = "mov"
}
