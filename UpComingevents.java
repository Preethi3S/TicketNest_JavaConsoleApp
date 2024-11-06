import java.sql.*;

public class UpComingevents {
    public void displayEvents() {
        System.out.println("\n\n\t\t\t\t Hurrah !! These are the Upcoming Events :) \n\n");
        String query = "select * from admin";

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = DbConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            int count = 0;

            while (rs.next()) {
                System.out.println("\t\t\tEvent Number : " + (++count) + "\n");
                System.out.println(" Event ID : " + rs.getInt(1));
                System.out.println(" Event Name : " + rs.getString(2));
                System.out.println(" Event Location : " + rs.getString(3));
                System.out.println(" Event Capacity : " + rs.getInt(4));
                System.out.println(" Event Date : " + rs.getDate(5));
                System.out.println(" Event Ticket cost Per Person : " + rs.getInt(6));
                System.out.println("--------------------------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching upcoming events: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}
