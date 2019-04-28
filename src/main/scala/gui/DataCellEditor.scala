package gui

import javax.swing.{DefaultCellEditor, JTextField}

class DataCellEditor extends DefaultCellEditor(new JTextField) {

  override def stopCellEditing(): Boolean = {
    val input = editorComponent.asInstanceOf[JTextField]
    val text = input.getText
    if (text.matches("\\d|[a-fA-F]")) input.setText("0" + text)
    input.getText.matches("(\\d|[a-fA-F]){2}") && super.stopCellEditing()
  }
}
