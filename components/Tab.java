package sstream.components;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.undo.UndoManager;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JMenu;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Font;

public class Tab extends JScrollPane {

  private TabButton icon;
  private final Font COURIER_NEW = new Font("Courier New", 0, 14);
  private final JPanel lineNumberMargin;
  private final JTextArea lineNumbers;
  private final UndoManager undoManager;
  private final JTextArea textArea;
  private final JPanel view;
  private boolean saveExists;
  private java.io.File file;

  public Tab() {
    this.icon = new TabButton(new ImageIcon("assets/images/close_icon_16x16.png"));
    this.saveExists = false;
    this.undoManager      = new UndoManager();
    this.lineNumberMargin = new JPanel(new BorderLayout());
    this.view             = new JPanel(new BorderLayout());
    this.textArea         = new JTextArea(50, 96);
    this.lineNumbers      = new JTextArea(53, 6);

    this.lineNumbers.setFont(COURIER_NEW);
    this.lineNumbers.setText("1\n2\n3");
    this.lineNumbers.setEnabled(false);
    this.lineNumbers.setBackground(Color.GRAY);

    this.lineNumberMargin.add(lineNumbers, "Center");
    this.lineNumberMargin.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

    this.textArea.setFont(COURIER_NEW);
    this.textArea.setMargin(new Insets(0, 3, 3, 3));
    this.textArea.getDocument().addUndoableEditListener(undoManager);
    this.textArea.setTabSize(2);

    this.view.add(this.lineNumberMargin, "West");
    this.view.add(this.textArea, "Center");
    this.view.setBorder(BorderFactory.createEmptyBorder());

    this.setViewportView(view);
  }

  public void  undo() {
    this.undoManager.undo();
  }
  
  public void redo() {
    this.undoManager.redo();
  }
  
  public String fileName() {
    return this.file.getName();
  }
  
  public java.io.File getFile() {
    return this.file;
  }
  
  public void setFile(java.io.File file) {
    this.file = file;
  }
  
  public String getText() {
    return this.textArea.getText();
  }
  
  public boolean saveExists() {
    return this.saveExists;
  }
  
  public void setSaveState(boolean state) {
    this.saveExists = state;
  }
  
  private class Tab

  

}


