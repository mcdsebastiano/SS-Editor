package jim.controllers;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import jim.components.Tab;

public abstract class FileController {
  
  protected static void setFile(File file) {
    TabController.workarea().file(file);
    TabController.setTabTitle();
    TabController.workarea().save();
  }

  protected static void open() {
    JFileChooser fc = new JFileChooser();
    int selection = fc.showOpenDialog(TabController.pane());

    if(selection == JFileChooser.APPROVE_OPTION) {
      JTabbedPane pane = TabController.pane();
      BufferedReader br;
      int lines = 1;
      String str;
      File file;

      try {
        file = fc.getSelectedFile();

        if(pane.indexOfTab(file.getName()) != -1) {
          pane.setSelectedIndex(pane.indexOfTab(file.getName()));
          return;
        } else if(pane.getTabCount() == 0 || !TabController.workarea().getText().equals("")) {
          TabController.newTab("");
        }

        br = new BufferedReader(new FileReader(file));
        
        while((str = br.readLine()) != null) {
          TabController.write(str + "\n");
          lines++;
        }
        
        br.close();
        setFile(file);
        TabController.focus();
        TabController.setLineCount(lines);
      } catch(IOException ex) {
        ex.printStackTrace();
      } finally {

      }
    }
  }

  protected static void save(File file) {
    BufferedWriter bw;

    try {
      bw = new BufferedWriter(new FileWriter(file));
      bw.write(TabController.workarea().getText());
      bw.flush();
      bw.close();
    } catch(Exception ex) {
      ex.printStackTrace();
    } finally {
    }
  }

  protected static void saveAs() {
    JFileChooser fc = new JFileChooser();
    int selection = fc.showSaveDialog(TabController.pane());

    if(selection == JFileChooser.APPROVE_OPTION) {
      File file = fc.getSelectedFile();

      if(file.exists()) {
        if(TabController.workarea().file() != file) {
          int confirm = JOptionPane.showConfirmDialog(TabController.pane(), "Are you sure you want to overwrite: " + file.getName() + "?", "Overwrite File?", JOptionPane.YES_NO_OPTION);

          if(confirm == JOptionPane.NO_OPTION || confirm == JOptionPane.CANCEL_OPTION) return;
        }
      }

      save(file);
      setFile(file);
    }
  }
}