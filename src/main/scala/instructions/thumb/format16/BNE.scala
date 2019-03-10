package instructions.thumb.format16

case class BNE(soffset8: Int) extends Format16 {
  override val mnemonic: String = "bne"
}
