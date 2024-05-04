package main.persistence.controllers;

import main.domain.classes.Board;
import java.util.ArrayList;
import java.util.List;

/**
 * The 'CtrlBoardStorage' class represents a controller for managing a collection of boards.
 * It provides methods to add and remove boards from the collection, as well as retrieve them.
 */
public class CtrlBoardStorage {
    private List<Board> boards = new ArrayList<>();

    /**
     * Adds a board to the collection.
     *
     * @param b The board to be added.
     */
    public void addBoard(Board b) {
        boards.add(b);
    }

    /**
     * Removes the last board from the collection and decreases the next ID.
     */
    public void removeLastBoard() {
        int i = boards.size() - 1;
        boards.get(i).decreaseNextId();
        boards.remove(i);
    }

    /**
     * Returns the number of boards in the collection.
     *
     * @return The number of boards.
     */
    public int getNumBoards() {
        return boards.size();
    }

    /**
     * Returns the board at the specified index in the collection.
     *
     * @param i The index of the board to retrieve.
     * @return The board at the specified index.
     */
    public Board getBoard(int i) {
        return boards.get(i);
    }

    public List<Board> getBoardList() {
        return boards;
    }
}