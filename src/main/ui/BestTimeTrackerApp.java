package ui;

import model.TimeDatabase;
import model.Time;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Best time tracker application. Also contains code for console prompts
// NOTE: much of this code for the menu design and other console prompts are based on or reuses code from
// TellerApp.java class from TellerApp example repo of CPSC 210
public class BestTimeTrackerApp {
    private static final String JSON_STORE = "./data/timedatabase.json";
    private Scanner input;
    private TimeDatabase timeList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private String swimmerName;

    // EFFECTS: runs the best time tracker application
    public BestTimeTrackerApp() throws FileNotFoundException {
        timeList = new TimeDatabase();
        input = new Scanner(System.in);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        runBestTimeTracker();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBestTimeTracker() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMenu();
            command = input.nextLine();

            if (command.equals("7")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            doAddTime();
        } else if (command.equals("2")) {
            doRemoveTime();
        } else if (command.equals("3")) {
            doLookupSwimmer();
        } else if (command.equals("4")) {
            doDisplayAll();
        } else if (command.equals("5")) {
            saveTimesDatabase();
        } else if (command.equals("6")) {
            loadTimesDatabase();
        } else if (command.equals("a")) {
            doSwimmerTime();
        } else if (command.equals("b")) {
            doSwimmerBestTime();
        } else {
            System.out.println("Input not recognized. Try again.\n");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("Welcome to the Best Time Tracker!");
        System.out.println("Input one of the options below:");
        System.out.println("\t1 -> Add time");
        System.out.println("\t2 -> Remove time");
        System.out.println("\t3 -> Lookup swimmer");
        System.out.println("\t4 -> Display all times");
        System.out.println("\t5 -> Save times to file");
        System.out.println("\t6 -> Load times from file");
        System.out.println("\t7 -> Quit");
    }

    // TODO remove variable checkers, add exception catching, fix prompts
    // MODIFIES: this
    // EFFECTS: adds a time to the list (option 1)
    private void doAddTime() {
        String name = scanName();
        String sex = scanSex();
        int age = scanAge();
        String meetName = scanMeet();
        String eventName = scanEvent();
        String eventTime = scanTime();

        Time t = new Time(name, sex, age, meetName, eventName, eventTime);
        timeList.addTime(t);

        System.out.println("\nTime added!\n");
    }

    // TODO implement java exceptions
    private String scanName() {
        String name;

        System.out.print("\nName: ");
        return name = input.nextLine();
    }

    private String scanSex() {
        String sex;

        System.out.print("Sex: ");
        return sex = input.nextLine();
    }

    private int scanAge() {
        int age;

        System.out.print("Age: ");
        age = input.nextInt();
        input.nextLine();
        return age;
    }

    private String scanMeet() {
        String meetName;

        System.out.print("Meet name: ");
        return meetName = input.nextLine();
    }

    private String scanEvent() {
        String event;

        System.out.print("Event: ");
        return event = input.nextLine();
    }

    private String scanTime() {
        String time;

        System.out.print("Time: ");
        return time = input.nextLine();
    }

    // TODO fix concurrent modification and remove prompts
    // MODIFIES: this
    // EFFECTS: removes a time from the list (option 2)
    private void doRemoveTime() {
        System.out.print("\nName: ");
        String name = input.nextLine();
        System.out.print("Meet name: ");
        String meetName = input.nextLine();
        System.out.println("You entered " + meetName);
        System.out.print("Event name: ");
        String eventName = input.nextLine();
        System.out.println("You entered " + eventName);

        timeList.removeTime(name, meetName, eventName);

        System.out.println("Removed!\n");
    }

    // ADD ANOTHER MENU THAT -shows all of swimmer's best times,    - shows all of swimmer's times
    // MODIFIES: this
    // EFFECTS: given a name, displays all best times under that name
    private void doLookupSwimmer() {
        System.out.print("\nName: ");
        swimmerName = input.nextLine();

        System.out.println("What do you want to know about " + swimmerName);
        System.out.println("\ta -> Get swimmer times");
        System.out.println("\tb -> Get swimmer best times");

        String swimmerMenuSelection = input.nextLine();
        processCommand(swimmerMenuSelection);
    }

    private void doSwimmerTime() {
        List<Time> swimmerTimeList = timeList.getAllSwimmerTimes(swimmerName);
        printTimeList(swimmerTimeList);
    }

    private void doSwimmerBestTime() {
        List<Time> swimmerBestTimeList = timeList.getSwimmerBestTime(swimmerName);
        printTimeList(swimmerBestTimeList);
    }

    //!!!! fix display format concerning spacing
    //MODIFIES: this
    // EFFECTS: displays all times in list (option 4)
    private void doDisplayAll() {
        List<Time> tl = timeList.getTimeList();
        printTimeList(tl);
    }

    private void printTimeList(List<Time> tl) {
        System.out.println(String.format("\t%-20s%-5s%-5s%-30s%-18s%-4s", "Name", "Sex", "Age", "Meet", "Event",
                "Time"));
        System.out.println(String.format("\t%-86s", " ").replace(' ', '-'));
        for (Time t : tl) {
            System.out.println(String.format("\t%-20s%-5s%-5d%-30s%-18s%-8s", t.getName(), t.getSwimmerGroup(),
                    t.getAge(), t.getMeetName(), t.getEvent(), t.getEventTime()));
        }
        System.out.println("\n");
    }

    // EFFECTS: saves the time database to file (option 5)
    private void saveTimesDatabase() {
        try {
            jsonWriter.openTimeDatabaseFile();
            jsonWriter.write(timeList);
            jsonWriter.close();
            System.out.println("Saved time database\n"); //TODO add extra features like counting times???
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads time database from file (option 6)
    private void loadTimesDatabase() {
        try {
            timeList = jsonReader.readTimeDatabaseFile();
            System.out.println("Loaded time database from " + JSON_STORE + "\n");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
