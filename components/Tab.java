package jim.components;

import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Insets;

public class Tab extends JScrollPane {
  private WorkArea w;
  private static final long serialVersionUID = 1L;
  private final JPanel view;
  private ImageIcon icon;

  public Tab() {
    this.w = new WorkArea();
    this.icon = new ImageIcon("assets/images/12x12/70.png");
    this.view  = new JPanel(new BorderLayout());
    this.view.add(w.linenumbers(), "West");
    this.view.add(w.pad(), "Center");
    this.view.setBorder(BorderFactory.createEmptyBorder());
    this.setViewportView(this.view);
    this.getVerticalScrollBar().setUnitIncrement(12);
    this.getHorizontalScrollBar().setUnitIncrement(12);
  }
  
  public WorkArea workarea() {
    return this.w;
  }
  
  public JPanel view() {
    return this.view;
  }
  
  public ImageIcon icon() {
    return this.icon;
  }

}