package instructions.thumb.format14

case class PUSH(r: Int, rlist: List[Int]) extends Format14 {
  override val mnemonic: String = "push"
  override val regListOperands: List[String] = if (r == 0) List(regListFormat(rlist)) else List(regListFormat(rlist :+ 15))
}
