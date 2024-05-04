package main.domain.classes;

import main.domain.classes.exceptions.ExceptionGame;



public class Game {
    private Board board;
    private User user;
    private long time;
    private boolean started;
    private boolean finished;
    private long startTime;

    public Game(Board board, User user) {
        this.board = board;
        this.user = user;
        this.time = 0;
        this.started = false;
        this.finished = false;
    }

    public Board getBoard() {
        return board;
    }

    //acaba la partida
    public void finish() {

        stopPlaying();

        int points = calculatePoints();
        int kenkenSize = board.getSize() - 3; //-3 perque nosaltres comencem desde el 3
        int difficulty = board.getDifficulty() - 1; //-1 perque nosaltres la difficultat la comencem desde 1

        user.getStats().updateStats(kenkenSize, difficulty, this.time, points);
        
        this.finished = true;
    }



    //calcula els punts tenint en compte la difficultat i el temps(depen del size)
    private int calculatePoints() {
        int basePoints = 50;
        int difficulty = board.getDifficulty();
        switch(difficulty) {
            case 1:
                basePoints = 50;
                break;
            case 2:
                basePoints = 75;
                break;
            case 3:
                basePoints = 100;
                break;
            case 4:
                basePoints = 125;
                break;
            default:
                return 0;
        }
        int size = board.getSize();
        double timeMultiplier = 1.0;
        int seconds = (int)this.time/1000;
        if (seconds <= 60*size) timeMultiplier = 1.1;
        else if (seconds > 120*size) timeMultiplier = 0.9;
        int points = (int) (basePoints*timeMultiplier);
        return points;
    }

    public boolean isStarted () {
        return started;
    }

    public boolean isFinished () {
        return finished;
    }

    public void addTime (long time) {
        this.time = time;
    }

    public double getTime () {
        double dTime = (double) time;
        return dTime;
    }

    public void startGame() {
        if (finished) {
            return;
        }
        this.started = true;
        this.startTime = System.currentTimeMillis();
    }

    //returns: 1->invalid movement, 2->value in the same row/column
    //3->wrong result, 4->valid movement, 5->solved
    public void makeMove(int r, int c, int v) throws ExceptionGame {    
        int size = board.getSize();
        if (r < 0 || c < 0 || r >= size || c >= size) {
            throw new ExceptionGame("Out of bounds");
        }

        if (v < 0 || v > size) {
            throw new ExceptionGame("Invalid value");
        }

        board.modifyCellValue(v, r, c);

        if (!board.checkRowColRule(r, c, v)) { 
            throw new ExceptionGame("Value already in row/column");
        }
        
        if (!board.checkOpRule(r, c)) {
            throw new ExceptionGame("Region operation not correct");
        }      
    }

    public void stopPlaying() {

        long currentTime = System.currentTimeMillis();
        long time = currentTime - this.startTime;
        this.time += time;
        
        int boardId = this.board.getId();
        user.addSavedGame(boardId, this);
    }

    public boolean checkSolution() {
        return this.board.checkSolution();
    }

    public void continueGame() {
        startTime = System.currentTimeMillis();
    }

    public void getHint() {
        board.getHint();
    }
}