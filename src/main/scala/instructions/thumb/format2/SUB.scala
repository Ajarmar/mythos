package instructions.thumb.format2

case class SUB(i: Int, rn_offset3: Int, rs: Int, rd: Int) extends Format2 {
  override val mnemonic: String = "sub"
}
