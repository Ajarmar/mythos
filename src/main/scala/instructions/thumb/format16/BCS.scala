package instructions.thumb.format16

case class BCS(soffset8: Int) extends Format16 {
  override val mnemonic: String = "bcs"
}
