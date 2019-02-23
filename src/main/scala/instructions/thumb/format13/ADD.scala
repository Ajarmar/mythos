package instructions.thumb.format13

case class ADD(s: Int, sword7: Int) extends Format13 {
  override val mnemonic: String = "add"
}
