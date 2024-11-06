import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EventsHandling {

    public void addEvents() throws SQLException {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("-------------------------------------------------------------------------------------------------------------");

            System.out.print("\nEnter Event ID (0-9): ");
            int eventId = sc.nextInt();
            sc.nextLine();

            System.out.print("\nEnter Event Name: ");
            String eventName = sc.nextLine();

            System.out.print("\nEnter Event Location: ");
            String eventLocation = sc.nextLine();

            System.out.print("\nEnter Event Ticket Capacity: ");
            int capacity = sc.nextInt();
            sc.nextLine();

            System.out.print("\nEnter Event Ticket Cost: ");
            int ticketCost = sc.nextInt();
            sc.nextLine();

            System.out.print("\nEnter Event Date (yyyy-MM-dd): ");
            String eventDateInput = sc.nextLine();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);

            Date eventHappenDate = null;

            try {
                eventHappenDate = dateFormat.parse(eventDateInput);
                System.out.println("Confirming the Event happening date: " + eventHappenDate);
            } catch (Exception e) {
                System.out.println("Invalid Event date Input. Please enter the date in the format yyyy-MM-dd.");
                return;
            }

            try {
                String query = "INSERT INTO admin (EventID, EventName, EventLocation, Capacity, EventDate, TicketCost) VALUES (?, ?, ?, ?, ?, ?)";
                Connection con = DbConnection.getConnection();
                PreparedStatement pst = con.prepareStatement(query);

                pst.setInt(1, eventId);
                pst.setString(2, eventName);
                pst.setString(3, eventLocation);
                pst.setInt(4, capacity);
                java.sql.Date sqlEventDate = new java.sql.Date(eventHappenDate.getTime());
                pst.setDate(5, sqlEventDate);
                pst.setInt(6, ticketCost);

                pst.executeUpdate();
                System.out.println("Event Added Successfully");

                con.close();
            } catch (SQLException e) {
                System.err.println("A database error occurred while adding the event: " + e.getMessage());
            }

        } catch (InputMismatchException e) {
            System.err.println("Invalid input. Please enter valid numeric values where required.");
            sc.nextLine();
        } finally {
            sc.close();
            System.out.println("-------------------------------------------------------------------------------------------------------------");
        }
    }


    public void updateEvents() throws SQLException{
        System.out.println("In progresss");
    }

    public void deleteEvents() throws SQLException {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("-------------------------------------------------------------------------------------------------------------");
            System.out.println("Enter an Event ID to Delete");
            int eventId = sc.nextInt();

            try {
                String query = "DELETE FROM admin WHERE EventID = " + eventId;
                Connection con = DbConnection.getConnection();
                Statement st = con.createStatement();
                st.executeUpdate(query);

                System.out.println("Event Deleted Successfully !!!!");

                con.close();
            } catch (SQLException e) {
                System.err.println("A database error occurred while deleting the event: " + e.getMessage());
            }

        } catch (InputMismatchException e) {
            System.err.println("Invalid input. Please enter a numeric Event ID.");
            sc.nextLine(); // Clear invalid input
        } finally {
            sc.close();
            System.out.println("-------------------------------------------------------------------------------------------------------------");
        }
    }
}
