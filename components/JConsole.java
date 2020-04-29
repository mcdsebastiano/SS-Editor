package jim.components;

import javax.swing.text.BadLocationException;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;

import java.util.ArrayList;

import java.net.UnknownHostException;

import java.lang.Process;
import java.lang.ProcessBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class JConsole extends JScrollPane implements KeyListener {

  private JPanel view;
  private String command;
  private final JTextArea textArea;
  private Process process;
  private ProcessBuilder pb;
  private String directory;
  private String user;
  private String hostname;
  private String sessionInfo;
  
  public boolean check(KeyEvent key, char other) {
    return (key.getKeyChar() == other);
  }
  
  public int getLengthFromCaretPosition(int pos) throws BadLocationException {
    int line = this.textArea.getLineOfOffset(pos);
    return this.textArea.getLineStartOffset(line) + this.sessionInfo.length() + 1;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    if(e.getID() == KeyEvent.KEY_TYPED) {
      if(check(e, '\n')) {
        printSessionInfo();
      }
    }
  }
  @Override
  public void keyPressed(KeyEvent e) {
    if(check(e, '\b')) {
        int pos = this.textArea.getCaretPosition(); 
        try {
          if(getLengthFromCaretPosition(pos) > pos) {
            e.consume();
          }
        } catch(BadLocationException ex) {
          ex.printStackTrace();
        }
      }

  }
  @Override
  public void keyReleased(KeyEvent e) {
    
  }

  public void focus() {
    if(this.textArea.isFocusable())
      this.textArea.requestFocusInWindow();
  }
  
  public void printSessionInfo() {
    BufferedReader br;
    String str = null;
    java.net.InetAddress localMachine = null;

    user = System.getProperty("user.name")+"@";
    this.textArea.append(user);
    
    try {
       localMachine = java.net.InetAddress.getLocalHost();
    } catch(UnknownHostException ex) {
      ex.printStackTrace();
    }
    
    hostname = localMachine.getHostName()+":~";
    this.textArea.append(hostname);  
    
    str = null;
    try {
      br = new BufferedReader(new InputStreamReader(new ProcessBuilder("pwd").start().getInputStream()));
      while((str = br.readLine()) != null) {
        directory = str.substring(2)+"$ ";
      }  
      br.close();
    } catch(IOException ex) {
      ex.printStackTrace();
    }
    
    this.textArea.append(directory);
    this.sessionInfo = user+hostname+directory;
  }
  
  public JConsole() {
    this.view = new JPanel(new BorderLayout());
    this.textArea = new JTextArea(12,0);
    this.textArea.addKeyListener(this);
    this.view.add(this.textArea);
    this.view.setBorder(BorderFactory.createEmptyBorder());
    this.setViewportView(view);
    printSessionInfo();
  }
}