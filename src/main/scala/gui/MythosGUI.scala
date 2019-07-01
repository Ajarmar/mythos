package gui

import java.awt.event.{KeyEvent, MouseEvent}
import javax.swing.filechooser.FileNameExtensionFilter
import javax.swing.table.{TableCellRenderer, TableColumn}
import javax.swing._

import control.Controller

import scala.collection.mutable
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits._
import scala.swing._
import scala.swing.event.{ButtonClicked, Key, MouseClicked}
import scala.util.{Failure, Success}

class MythosGUI(c: Controller) extends MainFrame {
  title = "Mythos"
  //preferredSize = new Dimension(640, 480)

  final val fc: FileChooser = new FileChooser()
  fc.fileFilter = new FileNameExtensionFilter("GBA ROMs", "gba")

  //val button: Button = new Button("Select file")
  val fileField: TextField = new TextField(columns = 32) {
    editable = false
  }

  private val loadRom = new MenuItem("Open ROM") {
    mnemonic = Key.O
    peer.setAccelerator(KeyStroke.getKeyStroke("control O"))
  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += loadRom
    }
  }

  // Instruction table
  private val instrTableColumns = 4
  private val instrTable: Table = new Table(rows = 40000, columns = instrTableColumns) {
    font = Font("Monospaced",Font.Bold,12)
    rowHeight = 20
    for (i <- 0 until instrTableColumns) {
      val col: TableColumn = peer.getColumnModel.getColumn(i)
      col.setPreferredWidth(MythosGUI.instrTableWidths(i))
      col.setHeaderValue(MythosGUI.instrTableColumnNames(i))
    }
    listenTo(mouse.clicks)
    reactions += {
      case e @ MouseClicked(component,point,_,_,_) =>
        if (e.peer.getButton == MouseEvent.BUTTON3) {
          val menu = new PopupMenu {
            contents += new MenuItem("Go to address...") {
              mnemonic = Key.G
              peer.setAccelerator(KeyStroke.getKeyStroke("control G"))
            }
            contents += new MenuItem("Mark selected rows as non-instructions") {
              reactions += {
                case ButtonClicked(_) =>
                  println("Selected rows: ")
                  instrTable.peer.getSelectedRows.foreach(r => println(r))
              }
            }
          }
          menu.show(component,point.getX.toInt,point.getY.toInt)
        }
    }
  }
  private val instrTableScroll = new ScrollPane(instrTable) {
  }

  private val dataTableColumns = 16
  private val dataTable: Table = new Table(Array.fill(24768,16)("00").map(_.toArray[Any]), (0 to 15).map(_.toHexString.toUpperCase)) {
    font = Font("Monospaced",Font.Bold,12)
    rowHeight = 16
    showGrid = false
    peer.setCellSelectionEnabled(true)
    peer.setDefaultRenderer(classOf[Object],new DataCellRenderer)
    peer.setDefaultEditor(classOf[Object],new DataCellEditor)
    for (i <- 0 until dataTableColumns) {
      val col: TableColumn = peer.getColumnModel.getColumn(i)
      col.setPreferredWidth(16)
    }
  }

  private val dataTableScroll = new ScrollPane(dataTable) {
    rowHeaderView = new ListView(
      (0x02000000 to 0x0203FFFF by 16).map(i => "0x0".+(i.toHexString.toUpperCase()))
        ++ (0x03000000 to 0x03007FFF by 16).map(i => "0x0".+(i.toHexString.toUpperCase()))
        ++ (0x04000000 to 0x040003FF by 16).map(i => "0x0".+(i.toHexString.toUpperCase()))
        ++ (0x05000000 to 0x050003FF by 16).map(i => "0x0".+(i.toHexString.toUpperCase()))
        ++ (0x06000000 to 0x06017FFF by 16).map(i => "0x0".+(i.toHexString.toUpperCase()))
        ++ (0x07000000 to 0x070003FF by 16).map(i => "0x0".+(i.toHexString.toUpperCase()))) {
      //font = Font("Monospaced",Font.Plain,12)
    }
  }

  private val tablesPane = new SplitPane(Orientation.Horizontal,instrTableScroll,dataTableScroll) {
    resizeWeight = 0.6
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

    add(fileField,constraints(1,0,weightx = 0.5,fill=GridBagPanel.Fill.Horizontal,anchor = GridBagPanel.Anchor.FirstLineStart))
    add(tablesPane,constraints(0,1,gridwidth = 2,weightx = 0.9,weighty = 0.7,fill=GridBagPanel.Fill.Both,anchor = GridBagPanel.Anchor.FirstLineStart))
  }

  listenTo(loadRom)

  reactions += {

    case ButtonClicked(`loadRom`) => {
      val ret: FileChooser.Result.Value = fc.showOpenDialog(MythosGUI.this)
      ret match {
        case FileChooser.Result.Approve => {
          val filepath = fc.selectedFile.getAbsolutePath
          fileField.text = filepath
          val instrList: Future[List[(String,String,String)]] = Future {
            c.initSimulator(filepath)
            c.getROMInstructions(filepath,0,40000) //TODO not hard coded
          }
          instrList onComplete {
            case Success(instrs) => instrs.foreach(i => {
              Swing.onEDT {
                instrTable.update(instrs.indexOf(i),MythosGUI.ADDR_COLUMN,i._1)
                instrTable.update(instrs.indexOf(i),MythosGUI.HEX_COLUMN,i._2)
                instrTable.update(instrs.indexOf(i),MythosGUI.INSTR_COLUMN,i._3)
              }
              Thread.sleep(1)
            })

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
  val HEX_COLUMN_WIDTH = 30
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
