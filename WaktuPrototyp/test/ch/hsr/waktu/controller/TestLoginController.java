package ch.hsr.waktu.controller;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.hsr.waktu.TestSuite;
import ch.hsr.waktu.controller.datacontroller.UserController;
import ch.hsr.waktu.controller.datacontroller.UserControllerXml;
import ch.hsr.waktu.services.WaktuException;

public class TestLoginController extends TestSuite {

    private UserController previousUserController;

    @Before
    public void before() {
        previousUserController = UserController.getInstance();
        UserController.setInstance(new UserControllerXml());
    }

    @After
    public void after() {
        UserController.setInstance(previousUserController);
    }

    @Test
    public void canLogin_InvalidUsername_False() throws WaktuException {
        assertEquals(
                false,
                LoginController.getInstance().canLogin(
                        "boaheyistdaseinlangerung�ltigername"));
    }

    @Test
    public void canLogin_ValidUsername_True() throws WaktuException {
        assertEquals(true, LoginController.getInstance()
                .canLogin("chucknorris"));
    }

    @Test
    public void login_InvalidPassword_False() throws WaktuException {
        assertEquals(
                false,
                LoginController.getInstance().login("mickeymouse",
                        "falschespasswort"));
    }

    @Test
    public void login_InvalidUsername_False() throws WaktuException {
        assertEquals(
                false,
                LoginController.getInstance().login("ungueltigernameh",
                        "whatever"));
    }

    @Test
    public void login_ValidUsernamePassword_True() throws WaktuException {
        assertEquals(true,
                LoginController.getInstance().login("chucknorris", "1337"));
    }

    @Test
    public void logout_LoggedInUserIsNull() {
        LoginController.getInstance().logout();
        assertEquals(null, LoginController.getInstance().getLoggedInUser());
    }
}
