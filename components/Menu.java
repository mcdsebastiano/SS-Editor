package sstream.components;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Menu extends JMenu {
  public Menu(sstream.controllers.MenuController.Categories category) {
    this.setText(category.toString());
    this.setMnemonic(category.mnemonic());
  }
}