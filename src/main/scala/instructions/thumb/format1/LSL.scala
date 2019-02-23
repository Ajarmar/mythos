package instructions.thumb.format1

case class LSL(offset5: Int, rs: Int, rd: Int) extends Format1 {
  override val mnemonic: String = "lsl"
}
