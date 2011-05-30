package ch.hsr.waktu.guicontroller;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.LoginController;
import ch.hsr.waktu.controller.PermissionController;
import ch.hsr.waktu.services.WaktuException;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;

public class GuiController {

	private static GuiController theInstance = null;

	/**
	 * 
	 * @return The only one instance of GuiController
	 */
	public static GuiController getInstance() {
		if (theInstance == null) {
			theInstance = new GuiController();
		}
		return theInstance;
	}

	private Logger logger = Logger.getLogger(GuiController.class);

	/**
	 * Checks if loggedInUser can add Projects
	 * @return A boolean if can add a project
	 */
	public boolean canAddProject() {
		try {
			return PermissionController.getInstance().checkPermission(
					"addProject");
		} catch (WaktuException e) {
			logger.info(e.getMessage());
		}
		return false;
	}

	/**
	 * Checks if loggedInUser can modify projects
	 * @return A boolean if can modify projects
	 */
	public boolean canModifyProject() {
		try {
			return PermissionController.getInstance().checkPermission(
					"updateProject");
		} catch (WaktuException e) {
			logger.info(e.getMessage());
		}
		return false;
	}

	/**
	 * Checks if loggedInUser can add project staff
	 * @return A boolean if can add project staff to project
	 */
	public boolean canAddProjectStaff() {
		try {
			return PermissionController.getInstance().checkPermission(
					"addProjectStaff");
		} catch (WaktuException e) {
			logger.info(e.getMessage());
		}
		return false;
	}

	/**
	 * Checks if loggedInUser can Add Users
	 * @return  A boolean if can add an user
	 */
	public boolean canAddUser() {
		try {
			return PermissionController.getInstance().checkPermission("addUser");
		} catch (WaktuException e) {
			logger.info(e.getMessage());
		}
		return false;
	}

	/**
	 * Checks if loggedInUsr can modify users
	 * @return  A boolean if can modify a user
	 */
	public boolean canModifyUser() {
		try {
			return PermissionController.getInstance().checkPermission(
					"updateUser");
		} catch (WaktuException e) {
			logger.info(e.getMessage());
		}
		return false;
	}

	/**
	 * Checks if current logged in user is usrToModify.
	 * @param usrToModify
	 * @return A boolean if the logged in usr can change the password 
	 */
	public boolean canModifyUser(Usr usrToModify) {
		boolean permission = false;
		try {
			permission = PermissionController.getInstance().checkPermission("updateUser");
		} catch (WaktuException e) {
			logger.info(e.getMessage());
		}
		
		if(!usrToModify.equals(LoginController.getInstance().getLoggedInUser())) {
			permission = false;
		}
		
		return permission;
	}

	/**
	 * Checks if current logged in user is project manager of project.
	 * @param project
	 * @return  A boolean if can add a work package
	 */
	public boolean canAddWorkPackage(Project project) {
		boolean permission = false;
		try {
			permission = PermissionController.getInstance().checkPermission("addWorkPackage");
		} catch (WaktuException e) {
			logger.info(e.getMessage());
		}
		
		if(!project.getProjectManager().equals(LoginController.getInstance().getLoggedInUser())) {
			permission = false;
		}
		
		return permission;
	}

}
