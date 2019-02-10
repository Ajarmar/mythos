package instructions.arm

import scala.util.matching.Regex

object ARM {
  // Data Processing must be checked AFTER Multiply, MultiplyLong, SingleDataSwap,
  // BX, HalfwordDataTransferReg, HalfwordDataTransferImm
  val DataProcessing: Regex = "^([01]{4})00([01])([01]{4})([01])([01]{4})([01]{4})([01]{12})$".r
  val Multiply: Regex = "^([01]{4})000000([01])([01])([01]{4})([01]{4})([01]{4})1001([01]{4})$".r
  val MultiplyLong: Regex = "^([01]{4})00001([01])([01])([01])([01]{4})([01]{4})([01]{4})1001([01]{4})$".r
  val SingleDataSwap: Regex = "^([01]{4})00010([01])00([01]{4})([01]{4})00001001([01]{4})$".r
  val BX: Regex = "^([01]{4})000100101111111111110001([01]{4})$".r
  val HalfwordDataTransferReg: Regex = "^([01]{4})000([01])([01])0([01])([01])([01]{4})([01]{4})00001([01])([01])1([01]{4})$".r
  val HalfwordDataTransferImm: Regex = "^([01]{4})000([01])([01])1([01])([01])([01]{4})([01]{4})([01]{4})1([01])([01])1([01]{4})$".r
  val SingleDataTransfer: Regex = "^([01]{4})01([01])([01])([01])([01])([01])([01])([01]{4})([01]{4})([01]{12})$".r
  val BlockDataTransfer: Regex = "^([01]{4})100([01])([01])([01])([01])([01])([01]{4})([01]{16})$".r
  val Branch: Regex = "^([01]{4})101([01])([01]{24})$".r
  val CoDataTransfer: Regex = "^([01]{4})110([01])([01])([01])([01])([01])([01]{4})([01]{4})([01]{4})([01]{8})$".r
  val CoDataOperation: Regex = "^([01]{4})1110([01]{4})([01]{4})([01]{4})([01]{4})([01]{3})0([01]{4})$".r
  val CoRegTransfer: Regex = "^([01]{4})1110([01]{3})([01])([01]{4})([01]{4})([01]{4})([01]{3})1([01]{4})$".r
  val SWI: Regex = "^([01]{4})1111(?:[01]{24})$".r
}
