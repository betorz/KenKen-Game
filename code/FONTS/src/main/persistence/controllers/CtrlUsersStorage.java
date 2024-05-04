package main.persistence.controllers;

import main.domain.classes.User;
import java.util.HashMap;

/**
 * The `CtrlUsersStorage` class represents a storage mechanism for managing user data.
 * It provides methods to add, retrieve, check existence, and delete user objects.
 */
public class CtrlUsersStorage {
    private HashMap<String, User> users = new HashMap<>();

    /**
     * Adds a user to the storage.
     *
     * @param user The user object to be added.
     */
    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    /**
     * Retrieves a user from the storage based on the username.
     *
     * @param username The username of the user to retrieve.
     * @return The user object associated with the given username, or null if not found.
     */
    public User getUser(String username) {
        return users.get(username);
    }

    /**
     * Checks if a user with the given username exists in the storage.
     *
     * @param username The username to check.
     * @return true if a user with the given username exists, false otherwise.
     */
    public boolean existsUser(String username) {
        return users.containsKey(username);
    }

    /**
     * Deletes a user from the storage based on the username.
     *
     * @param username The username of the user to delete.
     */
    public void deleteUser(String username) {
        users.remove(username);
    }
}