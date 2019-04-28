package gui

import java.awt.{Color, Component}
import javax.swing.{JLabel, JTable}
import javax.swing.table.DefaultTableCellRenderer

class DataCellRenderer extends DefaultTableCellRenderer {

  override def getTableCellRendererComponent(table: JTable, value: scala.Any, isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int): Component = {

    val l = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column)
    val maybeValue: Option[Any] = Option(value)
    if (!isSelected) {
      maybeValue.getOrElse(None) match {
        case _ =>
      }
    }
    l
  }
}
