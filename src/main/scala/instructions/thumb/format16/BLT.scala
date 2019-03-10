package instructions.thumb.format16

case class BLT(soffset8: Int) extends Format16 {
  override val mnemonic: String = "blt"
}
