import instructions.thumb.ThumbInstruction
import instructions.thumb.format1.LSL
import org.scalatest.FunSuite

class ThumbInstructionTest extends FunSuite {

  test("Format1.apply") {
    val lslString = "0000000111000001"
    val ti = ThumbInstruction(lslString)
    val lsl = LSL("00111","000","001")
    val otherLsl = LSL("00111","001","110")
    assert(ti.equals(lsl))
    assert(!ti.equals(otherLsl))
  }
}
