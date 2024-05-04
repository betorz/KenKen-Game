package main.domain.controllers;

import main.domain.classes.Game;
import main.domain.classes.Board;
import main.domain.classes.User;
import main.domain.classes.exceptions.*;


public class CtrlGame {

    private Game currentGame;

    public CtrlGame() {
        currentGame = null;
    }

    public void startGame(Board board, User user) {
        currentGame = new Game(board, user);
        currentGame.startGame();
        System.out.println(currentGame);
    }

    public void continueGame(Game game) {
        currentGame = game;
        currentGame.continueGame();
    }

    public void makeMove(int r, int c, int v) throws ExceptionGame{
        currentGame.makeMove(r, c, v);
    }
    
    public void stopPlaying() {

        currentGame.stopPlaying();
        currentGame = null;
    }

    public void getHint() {
        currentGame.getHint();
    }

    public boolean checkSolution() {
        return currentGame.checkSolution();
    }

    public void finishGame() {
        currentGame.finish();
    }

    public boolean isFinished() {
        return currentGame.isFinished();
    }

}
