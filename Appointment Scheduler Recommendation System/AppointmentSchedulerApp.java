package appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class AppointmentSchedulerApp {

	public static void main(String args[])
	{
	 Scanner scanner = new Scanner(System.in);
     AppointmentScheduler scheduler = new AppointmentScheduler();

     System.out.println("--------Welcome to the Appointment Scheduler!---------");

     while (true) {
         System.out.println("\nMenu:");
         System.out.println("1. Register");
         System.out.println("2. Login");
         System.out.println("3. Exit");
         System.out.print("Choose an option: ");
         int choice = scanner.nextInt();
         scanner.nextLine(); // Consume newline

         switch (choice) {
             case 1:
                 System.out.print("Enter your username: ");
                 String regUsername = scanner.nextLine();
                 System.out.print("Enter your password: ");
                 String regPassword = scanner.nextLine();
                 System.out.print("Enter your first name: ");
                 String firstName = scanner.nextLine();
                 System.out.print("Enter your last name: ");
                 String lastName = scanner.nextLine();
                 System.out.print("Enter your email: ");
                 String email = scanner.nextLine();
                 System.out.print("Enter your phone number: ");
                 String phoneNumber = scanner.nextLine();

                 User newUser = new User(regUsername, regPassword, firstName, lastName, email, phoneNumber);
                 scheduler.addUser(newUser);
                 System.out.println("Registration successful!");
                 break;

             case 2:
                 System.out.print("Enter your username: ");
                 String username = scanner.nextLine();
                 System.out.print("Enter your password: ");
                 String password = scanner.nextLine();

                 if (scheduler.isValidUser(username, password)) {
                     User loggedInUser = new User(username, password, "", "", "", ""); // Temporary user for comparison
                     System.out.println("Login successful!");
                     while (true) {
                         System.out.println("\nUser Menu:");
                         System.out.println("1. Add Appointment");
                         System.out.println("2. View Appointments");
                         System.out.println("3. Logout");
                         System.out.print("Choose an option: ");
                         int userChoice = scanner.nextInt();
                         scanner.nextLine(); // Consume newline

                         switch (userChoice) {
                             case 1:
                                 System.out.print("Enter appointment start year: ");
                                 int year = scanner.nextInt();
                                 System.out.print("Enter appointment start month: ");
                                 int month = scanner.nextInt();
                                 System.out.print("Enter appointment start day: ");
                                 int day = scanner.nextInt();
                                 System.out.print("Enter appointment start hour: ");
                                 int hour = scanner.nextInt();
                                 System.out.print("Enter appointment start minute: ");
                                 int minute = scanner.nextInt();
                                 scanner.nextLine(); // Consume newline

                                 LocalDateTime startTime = LocalDateTime.of(year, month, day, hour, minute);
                                 LocalDateTime endTime = startTime.plusHours(1);

                                 System.out.print("Enter appointment description: ");
                                 String description = scanner.nextLine();

                                 scheduler.addAppointment(startTime, endTime, description, loggedInUser);
                                 System.out.println("Appointment added!");
                                 break;

                             case 2:
                                 System.out.println("Your Appointments:");
                                 for (Appointment appointment : scheduler.getAppointmentsByUser(loggedInUser)) {
                                     System.out.println(appointment);
                                 }
                                 break;

                             case 3:
                                 System.out.println("Logging out...");
                                 break;

                             default:
                                 System.out.println("Invalid option!");
                                 break;
                         }

                         if (userChoice == 3) {
                             break;
                         }
                     }
                 } else {
                     System.out.println("Invalid username or password.");
                 }
                 break;

             case 3:
                 System.out.println("Exiting...");
                 scanner.close();
                 System.exit(0);
                 break;

             default:
                 System.out.println("Invalid option!");
                 break;
         }
     }
	    }
}
