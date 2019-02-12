package instructions.thumb.format4

case class LSL(rs: String, rd: String) extends Format4 {
  override val mnemonic: String = "lsl"
}
