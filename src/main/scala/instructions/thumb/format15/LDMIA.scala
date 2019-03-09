package instructions.thumb.format15

case class LDMIA(rb: Int, rlist: List[Int]) extends Format15 {
  override val mnemonic: String = "ldmia"
}
