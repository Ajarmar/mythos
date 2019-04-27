package gui

import javax.swing.filechooser.FileNameExtensionFilter
import javax.swing.table.TableColumn
import javax.swing.{UIManager, UnsupportedLookAndFeelException}

import control.Controller

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits._
import scala.swing._
import scala.swing.event.ButtonClicked
import scala.util.{Failure, Success}

class MythosGUI(c: Controller) extends MainFrame {
  title = "Mythos"
  //preferredSize = new Dimension(640, 480)

  final val fc: FileChooser = new FileChooser()
  fc.fileFilter = new FileNameExtensionFilter("GBA ROMs", "gba")

  val button: Button = new Button("Select file")
  val fileField: TextField = new TextField(columns = 32) {
    editable = false
  }

  // Instruction table
  private val instrTableColumns = 4
  private val instrTable: Table = new Table(rows = 4000000, columns = instrTableColumns) {
    font = Font("Monospaced",Font.Bold,12)
    rowHeight = 20
    for (i <- 0 until instrTableColumns) {
      val col: TableColumn = peer.getColumnModel.getColumn(i)
      col.setPreferredWidth(MythosGUI.instrTableWidths(i))
      col.setHeaderValue(MythosGUI.instrTableColumnNames(i))
    }
  }
  private val instrTableScroll = new ScrollPane(instrTable) {
  }

  // Main frame contents

  contents = new GridBagPanel() {
    def constraints(x: Int, y: Int,
                    gridwidth: Int = 1, gridheight: Int = 1,
                    weightx: Double = 0.0, weighty: Double = 0.0,
                    fill: GridBagPanel.Fill.Value = GridBagPanel.Fill.None,
                    ipadx: Int = 0, ipady: Int = 0,
                    insets: Insets = new Insets(0,0,0,0),
                    anchor: GridBagPanel.Anchor.Value = GridBagPanel.Anchor.Center): Constraints = {
      val c = new Constraints
      c.gridx = x
      c.gridy = y
      c.gridwidth = gridwidth
      c.gridheight = gridheight
      c.weightx = weightx
      c.weighty = weighty
      c.fill = fill
      c.ipadx = ipadx
      c.ipady = ipady
      c.insets = insets
      c.anchor = anchor
      c
    }

    add(button,constraints(0,0,anchor = GridBagPanel.Anchor.FirstLineStart))
    add(fileField,constraints(1,0,anchor = GridBagPanel.Anchor.FirstLineStart))
    add(instrTableScroll,constraints(1,1,anchor = GridBagPanel.Anchor.FirstLineStart))
  }

  listenTo(button)

  reactions += {

    case ButtonClicked(`button`) => {
      val ret: FileChooser.Result.Value = fc.showOpenDialog(MythosGUI.this)
      ret match {
        case FileChooser.Result.Approve => {
          val filepath = fc.selectedFile.getAbsolutePath
          fileField.text = filepath
          val instrList: Future[List[(String,String)]] = Future {
            c.initSimulator(filepath)
            c.getAllROMInstructions(filepath)
          }
          instrList onComplete {
            case Success(instrs) => Swing.onEDT {
              instrs.foreach(i => {
                instrTable.update(instrs.indexOf(i),MythosGUI.ADDR_COLUMN,i._1)
                instrTable.update(instrs.indexOf(i),MythosGUI.INSTR_COLUMN,i._2)
              })
            }
            case Failure(e) => e.printStackTrace()
          }
        }

        case _ =>
      }
    }

  }
}

object MythosGUI {
  def main(args: Array[String]): Unit = {

    val c: Controller = new Controller()

    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName)
    } catch {
      case e: UnsupportedLookAndFeelException => e.printStackTrace()

      case e: ClassNotFoundException => e.printStackTrace()

      case e: InstantiationException => e.printStackTrace()

      case e: IllegalAccessException => e.printStackTrace()
    }

    Swing.onEDT {
      val gui = new MythosGUI(c)
      gui.visible = true
    }
  }

  val CURSOR_COLUMN = 0
  val ADDR_COLUMN = 1
  val HEX_COLUMN = 2
  val INSTR_COLUMN = 3

  val CURSOR_COLUMN_WIDTH = 20
  val ADDR_COLUMN_WIDTH = 60
  val HEX_COLUMN_WIDTH = 60
  val INSTR_COLUMN_WIDTH = 200

  val instrTableWidths: Map[Int, Int] = Map(
    CURSOR_COLUMN -> CURSOR_COLUMN_WIDTH,
    ADDR_COLUMN -> ADDR_COLUMN_WIDTH,
    HEX_COLUMN -> HEX_COLUMN_WIDTH,
    INSTR_COLUMN -> INSTR_COLUMN_WIDTH)

  val CURSOR_COLUMN_NAME = ""
  val ADDR_COLUMN_NAME = "Address"
  val HEX_COLUMN_NAME = "Hex"
  val INSTR_COLUMN_NAME = "Instruction"

  val instrTableColumnNames: Map[Int,String] = Map(
    CURSOR_COLUMN -> CURSOR_COLUMN_NAME,
    ADDR_COLUMN -> ADDR_COLUMN_NAME,
    HEX_COLUMN -> HEX_COLUMN_NAME,
    INSTR_COLUMN -> INSTR_COLUMN_NAME)
}