package instructions.thumb.format12

case class ADD(sp: Int, rd: Int, word8: Int) extends Format12 {
  override val mnemonic: String = "add"
}
