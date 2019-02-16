package instructions.thumb.format9

case class LDRB(offset5: String, rb: String, rd: String) extends Format9 {
  override val mnemonic: String = "ldrb"
}
