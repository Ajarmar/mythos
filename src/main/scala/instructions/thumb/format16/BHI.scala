package instructions.thumb.format16

case class BHI(soffset8: Int) extends Format16 {
  override val mnemonic: String = "bhi"
}
