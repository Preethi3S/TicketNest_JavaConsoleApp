import java.util.Scanner;
import java.sql.*;

public class UserHandling {
    Scanner sc = new Scanner(System.in);

    public void CreateAccount() throws SQLException {
        System.out.println("-------------------------------------------------------------------------------------------------------------");

        System.out.println("Let's Create an account for you");

        System.out.print("Enter Unique Userid : ");
        String userId = sc.nextLine();

        System.out.print("\nEnter UserName : ");
        String userName = sc.nextLine();

        System.out.print("\nEnter Strong Password : ");
        String password = sc.nextLine();

        System.out.print("\nEnter User Email id : ");
        String email = sc.nextLine();

        System.out.print("\nEnter User Mobile No : ");
        String phone = sc.nextLine();

        System.out.println(" \nCreating Your ACCOUNT \n");

        try {
            String query = "insert into user values(?,?,?,?,?)";
            Connection con = DbConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, userId);
            pst.setString(2, userName);
            pst.setString(3, password);
            pst.setString(4, phone);
            pst.setString(5, email);

            pst.executeUpdate();

            System.out.println("-------------------------------------------------------------------------------------------------------------");
            System.out.println("Your Account has been created successfully \n");
            System.out.println("-------------------------------------------------------------------------------------------------------------");
            con.close();
        } catch (SQLException e) {
            System.err.println("Error while creating the account: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    public void LoginUser() throws SQLException {
        System.out.println("-------------------------------------------------------------------------------------------------------------");

        System.out.print("Enter User Id : ");
        String userid = sc.nextLine();

        System.out.print("Enter Password : ");
        String password = sc.nextLine();

        try {
            String query = "select userid from user where userid = ? and userpassword = ?";

            Connection con = DbConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, userid);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                System.out.println("Login Successful");
                UpComingevents ucp = new UpComingevents();
                ucp.displayEvents();
                UserEvents ue = new UserEvents(userid);
            } else {
                System.out.println("Invalid id or password");
            }

            con.close();
        } catch (SQLException e) {
            System.err.println("Error during login: " + e.getMessage());
        }

        System.out.println("-------------------------------------------------------------------------------------------------------------");
    }
}
