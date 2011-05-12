package ch.hsr.waktu.controller;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.datacontroller.UserController;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.services.Md5;
import ch.hsr.waktu.services.WaktuException;

public class LoginController {

	private static LoginController theInstance = null;

	public static LoginController getInstance() {
		if (theInstance == null) {
			theInstance = new LoginController();
		}
		return theInstance;
	}
	
	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(PermissionController.class);
	
	private static Usr loggedInUser;
	
	public Usr getLoggedInUser() {
		return loggedInUser;
	}

	private void setLoggedInUser(Usr user) {
		loggedInUser = user;
	}
	
	/**
	 * 
	 * @param user
	 */
	public void logout() {
		setLoggedInUser(null);
	}

	/**
	 * 
	 * @param username
	 * @throws WaktuException 
	 */
	public boolean login(String username, String password) throws WaktuException {
		if (canLogin(username)) {
			Usr user = UserController.getInstance().getUser(username);
			String passwordHash = Md5.hash(password);
			if (user.getPasswordHash().equals(passwordHash)) {
				setLoggedInUser(user);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 */
	public boolean canLogin(String username) {

		try {
			if (UserController.getInstance().getUser(username) != null) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
