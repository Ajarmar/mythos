package instructions.thumb.format16

case class BGT(soffset8: Int) extends Format16 {
  override val mnemonic: String = "bgt"
}
