package ui.panel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

// ************* CODE WAS NOT COMPLETED IN TIME AND NOT IMPLEMENTED IN APPLICATION GUI. PLEASE DO NOT MARK ************
// Represents panel that allows user to lookup times based on swimmer names and filter times depending on stroke
public class LookupTimePanel extends JPanel {
    private static final int PADDING = 3;

    private JPanel inputPanel;
    private JPanel checkboxPanel;
    private JTextField nameLookupInput;
    private JButton nameLookupButton;
    private JCheckBox bestTimeCheckbox;
    private JCheckBox stroke1Checkbox;
    private JCheckBox stroke2Checkbox;
    private JCheckBox stroke3Checkbox;
    private JCheckBox stroke4Checkbox;
    private JCheckBox stroke5Checkbox;
    private JCheckBox distance1Checkbox;
    private JCheckBox distance2Checkbox;
    private JCheckBox distance3Checkbox;
    private JCheckBox distance4Checkbox;
    private JCheckBox distance5Checkbox;
    private JCheckBox distance6Checkbox;

    // EFFECTS: constructs the lookup times panel
    public LookupTimePanel() {
        Dimension preferredSize = new Dimension(235, 150);
        this.setPreferredSize(preferredSize);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new LineBorder(new Color(0,0,0), 2));
        initializeFields();
        setupTopPanel();
        setupBottomPanel();
        addComponents();
    }

    // MODIFIES: this
    // EFFECTS: sets up name lookup panel interface
    private void setupTopPanel() {
        inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        nameLookupInput = new JTextField();
        nameLookupButton = new JButton("Search");
        bestTimeCheckbox = new JCheckBox("Best Time");
        inputPanel.setBorder(BorderFactory.createTitledBorder("Lookup Swimmer"));
        inputPanel.setPreferredSize(new Dimension(180, 100));
        nameLookupInput.setPreferredSize(new Dimension(100, 35));
        inputPanel.setMaximumSize(new Dimension(210, 200));
    }

    // EFFECTS: returns reference to nameLookupButton
    public JButton getNameLookupButton() {
        return nameLookupButton;
    }

    // EFFECTS: returns reference to bestTimeCheckbox
    public JCheckBox getBestTimeCheckbox() {
        return bestTimeCheckbox;
    }

    // MODIFIES: this
    // EFFECTS: sets up name checkbox filter interface
    private void setupBottomPanel() {
        checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new GridLayout(6, 2, 10, 10));
    }

    // MODIFIES: this
    // EFFECTS: initializes all fields
    private void initializeFields() {
        stroke1Checkbox = new JCheckBox("Butterfly");
        stroke2Checkbox = new JCheckBox("Backstroke");
        stroke3Checkbox = new JCheckBox("Breaststroke");
        stroke4Checkbox = new JCheckBox("Freestyle");
        stroke5Checkbox = new JCheckBox("Individual Medley");
        distance1Checkbox = new JCheckBox("50m");
        distance2Checkbox = new JCheckBox("100m");
        distance3Checkbox = new JCheckBox("200m");
        distance4Checkbox = new JCheckBox("400m");
        distance5Checkbox = new JCheckBox("800m");
        distance6Checkbox = new JCheckBox("1500m");
    }

    // MODIFIES: this
    // EFFECTS: adds all instantiated components to lookup pane;
    private void addComponents() {
        add(Box.createRigidArea(new Dimension(0, 25)));
        add(inputPanel);
        add(checkboxPanel);
        inputPanel.add(nameLookupInput);
        inputPanel.add(nameLookupButton);
        inputPanel.add(bestTimeCheckbox);
        checkboxPanel.add(stroke1Checkbox);
        checkboxPanel.add(stroke2Checkbox);
        checkboxPanel.add(stroke3Checkbox);
        checkboxPanel.add(stroke4Checkbox);
        checkboxPanel.add(stroke5Checkbox);
        checkboxPanel.add(distance1Checkbox);
        checkboxPanel.add(distance2Checkbox);
        checkboxPanel.add(distance3Checkbox);
        checkboxPanel.add(distance4Checkbox);
        checkboxPanel.add(distance5Checkbox);
        checkboxPanel.add(distance6Checkbox);
    }
}
