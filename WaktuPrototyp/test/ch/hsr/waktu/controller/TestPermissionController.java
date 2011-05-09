package ch.hsr.waktu.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.hsr.waktu.controller.PermissionController;
import ch.hsr.waktu.controller.datacontroller.UserController;
import ch.hsr.waktu.controller.datacontroller.UserControllerInterface;
import ch.hsr.waktu.controller.datacontroller.UserControllerXml;

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
		assertEquals(false, PermissionController.getInstance().canLogin("boaheyistdaseinlangerungültigername"));
	}
	
	@Test
	public void canLogin_ValidUsername_True() {
		assertEquals(true, PermissionController.getInstance().canLogin("chucknorris"));
	}
	
	@Test
	public void login_InvalidPassword_False() {
		assertEquals(false, PermissionController.getInstance().login("mickeymouse", "falschespasswort"));
	}
	
	@Test
	public void login_InvalidUsername_False() {
		assertEquals(false, PermissionController.getInstance().login("üngültigernameh", "whatever"));
	}
	
	@Test
	public void login_ValidUsernamePassword_True() {
		assertEquals(true, PermissionController.getInstance().login("chucknorris", "1337"));
	}
	
	@Test
	public void logout_LoggedInUserIsNull() {
		PermissionController.getInstance().logout();
		assertEquals(null, PermissionController.getInstance().getLoggedInUser());
	}
	
}
