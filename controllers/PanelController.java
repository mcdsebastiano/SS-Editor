package jim.controllers;

import java.awt.Component;
import jim.components.Container;

public abstract class PanelController {

  private static Container container = new Container();

  public static Container container() { 
    return container;
  }

  public static void add(java.awt.Component pane, String layout) {
    container.add(pane, layout);
    update();
  }

  public static void remove(Component component) {
    container.remove(component);
    update();
  }

  public static void update() {
    container.updateUI();
  }
}
