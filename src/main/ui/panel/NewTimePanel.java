package ui.panel;

import javax.swing.*;
import java.awt.*;

public class NewTimePanel extends JPanel {
    private JTextField nameInput;
    private JComboBox swimmerGroupDropdown;
    private JTextField swimmerAgeInput;
    private JTextField meetDescriptionInput;
    private JComboBox eventDistanceDropdown;
    private JComboBox eventStrokeDropdown;
    private JTextField eventTimeInput;
    private JButton addTimeButton;
    private JLabel labelHandle;
    private JLabel nameLabel;
    private JLabel swimmerGroupLabel;
    private JLabel ageLabel;
    private JLabel meetNameLabel;
    private JLabel eventDistanceLabel;
    private JLabel eventStrokeLabel;
    private JLabel eventTimeLabel;

    private TimeDatabasePanel timeDatabasePanel;

    public NewTimePanel() {
        initializeFields();
        setComponentBounds();
        addComponents();  // add to panel to be returned back to windows
        setComponentDimensions();
        this.setLayout(null);
    }

    // MODIFIES: this
    // EFFECTS: initializes all fields
    private void initializeFields() {
        setAddTimeButton();
        setNameInput();
        setSwimmerGroupDropdown();
        setSwimmerAgeInput();
        setMeetDescriptionInput();
        setEventDistanceDropdown();
        setEventStrokeDropdown();
        setEventTimeInput();
        nameLabel = getLabelName("Name");
        swimmerGroupLabel = getLabelName("Sex");
        ageLabel = getLabelName("Age");
        meetNameLabel = getLabelName("Meet Name");
        eventDistanceLabel = getLabelName("Distance (m)");
        eventStrokeLabel = getLabelName("Stroke");
        eventTimeLabel = getLabelName("Time (mm:ss:ms)");
    }

    private void setNameInput() {
        nameInput = new JTextField();
    }

    public JTextField getNameInput() {
        return nameInput;
    }

    private void setSwimmerGroupDropdown() {
        String[] sexString = { "", "M", "F" };
        swimmerGroupDropdown = new JComboBox(sexString);
        swimmerGroupDropdown.setSelectedIndex(0);
    }

    public JComboBox getSwimmerGroupDropdown() {
        return swimmerGroupDropdown;
    }

    private void setSwimmerAgeInput() {
        swimmerAgeInput = new JTextField();
    }

    public JTextField getSwimmerAgeInput() {
        return swimmerAgeInput;
    }

    private void setMeetDescriptionInput() {
        meetDescriptionInput = new JTextField("");
    }

    public JTextField getMeetDescriptionInput() {
        return meetDescriptionInput;
    }

    private void setEventDistanceDropdown() {
        String[] distanceString = { null, "50", "100", "200", "400", "800", "1500" };
        eventDistanceDropdown = new JComboBox(distanceString); // weird because its duplicated
        eventDistanceDropdown.setSelectedIndex(0);
    }

    public JComboBox getEventDistanceDropdown() {
        return eventDistanceDropdown;
    }

    private void setEventStrokeDropdown() {
        String [] strokeString = {null, "Butterfly", "Backstroke", "Breaststroke", "Freestyle", "Individual Medley"};
        eventStrokeDropdown = new JComboBox(strokeString);
    }

    public JComboBox getEventStrokeDropdown() {
        return eventStrokeDropdown;
    }

    private void setEventTimeInput() {
        eventTimeInput = new JTextField("");
    }

    public JTextField getEventTimeInput() {
        return eventTimeInput;
    }

    private void setAddTimeButton() {
        addTimeButton = new JButton("Add Time");
        addTimeButton.setBackground(new Color(0, 128, 255));
        addTimeButton.setForeground(new Color(255, 255, 255));
    }

    public JLabel getLabelName(String caption) {
        labelHandle = new JLabel(caption);
        labelHandle.setHorizontalAlignment(JLabel.CENTER);
        return labelHandle;
    }

    // make colour variables declaration
    public JButton getAddTimeButton() {
        return addTimeButton;
    }

    // MODIFIES: this
    // EFFECTS: sets all fields bounds
    private void setComponentBounds() {
        nameInput.setBounds(50, 55, 175, 35);
        swimmerGroupDropdown.setBounds(250, 55, 50, 30);
        swimmerAgeInput.setBounds(325, 55, 55, 35);
        meetDescriptionInput.setBounds(400, 55, 275, 35);
        eventDistanceDropdown.setBounds(700, 55, 100, 30);
        eventStrokeDropdown.setBounds(825, 55, 150, 30);
        eventTimeInput.setBounds(1000, 55, 100, 35);
        addTimeButton.setBounds(1125, 55, 100, 36);
        nameLabel.setBounds(50, 30, 175, 30);
        swimmerGroupLabel.setBounds(250, 30, 50, 25);
        ageLabel.setBounds(325, 30, 50, 25);
        meetNameLabel.setBounds(325, 30, 350, 25);
        eventDistanceLabel.setBounds(700, 30, 100, 25);
        eventStrokeLabel.setBounds(825, 30, 150, 25);
        eventTimeLabel.setBounds(1000, 30, 100, 25);
    }

    private void setComponentDimensions() {
        Dimension preferredSize = new Dimension();
        for (int i = 0; i < getComponentCount(); i++) {
            Rectangle bounds = getComponent(i).getBounds();
            preferredSize.width = bounds.x + bounds.width;
            preferredSize.height = bounds.y + bounds.height;
        }
        setPreferredSize(preferredSize);
    }

    // MODIFIES: this
    // EFFECTS: adds all components to this panel
    private void addComponents() {
        add(nameLabel);
        add(swimmerGroupLabel);
        add(swimmerAgeInput);
        add(meetNameLabel);
        add(eventDistanceLabel);
        add(eventStrokeLabel);
        add(eventTimeLabel);
        add(nameInput);
        add(swimmerGroupDropdown);
        add(ageLabel);
        add(meetDescriptionInput);
        add(eventDistanceDropdown);
        add(eventStrokeDropdown);
        add(eventTimeInput);
        add(addTimeButton);
    }
}
