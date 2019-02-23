package instructions.thumb.format6

case class LDR(rd: Int, word8: Int) extends Format6 {
  override val mnemonic: String = "ldr"
  override val specialFormatting: Option[(Int => String) => String] =
    Some((pcFunc: Int => String) => disassembled().replaceAll("\\[.*\\]",pcFunc(word8 << 2)))
}
