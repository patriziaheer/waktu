package ch.hsr.waktu.controller;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.hsr.waktu.controller.datacontroller.UserController;
import ch.hsr.waktu.controller.datacontroller.UserControllerXml;
import ch.hsr.waktu.services.WaktuException;

public class TestPermissionController {

	private UserController previousUserController;
	
	@Before
	public void storePreviousControllers() {
		previousUserController = UserController.getInstance();
	}
	
	@Before
	public void setFakeControllers() {
		UserController.setInstance(new UserControllerXml());
	}
	
	@After
	public void restorePreviousControllers() {
		UserController.setInstance(previousUserController);
	}

	
	@Test
	public void canLogin_InvalidUsername_False() {		
		assertEquals(false, LoginController.getInstance().canLogin("boaheyistdaseinlangerungültigername"));
	}
	
	@Test
	public void canLogin_ValidUsername_True() {
		assertEquals(true, LoginController.getInstance().canLogin("chucknorris"));
	}
	
	@Test
	public void login_InvalidPassword_False() {
		try {
			assertEquals(false, LoginController.getInstance().login("mickeymouse", "falschespasswort"));
		} catch (WaktuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void login_InvalidUsername_False() {
		try {
			assertEquals(false, LoginController.getInstance().login("üngültigernameh", "whatever"));
		} catch (WaktuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void login_ValidUsernamePassword_True() {
		try {
			assertEquals(true, LoginController.getInstance().login("chucknorris", "1337"));
		} catch (WaktuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void logout_LoggedInUserIsNull() {
		LoginController.getInstance().logout();
		assertEquals(null, LoginController.getInstance().getLoggedInUser());
	}
	
}
