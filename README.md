 My Personal Project: BestTimeTracker

## Best Time Tracker for Swimming Application

This application will be similar to the different online timing and ranking services found on the *FINA*, *Swimming 
Canada*, and *USA Swimming* websites. This application will allow people to record their times for different swimming 
events, along with other necessary information like name, age, and swimmer group. After recording their times, people 
will be able to determine their own personal best times for each event.

This application is designed for use by competitive swimmers. As a former competitive swimmer, myself, I used to 
frequently document sets from my training sessions, and the times I swam both in practice and during swim meets to 
track my performance relative to my goal times. This application will make it easier to record times for personal use. 

## User Stories:

- As a user, I want to be able to create a new time and add it to my list of times
- As a user, I want to be able to view a list of all times
- As a user, I want to be able to search a name and view all the times ever swim and current best times under a 
swimmer's name
- As a user, I want to be able to delete a time from my list of times
- As a user, I want to be able to save my updated time database to file
- As a user, I want to be able to load my previous time database from file

## Phase 4: Task 2

In designing my application, it was important to ensure that the user input for time was consistent across all time 
entries. In the eventTimeInSeconds() method in the Time class, to properly parse the time user input I compared each 
input to make sure it matched the (mm:ss:ms) format. If not, a NumberFormatException was thrown. This exception 
is caught in the addTimeButtonClicked method of the BestTimeTrackerEditor class, which would then play an 
audio notification and prompt the user to input the time again in the correct format.

## Phase 4: Task 3

No doubt, my project design is not perfect. There is a lot of coupling involved in trying to update the Time Database 
JTable - this could be improved by implementing a bidirectional relationship between the time database and its 
corresponding UI table. Also, the hierarchy of my UML diagram is extremely flat; the BestTimeTrackerEditor class does 
not obey the Single Responsibility principle as I attempted to make it do more than just display different panels. I 
could have created another class like a TimeActionListeners class that would only contain and evoke component Action 
Listeners. This could even be further seperated into different class or interfaces depending on the action done.  