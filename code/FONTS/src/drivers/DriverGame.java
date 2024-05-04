package drivers;

import java.util.Scanner;
import java.io.File;


import main.domain.controllers.CtrlDomini;

import main.domain.classes.exceptions.*;


public class DriverGame {

    private CtrlDomini ctrlDomini = null;
    private Scanner scanner;

    public DriverGame() {
        ctrlDomini = new CtrlDomini();
    }

    ///////////////////////////////////////
    //aixo s'haura de canviar i afegir al ctrl board
 
    public void createBoardFromTxt(DriverGame driverGame) throws ExceptionBoard{
        System.out.println("Introdueix el path del fitxer");
        String path = scanner.nextLine();
        File file = new File(path);
        int boardId = ctrlDomini.createBoardFromTxt(file);
        boolean sol = ctrlDomini.hasSolution(boardId);
        if (sol) {
            System.out.println("(1|PlayGame) - Play Game");
            System.out.println("(2|ShowSolution) - Show Solution");
            int op = driverGame.scanner.nextInt();
            if (op == 1) {
                ctrlDomini.startGame(boardId);
                playGame(driverGame);
            }
            else driverGame.showSolution();
            }
        else {
            System.out.println("El tauler no té solució");
        }
        ctrlDomini.removeLastBoard();
    }

    public void createBoardFromTerminal(DriverGame driverGame) throws ExceptionBoard{
        
        int boardId = ctrlDomini.createBoardFromTerminal(driverGame.scanner);
        boolean sol = ctrlDomini.hasSolution(boardId);
        if (sol) {
            System.out.println("(1|PlayGame) - Play Game");
            System.out.println("(2|ShowSolution) - Show Solution");
            int op = driverGame.scanner.nextInt();
            if (op == 1) {
                ctrlDomini.startGame(boardId);
                playGame(driverGame);
            }
            else  driverGame.showSolution();
        }
        else {
            System.out.println("El tauler no té solució");
        }
        ctrlDomini.removeLastBoard();
    }

    public void testPlayerInitialization() throws ExceptionUser, ExceptionBoard {
        ctrlDomini.createUser("testUser", "testUser", "testUser");
        ctrlDomini.login("testUser", "testUser");

        File file = new File("../../EXE/predBoards.txt");
        try {
            ctrlDomini.createPredBoards(file);
        } catch (ExceptionBoard e) {
            System.err.println(e.getMessage());
        }
    }

    public void startGame(DriverGame driverGame) {
        
        ctrlDomini.showBoardList();
        
        System.out.println("Introduce board you want to play");
        int boardId = scanner.nextInt();
        ctrlDomini.startGame(boardId);
        
        System.out.println("Game started!");
        System.out.println("");
        playGame(driverGame);
        
    }

    public void playGame(DriverGame driverGame) {
        boolean playing = true;
        while (playing) {
            ctrlDomini.printBoardConfig();
            ctrlDomini.printBoardValues();
            System.out.println("(1|MakeMove) - Make Move");
            System.out.println("(2|GetHint) - Get Hint");
            System.out.println("(4|StopPlaying) - Stop Playing");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    if (!ctrlDomini.isFinished()) {
                        driverGame.makeMove();
                        if (ctrlDomini.checkSolution()) {
                            ctrlDomini.finishGame();
                            System.out.println("You won!");
                            playing = false;
                        }
                    }
                    else {
                        System.out.println("The board is finished!");
                        playing = false;
                    }
                    break;

                case 2:
                    if (!ctrlDomini.isFinished()) {
                        driverGame.getHint();
                        if (ctrlDomini.checkSolution()) {
                            ctrlDomini.finishGame();
                            System.out.println("You won!");
                            playing = false;
                        }
                    }
                    else {
                        System.out.println("The board is finished!");
                        playing = false;
                    }
                    break;

                case 3:
                    driverGame.showSolution();
                    ctrlDomini.finishGame();
                    playing = false;
                    break;

                case 4:
                    driverGame.stopPlaying();
                    playing = false;
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

    public void makeMove() {

        System.out.println("Insert row you want to put number");
        int row = scanner.nextInt();
        System.out.println("Insert column you want to put number");
        int column = scanner.nextInt();
        System.out.println("Insert value in cell");
        int value = scanner.nextInt();

        try {
            ctrlDomini.makeMove(row, column, value);
        } catch (ExceptionGame e) {
            System.out.println(e.getMessage());
        }
    }

    public void getHint() {
        ctrlDomini.getHint();
    }

    public void showSolution() {
        ctrlDomini.showSolution();
        ctrlDomini.printBoardValues();
    }

    public void stopPlaying() {
        ctrlDomini.stopPlaying();
    }


    
    public static void main(String[] args) throws ExceptionUser, ExceptionBoard {

        DriverGame driverGame = new DriverGame();

        driverGame.testPlayerInitialization();
        showMethods();

        driverGame.scanner = new Scanner(System.in);
        String input = driverGame.scanner.nextLine();

        while(!input.equals("0") && !input.equals("Exit")) {

            switch (input) {
                case "1":
                case "StartGame":
                    driverGame.startGame(driverGame);
                    break;
                case "2":
                case "CreateBoardTxt":
                    driverGame.createBoardFromTxt(driverGame);
                    break;
                case "3":
                case "CreateBoardTerminal":
                    try {
                        driverGame.createBoardFromTerminal(driverGame);
                    } catch (ExceptionBoard e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Invalid input");
                    break;

            }
            System.out.println("Press ENTER to continue");
            driverGame.scanner.nextLine();
            showMethods();
            input = driverGame.scanner.nextLine();
        }
        driverGame.scanner.close();
    }

    private static void showMethods() {
        System.out.println("(0|Exit) - Exit");
        System.out.println("(1|StartGame) - Start Game");
        System.out.println("(2|CreateBoardTxt) - Create Board Txt");
        System.out.println("(3|CreateBoardTerminal) - Create Board Terminal");
    }
}
