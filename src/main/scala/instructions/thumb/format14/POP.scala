package instructions.thumb.format14

case class POP(r: Byte, rlist: List[Byte]) extends Format14 {

  //override val regListOperands: List[String] = if (r == 0) List(regListFormat(rlist)) else List(regListFormat(rlist :+ 15))
}
