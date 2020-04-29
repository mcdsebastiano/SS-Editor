package jim;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import jim.controllers.MenuController;
import jim.controllers.TabController;
import jim.controllers.PanelController;

public class Editor extends JFrame {

  private final static long serialVersionUID = 1L;
  
  public Editor() {
    MenuController.build();
    setJMenuBar(MenuController.menubar());
    PanelController.add(TabController.pane(), "Center");
    add(PanelController.container());
  }
 
  public static void createAndShowGUI() {
    final Editor editor = new Editor();
    editor.setTitle("jim editor");
    editor.setDefaultCloseOperation(3);
    editor.setBounds(0, 0, 800, 900);
    editor.setVisible(true);
  }
  
  public static void main(final String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch(Exception ex) {
      ex.printStackTrace();
    }
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }
}
