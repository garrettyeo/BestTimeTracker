package ui.panel;

import javax.swing.*;

public class FileMenuBar extends JMenuBar {
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItemSave;
    private JMenuItem menuItemLoad;

    // MODIFIES: this
    // EFFECTS: constructs a menu bar
    public FileMenuBar() {
        menuBar = new JMenuBar();
        initializeMenu();
    }

    private void initializeMenu() {
        menu = new JMenu("File");
        add(menu);
        menuItemSave = new JMenuItem("Save");
        menu.add(menuItemSave);
        menu.addSeparator();
        menuItemLoad = new JMenuItem("Load");
        menu.add(menuItemLoad);
        add(menuBar);
    }

    public JMenuItem getMenuItemSave() {
        return menuItemSave;
    }

    public JMenuItem getMenuItemLoad() {
        return menuItemLoad;
    }
}
