import java.sql.*;
import java.util.Scanner;

public class UserEvents {

    public String UserId;

    public UserEvents(String UserID) throws SQLException {
        UpComingevents ucps = new UpComingevents();
        this.UserId = UserID;
        int bookChoice;
        do {
            System.out.println("-------------------------------------------------------------------------------------------------------------");
            System.out.println("Enter 1 to book an Event \nEnter 2 to delete a booked Event \nEnter 3 to see the History of your booked Events \nEnter 4 to see Events Available \nEnter 5 to exit");

            Scanner scan = new Scanner(System.in);
            System.out.print("Enter your choice : ");
            bookChoice = scan.nextInt();

            if (bookChoice == 1) {
                ucps.displayEvents();
                try {
                    bookEvent();
                } catch (SQLException e) {
                    System.err.println("Error while booking the event: " + e.getMessage());
                }
            } else if (bookChoice == 2) {
                try {
                    DeleteEvent();
                } catch (SQLException e) {
                    System.err.println("Error while deleting the booked event: " + e.getMessage());
                }
            } else if (bookChoice == 3) {
                try {
                    ViewHistoryBooking();
                } catch (SQLException e) {
                    System.err.println("Error while viewing booking history: " + e.getMessage());
                }
            } else if (bookChoice == 4) {
                ucps.displayEvents();
            } else if (bookChoice == 5) {
                System.out.println("\n Thank you for using our console... :) ");
            } else {
                System.out.println("You have entered a wrong choice. Please try again.");
            }
        } while (bookChoice != 5);
    }

    public void bookEvent() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------------------------------------------");

        try {
            System.out.print("Enter an Event ID to Book Ticket : ");
            int Evid = sc.nextInt();

            if (isTicketAvailable(Evid)) {
                String query = "INSERT INTO bookedEvent VALUES(?, ?, ?)";
                String query2 = "SELECT eventname FROM admin WHERE eventid = ?";
                Connection con = DbConnection.getConnection();
                
                PreparedStatement pst2 = con.prepareStatement(query2);
                pst2.setInt(1, Evid);
                ResultSet rs = pst2.executeQuery();

                if (rs.next()) {
                    String EventName = rs.getString(1);
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1, UserId);
                    pst.setString(2, EventName);
                    pst.setInt(3, Evid);
                    pst.executeUpdate();
                    System.out.println(" $$$$ Transaction Succeed....");
                    System.out.println("\nYour Ticket has been booked Successfully");
                } else {
                    System.out.println("Event not found.");
                }

                con.close();
            } else {
                System.out.println("\nOOPS !!! Ticket is Over.....");
            }

        } catch (SQLException e) {
            System.err.println("Database error during booking: " + e.getMessage());
        } finally {
            System.out.println("-------------------------------------------------------------------------------------------------------------");
        }
    }

    public boolean isTicketAvailable(int EventID) throws SQLException {
        boolean isAvailable = false;
        try {
            String query = "SELECT COUNT(userid) FROM bookedEvent WHERE eventid = " + EventID;
            String query2 = "SELECT capacity FROM admin WHERE eventid = " + EventID;
            Connection conn = DbConnection.getConnection();

            Statement st = conn.createStatement();
            Statement st2 = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            ResultSet rs2 = st2.executeQuery(query2);

            if (rs.next() && rs2.next()) {
                int booked = rs.getInt(1);
                int capacity = rs2.getInt(1);
                isAvailable = booked < capacity;
            } else {
                System.out.println("Event data not found.");
            }

            conn.close();
        } catch (SQLException e) {
            System.err.println("Database error during ticket availability check: " + e.getMessage());
        }
        return isAvailable;
    }

    public void DeleteEvent() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------------------------------------------");

        try {
            System.out.print("Enter an Event ID to delete the booked Ticket: ");
            int Evid = sc.nextInt();

            String query = "DELETE FROM bookedEvent WHERE userid = ? AND eventid = ?";
            Connection con = DbConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, UserId);
            pst.setInt(2, Evid);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Your Ticket for the Event has been removed Successfully. You will get your money back now.");
            } else {
                System.out.println("No booking found for this event ID.");
            }

            con.close();
        } catch (SQLException e) {
            System.err.println("Database error during event deletion: " + e.getMessage());
        } finally {
            System.out.println("-------------------------------------------------------------------------------------------------------------");
        }
    }

    public void ViewHistoryBooking() throws SQLException {
        System.out.println("-------------------------------------------------------------------------------------------------------------");

        try {
            System.out.println("Your Booking History:");
            String query = "SELECT * FROM bookedEvent WHERE userid = ?";
            Connection con = DbConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, UserId);
            ResultSet rs = pst.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("No booking history found.");
            } else {
                while (rs.next()) {
                    System.out.println("User Id: " + rs.getString(1) + " | Event Name: " + rs.getString(2) + " | Event ID: " + rs.getInt(3));
                }
            }

            con.close();
        } catch (SQLException e) {
            System.err.println("Database error while viewing booking history: " + e.getMessage());
        } finally {
            System.out.println("-------------------------------------------------------------------------------------------------------------");
        }
    }
}
