package sstream.components;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

private class TabButton extends JButton implements ActionListener {
  
  private ImageIcon icon;
  private int size = 16;
  
  public TabButton(ImageIcon icon) {
    this.icon = icon;
    this.setPrefferredSize(new Dimension(size, size));
  }
  @Override
  public void actionPerformed(ActionEvent event) {
    
  }
}