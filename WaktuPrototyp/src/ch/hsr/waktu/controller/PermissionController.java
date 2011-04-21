package ch.hsr.waktu.controller;

import ch.hsr.waktu.controller.datacontroller.UserController;
import ch.hsr.waktu.domain.PermissionTable;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.SystemAction;
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
	
	private Usr loggedInUser;
	
	private PermissionController(){

	}
	
	/**
	 * 
	 * @param username
	 */
	public boolean login(String username, String password){
		if(canLogin(username)) {
			Usr user = UserController.getInstance().getUser(username);
			String passwordHash = Md5.hash(password);
			if(user.getPasswordHash().equals(passwordHash)) {
				loggedInUser = user;
				return true;
			}
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
		return PermissionTable.getPermission(SystemAction.AddProjects, systemRole, null);
	}

	/**
	 * 
	 * @param user
	 */
	public boolean canAddProjectStaff(Project project){
		return false;
	}

	/**
	 * 
	 * @param user
	 */
	public boolean canAddUser(){
		return true;
	}

	/**
	 * 
	 * @param user
	 */
	public boolean canAddWorkPackage(Project project){
		return false;
	}

	/**
	 * 
	 * @param username
	 * @param password
	 */
	public boolean canLogin(String username){
		System.out.println("canLogin entered, username: " + username);
		
		try {
			UserController.getInstance().getUser(username);
			System.out.println("Can Login reached");
			return true;
		} catch(Exception e) {
		
		}
		return false;
	}

	public Usr getLoggedInUser() {
		return loggedInUser;
	}

}