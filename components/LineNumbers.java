package jim.components;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

public class LineNumbers extends JPanel {
  
  private JLabel lineNumbers;
  private int lines;
  public LineNumbers(int rows, int cols) {
    super(new BorderLayout());
    this.lines = 1;
    this.lineNumbers = new JLabel();
    this.lineNumbers.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
    this.lineNumbers.setForeground(Color.WHITE);
    this.lineNumbers.setText("1\n");
    
    this.add(lineNumbers, "North");
    this.setBackground(Color.gray);
    this.setPreferredSize(new Dimension(60, 0));
    this.setBorder(BorderFactory.createEmptyBorder(3, 15, 0, 15));
  }

  public void count() {
    this.lines++;
  }

  public void clear() {
    this.lines = 1;
  }

  public void set(int lines) {
    this.lines = lines;
  }

  public int get() {
    return this.lines;
  }

  public void write(String str) {
    this.lineNumbers.setText(str);
  }
}