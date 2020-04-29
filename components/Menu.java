package jim.components;

import javax.swing.JMenu;

public class Menu extends JMenu {
  
  private static final long serialVersionUID = 1L;

  public Menu(jim.controllers.MenuController.Categories category) {
    this.setText(category.toString());
    this.setMnemonic(category.mnemonic());
  }
}