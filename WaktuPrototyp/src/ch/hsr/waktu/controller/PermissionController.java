package ch.hsr.waktu.controller;

import ch.hsr.waktu.domain.Usr;

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
	
	private PermissionController(){

	}

	/**
	 * 
	 * @param user
	 */
	public boolean canAddProject(Usr user){
		return false;
	}

	/**
	 * 
	 * @param user
	 */
	public boolean canAddProjectStaff(Usr user){
		return false;
	}

	/**
	 * 
	 * @param user
	 */
	public boolean canAddUser(Usr user){
		return false;
	}

	/**
	 * 
	 * @param user
	 */
	public boolean canAddWorkPackage(Usr user){
		return false;
	}

	/**
	 * 
	 * @param username
	 * @param password
	 */
	public boolean canLogin(String username, String password){
		return true;
	}

}