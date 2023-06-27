// Sources:
// https://codegym.cc/groups/posts/how-to-call-a-method-in-java-#:~:text=To%20call%20a%20method%20in%20Java%2C%20simply%20write%20the%20method's,()%20and%20a%20semicolon(%3B).
// https://jenkov.com/tutorials/java-internationalization/simpledateformat.html
// https://stackoverflow.com/questions/5387371/how-to-convert-minutes-to-hours-and-minutes-hhmm-in-java
// https://www.baeldung.com/java-read-input-until-condition
// https://www.w3schools.com/java/java_arraylist.asp
// https://www.w3schools.com/java/java_user_input.asp
// https://www.w3schools.com/java/java_for_loop.asp
// https://www.w3schools.com/java/java_data_types.asp
// https://www.geeksforgeeks.org/date-gettime-method-in-java-with-examples/


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MotorPhPayroll {

    public static void main(String[] args) throws Exception {
        List<String> dateList = new ArrayList<>();     // lists to hold time-in, time-out values, and calculated durations
        List<String> timeInList = new ArrayList<>();
        List<String> timeOutList = new ArrayList<>();
        List<Long> durationList = new ArrayList<>();   // for the total hours worked
        Scanner scanner = new Scanner(System.in);       // read user input
        boolean continueAdding = true;                  // boolean to control input loop

        System.out.println("Enter the hourly rate:");
        double hourlyRate = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        System.out.println("Enter in the hours worked by employee:");

        // While loop to continuously ask for date and hours worked by the employee using scanner. Will continuously ask for data until the user says no.

        while (continueAdding) {
            System.out.println("Enter Date (YYYY-MM-DD):");                            // asks for date
            String date = scanner.nextLine();
            dateList.add(date);

            System.out.println("Enter Employee's time in (HH:MM:SS 24-Hour Format):");                // asks for time in
            String timeIn = scanner.nextLine();
            timeInList.add(timeIn);

            System.out.println("Enter Employee's time out (HH:MM:SS 24-Hour Format):");               // asks for time out
            String timeOut = scanner.nextLine();
            timeOutList.add(timeOut);

            System.out.println("Date and time entries are added!");                     // tells the user that data has been entered

            System.out.println("Do you want to add more data? (Y/N)");                 // asks the user if they want to continue or not
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("N")) {                            // if no, the while loop will end
                continueAdding = false;
            }
        }

        System.out.println("Total hours worked:");

        long totalSeconds = 0;             // this will store the total seconds worked

        for (int i = 0; i < dateList.size(); i++) {                                           // for loop is used to iterate through the data stored in the lists.
            String date = dateList.get(i);
            String timeIn = timeInList.get(i);
            String timeOut = timeOutList.get(i);
            long difference = calculateTotalSecondsWorked(timeIn, timeOut);                     // the method calculateTotalSecondsWorked is called to calculate the difference between the in and out values in seconds
            durationList.add(difference);
            totalSeconds += difference;
            System.out.println("Date: " + date + ", Hours worked: " + formatDuration(difference));
        }

        System.out.println("\nTotal of hours worked: " + formatDuration(totalSeconds));

        double totalGross = totalSeconds / 3600.0 * hourlyRate;                 // calculate the total gross
        System.out.println("\nTotal Gross: Php " + totalGross);

        scanner.close();
    }
    
    // Method for converting user input (string) to seconds
    private static long calculateTotalSecondsWorked(String timeIn, String timeOut) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date timeIn2 = format.parse(timeIn);
        Date timeOut2 = format.parse(timeOut);
        return (timeOut2.getTime() - timeIn2.getTime()) / 1000;
    }

    private static String formatDuration(long duration) {
        long hours = duration / 3600;
        long minutes = (duration % 3600) / 60;
        long seconds = duration % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
    
}
