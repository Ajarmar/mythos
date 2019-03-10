package instructions.thumb

import scala.util.matching.Regex

object THUMB {
  val Format1: Regex = "^000(00|01|10)([01]{5})([01]{3})([01]{3})$".r
  val Format2: Regex = "^00011([01])([01])([01]{3})([01]{3})([01]{3})$".r
  val Format3: Regex = "^001([01]{2})([01]{3})([01]{8})$".r
  val Format4: Regex = "^010000([01]{4})([01]{3})([01]{3})$".r
  val Format5: Regex = "^010001([01]{2})([01])([01])([01]{3})([01]{3})$".r
  val Format6: Regex = "^01001([01]{3})([01]{8})$".r
  val Format7: Regex = "^0101([01])([01])0([01]{3})([01]{3})([01]{3})$".r
  val Format8: Regex = "^0101([01])([01])1([01]{3})([01]{3})([01]{3})$".r
  val Format9: Regex = "^011([01])([01])([01]{5})([01]{3})([01]{3})$".r
  val Format10: Regex = "^1000([01])([01]{5})([01]{3})([01]{3})$".r
  val Format11: Regex = "^1001([01])([01]{3})([01]{8})$".r
  val Format12: Regex = "^1010([01])([01]{3})([01]{8})$".r
  val Format13: Regex = "^10110000([01])([01]{7})$".r
  val Format14: Regex = "^1011([01])10([01])([01]{8})$".r
  val Format15: Regex = "^1100([01])([01]{3})([01]{8})$".r
  val Format16: Regex = "^1101([01]{4})([01]{8})$".r // Check after 17
  val Format17: Regex = "^11011111([01]{8})$".r
  val Format18: Regex = "^11100([01]{11})$".r
  val Format19: Regex = "^1111([01])([01]{11})$".r
}
