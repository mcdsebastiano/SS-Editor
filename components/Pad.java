package jim.components;

import javax.swing.undo.UndoManager;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Font;

public class Pad extends JTextArea {
  
  private UndoManager undoManager;
  
  public Pad(int rows, int cols) {
    super(rows, cols);
    this.undoManager = new UndoManager();
    this.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
    this.setMargin(new Insets(3, 3, 3, 3));
    this.getDocument().addUndoableEditListener(undoManager);
    this.setTabSize(2);
  }
  
  public UndoManager undoManager() {
    return this.undoManager;
  }
  
}