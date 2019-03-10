package instructions.thumb.format19

case class BL(h: Int, offset: Int) extends Format19 {
  override val mnemonic: String = "bl"
}
