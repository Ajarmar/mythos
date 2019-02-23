package instructions.thumb.format1

case class LSR(offset5: Int, rs: Int, rd: Int) extends Format1 {
  override val mnemonic: String = "lsr"
}
