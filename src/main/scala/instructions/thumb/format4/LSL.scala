package instructions.thumb.format4

case class LSL(rs: Int, rd: Int) extends Format4 {
  override val mnemonic: String = "lsl"
}
