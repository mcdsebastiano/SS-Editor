package sstream.controllers;

import sstream.components.Tab;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.undo.*;

public abstract class TabController {

  private final static JTabbedPane pane = new JTabbedPane();

  public static Tab newTab(final String title) {
    final Tab tab = new Tab();
    pane.addTab(title, tab);
    pane.setSelectedIndex(pane.getTabCount() - 1);
    return tab;
  }
  
  public static Tab tab() {
    return ((Tab)pane.getSelectedComponent());
  }
  
  public static java.io.File file() {
    return tab().getFile();
  }
  
  public static void setFile(java.io.File file) {
    tab().setFile(file);
  }

  public static JScrollPane getTab(final int n) {
    return (JScrollPane)pane.getTabComponentAt(n);
  }

  public static void nextTab() {
    pane.setSelectedIndex(pane.getSelectedIndex() + 1);
  }

  public static void prevTab() {
    pane.setSelectedIndex(pane.getSelectedIndex() - 1);
  }

  public static void closeTab() {
    pane.remove(pane.getSelectedIndex());
  }

  public static JTabbedPane pane() {
    return pane;
  }

  public static void undo() {
    tab().undo();
  }
  public static void redo() {
    tab().redo();
  }
  
  public static String getText() {
    return tab().getText();
  }
  
  public static void setSaveState(boolean state) {
    tab().setSaveState(state);
  }
  public static boolean getSaveState() {
    return tab().saveExists();
  }
  public static void setTabTitle() {
    pane.setTitleAt(pane.getSelectedIndex(), file().getName());
  }

}
