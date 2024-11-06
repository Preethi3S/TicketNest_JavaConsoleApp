import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminClass {
    private final String AdminName = "Preethi";
    private final String AdminPassword = "02072006";

    public AdminClass(String Adname, String adPass) {
        try {
            if (AdminName.equals(Adname) && AdminPassword.equals(adPass)) {
                System.out.println("-------------------------------------------------------------------------------------------------------------");
                System.out.println("Welcome, Admin!");
                AdminOptions();
            } else {
                System.out.println("OOPS!!! Admin Name or Admin Password is Invalid");
            }
        } catch (SQLException e) {
            System.err.println("A database error occurred: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public void AdminOptions() throws SQLException {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("To ADD an Event Enter 1");
            System.out.println("To UPDATE an Event Enter 2");
            System.out.println("To DELETE an Event Enter 3");

            System.out.print("Admin Choice: ");
            int adchoice = sc.nextInt();
            sc.nextLine();

            EventsHandling eh = new EventsHandling();

            switch (adchoice) {
                case 1:
                    System.out.println("Adding Event...");
                    eh.addEvents();
                    break;
                case 2:
                    System.out.println("Updating Event...");
                    eh.updateEvents();
                    break;
                case 3:
                    System.out.println("Deleting Event...");
                    eh.deleteEvents();
                    break;
                default:
                    System.out.println("Enter a valid option.");
                    break;
            }
        } catch (InputMismatchException e) {
            System.err.println("Invalid input. Please enter a number for the choice.");
            sc.nextLine(); // Clear invalid input
        } catch (SQLException e) {
            System.err.println("A database error occurred: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
