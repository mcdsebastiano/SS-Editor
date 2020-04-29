package jim.controllers;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import jim.components.WorkArea;

public class KeyController implements KeyListener {
  
  private WorkArea w = TabController.workarea();
  
  @Override
  public void keyTyped(KeyEvent key) {
    TabController.setLineCount(w.pad().getLineCount());
  }
  @Override
  public void keyPressed(KeyEvent key) {
    
  }
  @Override
  public void keyReleased(KeyEvent key) {
    
  }
  
  
}


