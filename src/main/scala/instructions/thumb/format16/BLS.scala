package instructions.thumb.format16

case class BLS(soffset8: Int) extends Format16 {
  override val mnemonic: String = "bls"
}
