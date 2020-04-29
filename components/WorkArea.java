package jim.components;

import javax.swing.undo.UndoManager;

public class WorkArea {
  private java.io.File file;
  private boolean saved;
  private Pad pad;
  private LineNumbers ln;

  public WorkArea() {
    this.pad = new Pad(50, 96);
    this.ln = new LineNumbers(50, 6);
  }
  
  public Pad pad() {
    return this.pad;
  }
  
  public void focus() {
    this.pad.requestFocusInWindow();
  }
  
  public void redo() {
    this.pad.undoManager().redo();
  }
  
  public void undo() {
    this.pad.undoManager().undo();
  }
  
  public LineNumbers linenumbers() {
    return this.ln;
  }
    
  public java.io.File file() {
    return this.file;
  }
  
  public void file(java.io.File file) {
    this.file = file;
  }
  
  public boolean saved() {
    return this.saved;
  }
  
  public void save() {
    this.saved = true;
  }
  
  public String getText() {
    return this.pad.getText();
  }
  
  public void cut() {
    
  }
  public void copy() {
    
  }
  public void paste() {
    
  }
  public void selectAll() {
    
  }
}