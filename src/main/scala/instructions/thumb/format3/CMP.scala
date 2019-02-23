package instructions.thumb.format3

case class CMP(rd: Int, offset8: Int) extends Format3 {
  override val mnemonic: String = "cmp"
}
