package test;


import org.junit.Before;
import org.junit.Test;

import main.domain.classes.Ranking;
import main.domain.classes.User;
import main.domain.classes.exceptions.ExceptionUser;
import main.domain.controllers.CtrlUser;

import java.util.List;

import static org.junit.Assert.*;

public class RankingTest {

    private Ranking ranking;
    private CtrlUser ctrlUser;

    @Before
    public void setUp() throws ExceptionUser {
        ctrlUser = new CtrlUser();

        ranking = Ranking.getInstance();
    }


    @Test
    public void testAddUser() throws ExceptionUser {
        User user = ctrlUser.createUser("testUser3", "password", "password");
        ranking.addUser(user);
        List<User> userList = ranking.getUserList();
        assertTrue(userList.contains(user));
    }

    @Test
    public void testRemoveUser() throws ExceptionUser {
        User user = ctrlUser.createUser("testUser4", "password", "password");
        ranking.addUser(user);
        ranking.removeUser(user);
        List<User> userList = ranking.getUserList();
        assertFalse(userList.contains(user));
    }
}
