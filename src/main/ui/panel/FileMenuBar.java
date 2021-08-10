package ui.panel;

import javax.swing.*;

// Represents the menu bar for saving and loading file
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

    // MODIFIES: this
    // EFFECTS: initializes default menu bar components
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

    // EFFECTS: returns reference to menu save button
    public JMenuItem getMenuItemSave() {
        return menuItemSave;
    }

    // EFFECTS: returns reference to menu load button
    public JMenuItem getMenuItemLoad() {
        return menuItemLoad;
    }
}
