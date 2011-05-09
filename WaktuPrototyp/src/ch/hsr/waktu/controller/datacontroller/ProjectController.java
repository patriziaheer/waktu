package ch.hsr.waktu.controller.datacontroller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.PermissionController;
import ch.hsr.waktu.controller.PersistenceController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.ProjectStaff;
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
	private PermissionController pc = PermissionController.getInstance();

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
		logger.info("constructor");
	}

	/**
	 * 
	 * @param projectIdentifier
	 * @param description
	 * @param plannedTime
	 * @throws WaktuGeneralException 
	 */
	public Project addProject(String projectIdentifier, String description,
			int plannedTime) throws WaktuGeneralException {
		Usr projectManager = null;
		return this.addProject(projectIdentifier, description, projectManager,
				plannedTime);

	}

	/**
	 * 
	 * 
	 * @param projectIdentifier
	 * @param description
	 * @param projectManager
	 * @param plannedTime
	 * @throws WaktuGeneralException 
	 */
	public Project addProject(String projectIdentifier, String description,
			Usr projectManager, int plannedTime) throws WaktuGeneralException {

		if(!pc.checkPermission("addProject")) {
			throw new WaktuGeneralException("Permission denied");
		}
		
//		if(!pc.checkBusinessRules("addProject")) {
//			throw new WaktuException("BusinessRules");
//		}		
		
		Project newProject = new Project(projectIdentifier, description,
				projectManager, plannedTime);
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		em.getTransaction().begin();
		em.persist(newProject);
		em.flush();
		em.getTransaction().commit();
		add.emit(newProject);

		return newProject;

	}

	public List<Project> getActiveProjects() {

		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		@SuppressWarnings("unchecked")
		List<Project> projects = em.createQuery(
				"SELECT p FROM Project p WHERE p.active = TRUE")
				.getResultList();

		// for (Project project : projects) {
		// logger.info("ACTIVE PROJECTS: " + project.toString());
		// }

		em.close();
		return projects;
	}

	public List<Project> getActiveProjects(Usr usr) {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		@SuppressWarnings("unchecked")
		// TODO: nur usr.usrid auswählen in ProjectStaff
		List<ProjectStaff> projectStaff = em.createQuery("SELECT p FROM ProjectStaff p").getResultList();

		em.close();
		
		List<Project> activeProjects = new ArrayList<Project>();
		
		for(ProjectStaff p : projectStaff) {
			
			activeProjects.add(p.getProject());
		}
		
		return activeProjects;
	}

	public List<Project> getAllProjects() {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		@SuppressWarnings("unchecked")
		List<Project> projects = em.createQuery("SELECT p FROM Project p")
				.getResultList();
		// for (Project project : projects) {
		// logger.info(project.toString());
		// }

		em.close();
		return projects;
	}

	public List<Project> getInactiveProjects() {

		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		@SuppressWarnings("unchecked")
		List<Project> projects = em.createQuery(
				"SELECT p FROM Project p WHERE p.active = FALSE")
				.getResultList();

		// for (Project project : projects) {
		// logger.info("INACTIVE PROJECTS: " + project.toString());
		// }

		em.close();
		return projects;
	}

	public Project getProject(int projectId) {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		Project project = (Project) em.createQuery(
				"SELECT p FROM Project p WHERE p.id = " + projectId)
				.getSingleResult();

		em.close();
		return project;
	}

	/**
	 * 
	 * @param project
	 */

	public boolean updateProject(Project project) {
		// TODO: Was wenn ID des Projektes nicht vorhanden? Exception?
		// TODO: Was wenn ProjectIdentifier anders als vorher?

		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		em.getTransaction().begin();
		Project updateProj = (Project) em.createQuery(
				"SELECT p FROM Project p WHERE p.id = " + project.getId())
				.getSingleResult();

		updateProj.setDescription(project.getDescription());
		updateProj.setActiveState(project.isActive());
		updateProj.setPlannedTime(project.getPlannedTime());
		updateProj.setProjectManager(project.getProjectManager());

		em.getTransaction().commit();
		logger.info("Project " + project.getProjectIdentifier() + " updated");
		// TODO: update.emit() wieder einschalten (Observer von QT)
		update.emit();

		return true;
	}

}