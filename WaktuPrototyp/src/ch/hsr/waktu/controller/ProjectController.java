package ch.hsr.waktu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;

import com.trolltech.qt.QSignalEmitter;

/**
 * @author simon.staeheli
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
 */
public class ProjectController extends QSignalEmitter {

	public enum ProjectProperties {
		Data, WorkPackages, ProjectStaff, WorkSessions
	}

	private static ProjectController theInstance = null;

	public static ProjectController getInstance() {
		if (theInstance == null) {
			theInstance = new ProjectController();
		}
		return theInstance;
	}

	private Logger logger = Logger.getLogger(ProjectController.class);
	public Signal0 update = new Signal0();
	public Signal1<Project> add = new Signal1<Project>();

	private ProjectController() {
		loadProjectsFromDB();
		logger.info("constructor");
	}

	private static void loadProjectsFromDB() {
		// TODO: reading projects from db and assigning them to projectList
		// loading projects according to user's permission!
	}

	/**
	 * 
	 * @param projectIdentifier
	 * @param description
	 * @param plannedTime
	 */
	public Project addProject(String projectIdentifier, String description,
			int plannedTime) {
		Usr projectManager = null;
		return this.addProject(projectIdentifier, description, projectManager ,plannedTime);
	}
	
	/**
	 * 
	 * 
	 * @param projectIdentifier
	 * @param description
	 * @param projectManager
	 * @param plannedTime
	 */
	public Project addProject(String projectIdentifier, String description,
			Usr projectManager, int plannedTime) {

		Project newProject = new Project(projectIdentifier, description, projectManager, plannedTime);
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		em.getTransaction().begin();
		em.persist(newProject);
		em.flush();
		em.getTransaction().commit();
		// TODO: add.emit() wieder einschalten (Observer von QT)
		// add.emit(newProject);
		return newProject;
		
	}

	public List<Project> getActiveProjects() {

		ArrayList<Project> activeProjects = new ArrayList<Project>();
	
		return activeProjects;
	}

	public List<Project> getAllProjects() {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		@SuppressWarnings("unchecked")
		List<Project> projects = em.createQuery("SELECT p FROM Project p").getResultList();

		for (Project project : projects) {
			logger.info(project.toString());
		}

		em.close();
		return projects;

		//return projectList;
	}

	public List<Project> getInactiveProjects() {

		ArrayList<Project> inactiveProjects = new ArrayList<Project>();

		return inactiveProjects;
	}

	/**
	 * 
	 * @param project
	 */
	public boolean updateProject(Project project) {
		// TODO better: return value = value which changed within project?
		// Args project? o_O better: project to change and values to change
		update.emit();
		return false;
	}

}