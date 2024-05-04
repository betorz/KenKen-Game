package main.domain.controllers;

import main.domain.classes.*;
import main.domain.classes.exceptions.*;
import main.persistence.controllers.CtrlPersistencia;

import java.io.File;
import java.util.*;

/**
 * The `CtrlDomini` class is responsible for controlling the domain logic of the application.
 * It manages the interaction between the user interface and the underlying data and business logic.
 * This class provides methods for managing boards, games, users, and rankings.
 */
public class CtrlDomini {

    private CtrlPersistencia ctrlPersistencia;
    private CtrlBoard ctrlBoard;
    private CtrlGame ctrlGame;
    private CtrlUser ctrlUser;
    private CtrlRanking ctrlRanking;

    public CtrlDomini() {
        ctrlPersistencia = new CtrlPersistencia();
        ctrlBoard = new CtrlBoard();
        ctrlGame = new CtrlGame();
        ctrlUser = new CtrlUser();
        ctrlRanking = new CtrlRanking();
    }

    /////////////////////////////////
    //Per poder fer la inicialització dels users en els drivers
    public CtrlUser getCtrlUser() {
        return ctrlUser;
    }
    public CtrlRanking getCtrlRanking() {
        return ctrlRanking;
    }
    /////////////////////////////////

    /////////////////////////////////BOARD FUNCTIONS/////////////////////////////////////
     /**
     * Removes the last board from the controller.
     * This method delegates the remove last board operation to the `ctrlBoard` object.
     */
    public void removeLastBoard() {
        ctrlPersistencia.removeLastBoard();
    }

    /**
     * Checks if the specified board has a solution.
     * This method delegates the has solution operation to the `ctrlBoard` object.
     *
     * @param iboard the index of the board to check
     * @return true if the board has a solution, false otherwise
     */
    public boolean hasSolution(int iboard) {
        return ctrlBoard.hasSolution(ctrlPersistencia.getBoard(iboard));
    }

    public void showBoardList() {
        ctrlBoard.showBoardList( ctrlPersistencia.getBoardList());
    }
    


    public void createPredBoards(File file) throws ExceptionBoard {
        List<List<Integer>> allBoards = ctrlBoard.createBoards(file);

        for (List<Integer> boardInfo : allBoards) {
            ctrlPersistencia.addBoard(ctrlBoard.makeBoard(boardInfo));
        }
    }

    public void printBoardValues() {

        int[][] values = ctrlBoard.getCellValuesMatrix();
        int boardId = ctrlBoard.getCurrentBoardId();
        int size = ctrlPersistencia.getBoard(boardId).getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(values[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printBoardConfig() {
        int id = ctrlBoard.getCurrentBoardId();
        int[][] regIds = ctrlBoard.getCellRegIdsMatrix(ctrlPersistencia.getBoard(id));
        int size = ctrlPersistencia.getBoard(id).getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(regIds[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        List<Pair<Character, Integer>> regionsInfo = ctrlBoard.getRegionsInfo(ctrlPersistencia.getBoard(id));
        for (int i = 0; i < regionsInfo.size(); ++i) {
            Pair<Character, Integer> r =regionsInfo.get(i); 
            System.out.println(i + ": " + r.getX() + r.getY());
        }
        System.out.println();
    }

    public int createBoardFromTxt(File file) throws ExceptionBoard {

        List<List<Integer>> allBoards = ctrlBoard.createBoards(file); //excepcio si allBoards.size() > 1
        Board board = ctrlBoard.makeBoard(allBoards.get(0));
        ctrlPersistencia.addBoard(board);
        int id = ctrlBoard.getIdBoard(board);
        
        return id;
    }

    public int createBoardFromTerminal(Scanner scanner) throws ExceptionBoard {

        List<Integer> boardInfo = new ArrayList<>();
        System.out.println("Introdueix la mida");
        int size = scanner.nextInt();

        if (size > 9 || size < 3) {
            throw new ExceptionBoard("No correct size");
        }
        boardInfo.add(size);
        System.out.println("Introdueix el numero de regions");
        int numRegions = scanner.nextInt();
        if (numRegions < 1 || numRegions > size*size) {
            throw new ExceptionBoard("No correct number of regions");
        }
        boardInfo.add(numRegions);
        scanner.nextLine();
        Scanner lineScanner;
        for (int i = 0; i < numRegions; ++i) {
            System.out.println("Introdueix la Regio "+ i + " --> *All values must be integers*");
            System.out.println("Format: Operation Result NumCells x11 y11 [value11]  x12 y12 [value12]");
            System.out.println("Operation can be: 0: equals, 1: addition, 2: substraction, " +
                                "3: multiplication, 4: division, 5: module, 6: power");
            String regionInfo = scanner.nextLine(); 
            lineScanner = new Scanner(regionInfo);

            boardInfo.add(lineScanner.nextInt());
            boardInfo.add(lineScanner.nextInt());
            int numCells = lineScanner.nextInt();
            boardInfo.add(numCells);
            
            for (int j = 0; j < numCells; j++) {
                boardInfo.add(lineScanner.nextInt()-1);
                boardInfo.add(lineScanner.nextInt()-1);
                if (lineScanner.hasNext()) {
                    lineScanner.useDelimiter("");
                    lineScanner.next();
                    if (lineScanner.hasNext("\\[")) {
                        lineScanner.next();
                        boardInfo.add(-1);
                        boardInfo.add(lineScanner.nextInt());
                        boardInfo.add(-1);
                        lineScanner.next();
                    }
                    lineScanner.useDelimiter("\\s+");
                }
            }
        }
        Board board = ctrlBoard.makeBoard(boardInfo);
        ctrlPersistencia.addBoard(board);
        int id = ctrlBoard.getIdBoard(board);
        
        return id;
        
    }
    /////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////GAME FUNCTIONS/////////////////////////////////////

    /**
     * Starts a new game on the specified board for the current user.
     * This method delegates the start game operation to the `ctrlGame` object.
     *
     * @param iboard the index of the board to start the game on
     */
    public void startGame(int boardId) {

        User user = ctrlUser.getCurrentUser();
        if (user.getSavedGames().containsKey(boardId)) {     
            ctrlBoard.setCurrentBoard(user.getSavedGames().get(boardId).getBoard());
            ctrlGame.continueGame(user.getSavedGames().get(boardId));
        }
        else {
            Board board = ctrlBoard.duplicateBoard(ctrlPersistencia.getBoard(boardId));
            ctrlBoard.setCurrentBoard(board);
            ctrlGame.startGame(board, user);
        }
    }

    /**
     * Makes a move in the game.
     * This method delegates the move operation to the `ctrlGame` object.
     *
     * @param r The row index of the move.
     * @param c The column index of the move.
     * @param v The value of the move.
     * @return The result of the move.
     */
    public void makeMove(int r, int c, int v) throws ExceptionGame{
        ctrlGame.makeMove(r, c, v);
    }

    public void showSolution() {
        ctrlBoard.solveBoard();
    }
    
    /**
     * Stops the current game being played.
     * This method delegates the stop playing operation to the `ctrlGame` object.
     */
    public void stopPlaying() {
        ctrlGame.stopPlaying();
    }

    public boolean checkSolution() {
        return ctrlGame.checkSolution();
    }

    public void finishGame() {
        ctrlGame.finishGame();
    }

    /**
     * Returns a hint for the current game.
     * This method delegates the hint operation to the `ctrlGame` object.
     *
     * @return true if a hint is available, false otherwise
     */
    public void getHint() {
        ctrlGame.getHint();
    }

    public boolean isFinished() {
        return ctrlGame.isFinished();
    }
    ////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////USER FUNCTIONS/////////////////////////////////////
    /**
     * Creates a new user with the specified username and password.
     * This method delegates the registration operation to the `ctrlUser` object.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @param password2 The confirmation of the password.
     * @throws ExceptionUser 
     */
    public void createUser(String username, String password, String password2) throws ExceptionUser {
        if (username == null || username.isEmpty()) {
            throw new ExceptionUser("Username cannot be empty");
        }

        if (password == null || password.isEmpty()) {
            throw new ExceptionUser("Password cannot be empty");
        }

        if (password2 == null || password2.isEmpty()) {
            throw new ExceptionUser("Password confirmation cannot be empty");
        }

        if (ctrlPersistencia.existsUser(username)) {
            throw new ExceptionUser("Username already exists");
        }

        if (!password.equals(password2)) {
            throw new ExceptionUser("Passwords do not match");
        }

        User currentUser = this.ctrlUser.createUser(username, password, password2);
        //aqui anira afegir current user al ranking 
        Ranking ranking = this.ctrlRanking.getRanking();
        ranking.addUser(currentUser);

        ctrlPersistencia.addUser(currentUser);
    }

    /**
     * Logs in the user with the specified username and password.
     * This method delegates the login operation to the `ctrlUser` object.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @throws ExceptionUser 
     */
    public void login(String username, String password) throws ExceptionUser {
        if (username == null || username.isEmpty()) {
            throw new ExceptionUser("Username cannot be empty");
        }

        if (password == null || password.isEmpty()) {
            throw new ExceptionUser("Password cannot be empty");
        }

        if (!ctrlPersistencia.existsUser(username)) {
            throw new ExceptionUser("User does not exist");
        }
        if (!ctrlPersistencia.getUser(username).getPassword().equals(password)) {
            throw new ExceptionUser("Incorrect password");
        }
        this.ctrlUser.login(username, password, ctrlPersistencia.getUser(username));
    }
        
        

    /**
     * Changes the password of the user.
     * This method delegates the change password operation to the `ctrlUser` object.
     * 
     * @param password     the current password of the user
     * @param newPassword  the new password to set
     * @param newPassword2 the confirmation of the new password
     * @throws ExceptionUser 
     */
    public void changePassword(String password, String newPassword, String newPassword2) throws ExceptionUser {
        if (password == null || password.isEmpty()) {
            throw new ExceptionUser("Password cannot be empty");
        }

        if (newPassword == null || newPassword.isEmpty()) {
            throw new ExceptionUser("New password cannot be empty");
        }

        if (newPassword.equals(password)) {
            throw new ExceptionUser("New password cannot be the same as the current password");
        }

        if (newPassword2 == null || newPassword2.isEmpty()) {
            throw new ExceptionUser("New password confirmation cannot be empty");
        }

        if (!newPassword.equals(newPassword2)) {
            throw new ExceptionUser("New passwords do not match");
        }
        this.ctrlUser.changePassword(password, newPassword, newPassword2);
    }

    /**
     * Logs out the current user.
     * This method delegates the logout operation to the `ctrlUser` object.
     * @throws ExceptionUser 
     */
    public void logout() throws ExceptionUser {
        
        this.ctrlUser.logout();
    }

    /**
     * Deletes a user from the system.
     * This method delegates the delete user operation to the `ctrlUser` object.
     * 
     * @param username the username of the user to be deleted
     * @throws ExceptionUser 
     */
    public void deleteUser(String password) throws ExceptionUser {
        if (password == null || password.isEmpty()) {
            throw new ExceptionUser("Password cannot be empty");
        }
        this.ctrlPersistencia.deleteUser(this.ctrlUser.deleteUser(password, null));
        Ranking ranking = this.ctrlRanking.getRanking();
        ranking.removeUser(this.ctrlUser.getCurrentUser());
        
    }
    /////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////RANKING FUNCTIONS/////////////////////////////////////////
    /**
     * Orders the ranking by points.
     * This method delegates the order ranking by points operation to the `ctrlRanking` object.
     * @throws ExceptionRankingEmpty 
     */
    public void orderRankingByPoints() throws ExceptionRankingEmpty {
        ctrlRanking.orderRankingByPoints();
    }

    /**
     * Orders the ranking by name.
     * This method delegates the order ranking by name operation to the `ctrlRanking` object.
     * @throws ExceptionRankingEmpty 
     */
    public void orderRankingByName() throws ExceptionRankingEmpty { 
        ctrlRanking.orderRankingByName();
    }

    /**
     * Orders the ranking list by time for a given KenKen size and difficulty.
     * This method delegates the order ranking by time operation to the `ctrlRanking` object.
     *
     * @param kenkenSize the size of the KenKen puzzle
     * @param difficulty the difficulty level of the KenKen puzzle
     * @throws ExceptionRankingEmpty 
     */
    public void orderRankingByTime(int kenkenSize, int difficulty) throws ExceptionRankingEmpty { //ordena la llista pel temps 
        ctrlRanking.orderRankingByTime(kenkenSize, difficulty);
    }

    /**
     * Orders the ranking list by difficulty.
     * This method delegates the order ranking by difficulty operation to the `ctrlRanking` object.
     * @throws ExceptionRankingEmpty 
     */
    public void orderRankingByDifficulty(int kenkenDifficulty) throws ExceptionRankingEmpty { //ordena la llista per dificultat??
        ctrlRanking.orderRankingByDifficulty(kenkenDifficulty);
    }

    /**
     * Orders the ranking list by the number of solved Kenkens for a given Kenken size.
     * This method delegates the order ranking by number of solved operation to the `ctrlRanking` object.
     * 
     * @param kenkenSize the size of the Kenken puzzles
     * @throws ExceptionRankingEmpty 
     */
    public void orderRankingByNumberOfSolved(int kenkenSize) throws ExceptionRankingEmpty { 
        ctrlRanking.orderRankingByNumberOfSolved(kenkenSize);
    }
}