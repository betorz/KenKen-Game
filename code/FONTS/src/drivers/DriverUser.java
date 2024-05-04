package drivers;

import java.util.Scanner;

import main.domain.classes.exceptions.ExceptionUser;
import main.domain.controllers.CtrlDomini;

/**
 * The DriverUser class represents a driver program for user-related operations.
 * It provides methods for creating a user, logging in, changing password, logging out, and deleting a user.
 */
public class DriverUser {

    private CtrlDomini ctrlDomain = new CtrlDomini();
    private Scanner scanner;

    /**
     * Creates a new user by taking input from the user and calling the register method of CtrlDomini.
     */
    public void createUser() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        System.out.println("Enter password again:");
        String password2 = scanner.nextLine();
        try {
            ctrlDomain.createUser(username, password, password2);
            System.out.println("\nUser created successfully!");
        } catch (ExceptionUser e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Logs in a user by taking input from the user and calling the login method of CtrlDomini.
     */
    public void login() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        try {
            ctrlDomain.login(username, password);
            System.out.println("\nUser logged in successfully!"); 
        } catch (ExceptionUser e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Changes the password of a user by taking input from the user and calling the changePassword method of CtrlDomini.
     */
    public void changePassword() {
        System.out.println("Enter current password:");
        String password = scanner.nextLine();
        System.out.println("Enter new password:");
        String newPassword = scanner.nextLine();
        System.out.println("Enter new password again:");
        String newPassword2 = scanner.nextLine();
        try {
            ctrlDomain.changePassword(password, newPassword, newPassword2);
        } catch (ExceptionUser e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Logs out the current user by calling the logout method of CtrlDomini.
     */
    public void logout() {
        try {
            ctrlDomain.logout();
        } catch (ExceptionUser e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes a user by taking input from the user and calling the deleteUser method of CtrlDomini.
     */
    public void deleteUser() {
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        try {
            ctrlDomain.deleteUser(password);
            System.out.println("\nUser deleted successfully!");
        } catch (ExceptionUser e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * The main method of the DriverUser class.
     * It creates an instance of DriverUser, displays the available methods, and takes input from the user to perform the corresponding operation.
     */
    public static void main(String[] args) {
        DriverUser driverUser = new DriverUser();
        showMethods(); //funcio privada
        
        driverUser.scanner = new Scanner(System.in);
        String input = driverUser.scanner.nextLine();


        while(!input.equals("0") && !input.equals("Exit")) {

            switch (input) {
                
                case "1":
                case "Register":
                case "CreateUser":
                    driverUser.createUser();
                    break;

                case "2":
                case "Login":
                    driverUser.login();
                    break;
                
                case "3":
                case "ChangePassword":
                    driverUser.changePassword();
                    break;

                case "4":
                case "Logout":
                    driverUser.logout();
                    break;

                case "5":
                case "DeleteUser":
                    driverUser.deleteUser();
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
            System.out.println("--------------------------------------------------------------");
            System.out.println("Press ENTER to continue");
            input = driverUser.scanner.nextLine();
            showMethods();
            input = driverUser.scanner.nextLine();
        }
        driverUser.scanner.close();
    }

    /**
     * Displays the available methods to the user.
     */
    private static void showMethods() {
        System.out.println("(0|Exit) - Exit");
        System.out.println("(1|Register/CreateUser) - Create a user");
        System.out.println("(2|Login) - Login");
        System.out.println("(3|ChangePassword) - Change password");
        System.out.println("(4|Logout) - Logout");
        System.out.println("(5|DeleteUser) - Delete user");
    }
}