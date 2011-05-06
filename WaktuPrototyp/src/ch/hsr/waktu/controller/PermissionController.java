package ch.hsr.waktu.controller;

import java.util.List;

import javax.persistence.EntityManager;

import ch.hsr.waktu.controller.datacontroller.UserController;
import ch.hsr.waktu.domain.Project;
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
	
	public static void setInstance(
			PermissionController permissionControllerInstance) {
		theInstance = permissionControllerInstance;
	}
	
	private static Usr loggedInUser;
	
	protected PermissionController(){

	}

	public Usr getLoggedInUser() {
		return loggedInUser;
	}
	
	private void setLoggedInUser(Usr user) {
		loggedInUser = user;
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
				setLoggedInUser(user);
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
		setLoggedInUser(null);
	}
	
	public List<Project> getPermissionTable() {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		@SuppressWarnings("unchecked")
		List<Project> permissions = em.createQuery("SELECT p FROM Permission p")
				.getResultList();

		em.close();
		return permissions;
	}
	
//	public Project addPermission(SystemRole systemRole, ) {
//
//		Permission newPermission = new Permission();
//		EntityManager em = PersistenceController.getInstance().getEMF()
//				.createEntityManager();
//		em.getTransaction().begin();
//		em.persist(newPermission);
//		em.flush();
//		em.getTransaction().commit();
//		add.emit(newPermission);
//
//		return newPermission;
//
//	}

	/**
	 * 
	 * @param user
	 */
	public boolean canAddProject(Usr loggedInUser){
		return true;
		/*SystemRole systemRole = loggedInUser.getRole();
		return PermissionTable.getPermission(SystemAction.AddProjects, systemRole, null);*/
	}

	/**
	 * 
	 * @param user
	 */
	public boolean canAddProjectStaff(Usr loggedInUser, Project project){
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

	/**
	 * 
	 * @param username
	 * @param password
	 */
	public boolean canLogin(String username){
		
		try {
			if(UserController.getInstance().getUser(username) != null) {
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}