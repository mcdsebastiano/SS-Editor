package sstream.controllers;

import javax.swing.JFileChooser;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.util.ArrayList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

import sstream.components.Menu;

public abstract class MenuController {
  public static class Task {
    public MenuAction action;
    public String title;
    public void bind(JMenuItem a) { this.action.bind(a); }
    public int keyCode()  {  return this.action.keyCode(); }
    public int modifier() {  return this.action.modifier(); }
    public Task(String title, MenuAction action) {
      this.title = title;
      this.action = action;
    }
  }
  public interface MenuAction {
    public void bind(JMenuItem item);
    public int modifier();
    public int keyCode();
  }
  public interface MenuOptions {
    ArrayList<Task> tasks = new ArrayList<>();
    Task getTask(int idx);
    void tasks();
    int mnemonic();
  }

  public enum Categories implements MenuOptions {
    FILE("File") {
      @Override public Task getTask(int idx) { return tasks.get(idx); }
      @Override public int mnemonic()  { return 70; }
      @Override public void tasks() {
        tasks.add(new Task("New File", new MenuAction() {
          @Override public int keyCode()  { return 78; }
          @Override public int modifier() { return 2; }
          @Override public void bind(JMenuItem item) {
            item.addActionListener(event -> TabController.newTab("new tab"));
          }
        }));
        tasks.add(new Task("Open File", new MenuAction() {
          @Override public int keyCode()  { return 79; }
          @Override public int modifier() { return 2; }
          @Override public void bind(JMenuItem item) {
            item.addActionListener(event -> { });
          }
        }));
        tasks.add(new Task("Save", new MenuAction() {
          @Override public int keyCode()  { return 83; }
          @Override public int modifier() { return 2; }
          @Override public void bind(JMenuItem item) {
            item.addActionListener(event -> {
              if(TabController.getSaveState() == false) saveAs();
              else save(TabController.file());
            });
          }
        }));
        tasks.add(new Task("Save As", new MenuAction() {
          @Override public int keyCode()  { return 83; }
          @Override public int modifier() { return 8 | 2; }
          @Override public void bind(JMenuItem item) {
            item.addActionListener(event -> saveAs());
          }
        }));
        tasks.add(new Task("Exit", new MenuAction() {
          @Override public int keyCode()  { return 115; }
          @Override public int modifier() { return 8; }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> { }); }
        }));
      }
    },
    EDIT("Edit") {
      @Override public Task getTask(int idx) { return tasks.get(idx); }
      @Override public int mnemonic() { return 68; }
      @Override public void tasks() {
        tasks.add(new Task("Undo", new MenuAction() {
          @Override public int keyCode()  { return 90; }
          @Override public int modifier() { return 2;  }
          @Override public void bind(JMenuItem item) {
            item.addActionListener(event -> {
              TabController.undo();
            });
          }
        }));
        tasks.add(new Task("Redo", new MenuAction() {
          @Override public int keyCode()  { return 89; }
          @Override public int modifier() { return 2; }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> { }); }
        }));
        tasks.add(new Task("Cut", new MenuAction() {
          @Override public int keyCode()  { return 88; }
          @Override public int modifier() { return 2; }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> { }); }
        }));
        tasks.add(new Task("Copy", new MenuAction() {
          @Override public int keyCode()  { return 67; }
          @Override public int modifier() { return 2; }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> { }); }
        }));
        tasks.add(new Task("Paste", new MenuAction() {
          @Override public int keyCode()  { return 86; }
          @Override public int modifier() { return 2; }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> { }); }
        }));
        tasks.add(new Task("Select All", new MenuAction() {
          @Override public int keyCode()  { return 65; }
          @Override public int modifier() { return 2; }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> { }); }
        }));
      }
    },
    SEARCH("Search") {
      @Override public Task getTask(int idx) { return tasks.get(idx); }
      @Override public int mnemonic() { return 83; }
      @Override public void tasks() {
        tasks.add(new Task("Find", new MenuAction() {
          @Override public int keyCode()  { return 70; }
          @Override public int modifier() { return 2; }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> { }); }
        }));
        tasks.add(new Task("Find Previous", new MenuAction() {
          @Override public int keyCode()  { return 37; }
          @Override public int modifier() { return 2; }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> { }); }
        }));
        tasks.add(new Task("Find Next", new MenuAction() {
          @Override public int keyCode()  { return 39; }
          @Override public int modifier() { return 1; }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> { }); }
        }));
        tasks.add(new Task("Goto", new MenuAction() {
          @Override public int keyCode() {  return 71; }
          @Override public int modifier() { return 2; }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> { }); }
        }));
      }
    },
    BUILD("Build") {
      @Override public Task getTask(int idx) { return tasks.get(idx); }
      @Override public int mnemonic() { return 66; }
      @Override public void tasks() {
        tasks.add(new Task("Terminal", new MenuAction() {
          @Override public int keyCode()  { return 69; }
          @Override public int modifier() { return 2; }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> { }); }
        }));
        tasks.add(new Task("Customize Build", new MenuAction() {
          @Override public int keyCode()  { return 66; }
          @Override public int modifier() { return 2; }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> { }); }
        }));
        tasks.add(new Task("Compile", new MenuAction() {
          @Override public int keyCode()  { return 116; }
          @Override public int modifier() { return 0; }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> { }); }
        }));
        tasks.add(new Task("Run", new MenuAction() {
          @Override public int keyCode()  { return 116; }
          @Override public int modifier() { return 1; }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> { }); }
        }));
      }
    },
    WINDOW("Window") {
      @Override public Task getTask(int idx) { return tasks.get(idx); }
      @Override public int mnemonic() { return 87; }
      @Override public void tasks() {
        tasks.add(new Task("Close Tab", new MenuAction() {
          @Override public int keyCode()  { return 87; }
          @Override public int modifier() { return 2; }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> TabController.closeTab()); }
        }));
        tasks.add(new Task("Next Tab", new MenuAction() {
          @Override public int keyCode()  { return 39; }
          @Override public int modifier() { return 8 | 2; }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> TabController.nextTab()); }
        }));
        tasks.add(new Task("Previous Tab", new MenuAction() {
          @Override public int keyCode()  { return 37; }
          @Override public int modifier() { return 8 | 2; }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> TabController.prevTab()); }
        }));
      }
    },
    HELP("Help") {
      @Override public Task getTask(int idx) { return tasks.get(idx); }
      @Override public int mnemonic() { return 72; }
      @Override public void tasks() {
        tasks.add(new Task("Help", new MenuAction() {
          @Override public int keyCode()  { return 69; }
          @Override public int modifier() { return 2; }
          @Override public void bind(JMenuItem item) { item.addActionListener(event -> { }); }
        }));
      }
    };
    private String category;
    @Override public String toString() { return category; }
    private Categories(String category) { this.category = category; }
  }

  public final static JMenuBar menubar = new JMenuBar();
  public static JMenuBar menubar() { return menubar; }

  public static void buildMenu() {
    for(Categories category : Categories.values()) {
      Menu menu = new Menu(category);
      category.tasks.clear();
      category.tasks();
      menubar.add(menu);

      int size = category.tasks.size();

      for(int i = 0; i < size; i++) {
        final JMenuItem menuItem = new JMenuItem();
        Task task = category.tasks.get(i);
        int modifier = task.modifier();
        int keyCode = task.keyCode();
        int idx = category.ordinal();
        menuItem.setAccelerator(KeyStroke.getKeyStroke(keyCode, modifier));
        menuItem.setText(task.title);
        task.bind(menuItem);
        menubar.getMenu(idx).add(menuItem);

        if(i < size - 1) menubar.getMenu(idx).add(new JSeparator());
      }
    }
  }

  public static void saveAs() {
    JFileChooser fc = new JFileChooser();
    int selection = fc.showSaveDialog(TabController.pane());

    if(selection == JFileChooser.APPROVE_OPTION) {
      save(fc.getSelectedFile());
      TabController.setFile(fc.getSelectedFile());
      TabController.setTabTitle();
      TabController.setSaveState(true);
    }
  }

  public static void save(File file) {
    try {
      BufferedWriter bw = new BufferedWriter(new FileWriter(file));
      bw.write(TabController.getText());
      bw.flush();
      bw.close();
    } catch(Exception ex) {
      System.out.println(ex);
    }
  }
}