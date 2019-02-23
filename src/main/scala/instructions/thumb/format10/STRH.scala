package instructions.thumb.format10

case class STRH(offset5: Int, rb: Int, rd: Int) extends Format10 {
  override val mnemonic: String = "strh"
}
