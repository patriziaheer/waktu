package ch.hsr.waktu.controller;

import java.util.ArrayList;
import java.util.List;

import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.ProjectStaff;
import ch.hsr.waktu.domain.Usr;

/**
 * @author simon.staeheli
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
 */
public class ProjectStaffController {
	
	private static ProjectStaffController theInstance = null;
	
	public static ProjectStaffController getInstance() {
		if (theInstance == null) {
			theInstance = new ProjectStaffController();
		}
		return theInstance;
	}

	private ProjectStaffController(){

	}

	/**
	 * 
	 * @param user
	 * @param project
	 */
	public ProjectStaff addProjectStaff(Usr user, Project project){
		return null;
	}

	/**
	 * 
	 * @param user
	 */
	public List<Project> getProjects(Usr user){
		return new ArrayList<Project>();
	}

	/**
	 * 
	 * @param project
	 */
	public List<Usr> getUsers(Project project){
		return new ArrayList<Usr>();
	}

	/**
	 * 
	 * @param user
	 * @param project
	 */
	public boolean removeUser(Usr user, Project project){
		return false;
	}

}