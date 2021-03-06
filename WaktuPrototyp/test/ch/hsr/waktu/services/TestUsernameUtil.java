package ch.hsr.waktu.services;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.hsr.waktu.TestSuite;
import ch.hsr.waktu.controller.datacontroller.UserController;
import ch.hsr.waktu.controller.datacontroller.UserControllerXml;

public class TestUsernameUtil extends TestSuite {

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
    public void generateUsername_UsernameNameNotYetPresentInUserBase()
            throws WaktuException {
        assertEquals("fritzmuster", UsernameUtil.generateUsername(
                UserController.getInstance().getAllUsers(), "Fritz", "Muster"));
    }

    @Test
    public void generateUsername_UsernameNameNotYetPresentInUserBaseTwoFirstnames()
            throws WaktuException {
        assertEquals("kareemabduljabbar", UsernameUtil.generateUsername(
                UserController.getInstance().getAllUsers(), "Kareem Abdul",
                "Jabbar"));
    }

    @Test
    public void generateUsername_UsernameNameAlreadyPresentInUserBase()
            throws WaktuException {
        assertEquals("hansmuster3", UsernameUtil.generateUsername(
                UserController.getInstance().getAllUsers(), "Hans", "Muster"));
    }
}
