package main.domain.controllers;

import main.domain.classes.Board;
import main.domain.classes.Pair;
import main.domain.classes.exceptions.ExceptionBoard;

import java.io.FileNotFoundException; 
import java.io.File;
import java.util.*;

public class CtrlBoard {
    private Board currentBoard = null;


    /**
     * Sets the current board to the specified board.
     * 
     * @param board the board to set as the current board
     */
    public void setCurrentBoard(Board board) {
        currentBoard = board;
    }

    /**
     * Creates a new board based on the given board information.
     * 
     * @param boardInfo the list of integers containing the board information
     */
    public Board makeBoard(List<Integer> boardInfo) {
        int size = boardInfo.get(0);
        Board board = new Board(size);
        currentBoard = board;
        int numRegions = boardInfo.get(1);
        int p = 2;
        for (int i = 0; i < numRegions; ++i) {
            int op = boardInfo.get(p);
            int result = boardInfo.get(p+1);
            int numCells = boardInfo.get(p+2);
            p += 3;
            List<Pair<Integer, Integer>> cells = new ArrayList<>();

            for (int j = 0; j < numCells; ++j) {
                int r = boardInfo.get(p);
                int c = boardInfo.get(p+1);
                p += 2;
                cells.add(new Pair<>(r, c));
                
                if  (p < boardInfo.size() && boardInfo.get(p) == -1) {
                    ++p;
                    if (p < boardInfo.size()) modifyCellValue(boardInfo.get(p), r, c);      
                    p+=2;
                }
                
            }
            board.makeRegion(op, result, cells);
        }
        board.calculateDifficulty();
        currentBoard = board;
        return board;
        
    }


    /**
     * Retrieves the Board object with the specified board ID.
     *
     * @param iboard The ID of the board to retrieve.
     * @return The Board object with the specified ID, or null if no board is found.
     */

    /**
     * Represents a game board.
     */
    public Board duplicateBoard(Board board) {
        Board duplicatedboard = board;
        return duplicatedboard.copy();
    }

    /**
     * Returns the matrix of cell values in the current board.
     *
     * @return the matrix of cell values
     */
    public int[][] getCellValuesMatrix() {
        return currentBoard.getCellValuesMatrix();
    }

    /**
     * Retrieves the cell region IDs matrix for a given board.
     *
     * @param iboard the ID of the board
     * @return the cell region IDs matrix
     */
    public int[][] getCellRegIdsMatrix(Board board) {
        return board.getCellRegIdsMatrix();
    }

    /**
     * Retrieves the information about the regions in a board.
     *
     * @param iboard the identifier of the board
     * @return a list of pairs, where each pair contains the operation and result of a region
     */
    public List<Pair<Character, Integer>> getRegionsInfo(Board board) {
        int numRegions = board.getNumRegions();
        List<Pair<Character, Integer>> pairs = new ArrayList<>();
        for (int i = 0; i < numRegions; ++i) {
            char op = board.getRegionOp(i);
            int result = board.getRegionResult(i);
            pairs.add(new Pair<>(op, result));
        }
        return pairs;
    }
    
    /**
     * Solves the current board.
     */
    public void solveBoard() {
        currentBoard.solveBoard();
    }


    /**
     * Returns the ID of the current board.
     *
     * @return the ID of the current board
     */
    public int getCurrentBoardId() {
        return currentBoard.getId();
    }

    /**
     * Checks if the specified board has a solution.
     *
     * @param iboard the index of the board to check
     * @return true if the board has a solution, false otherwise
     */
    public boolean hasSolution(Board board) {
        Board copy = duplicateBoard(board);
        return copy.solveBoard();
    }

    public void modifyCellValue(int value, int r, int c) {
        currentBoard.modifyCellValue(value, r, c);
    }
    
    public void showBoardList(List<Board> boardList) {
        for (Board b : boardList) {
            String kenkenSize = null;
            switch (b.getSize()) {
                case 3:
                    kenkenSize = "3x3";
                    break;
                case 4:
                    kenkenSize = "4x4";
                    break;
                case 5:
                    kenkenSize = "5x5";
                    break;
                case 6:
                    kenkenSize = "6x6";
                    break;
                case 7:
                    kenkenSize = "7x7";
                    break;
                case 8:
                    kenkenSize = "8x8";
                    break;
                case 9:
                    kenkenSize = "9x9";
                    break;
            }
            String kenkenDifficulty = null;
            switch (b.getDifficulty()) {
                case 1:
                    kenkenDifficulty = "EASY";
                    break;
                case 2:
                    kenkenDifficulty = "MEDIUM";
                    break;
                case 3:
                    kenkenDifficulty = "HARD";
                    break;
                case 4:
                    kenkenDifficulty = "EXPERT";
                    break;
            }
            System.out.println(b.getId() + " " + kenkenSize + " " + kenkenDifficulty);
        }
    }

    public List<List<Integer>> createBoards(File file) throws ExceptionBoard {

        List<List<Integer>> allBoards = new ArrayList<>();
        try {
            Scanner sf = new Scanner(file);
            while (sf.hasNextInt()) {
                List<Integer> boardInfo = new ArrayList<>();
                int size = sf.nextInt();

                if (size > 9 || size < 3) {
                    throw new ExceptionBoard("No correct size");
                }

                boardInfo.add(size);
                int numRegions = sf.nextInt();

                if (numRegions < 1 || numRegions > size*size) {
                    throw new ExceptionBoard("No correct number of regions");
                }

                boardInfo.add(numRegions);
                for (int i = 0; i < numRegions; ++i) {
                    int op = sf.nextInt();
                    boardInfo.add(op);
                    int result = sf.nextInt();
                    boardInfo.add(result);
                    int numCells = sf.nextInt();
                    boardInfo.add(numCells);
                    for (int j = 0; j < numCells; ++j) {
                        int r = sf.nextInt();
                        int c = sf.nextInt();
                        boardInfo.add(r - 1);
                        boardInfo.add(c - 1);
                        if (sf.hasNext()) {
                            sf.useDelimiter("");
                            sf.next();
                            if (sf.hasNext("\\[")) {
                                sf.next();
                                int value = sf.nextInt();
                                boardInfo.add(-1);
                                boardInfo.add(value);
                                boardInfo.add(-1);
                                sf.next();
                                
                            }
                            sf.useDelimiter("\\s+");
                        }
                    }
                }
                allBoards.add(boardInfo);
                
            }
            sf.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error al abrir el archivo de los tableros predeterminados"); ////exception board
        }
        return allBoards;
    }

    public int getIdBoard(Board b) {
        return b.getId();
    }

}
