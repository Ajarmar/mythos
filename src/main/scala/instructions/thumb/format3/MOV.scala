package instructions.thumb.format3

case class MOV(rd: Int, offset8: Int) extends Format3 {
  override val mnemonic: String = "mov"
}
