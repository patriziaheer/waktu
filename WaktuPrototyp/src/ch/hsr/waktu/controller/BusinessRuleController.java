package ch.hsr.waktu.controller;

import java.util.List;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.datacontroller.ProjectController;
import ch.hsr.waktu.controller.datacontroller.WorkPackageController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.domain.WorkSession;
import ch.hsr.waktu.services.WaktuException;

public class BusinessRuleController {

	private static Logger logger = Logger.getLogger(BusinessRuleController.class);
	
	/**
	 * Checks if the accordant user has access rights to the specified method.
	 * @param Usr usr
	 * @throws WaktuException
	 */
	public static void check(Usr usr) throws WaktuException {

		if(usr.getHoliday() < 0) {
			logger.info("Negative holiday");
			throw new WaktuException("Negative holiday");
		}
		if(usr.getPensum() < 1) {
			logger.info("Pensum too low, must be between 1 - 100%");
			throw new WaktuException("Pensum too low, must be between 1 - 100%");
		}
		if(usr.getPensum() > 100) {
			logger.info("Pensum higher than 100%");
			throw new WaktuException("Pensum higher than 100%");
		}
		
	}
	
	public static void check(Project project) throws WaktuException {

		if(project.getPlannedTime() < 0) {
			logger.info("Negative planned time");
			throw new WaktuException("Negative planned time");			
		}
		
		List<Project> allProjects = ProjectController.getInstance().getAllProjects();
		for(Project p : allProjects) {
			System.out.println(p.getProjectIdentifier() + " // " + project.getProjectIdentifier());
			System.out.println(p.equals(project));
			if(p.getProjectIdentifier().equals(project.getProjectIdentifier()) && p.getId() != project.getId()){
				logger.info("Project identifier already exists");
				throw new WaktuException("Project identifier already exists");
			}
		}
	}
	
	public static void check(WorkSession workPackage) throws WaktuException {

		if(workPackage.getStart().after(workPackage.getEnd())) {
			logger.info("Start time is after end time");
			throw new WaktuException("Start time is after end time");			
		}
		
	}
	
	public static void check(WorkPackage workPackage) throws WaktuException {

		List<WorkPackage> allWorkPackages = WorkPackageController.getInstance().getAllWorkPackages(workPackage.getProject());
		for(WorkPackage wp : allWorkPackages) {
			if(wp.getDescription().equals(workPackage.getDescription())){
				logger.info("Work package name already exists");
				throw new WaktuException("Work package name already exists");
			}
		}
	}
}
