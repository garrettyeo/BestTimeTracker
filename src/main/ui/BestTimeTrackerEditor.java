package ui;

import javax.sound.sampled.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import model.Time;
import model.TimeDatabase;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.panel.*;

// Represents application window that contains all panels, their components, and their interactions
// NOTE: much of this code for the window layout and action listeners are based on or reuses code from the
// SimpleDrawingPlayerEditor.java class from the SimpleDrawingPlayer example repo of CPSC 210. Layout and panels are
// also inspired by the HyTek Meet Manager application
public class BestTimeTrackerEditor extends JFrame {
    public static final int WIDTH = 1300;
    public static final int HEIGHT = 700;
    private static final String JSON_STORE = "./data/timedatabase.json";

    private final Dimension size = new Dimension(WIDTH, HEIGHT);   // window size
    private NewTimePanel newTimePanel;
    private TimeDatabasePanel timeDatabasePanel;
    private RemoveTimePanel removeTimePanel;
    private LookupTimePanel lookupTimePanel;
    private FileMenuBar menuBar;

    // data and data persistence objects
    private TimeDatabase timeDatabase;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // application component references for action listeners
    private JButton addTimeButton;
    private JButton removeTimeButton;
    private JMenuItem menuSaveButton;
    private JMenuItem menuLoadButton;
    private JButton nameLookupButton;
    private JCheckBox bestTimeCheckbox;

    // EFFECTS: constructs the application window and initializes panels and necessary data and data persistence objects
    public BestTimeTrackerEditor() {
        super("Best Time Tracker");
        newTimePanel = new NewTimePanel();
        timeDatabasePanel = new TimeDatabasePanel(new Vector<>());
        removeTimePanel = new RemoveTimePanel();
        lookupTimePanel = new LookupTimePanel();
        menuBar = new FileMenuBar();

        timeDatabase = new TimeDatabase();
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        addPanelsToWindow();
        addActionListeners();
        initializeDefaultWindow();
    }

    // MODIFIES: this
    // EFFECTS: sets default properties for window
    private void initializeDefaultWindow() {
        setIconImage(new ImageIcon("src/main/ui/asset/image/swimicon.png").getImage());  // find cleaner icon
        setPreferredSize(size);
        setMinimumSize(size);
        setVisible(true);
        pack();
        setResizable(false);
        setLocationRelativeTo(getOwner());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // MODIFIES: this
    // EFFECTS: adds panels to application window layout
    private void addPanelsToWindow() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        setJMenuBar(menuBar);
        contentPane.add(newTimePanel, BorderLayout.NORTH);
        contentPane.add(timeDatabasePanel, BorderLayout.EAST);
        contentPane.add(removeTimePanel, BorderLayout.SOUTH);
        contentPane.add(lookupTimePanel, BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS: adds and invokes all GUI action listeners
    private void addActionListeners() {
        addTimeButton = newTimePanel.getAddTimeButton();
        addTimeButton.addActionListener(this::addTimeButtonClicked);
        menuLoadButton = menuBar.getMenuItemLoad();
        menuLoadButton.addActionListener(this::loadFileButtonClicked);
        menuSaveButton = menuBar.getMenuItemSave();
        menuSaveButton.addActionListener(this::saveFileButtonClicked);
        removeTimeButton = removeTimePanel.getRemoveTimeButton();
        removeTimeButton.addActionListener((this::removeTimeButtonClicked));
        nameLookupButton = lookupTimePanel.getNameLookupButton();
        nameLookupButton.addActionListener(this::nameLookupButtonClicked);
    }

    // MODIFIES: this
    // EFFECTS: invokes action listener whenever a time is added
    private void addTimeButtonClicked(ActionEvent actionEvent) {
        try {
            addTimeToList();
            refreshTableData();
            playSound("./src/main/ui/asset/audio/success.wav");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            playSound("./src/main/ui/asset/audio/error.wav");
            // print error message
        } // need to create exception that handles null inputs
    }

    // MODIFIES: this, timeDatabase
    // EFFECTS: adds time to timeDatabase
    private void addTimeToList() {
        Time newTime = getNewTime();
        timeDatabase.addTime(newTime);
    }

    // MODIFIES: this
    // EFFECTS: pulls information from add new time panel inputs and returns information in a Time object
    private Time getNewTime() {
        JTextField nameInput = newTimePanel.getNameInput();
        JComboBox swimmerGroupDropdown = newTimePanel.getSwimmerGroupDropdown();
        JTextField swimmerAgeInput = newTimePanel.getSwimmerAgeInput();
        JTextField meetDescriptionInput = newTimePanel.getMeetDescriptionInput();
        JComboBox eventDistanceDropdown = newTimePanel.getEventDistanceDropdown();
        JComboBox eventStrokeDropdown = newTimePanel.getEventStrokeDropdown();
        JTextField eventTimeInput = newTimePanel.getEventTimeInput();

        String name = nameInput.getText();
        String swimmerGroup = (String)swimmerGroupDropdown.getSelectedItem();
        String age = swimmerAgeInput.getText();
        String meetName = meetDescriptionInput.getText();
        String eventDistance = (String)eventDistanceDropdown.getSelectedItem();
        String eventStroke = (String)eventStrokeDropdown.getSelectedItem();
        String event = eventDistance + " " + eventStroke;
        String eventTime = eventTimeInput.getText();

        return new Time(name, swimmerGroup, age, meetName, event, eventTime);
    }

    // MODIFIES: this
    // EFFECTS: invokes action listener whenever a time is removed
    private void removeTimeButtonClicked(ActionEvent actionEvent) {
        JTable table = timeDatabasePanel.getTable();
        try {
            int selectedRowIndex = table.getSelectedRow();
            Vector<String> time = timeDatabasePanel.getRow(selectedRowIndex);
            String name = time.get(0);
            String meetName = time.get(3);
            String eventName = time.get(4);
            timeDatabase.removeTime(name, meetName, eventName);
            playSound("./src/main/ui/asset/audio/success.wav");
            refreshTableData();
        } catch (ArrayIndexOutOfBoundsException e) {
            playSound("./src/main/ui/asset/audio/error.wav");
            // print text
        }
    }

    // MODIFIES: this
    // EFFECTS: invokes action listener whenever file is to be saved
    private void saveFileButtonClicked(ActionEvent actionEvent) {
        try {
            jsonWriter.openTimeDatabaseFile();
            jsonWriter.write(timeDatabase);
            jsonWriter.close();
            //print text
        } catch (FileNotFoundException e) {
            // print text
        }
    }

    // MODIFIES: this
    // EFFECTS: invokes action listener whenever a previous file is to be loaded
    private void loadFileButtonClicked(ActionEvent actionEvent) {
        try {
            timeDatabase = jsonReader.readTimeDatabaseFile();
            refreshTableData();
        } catch (IOException e) {
            playSound("./src/main/ui/asset/audio/error.wav");
            //display text
        }
    }

    // MODIFIES: this
    // EFFECTS: filters timeDatabase and displays a new time table based on swimmers name
    private void nameLookupButtonClicked(ActionEvent actionEvent) {
        try {
            timeDatabasePanel.clearData();
            JScrollPane spTableLookup = timeDatabasePanel.getSpTable();
            JTable tableLookup = timeDatabasePanel.getTable();
            JTextField nameLookupInput = lookupTimePanel.getNameLookupInput();
            String nameLookupText = nameLookupInput.getText();
            List<Time> swimmerTimes = timeDatabase.getAllSwimmerTimes(nameLookupText);

            for (Time time : swimmerTimes) {
                Vector<String> timeVector = new Vector<>();
                timeVector.add(time.getName());
                timeVector.add(time.getSwimmerGroup());
                timeVector.add(time.getAge());
                timeVector.add(time.getMeetName());
                timeVector.add(time.getEvent());
                timeVector.add(time.getEventTime());
                timeDatabasePanel.addTime(timeVector);
            }
            spTableLookup.setViewportView(tableLookup);
        } catch (ArrayIndexOutOfBoundsException e) {
            playSound("./src/main/ui/asset/audio/error.wav");
            //nothing much
        }
    }

    // MODIFIES: this
    // EFFECTS: displays an updated time table in the timeDatabasePanel
    private void refreshTableData() {   // clean up this code
        JScrollPane spTable = timeDatabasePanel.getSpTable();
        JTable table = timeDatabasePanel.getTable();
        timeDatabasePanel.clearData();

        List<Time> timesList = timeDatabase.getTimeList();
        for (Time time : timesList) {
            Vector<String> timeVector = new Vector<>();
            timeVector.add(time.getName());
            timeVector.add(time.getSwimmerGroup());
            timeVector.add(time.getAge());
            timeVector.add(time.getMeetName());
            timeVector.add(time.getEvent());
            timeVector.add(time.getEventTime());
            timeDatabasePanel.addTime(timeVector);
        }
        spTable.setViewportView(table);
    }


    // MODIFIES: this
    // EFFECTS: given an audio file destination, plays audio file in window when called
    private void playSound(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
