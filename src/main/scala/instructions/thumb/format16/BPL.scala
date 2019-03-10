package instructions.thumb.format16

case class BPL(soffset8: Int) extends Format16 {
  override val mnemonic: String = "bpl"
}
