/*
 * so here's the dealio, everything is contained within this tab. so i could
 * split things up and make things convoluted as all hell by assigning the
 * methods to a "proper" classname that matches their origin components.
 * however i would always be calling TabController to reference the class
 * anyways.. what do?
 */

package jim.controllers;

import java.lang.StringBuilder;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;

import jim.components.Tab;
import jim.components.WorkArea;

public abstract class TabController {

  private final static JTabbedPane pane = new JTabbedPane();

  public static JTabbedPane pane() {
    return pane;
  }

  protected static Tab tab() {
     return ((Tab)pane.getSelectedComponent());
  }
  
  public static void newTab(final String title) {
    Tab tab = new Tab();
    pane.addTab(title, tab);
    pane.setSelectedIndex(pane.getTabCount() - 1);
    pane.setIconAt(pane.getSelectedIndex(), tab.icon());
    workarea().pad().addKeyListener(new KeyController());
    focus();
  }

  protected static void nextTab() {
    int idx = pane.getSelectedIndex(); 
    int count = pane.getTabCount() - 1;
    if(idx == count)
      pane.setSelectedIndex(0);
    else 
      pane.setSelectedIndex(idx + 1);

    focus();
  }

  protected static void prevTab() {
    int idx = pane.getSelectedIndex(); 
    int count = pane.getTabCount() - 1;
    if(idx == 0)
      pane.setSelectedIndex(count);
    else 
      pane.setSelectedIndex(idx - 1);
      
    focus();
  }

  protected static void closeTab() {
    if(pane.getTabCount() <= 0) return;

    pane.remove(pane.getSelectedIndex());
    
    if(pane.getTabCount() > 0) 
      focus();
  }
 
  protected static void setTabTitle() {
    String file = workarea().file().getName();
    pane.setTitleAt(pane.getSelectedIndex(), file);
  }  
 
  public static void setLineCount(int lines) {
    StringBuilder str = new StringBuilder();
    str.append("<html>");
    for(int i = 1; i <= lines; i++) {
      str.append(i+"<br />");
    }
    str.append("</html>");
    workarea().linenumbers().set(lines);
    workarea().linenumbers().write(str.toString());
  }

  protected static WorkArea workarea() {
    return tab().workarea();
  }
  
  protected static void focus() {
    if(tab() != null && workarea().pad().isFocusable())
      workarea().focus();
  }
  
  protected static void write(String content) {
    workarea().pad().append(content);
  }
}