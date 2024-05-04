package drivers;

import java.util.Scanner;
import main.domain.controllers.CtrlDomini; 
import java.lang.StringBuilder;
import java.util.Random;
import main.domain.classes.exceptions.*;

public class DriverRanking {
    
    private CtrlDomini ctrlDomini = null; 
    private Scanner scanner;

    public DriverRanking() {
        ctrlDomini = new CtrlDomini();
    }

    public void orderRankingByPoints() {
        
        try {
            ctrlDomini.orderRankingByPoints();
        } catch (ExceptionRankingEmpty e) {
            System.out.println(e.getMessage());;
        }
    }

    public void orderRankingByName() { 
        
        try {
            ctrlDomini.orderRankingByName();
        } catch (ExceptionRankingEmpty e) {
            System.out.println(e.getMessage());
        }
    }

    public void orderRankingByTime() { 
        
        System.out.println("Insert size option:");
        System.out.println("(1|3x3) - 3x3");
        System.out.println("(2|4x4) - 4x4");
        System.out.println("(3|5x5) - 5x5");
        System.out.println("(4|6x6) - 6x6");
        System.out.println("(5|7x7) - 7x7");
        System.out.println("(6|8x8) - 8x8");
        System.out.println("(7|9x9) - 9x9");
        System.out.println("");
        int kenkenSize = 0;
        switch (scanner.nextLine()) {
            case "1":
            case "3x3":
                kenkenSize = 0;
                break;

            case "2":
            case "4x4":
                kenkenSize = 1;
                break;

            case "3":
            case "5x5":
                kenkenSize = 2;
                break;

            case "4":
            case "6x6":
                kenkenSize = 3;
                break;

            case "5":
            case "7x7":
                kenkenSize = 4;
                break;

            case "6":
            case "8x8":
                kenkenSize = 5;
                break;

            case "7":
            case "9x9":
                kenkenSize = 6;
                break;

            default:
                //throw exception if not one of the option
                break;
        }
        System.out.println("");
        System.out.println("Insert difficulty option");
        System.out.println("(1|easy) - Easy");
        System.out.println("(2|medium) - Medium");
        System.out.println("(3|hard) - Hard");
        System.out.println("(4|expert) - Expert");
        System.out.println("");
        int difficulty = 0;
        switch (scanner.nextLine()) {
            case "1":
            case "easy":
                difficulty = 0;
                break;
            case "2":
            case "medium":
                difficulty = 1;
                break;
            case "3":
            case "hard":
                difficulty = 2;
                break;
            case "4":
            case "expert":
                difficulty = 3;
                break;
            default:
                //throw exception if not one of the option
                break;
        }
        System.out.println("");
        
        try {
            ctrlDomini.orderRankingByTime(kenkenSize, difficulty);
        } catch (ExceptionRankingEmpty e) {
            System.out.println(e.getMessage());
        }
    }

    public void orderRankingByDifficulty() {
        System.out.println("Insert difficulty option");
        System.out.println("(1|easy) - Easy");
        System.out.println("(2|medium) - Medium");
        System.out.println("(3|hard) - Hard");
        System.out.println("(4|expert) - Expert");
        System.out.println("");
        int difficulty = 0;
        switch (scanner.nextLine()) {
            case "1":
            case "easy":
                difficulty = 0;
                break;
            case "2":
            case "medium":
                difficulty = 1;
                break;
            case "3":
            case "hard":
                difficulty = 2;
                break;
            case "4":
            case "expert":
                difficulty = 3;
                break;
            default:
                //throw exception if not one of the option
                break;
        }
        System.out.println("");
        try {
            ctrlDomini.orderRankingByDifficulty(difficulty);
        } catch (ExceptionRankingEmpty e) {
            System.out.println(e.getMessage());
        }
    }

    public void orderRankingByNumberOfSolved() {
        System.out.println("Insert size option:");
        System.out.println("(1|3x3) - 3x3");
        System.out.println("(2|4x4) - 4x4");
        System.out.println("(3|5x5) - 5x5");
        System.out.println("(4|6x6) - 6x6");
        System.out.println("(5|7x7) - 7x7");
        System.out.println("(6|8x8) - 8x8");
        System.out.println("(7|9x9) - 9x9");
        System.out.println("");
        int kenkenSize = 0;
        switch (scanner.nextLine()) {
            case "1":
            case "3x3":
                kenkenSize = 0;
                break;

            case "2":
            case "4x4":
                kenkenSize = 1;
                break;

            case "3":
            case "5x5":
                kenkenSize = 2;
                break;

            case "4":
            case "6x6":
                kenkenSize = 3;
                break;

            case "5":
            case "7x7":
                kenkenSize = 4;
                break;

            case "6":
            case "8x8":
                kenkenSize = 5;
                break;

            case "7":
            case "9x9":
                kenkenSize = 6;
                break;

            default:
                //throw exception if not one of the option
                break;
        }
        System.out.println("");
        try {
            ctrlDomini.orderRankingByNumberOfSolved(kenkenSize);
        } catch (ExceptionRankingEmpty e) {
            System.out.println(e.getMessage());
        }
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateRandomString(int length) {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = rand.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public void testPlayerInitialization() throws ExceptionUser {

        String randomPlayer;
        Random random = new Random();
        for (int i = 0; i < 10; ++i) {

            randomPlayer = generateRandomString(10);
            ctrlDomini.createUser(randomPlayer, randomPlayer, randomPlayer);
            ctrlDomini.login(randomPlayer, randomPlayer);

            for (int size = 0; size < 7; ++size) { //size
                for (int difficulty = 0; difficulty < 4; ++difficulty) { //difficulty

                    int points = random.nextInt(100);
                    long time = 10 + ((long) (random.nextDouble() * (100 - 10))); //random time between 10 and 100
                    ctrlDomini.getCtrlUser().getCurrentUser().getStats().updateStats(size, difficulty, time, points);


                }
            }
            ctrlDomini.logout();
        }
    }


    public static void main(String[] args) throws ExceptionUser {
        
        DriverRanking driverRanking = new DriverRanking();
        showMethods();
        
        driverRanking.scanner = new Scanner(System.in);
        String input = driverRanking.scanner.nextLine();

        driverRanking.testPlayerInitialization();

        while(!input.equals("0") && !input.equals("Exit")) {

            switch (input) {
                
                case "1":
                case "OrderRankingByPoints":

                    driverRanking.orderRankingByPoints();
                    break;

                case "2":
                case "OrderRankingByName":
    
                    driverRanking.orderRankingByName();
                    break;
                
                case "3":
                case "OrderRankingByTime":

                    driverRanking.orderRankingByTime();
                    break;

                case "4":
                case "OrderRankingByDifficulty":

                    driverRanking.orderRankingByDifficulty();
                    break;

                case "5":
                case "OrderRankingByNumberOfSolved":

                    driverRanking.orderRankingByNumberOfSolved();
                    break;
            }
            System.out.println("Press ENTER to continue");
            input = driverRanking.scanner.nextLine();
            showMethods();
            input = driverRanking.scanner.nextLine();
        }
        driverRanking.scanner.close();
    }

    private static void showMethods() {

        System.out.println("(0|Exit) - Exit");
        //opcio per simular que hi ha gent a ranking??
        System.out.println("(1|OrderRankingByPoints) - Order ranking by points");
        System.out.println("(2|OrderRankingByName) - Order ranking by name");
        System.out.println("(3|OrderRankingByTime) - Order ranking by time");
        System.out.println("(4|OrderRankingByDifficulty) - Order ranking by difficulty");
        System.out.println("(5|OrderRankingByNumberOfSolved) - Order ranking by number of solved kenkens");
    }
}
