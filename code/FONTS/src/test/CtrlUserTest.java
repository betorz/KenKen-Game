package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.domain.classes.User;
import main.domain.classes.exceptions.ExceptionUser;
import main.domain.controllers.CtrlUser;

public class CtrlUserTest {

    private CtrlUser ctrlUser;
    private User user;

    @Before
    public void setUp() {
        ctrlUser = new CtrlUser();
        user = null;
    }

    @Test
    public void testCreateUser() throws ExceptionUser {
        User newUser = ctrlUser.createUser("testUser", "password", "password");
        assertNotNull(newUser);
        assertEquals("testUser", newUser.getUsername());
    }

    @Test
    public void testLogin() throws ExceptionUser {
        user = ctrlUser.createUser("testUser", "password", "password");
        ctrlUser.login("testUser", "password", user);
        assertEquals(user, ctrlUser.getCurrentUser());
    }

    @Test(expected = ExceptionUser.class)
    public void testLoginAlreadyLoggedIn() throws ExceptionUser {
        user = ctrlUser.createUser("testUser", "password", "password");
        ctrlUser.login("testUser", "password", user);
        ctrlUser.login("testUser", "password", user);
    }

    @Test
    public void testChangePassword() throws ExceptionUser {
        user = ctrlUser.createUser("testUser", "password", "password");
        ctrlUser.login("testUser", "password", user);
        ctrlUser.changePassword("password", "newPassword", "newPassword");
        assertEquals("newPassword", user.getPassword());
    }

    @Test(expected = ExceptionUser.class)
    public void testChangePasswordNotLoggedIn() throws ExceptionUser {
        user = ctrlUser.createUser("testUser", "password", "password");
        ctrlUser.changePassword("password", "newPassword", "newPassword");
    }

    @Test(expected = ExceptionUser.class)
    public void testChangePasswordIncorrectPassword() throws ExceptionUser {
        user = ctrlUser.createUser("testUser", "password", "password");
        ctrlUser.login("testUser", "password", user);
        ctrlUser.changePassword("wrongPassword", "newPassword", "newPassword");
    }

    @Test
    public void testLogout() throws ExceptionUser {
        user = ctrlUser.createUser("testUser", "password", "password");
        ctrlUser.login("testUser", "password", user);
        ctrlUser.logout();
        assertNull(ctrlUser.getCurrentUser());
    }

    @Test(expected = ExceptionUser.class)
    public void testLogoutNotLoggedIn() throws ExceptionUser {
        user = ctrlUser.createUser("testUser", "password", "password");
        ctrlUser.logout();
    }

    @Test
    public void testDeleteUser() throws ExceptionUser {
        user = ctrlUser.createUser("testUser", "password", "password");
        ctrlUser.login("testUser", "password", user);
        String deletedUsername = ctrlUser.deleteUser("password", user);
        assertNull(ctrlUser.getCurrentUser());
        assertEquals("testUser", deletedUsername);
    }

    @Test(expected = ExceptionUser.class)
    public void testDeleteUserNotLoggedIn() throws ExceptionUser {
        user = ctrlUser.createUser("testUser", "password", "password");
        ctrlUser.deleteUser("password", user);
    }
}
