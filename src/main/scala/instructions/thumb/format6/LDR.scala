package instructions.thumb.format6

case class LDR(rd: String, word8: String) extends Format6 {
  override val mnemonic: String = "ldr"
  override val specialFormatting: Option[(Int => String) => String] =
    Some((pcFunc: Int => String) => disassembled().replaceAll("\\[.*\\]",pcFunc(Integer.parseInt(word8.concat("00"),2))))
}
