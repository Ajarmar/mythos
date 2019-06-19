package instructions.thumb

import instructions.Instruction
import instructions.thumb.format1.Format1
import instructions.thumb.format10.Format10
import instructions.thumb.format11.Format11
import instructions.thumb.format12.Format12
import instructions.thumb.format13.Format13
import instructions.thumb.format14.Format14
import instructions.thumb.format15.Format15
import instructions.thumb.format16.Format16
import instructions.thumb.format17.Format17
import instructions.thumb.format18.Format18
import instructions.thumb.format19.Format19
import instructions.thumb.format2.Format2
import instructions.thumb.format3.Format3
import instructions.thumb.format4.Format4
import instructions.thumb.format5.Format5
import instructions.thumb.format6.Format6
import instructions.thumb.format7.Format7
import instructions.thumb.format8.Format8
import instructions.thumb.format9.Format9

abstract class ThumbInstruction extends Instruction {

}

object ThumbInstruction {
  /*def apply(instr: Short): ThumbInstruction = {
    instr >> 13 & 0x7 match {
      case 0 =>
        instr >> 11 & 0x3 match {
          case 3 =>
            Format2(
              instr >> 10 & 0x1,  // I
              instr >> 9 & 0x1,   // Op
              instr >> 6 & 0x7,   // Rn/Offset3
              instr >> 3 & 0x7,   // Rs
              instr & 0x7         // Rd
            )
          case _ =>
            Format1(
              instr >> 11 & 0x3,  // Op
              instr >> 6 & 0x1F,  // Offset5
              instr >> 3 & 0x7,   // Rs
              instr & 0x7         // Rd
            )
        }
      case 1 =>
        Format3(
          instr >> 11 & 0x3,  // Op
          instr >> 8 & 0x7,   // Rd
          instr & 0xFF        // Offset8
        )
      case 2 =>
        instr >> 10 & 0x7 match {
          case 0 =>
            Format4(
              instr >> 6 & 0xF, // Op
              instr >> 3 & 0x7, // Rs
              instr & 0x7       // Rd
            )
          case 1 =>
            Format5(
              instr >> 8 & 0x3, // Op
              instr >> 7 & 0x1, // H1
              instr >> 6 & 0x1, // H2
              instr >> 3 & 0x7, // Rs/Hs
              instr & 0x7       // Rd/Hd
            )
          case 2 =>
            Format6(
              instr >> 8 & 0x7, // Rd
              instr & 0xFF      // Word8
            )
          case 3 =>
            Format6(
              instr >> 8 & 0x7, // Rd
              instr & 0xFF      // Word8
            )
        }
        instr >> 12 & 0x1 match {
          case 1 =>
            instr >> 9 & 0x1 match {
              case 0 =>
                Format7(
                  instr >> 11 & 0x1,  // L
                  instr >> 10 & 0x1,  // B
                  instr >> 6 & 0x7,   // Ro
                  instr >> 3 & 0x7,   // Rb
                  instr & 0x7         // Rd
                )
              case 1 =>
                Format8(
                  instr >> 11 & 0x1,  // H
                  instr >> 10 & 0x1,  // S
                  instr >> 6 & 0x7,   // Ro
                  instr >> 3 & 0x7,   // Rb
                  instr & 0x7         // Rd)
            }
        }
      case 3 =>
        Format9(
          instr >> 12 & 0x1,  // B
          instr >> 11 & 0x1,  // L
          instr >> 6 & 0x1F,  // Offset5
          instr >> 3 & 0x7,   // Rb
          instr & 0x7         // Rd
        )
      case 4 =>
        instr >> 12 & 0x1 match {
          case 0 =>
            Format10(
              instr >> 11 & 0x1,  // L
              instr >> 6 & 0x1F,  // Offset5
              instr >> 3 & 0x7,   // Rb
              instr & 0x7         // Rd
            )
          case 1 =>
            Format11(
              instr >> 11 & 0x1,  // L
              instr >> 8 & 0x7,   // Rd
              instr & 0xFF        // Word8
            )
        }
      case 5 =>
        instr >> 12 & 0x1 match {
          case 0 =>
            Format12(
              instr >> 11 & 0x1,  // SP
              instr >> 8 & 0x7,   // Rd
              instr & 0xFF        // Word8
            )
          case 1 =>
            instr >> 9 & 0x3 match {
              case 0 =>
                instr >> 8 & 0xF match {
                  case 0 =>
                    Format13(
                      instr >> 7 & 0x1, // S
                      instr & 0x7F      // SWord7
                    )
                }
              case 2 =>
                Format14(
                  instr >> 11 & 0x1,  // L
                  instr >> 8 & 0x1,   // R
                  instr & 0xFF        // Rlist
                )
            }
        }
      case 6 =>
        instr >> 12 & 0x1 match {
          case 0 =>
            Format15(
              instr >> 1 & 0x1, // L
              instr >> 8 & 0x7, // Rb
              instr & 0xFF      // Rlist
            )
          case 1 =>
            instr >> 8 & 0xF match {
              case 0xF =>
                Format17(
                  instr & 0xFF  // Value8
                )
              case _ =>
                Format16(
                  instr >> 8 & 0xF, // Cond
                  instr & 0xFF      // Soffset8
                )
            }
        }
      case 7 =>
        instr >> 11 & 0x3 match {
          case 0 =>
            Format18(
              instr & 0x7FF // Offset11
            )
          case 2 =>
            Format19(
              0,            // H
              instr & 0x7FF // Offset
            )
          case 3 =>
            Format19(
              1,            // H
              instr & 0x7FF // Offset
            )
        }
    }
    ThumbError(ThumbError.NotAnInstruction)
  }*/
  def apply(binary: String): ThumbInstruction = binary match {
    case THUMB.Format2(i, op, rn_offset3, rs, rd) => Format2(i, op, rn_offset3, rs, rd)
    case THUMB.Format1(op, offset5, rs, rd) => Format1(op, offset5, rs, rd)
    case THUMB.Format3(op, rd, offset8) => Format3(op, rd, offset8)
    case THUMB.Format4(op, rs, rd) => Format4(op, rs, rd)
    case THUMB.Format5(op, h1, h2, rs_hs, rd_hd) => Format5(op, h1, h2, rs_hs, rd_hd)
    case THUMB.Format6(rd, word8) => Format6(rd, word8)
    case THUMB.Format7(l, b, ro, rb, rd) => Format7(l, b, ro, rb, rd)
    case THUMB.Format8(h, s, ro, rb, rd) => Format8(h, s, ro, rb, rd)
    case THUMB.Format9(b, l, offset5, rb, rd) => Format9(b, l, offset5, rb, rd)
    case THUMB.Format10(l, offset5, rb, rd) => Format10(l, offset5, rb, rd)
    case THUMB.Format11(l, rd, word8) => Format11(l, rd, word8)
    case THUMB.Format12(sp,rd,word8) => Format12(sp,rd,word8)
    case THUMB.Format13(s,sword7) => Format13(s,sword7)
    case THUMB.Format14(l,r,rlist) => Format14(l,r,rlist)
    case THUMB.Format15(l,rb,rlist) => Format15(l,rb,rlist)
    case THUMB.Format17(value8) => Format17(value8)
    case THUMB.Format16(cond,soffset8) => Format16(cond,soffset8)
    case THUMB.Format18(offset11) => Format18(offset11)
    case THUMB.Format19(h,offset) => Format19(h,offset)
    case _ => new ThumbError(ThumbError.NotAnInstruction)
  }
}