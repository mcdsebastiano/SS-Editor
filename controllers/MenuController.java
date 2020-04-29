package jim.controllers;

import javax.swing.JFileChooser;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuBar;
import java.util.ArrayList;
import java.awt.Component;

import jim.components.Menu;

public abstract class MenuController {
  private interface MenuAction {
    public JMenuItem type();
    public void bind(JMenuItem item);
    public int modifier();
    public int keyCode();
  }
  private interface MenuOptions {
    public ArrayList<Task> tasks = new ArrayList<>();
    public int mnemonic();
    public void tasks();
  }
  private static class Task {
    private MenuAction action;
    private String title;
    private void bind(JMenuItem a) { this.action.bind(a); }
    public JMenuItem type() { return this.action.type();  }
    private int keyCode()  {  return this.action.keyCode(); }
    private int modifier() {  return this.action.modifier(); }
    private Task(String title, MenuAction action) {
      this.title = title;
      this.action = action;
    }
  }

  public enum Categories implements MenuOptions {
    FILE("File") {
      @Override public int mnemonic()  { return 70; }
      @Override public void tasks() {
        tasks.add(new Task("New File", new MenuAction() {
          @Override public JMenuItem type() { return new JMenuItem(); }
          @Override public int keyCode()  { return 78; }
          @Override public int modifier() { return 2; }
          @Override public void bind(JMenuItem item) {
            item.addActionListener(event -> TabController.newTab("new tab"));
          }
        }));
        tasks.add(new Task("Open File", new MenuAction() {
          @Override public JMenuItem type() { return new JMenuItem(); }
          @Override public int keyCode()  { return 79; }
          @Override public int modifier() { return 2; }
          @Override public void bind(JMenuItem item) {
            item.addActionListener(event -> FileController.open() );
          }
        }));
        tasks.add(new Task("Save", new MenuAction() {
          @Override public JMenuItem type() { return new JMenuItem(); }
          @Override public int keyCode()  { return 83; }
          @Override public int modifier() { return 2; }
          @Override public void bind(JMenuItem item) {
            item.addActionListener(event -> {
              if(TabController.workarea().saved() == false) {
                FileController.saveAs();
              } else {
                FileController.save(TabController.workarea().file());
              }
            });
          }
        }));
        tasks.add(new Task("Save As", new MenuAction() {
          @Override public JMenuItem type() { return new JMenuItem(); }
          @Override public int keyCode()  { return 83; }
          @Override public int modifier() { return 8 | 2; }
          @Override public void bind(JMenuItem item) {
            item.addActionListener(event -> FileController.saveAs());
          }
        }));
        tasks.add(new Task("Exit", new MenuAction() {
          @Override public JMenuItem type() { return new JMenuItem(); }
          @Override public int keyCode()  { return 115; }
          @Override public int modifier() { return 8; }
          @Override public void bind(JMenuItem item) {
            item.addActionListener(event -> { }); 
          }
        }));
      }
    },
    EDIT("Edit") {
      @Override public int mnemonic() { return 68; }
      @Override public void tasks() {
        tasks.add(new Task("Undo", new MenuAction() {
          @Override public JMenuItem type() { return new JMenuItem(); }
          @Override public int keyCode()  { return 90; }
          @Override public int modifier() { return 2;  }
          @Override public void bind(JMenuItem item) {
            item.addActionListener(event -> TabController.workarea().undo());
          }
        }));
        tasks.add(new Task("Redo", new MenuAction() {
          @Override public JMenuItem type() { return new JMenuItem(); }
          @Override public int keyCode()  { return 89; }
          @Override public int modifier() { return 2;  }
          @Override public void bind(JMenuItem item) {
            item.addActionListener(event -> TabController.workarea().redo());
          }
        }));
        tasks.add(new Task("Cut", new MenuAction() {
          @Override public JMenuItem type() { return new JMenuItem(); }
          @Override public int keyCode()  { return 88; }
          @Override public int modifier() { return 2;  }
          @Override public void bind(JMenuItem item) {
            item.addActionListener(event -> { TabController.workarea().cut(); });
          }
        }));
        tasks.add(new Task("Copy", new MenuAction() {
          @Override public JMenuItem type() { return new JMenuItem(); }
          @Override public int keyCode()  { return 67; }
          @Override public int modifier() { return 2;  }
          @Override public void bind(JMenuItem item) { 
            item.addActionListener(event -> { TabController.workarea().copy(); }); 
          }
        }));
        tasks.add(new Task("Paste", new MenuAction() {
          @Override public JMenuItem type() { return new JMenuItem(); }
          @Override public int keyCode()  { return 86; }
          @Override public int modifier() { return 2;  }
          @Override public void bind(JMenuItem item) {
            item.addActionListener(event -> { TabController.workarea().paste(); }); 
          }
        }));
        tasks.add(new Task("Select All", new MenuAction() {
          @Override public JMenuItem type() { return new JMenuItem(); }
          @Override public int keyCode()  { return 65; }
          @Override public int modifier() { return 2;  }
          @Override public void bind(JMenuItem item) { 
            item.addActionListener(event -> { TabController.workarea().selectAll(); }); 
          }
        }));
      }
    },
    SEARCH("Search") {
      @Override public int mnemonic() { return 83; }
      @Override public void tasks() {
        tasks.add(new Task("Find", new MenuAction() {
          @Override public JMenuItem type() { return new JMenuItem(); }
          @Override public int keyCode()  { return 70; }
          @Override public int modifier() { return 2;  }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> { }); }
        }));
        tasks.add(new Task("Find Previous", new MenuAction() {
          @Override public JMenuItem type() { return new JMenuItem(); }
          @Override public int keyCode()  { return 37; }
          @Override public int modifier() { return 2;  }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> { }); }
        }));
        tasks.add(new Task("Find Next", new MenuAction() {
          @Override public JMenuItem type() { return new JMenuItem(); }
          @Override public int keyCode()  { return 39; }
          @Override public int modifier() { return 1;  }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> { }); }
        }));
        tasks.add(new Task("Goto", new MenuAction() {
          @Override public JMenuItem type() { return new JMenuItem(); }
          @Override public int keyCode() {  return 71; }
          @Override public int modifier() { return 2;  }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> { }); }
        }));
      }
    },
    BUILD("Build") {
      @Override public int mnemonic() { return 66; }
      @Override public void tasks() {
        tasks.add(new Task("Terminal", new MenuAction() {
          @Override public JMenuItem type() { return new JCheckBoxMenuItem(); }
          @Override public int keyCode()  { return 69; }
          @Override public int modifier() { return 2;  }
          @Override public void bind(JMenuItem item) {
            item.addActionListener(event -> {
              if(item.isSelected()) {
                ConsoleController.init();  
                PanelController.add(ConsoleController.console(), "South");
                ConsoleController.focus();
              } else {
                PanelController.remove(ConsoleController.console());
                TabController.focus();
              }
             }); 
          }
        }));
        tasks.add(new Task("Customize Build", new MenuAction() {
          @Override public JMenuItem type() { return new JMenuItem(); }
          @Override public int keyCode()  { return 66; }
          @Override public int modifier() { return 2;  }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> { }); }
        }));
        tasks.add(new Task("Compile", new MenuAction() {
          @Override public JMenuItem type() { return new JMenuItem(); }
          @Override public int keyCode()  { return 116; }
          @Override public int modifier() { return 0;   }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> { }); }
        }));
        tasks.add(new Task("Run", new MenuAction() {
          @Override public JMenuItem type() { return new JMenuItem(); }
          @Override public int keyCode()  { return 116; }
          @Override public int modifier() { return 1;   }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> { }); }
        }));
      }
    },
    WINDOW("Window") {
      @Override public int mnemonic() { return 87; }
      @Override public void tasks() {
        tasks.add(new Task("Close Tab", new MenuAction() {
          @Override public JMenuItem type() { return new JMenuItem(); }
          @Override public int keyCode()  { return 87; }
          @Override public int modifier() { return 2;  }
          @Override public void bind(JMenuItem item) {
            item.addActionListener(event -> TabController.closeTab());
          }
        }));
        tasks.add(new Task("Next Tab", new MenuAction() {
          @Override public JMenuItem type() { return new JMenuItem(); }
          @Override public int keyCode()  { return 33;    }
          @Override public int modifier() { return 8; }
          @Override public void bind(JMenuItem item) {
            item.addActionListener(event -> TabController.nextTab());
          }
        }));
        tasks.add(new Task("Previous Tab", new MenuAction() {
          @Override public JMenuItem type() { return new JMenuItem(); }
          @Override public int keyCode()  { return 34;    }
          @Override public int modifier() { return 8; }
          @Override public void bind(JMenuItem item) {
            item.addActionListener(event -> TabController.prevTab());
          }
        }));
      }
    },
    HELP("Help") {
      @Override public int mnemonic() { return 72; }
      @Override public void tasks() {
        tasks.add(new Task("Help", new MenuAction() {
          @Override public JMenuItem type() { return new JMenuItem(); }
          @Override public int keyCode()  { return 69; }
          @Override public int modifier() { return 2;  }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> { }); }
        }));
      }
    };
    private String category;
    @Override public String toString()  { return category; }
    private Categories(String category) { this.category = category; }
  }
  private final static JMenuBar menubar = new JMenuBar();
  public static JMenuBar menubar() { return menubar; }
  
  public static void build() {
    for(Categories category : Categories.values()) {
  
      MenuOptions.tasks.clear();
      category.tasks();
      
      Menu menu = new Menu(category);
      menubar.add(menu);

      int size = MenuOptions.tasks.size();
      
      for(int i = 0; i < size; i++) {
        Task task = MenuOptions.tasks.get(i);
        JMenuItem menuItem = task.type();
        
        KeyStroke accelerator = KeyStroke.getKeyStroke(task.keyCode(), task.modifier());
        menuItem.setAccelerator(accelerator);
        menuItem.setText(task.title);
        task.bind(menuItem);
        
        menubar.getMenu(category.ordinal()).add(menuItem);
      }
    }
  }
}