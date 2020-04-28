package sstream;

import sstream.controllers.MenuController;
import sstream.controllers.TabController;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.BorderLayout;

public class Editor extends JFrame {
  
  private final JPanel container;
  
  public Editor() {
    this.container = new JPanel(new BorderLayout());
    this.container.add(TabController.pane(), "Center");
    this.setJMenuBar(MenuController.menubar());
    TabController.newTab("new tab");
    MenuController.buildMenu();
    this.add(this.container);
  }
 
  public static void createAndShowGUI() {
    final Editor editor = new Editor();
    editor.setTitle("SimpleStream.editor");
    editor.setDefaultCloseOperation(3);
    editor.setBounds(0, 0, 600, 700);
    editor.setVisible(true);
  }
  
  public static void main(final String[] array) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }
}
