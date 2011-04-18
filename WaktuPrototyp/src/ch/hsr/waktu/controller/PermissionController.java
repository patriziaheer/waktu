package ch.hsr.waktu.controller;

import ch.hsr.waktu.controller.datacontroller.UserController;
import ch.hsr.waktu.domain.PermissionTable;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.services.Md5;

/**
 * @author pheer
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
 */
public class PermissionController {

	private static PermissionController theInstance = null;
	
	public static PermissionController getInstance() {
		if (theInstance == null) {
			theInstance = new PermissionController();
		}
		return theInstance;
	}
	
	private static Usr loggedInUser;
	
	private PermissionController(){

	}
	
	/**
	 * 
	 * @param username
	 */
	public boolean login(String username, String password){		
		if(canLogin(username, password)) {
			loggedInUser = UserController.getInstance().getUser(username);
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param user
	 */
	public void logout(){
		loggedInUser = null;
	}

	/**
	 * 
	 * @param user
	 */
	public boolean canAddProject(Usr loggedInUser){
		SystemRole systemRole = loggedInUser.getRole();
		return PermissionTable.getPermission(systemRole,"canAddProject");
	}

	/**
	 * 
	 * @param user
	 */
	public boolean canAddProjectStaff(Usr loggedInUser, Project project){
		return false;
	}

	/**
	 * 
	 * @param user
	 */
	public boolean canAddUser(Usr loggedInUser){
		return true;
	}

	/**
	 * 
	 * @param user
	 */
	public boolean canAddWorkPackage(Usr user, Project project){
		return false;
	}

	/**
	 * 
	 * @param username
	 * @param password
	 */
	public boolean canLogin(String username, String password){
		String passwordHash = Md5.hash(password);
		if(UserController.getInstance().getUser(username).getPassword().equals(passwordHash)) {
			return true;
		}
		return false;
	}

	public static Usr getLoggedInUser() {
		return loggedInUser;
	}

}