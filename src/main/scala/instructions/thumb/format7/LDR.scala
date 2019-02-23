package instructions.thumb.format7

case class LDR(ro: Int, rb: Int, rd: Int) extends Format7 {
  override val mnemonic: String = "ldr"
}
