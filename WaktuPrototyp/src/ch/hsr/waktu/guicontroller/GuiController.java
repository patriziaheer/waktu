package ch.hsr.waktu.guicontroller;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.datacontroller.ProjectController;
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

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(ProjectController.class);

	
	/**
	 * 
	 * @param user
	 */
	public boolean canAddProject(){
		return true;
		/*SystemRole systemRole = loggedInUser.getRole();
		return PermissionTable.getPermission(SystemAction.AddProjects, systemRole, null);*/
	}
	
	public boolean canModifyProject() {
		return true;
	}

	/**
	 * 
	 * @param user
	 */
	public boolean canAddProjectStaff(Project project){
		return true;
		/*if(ProjectController.getInstance().getProject(project.getId()).getProjectManager().equals(loggedInUser)) {
			return PermissionTable.getPermission(SystemAction.AddUserToOwnProjects, loggedInUser.getRole(), project);
		}
		return PermissionTable.getPermission(SystemAction.AddUserToAllProjects, loggedInUser.getRole(), project);*/
	}

	/**
	 * 
	 * @param user
	 */
	public boolean canAddUser(){
		return true; 
		//return PermissionTable.getPermission(SystemAction.AddUser, loggedInUser.getRole(), null);
	}

	public boolean canModifyUser() {
		return true;
	}

	public boolean canModifyUser(Usr usrToModify) {
		return true;
	}
	/**
	 * 
	 * @param user
	 */
	public boolean canAddWorkPackage(Project project){
		return true;
		/*if(ProjectController.getInstance().getProject(project.getId()).getProjectManager().equals(loggedInUser)) {
			return PermissionTable.getPermission(SystemAction.CreateOwnWorkPackages, loggedInUser.getRole(), project);
		}
		return PermissionTable.getPermission(SystemAction.CreateAllWorkPackages, loggedInUser.getRole(), project);*/
	}


}
