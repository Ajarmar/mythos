package instructions.thumb.format1

case class ASR(offset5: Int, rs: Int, rd: Int) extends Format1 {
  override val mnemonic: String = "asr"
}
