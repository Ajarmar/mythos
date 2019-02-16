package instructions.thumb.format10

case class STRH(offset5: String, rb: String, rd: String) extends Format10 {
  override val mnemonic: String = "strh"
}
