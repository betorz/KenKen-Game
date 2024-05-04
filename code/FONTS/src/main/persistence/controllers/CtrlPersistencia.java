package main.persistence.controllers;

import main.domain.classes.Board;
import main.domain.classes.User;
import java.util.*;


/**
 * The `CtrlPersistencia` class is responsible for managing the persistence of boards and users.
 * It provides methods to add, remove, and retrieve boards, as well as add, retrieve, and delete users.
 */
public class CtrlPersistencia {

    private CtrlBoardStorage ctrlBoardStorage;
    private CtrlUsersStorage ctrlUsersStorage;

    public CtrlPersistencia() {
        ctrlBoardStorage = new CtrlBoardStorage();
        ctrlUsersStorage = new CtrlUsersStorage();
    }


    /////////////////////////////BOARD STORAGE////////////////////
    /**
     * Adds a board to the controller's board storage.
     *
     * @param b The board to be added.
     */
    public void addBoard(Board b) {
        ctrlBoardStorage.addBoard(b);
    }

    /**
     * Removes the last board from the storage.
     */
    public void removeLastBoard() {
        ctrlBoardStorage.removeLastBoard();
    }

    /**
     * Returns the number of boards in the system.
     *
     * @return the number of boards
     */
    public int getNumBoards() {
        return ctrlBoardStorage.getNumBoards();
    }

    /**
     * Retrieves the Board object at the specified index.
     *
     * @param i the index of the Board object to retrieve
     * @return the Board object at the specified index
     */
    public Board getBoard(int i) {
        return ctrlBoardStorage.getBoard(i);
    }

    public List<Board> getBoardList() {
        return ctrlBoardStorage.getBoardList();
    }
    //////////////////////////////////////////////////////////////

    //////////////////////////USER STORAGE////////////////////////
    /**
     * Adds a user to the storage.
     *
     * @param user the user to be added
     */
    public void addUser(User user) {
        ctrlUsersStorage.addUser(user);
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username the username of the user to retrieve
     * @return the User object corresponding to the given username, or null if no user is found
     */
    public User getUser(String username) {
        return ctrlUsersStorage.getUser(username);
    }

    /**
     * Checks if a user with the given username exists.
     *
     * @param username the username to check
     * @return true if the user exists, false otherwise
     */
    public boolean existsUser(String username) {
        return ctrlUsersStorage.existsUser(username);
    }

    /**
     * Deletes a user with the specified username.
     *
     * @param username the username of the user to be deleted
     */
    public void deleteUser(String username) {
        ctrlUsersStorage.deleteUser(username);
    }
}
