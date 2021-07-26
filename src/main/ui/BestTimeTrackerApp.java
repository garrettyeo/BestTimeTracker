package ui;

import model.ListOfTimes;
import model.Time;

import java.util.Scanner;

// Best time tracker application. Also contains code for console prompts
// menu design and other console prompts are based on or reuses code from TellerApp.java class from
// TellerApp example repo
public class BestTimeTrackerApp {
    private Scanner input;
    private ListOfTimes timeList;
    private Time swimmerGroup;

    //initialized Time values to be used for console demo
    private Time t1 = new Time("Garrett", 'M', 21, "Lower Mainland Invitational",
            "100 Breaststroke", "1:05:25");
    private Time t2 = new Time("Daiya", 'M', 25, "World Championships",
            "400 IM", "4:06:23");
    private Time t3 = new Time("Daiya", 'M', 26, "Tokyo Olympics",
            "400 IM", "4:10:23");
    private Time t4 = new Time("Daiya", 'M', 26, "Tokyo Olympics",
            "200 Butterfly", "1:52:00");

    // EFFECTS: runs the best time tracker application
    public BestTimeTrackerApp() {
        runBestTimeTracker();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBestTimeTracker() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();

            if (command.equals("5")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThanks!");
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
        } else {
            System.out.println("Input not recognized. Try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes Times
    private void init() {
        timeList = new ListOfTimes();
        timeList.addTime(t1);
        timeList.addTime(t2);
        timeList.addTime(t3);
        timeList.addTime(t4);
        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nInput one of the options below:");
        System.out.println("\t1 -> Add time");
        System.out.println("\t2 -> Remove time");
        System.out.println("\t3 -> Lookup Swimmer");
        System.out.println("\t4 -> Display all times");
        System.out.println("\t5 -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: adds a time to the list
    private void doAddTime() {
        System.out.println("\n\tName:");
        String name = input.next();
        System.out.println("\tSex:");
        char sex = input.next().charAt(0);
        System.out.println("\tAge:");
        int age = input.nextInt();
        System.out.println("\tMeet name:");
        String meetName = input.next();
        System.out.println("\tEvent name");
        String eventName = input.next();
        System.out.println("\tEvent time (mm:ss:ms):");
        String eventTime = input.next();

        Time t = new Time(name, sex, age, meetName, eventName, eventTime);
        timeList.addTime(t);

        System.out.println("Added!");
    }

    // MODIFIES: this
    // EFFECTS: removes a time from the list
    private void doRemoveTime() {
        System.out.println("\n\tName:");
        String name = input.next();
        System.out.println("\tSex:");
        char sex = input.next().charAt(0);
        System.out.println("\tAge:");
        int age = input.nextInt();
        System.out.println("\tMeet name:");
        String meetName = input.next();
        System.out.println("\tEvent name");
        String eventName = input.next();
        System.out.println("\tEvent time (mm:ss:ms):");
        String eventTime = input.next();

        Time t = new Time(name, sex, age, meetName, eventName, eventTime);
        timeList.removeTime(t);

        System.out.println("Removed!");
    }

    // MODIFIES: this
    // EFFECTS: given a name, displays all best times under that name
    private void doLookupSwimmer() {
        System.out.println("\nName:");
        String name = input.next();
        System.out.println("\n\t" + name + "'s best times:");

        ListOfTimes swimmerBestTimes = timeList.getSwimmerBestTime(name);
        int size = swimmerBestTimes.getSize();

        for (int i = 0; i < size; i++) {
            Time t = swimmerBestTimes.getTime(i);
            System.out.println("\t" + t.getName() + "\t" + t.getSwimmerGroup() + "\t" + t.getAge() + "\t"
                    + t.getMeetName() + "\t" + t.getEvent() + "\t " + t.getEventTime());
        }
    }

    //MODIFIES: this
    // EFFECTS: displays all times in list
    private void doDisplayAll() {
        System.out.println("\t" + "Time \t Sex \t Age \t Meet \t Event \t Time");
        int size = timeList.getSize();
        for (int i = 0; i < size; i++) {
            Time t = timeList.getTime(i);
            System.out.println("\t" + t.getName() + "\t" + t.getSwimmerGroup() + "\t" + t.getAge() + "\t"
                    + t.getMeetName() + "\t" + t.getEvent() + "\t " + t.getEventTime());
        }
    }
}
