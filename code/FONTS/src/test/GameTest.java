package test;

import org.junit.Before;
import org.junit.Test;

import main.domain.classes.Board;
import main.domain.classes.Game;
import main.domain.classes.User;
import main.domain.classes.exceptions.ExceptionGame;
import main.domain.classes.exceptions.ExceptionUser;
import main.domain.controllers.*;

import static org.junit.Assert.*;

import java.util.List;

public class GameTest {

    private Board board;
    private User user;
    private Game game;
    private CtrlBoard ctrlBoard;
    private CtrlUser ctrlUser;

    @Before
    public void setUp() throws ExceptionUser {
        ctrlBoard = new CtrlBoard();
        ctrlUser = new CtrlUser();

        List<Integer> boardList = List.of(3,4,0,2,1,0,0,1,5,3,0,1,0,2,1,1,1,6,3,1,0,2,0,2,1,1,5,2,1,2,2,2);
        board = ctrlBoard.makeBoard(boardList);


        user = ctrlUser.createUser("usuari", "contrasenya", "constrasenya");
        game = new Game(board, user);
    }

    @Test
    public void testFinish() {
        // Test that finishing a game updates user stats and sets finished flag
        game.finish();
        assertTrue(game.isFinished());
        // Assert other conditions if necessary
    }

    @Test
    public void testStartGame() {
        // Test that starting a game sets the started flag and records start time
        game.startGame();
        assertTrue(game.isStarted());
        // Assert other conditions if necessary
    }

    @Test(expected = ExceptionGame.class)
    public void testMakeMoveOutOfBounds() throws ExceptionGame {
        // Test making a move out of bounds
        game.makeMove(-1, 0, 5);
        // Expecting an ExceptionGame to be thrown
    }

    @Test(expected = ExceptionGame.class)
    public void testMakeMoveInvalidValue() throws ExceptionGame {
        // Test making a move with an invalid value
        game.makeMove(0, 0, -1);
        // Expecting an ExceptionGame to be thrown
    }

    @Test
    public void testGetBoard() {
        assertEquals(board, game.getBoard());
    }

    @Test
    public void testAddTime() {
        double time = game.getTime();
        game.addTime(10);
        assertNotEquals(time, game.getTime());
    }

    @Test
    public void testGetTime() {
        assertNotEquals(0, game.getTime());
    }

}
