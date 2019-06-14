package instructions.arm

import instructions.Instruction

abstract class ArmInstruction extends Instruction {

}

object ArmInstruction {
  def apply(instr: Int): ArmInstruction = {
    instr >> 25 & 0x7 match {
      case 0 => {

        (instr >> 7 & 0x1,instr >> 4 & 0x1) match {
          case (1,1) => { // All format 0 except DataProc and BX

            instr >> 5 & 0x3 match {
              case 0 => { // 1001

                instr >> 23 & 0x3 match {
                  case 0 =>
                    Multiply(
                      instr >> 28 & 0xF,  // Cond
                      instr >> 21 & 0x1,  // A
                      instr >> 20 & 0x1,  // S
                      instr >> 16 & 0xF,  // Rd
                      instr >> 12 & 0xF,  // Rn
                      instr >> 8 & 0xF,   // Rs
                      instr & 0xF         // Rm
                    )
                  case 1 =>
                    MultiplyLong(
                      instr >> 28 & 0xF,  // Cond
                      instr >> 22 & 0x1,  // U
                      instr >> 21 & 0x1,  // A
                      instr >> 20 & 0x1,  // S
                      instr >> 16 & 0xF,  // RHi
                      instr >> 12 & 0xF,  // RLo
                      instr >> 8 & 0xF,   // Rn
                      instr & 0xF         // Rm
                    )
                  case 2 =>
                    SingleDataSwap(
                      instr >> 28 & 0xF,  // Cond
                      instr >> 22 & 0x1,  // B
                      instr >> 16 & 0xF,  // Rn
                      instr >> 12 & 0xF,  // Rd
                      instr & 0xF         // Rm
                    )
                }
              }
              case _ => {
                instr >> 22 & 0x1 match {
                  case 0 =>
                    HalfwordDataTransferReg(
                      instr >> 28 & 0xF,  // Cond
                      instr >> 24 & 0x1,  // P
                      instr >> 23 & 0x1,  // U
                      instr >> 21 & 0x1,  // W
                      instr >> 20 & 0x1,  // L
                      instr >> 16 & 0xF,  // Rn
                      instr >> 12 & 0xF,  // Rd
                      instr >> 6 & 0x1,   // S
                      instr >> 5 & 0x1,   // H
                      instr & 0xF         // Rm
                    )
                  case 1 =>
                    HalfwordDataTransferImm(
                      instr >> 28 & 0xF,  // Cond
                      instr >> 24 & 0x1,  // P
                      instr >> 23 & 0x1,  // U
                      instr >> 21 & 0x1,  // W
                      instr >> 20 & 0x1,  // L
                      instr >> 16 & 0xF,  // Rn
                      instr >> 12 & 0xF,  // Rd
                      instr >> 8 & 0xF,   // Offset Hi
                      instr >> 6 & 0x1,   // S
                      instr >> 5 & 0x1,   // H
                      instr & 0xF         // Offset Lo
                    )
                }
              }
            }
          }
        }
        instr >> 4 & 0xFFFFFF match {
          case 0x12FFF1 =>
            BranchExchange(
              instr >> 28 & 0xF,  // Cond
              instr & 0xF         // Rn
            )
        }
        DataProcessing(
          instr >> 28 & 0xF,  // Cond
          0,                  // I
          instr >> 21 & 0xF,  // Opcode
          instr >> 20 & 0x1,  // S
          instr >> 16 & 0xF,  // Rn
          instr >> 12 & 0xF,  // Rd
          instr & 0xFFF       // Operand 2
        )
      }

      case 1 => {
        DataProcessing(
          instr >> 28 & 0xF,  // Cond
          1,                  // I
          instr >> 21 & 0xF,  // Opcode
          instr >> 20 & 0x1,  // S
          instr >> 16 & 0xF,  // Rn
          instr >> 12 & 0xF,  // Rd
          instr & 0xFFF       // Operand 2
        )
      }

      case 2 => {
        SingleDataTransfer(
          instr >> 28 & 0xF,  // Cond
          0,                  // I
          instr >> 24 & 0x1,  // P
          instr >> 23 & 0x1,  // U
          instr >> 22 & 0x1,  // B
          instr >> 21 & 0x1,  // W
          instr >> 20 & 0x1,  // L
          instr >> 16 & 0xF,  // Rn
          instr >> 12 & 0xF,  // Rd
          instr & 0xFFF       // Offset
        )
      }

      case 3 => {
        instr >> 4 & 0x1 match {
          case 0 =>
            SingleDataTransfer(
              instr >> 28 & 0xF,  // Cond
              0,                  // I
              instr >> 24 & 0x1,  // P
              instr >> 23 & 0x1,  // U
              instr >> 22 & 0x1,  // B
              instr >> 21 & 0x1,  // W
              instr >> 20 & 0x1,  // L
              instr >> 16 & 0xF,  // Rn
              instr >> 12 & 0xF,  // Rd
              instr & 0xFFF       // Offset
            )
          case 1 =>
            Undefined(instr >> 28 & 0xF)  // Cond
        }
      }

      case 4 => {
        BlockDataTransfer(
          instr >> 28 & 0xF,  // Cond
          instr >> 24 & 0x1,  // P
          instr >> 23 & 0x1,  // U
          instr >> 22 & 0x1,  // S
          instr >> 21 & 0x1,  // W
          instr >> 20 & 0x1,  // L
          instr >> 16 & 0xF,  // Rn
          instr & 0xFFFF      // Register List
        )
      }

      case 5 => {
        Branch(
          instr >> 28 & 0xF,  // Cond
          instr >> 24 & 0x1,  // L
          instr & 0xFFFFFF    // Offset
        )
      }

      case 6 => {
        CoDataTransfer(
          instr >> 28 & 0xF,  // Cond
          instr >> 24 & 0x1,  // P
          instr >> 23 & 0x1,  // U
          instr >> 22 & 0x1,  // N
          instr >> 21 & 0x1,  // W
          instr >> 20 & 0x1,  // L
          instr >> 16 & 0xF,  // Rn
          instr >> 12 & 0xF,  // CRd
          instr >> 8 & 0xF,   // CP#
          instr & 0xFF        // Offset
        )
      }

      case 7 => {
        instr >> 24 & 0x1 match {

          case 0 => {
            instr >> 4 & 0x1 match {

              case 0 =>
                CoDataOperation(
                  instr >> 28 & 0xF,  // Cond
                  instr >> 20 & 0xF,  // CP Opc
                  instr >> 16 & 0xF,  // CRn
                  instr >> 12 & 0xF,  // CRd
                  instr >> 8 & 0xF,   // CP#
                  instr >> 5 & 0x7,   // CP
                  instr & 0xF         // CRm
                )

              case 1 =>
                CoRegTransfer(
                  instr >> 28 & 0xF,  // Cond
                  instr >> 21 & 0x7,  // CP Opc
                  instr >> 20 & 0x1,  // L
                  instr >> 16 & 0xF,  // CRn
                  instr >> 12 & 0xF,  // Rd
                  instr >> 8 & 0xF,   // CP#
                  instr >> 5 & 0x7,   // CP
                  instr & 0xF         // CRm
                )
            }
          }

          case 1 =>
            SoftwareInterrupt(instr >> 28 & 0xF)
        }
      }

    }
  }
}
