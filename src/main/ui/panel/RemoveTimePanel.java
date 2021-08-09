package ui.panel;

import javax.swing.*;
import java.awt.*;

public class RemoveTimePanel extends JPanel {
    private JButton removeTimeButton;

    public RemoveTimePanel() {
        initializeFields();
        addComponents();
        setLayout(null);
    }

    private void initializeFields() {
        setRemoveTimeButton();
    }

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

    private void addComponents() {
        add(removeTimeButton);
    }
}
