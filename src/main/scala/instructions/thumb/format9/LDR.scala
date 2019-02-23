package instructions.thumb.format9

case class LDR(offset5: Int, rb: Int, rd: Int) extends Format9 {
  override val mnemonic: String = "ldr"
}
