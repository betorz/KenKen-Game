package main.domain.controllers;

import main.domain.classes.User;
import main.domain.classes.exceptions.ExceptionUser;


public class CtrlUser {

    private User currentUser = null;

    
    /**
     * Represents a user in the system.
     * The user is added to the list of users.
     * 
     * @param username The username of the user.
     * @param password The password of the user.
     * @param password2 The confirmation of the password.
     * @throws ExceptionUser 
     */
    public User createUser(String username, String password, String password2) throws ExceptionUser {
            User user = new User(username, password);
            return user;
    }

    /**
     * Logs in a user with the given username and password.
     * 
     * @param username The username of the user.
     * @param password The password of the user.
     * @throws ExceptionUser
     */
    public void login(String username, String password, User user) throws ExceptionUser {
            if (currentUser != null) {
                throw new ExceptionUser("User already logged in");
            }
            currentUser = user;
    }

    /**
     * Changes the password of the current user.
     *
     * @param password     The current password of the user.
     * @param newPassword  The new password to set.
     * @param newPassword2 The confirmation of the new password.
     */
    public void changePassword(String password, String newPassword, String newPassword2) throws ExceptionUser {
            if (currentUser == null) {
                throw new ExceptionUser("There is not a user logged in");
            }
            if (!currentUser.getPassword().equals(password)) {
                throw new ExceptionUser("Incorrect password");
            }
            currentUser.setPassword(newPassword);
            System.out.println("\nPassword changed successfully!");
    }

    /**
     * Logs out the current user.
     * If no user is currently logged in, this method does nothing.
     * @throws ExceptionUser 
     */
    public void logout() throws ExceptionUser {
            if (currentUser == null) {
                throw new ExceptionUser("There is not a user logged in");
            }
            currentUser = null;
            System.out.println("\nUser logged out successfully!");

    }

    /**
    * Deletes a user from the system.
    *
    * @param password the username of the user to be deleted
    * @return the deleted user object, or null if the user does not exist
     * @throws ExceptionUser 
    */
    public String deleteUser(String password, User user) throws ExceptionUser {
            if (currentUser == null) {
                throw new ExceptionUser("There is not a user logged in");
            }

            if (!currentUser.getPassword().equals(password)) {
                throw new ExceptionUser("Incorrect password");
            }
            user = currentUser;
            currentUser = null;
            return user.getUsername();
    }

    /**
     * Returns the current user.
     * 
     * @return The current user.
     */
    public User getCurrentUser() {
        return currentUser;
    }
}