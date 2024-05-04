package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import main.domain.classes.Game;
import main.domain.classes.User;

import java.util.HashMap;

public class UserTest {
    private User user;
    
    @Before
    public void setUp() {
        user = new User("testuser", "password123");
    }

    @Test
    public void testGetUsername() {
        assertEquals("testuser", user.getUsername());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password123", user.getPassword());
    }

    @Test
    public void testSetPassword() {
        user.setPassword("newpassword");
        assertEquals("newpassword", user.getPassword());
    }

    @Test
    public void testAddSavedGame() {
        Game game = new Game(null, user);
        user.addSavedGame(1, game);
        HashMap<Integer, Game> savedGames = user.getSavedGames();
        assertTrue(savedGames.containsKey(1));
        assertEquals(game, savedGames.get(1));
    }

    @Test
    public void testGetPoints() {
        assertEquals(0, user.getPoints());
    }

    @Test
    public void testGetTimes() {
        assertEquals(99999999, user.getTimes(5, 1));
    }

    @Test
    public void testGetSolvedDifficulty() {
        assertEquals(0, user.getSolvedDifficulty(1));
    }

    @Test
    public void testGetSolvedSize() {
        assertEquals(0, user.getSolvedSize(5));
    }

    @Test
    public void testGetSavedGames() {
        HashMap<Integer, Game> savedGames = user.getSavedGames();
        assertNotNull(savedGames);
        assertTrue(savedGames.isEmpty());
    }

    @Test
    public void testGetStats() {
        assertNotNull(user.getStats());
    }
}
