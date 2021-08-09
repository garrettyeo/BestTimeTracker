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
        initializeWindow();
    }

    // MODIFIES: this
    // EFFECTS: sets default properties for window
    private void initializeWindow() {
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
    // EFFECTS: adds panels to windows layout
    private void addPanelsToWindow() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        setJMenuBar(menuBar);
        contentPane.add(newTimePanel, BorderLayout.NORTH);
        contentPane.add(timeDatabasePanel, BorderLayout.EAST);
        contentPane.add(removeTimePanel, BorderLayout.SOUTH);
        //contentPane.add(lookupTimePanel, BorderLayout.WEST);
    }

    private void addActionListeners() {
        addTimeButton = newTimePanel.getAddTimeButton();
        addTimeButton.addActionListener(this::addTimeButtonClicked);
        menuLoadButton = menuBar.getMenuItemLoad();
        menuLoadButton.addActionListener(this::loadFileButtonClicked);
        menuSaveButton = menuBar.getMenuItemSave();
        menuSaveButton.addActionListener(this::saveFileButtonClicked);
        removeTimeButton = removeTimePanel.getRemoveTimeButton();
        removeTimeButton.addActionListener((this::removeTimeButtonClicked));

        /* to be implemented later
        nameLookupButton = lookupTimePanel.getNameLookupButton();
        nameLookupButton.addActionListener(this::nameLookupButtonClicked);*/
    }

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

    // add new Time to time database
    private void addTimeToList() {
        Time newTime = getNewTime();
        timeDatabase.addTime(newTime);
    }

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

    private void loadFileButtonClicked(ActionEvent actionEvent) {
        try {
            timeDatabase = jsonReader.readTimeDatabaseFile();
            refreshTableData();
        } catch (IOException e) {
            playSound("./src/main/ui/asset/audio/error.wav");
            //display text
        }
    }

    // could not implement in time
    /*private void nameLookupButtonClicked(ActionEvent actionEvent) {
        try {
            JScrollPane spTableLookup = timeDatabasePanel.getSpTable();
            JTable tableLookup = timeDatabasePanel.getTable();
            String nameLookupText = nameLookupButton.getText();
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

            refreshTableData();
        } catch (ArrayIndexOutOfBoundsException e) {
            //nothing much
        }
    }*/

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


    // sound effect
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
