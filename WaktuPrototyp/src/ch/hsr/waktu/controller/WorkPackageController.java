package ch.hsr.waktu.controller;

import java.util.ArrayList;
import java.util.List;

import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.WorkPackage;

/**
 * @author simon.staeheli
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
 */
public class WorkPackageController {

	private static WorkPackageController theInstance = null;
	
	public static WorkPackageController getInstance() {
		if (theInstance == null) {
			theInstance = new WorkPackageController();
		}
		return theInstance;
	}
	
	private WorkPackageController(){

	}


	/**
	 * 
	 * @param project
	 * @param description
	 */
	public WorkPackage addWorkPackage(Project project, String description){
		return null;
	}

	/**
	 * 
	 * @param project
	 */
	public List<WorkPackage> getActiveWorkPackages(Project project){
		return new ArrayList<WorkPackage>();
	}

	/**
	 * 
	 * @param project
	 */
	public List<WorkPackage> getAllWorkPackages(Project project){
		return new ArrayList<WorkPackage>();
	}

	/**
	 * 
	 * @param project
	 */
	public List<WorkPackage> getInactiveWorkPackages(Project project){
		return null;
	}

	/**
	 * 
	 * @param workPackage
	 */
	public boolean updateWorkPackage(WorkPackage workPackage){
		return false;
	}

}