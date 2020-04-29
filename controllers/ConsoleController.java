package jim.controllers;

import javax.swing.JTabbedPane;

import jim.components.JConsole;

public abstract class ConsoleController {
  
  private static JTabbedPane pane = new JTabbedPane();
  private static JConsole console = new JConsole();

  protected static void init() {
    pane.addTab("Console", console);
    pane.setSelectedIndex(pane.getTabCount() - 1);
    focus();
  }

  protected static JTabbedPane console() {
    return pane;
  }
  protected static void focus() {
    console.focus();
  }
  
  protected static void printSessionInfo() {
    console.printSessionInfo();
  }


}