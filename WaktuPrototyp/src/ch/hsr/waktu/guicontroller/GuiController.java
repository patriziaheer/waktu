package ch.hsr.waktu.guicontroller;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.LoginController;
import ch.hsr.waktu.controller.PermissionController;
import ch.hsr.waktu.services.WaktuException;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;

public class GuiController {

	private static GuiController theInstance = null;

	public static GuiController getInstance() {
		if (theInstance == null) {
			theInstance = new GuiController();
		}
		return theInstance;
	}

	private Logger logger = Logger.getLogger(GuiController.class);

	/**
	 * 
	 * @param user
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
	 * 
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
	 * 
	 * @param user
	 */
	public boolean canAddUser() {
		try {
			return PermissionController.getInstance().checkPermission("addUser");
		} catch (WaktuException e) {
			logger.info(e.getMessage());
		}
		return false;
	}

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
	 * @param user
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
	 * @param user
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
