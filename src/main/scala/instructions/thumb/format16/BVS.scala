package instructions.thumb.format16

case class BVS(soffset8: Int) extends Format16 {
  override val mnemonic: String = "bvs"
}
