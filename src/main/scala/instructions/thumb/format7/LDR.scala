package instructions.thumb.format7

case class LDR(ro: String, rb: String, rd: String) extends Format7 {
  override val mnemonic: String = "ldr"
}
