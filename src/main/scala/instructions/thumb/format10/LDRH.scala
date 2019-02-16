package instructions.thumb.format10

case class LDRH(offset5: String, rb: String, rd: String) extends Format10 {
  override val mnemonic: String = "ldrh"
}
