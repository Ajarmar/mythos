package instructions.thumb.format16

case class BVC(soffset8: Int) extends Format16 {
  override val mnemonic: String = "bvc"
}
