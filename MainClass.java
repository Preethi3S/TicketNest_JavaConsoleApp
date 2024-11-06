import java.sql.SQLException; 
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        System.out.println("\n\n\t..............Welcome to TicketNest.................\n\n");
        System.out.println("Welcome to TicketNest , your go-to platform for effortlessly booking tickets to the best events!");

        Scanner sc = new Scanner(System.in);

        try {


            System.out.println("\nDid you Want Login as Admin? Enter 1 ");
            System.out.println("Did you want to Login/Register as User? Enter 2 ");

            System.out.print("\nEnter You Choice : ");
            int choice = sc.nextInt();
            sc.nextLine();

            System.out.println("-------------------------------------------------------------------------------------------------------------");

            if (choice == 1) {
                System.out.print("\nEnter Admin Name: ");
                String ADname = sc.nextLine();

                System.out.print("\nEnter Admin Password: ");
                String ADpassword = sc.nextLine();

                AdminClass ac = new AdminClass(ADname, ADpassword);
                
            } else if (choice == 2) {
                System.out.println("\nEnter 1 to Login");
                System.out.println("Enter 2 to Register");

                System.out.print("Enter Your Choice : ");
                int uschoice = sc.nextInt();
                sc.nextLine();

                UserHandling uh = new UserHandling();

                if (uschoice == 1) {
                    uh.LoginUser();
                } else if (uschoice == 2) {
                    uh.CreateAccount();
                    System.out.println("Login with the created account to access our Ticket Reservation System...");
                    System.out.println("\n Thank you for using our console... :) ");
                } else {
                    System.out.println("Hey  !!   It is an Invalid Choice .....");
                }
            } else {
                System.out.println("Hey  !!   It is an Invalid Choice .....");
            }
        } catch (SQLException e) {
            System.err.println("A database error occurred: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Invalid input. Please enter numbers for choices.");
            sc.nextLine();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
