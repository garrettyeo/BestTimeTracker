package ui.panel;

import javax.swing.*;
import java.awt.*;

// Represents panel and all components for the remove time panel
public class RemoveTimePanel extends JPanel {
    private JButton removeTimeButton;

    // EFFECTS: constructs the remove time panel
    public RemoveTimePanel() {
        addComponents();
        setRemoveTimeButton();
        setLayout(null);
    }

    // MODIFIES: this
    // EFFECTS: sets up remove time button styling according to specifications
    private void setRemoveTimeButton() {
        Dimension preferredSize = new Dimension(100, 100);
        removeTimeButton = new JButton("Remove Time");
        removeTimeButton.setBackground(new Color(255, 0, 0));
        removeTimeButton.setForeground(new Color(255, 255, 255));
        removeTimeButton.setBounds(500, 20, 300, 50);
        removeTimeButton.setFocusable(false);
        setPreferredSize(preferredSize);
    }

    public JButton getRemoveTimeButton() {
        return removeTimeButton;
    }

    // MODIFIES: this
    // EFFECTS: adds remove time button to panel
    private void addComponents() {
        add(removeTimeButton);
    }
}
