package ch.hsr.waktu.controller.datacontroller;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.BusinessRuleController;
import ch.hsr.waktu.controller.PermissionController;
import ch.hsr.waktu.controller.PersistenceController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.services.WaktuException;

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
	
	public static void setInstance(ProjectController projectControllerInstance) {
		theInstance = projectControllerInstance;
	}

	private Logger logger = Logger.getLogger(ProjectController.class);
	public Signal0 update = new Signal0();
	public Signal1<Project> add = new Signal1<Project>();

	protected ProjectController() {
		logger.info("constructor");
	}

	@SuppressWarnings("unchecked")
	public List<Project> getActiveProjects() throws WaktuException {
		EntityManager em = PersistenceController.getInstance("waktu").getEMF()
				.createEntityManager();
		
		if(!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}	
		
		List<Project> allActiveProjects;
		try {
			allActiveProjects = em.createQuery(
					"SELECT p FROM Project p WHERE p.active = TRUE")
					.getResultList();
		} catch (IllegalStateException e) {
			throw new WaktuException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuException("General problem");
		} finally {
			em.close();
		}

		return allActiveProjects;
	}

	@SuppressWarnings("unchecked")
	public List<Project> getActiveProjects(Usr usr)
			throws WaktuException {
		EntityManager em = PersistenceController.getInstance("waktu").getEMF()
				.createEntityManager();
		
		if(!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}	
		
		List<Project> activeProjectsOfUser;
		try {
			activeProjectsOfUser = em.createQuery(
					"SELECT p FROM ProjectStaff ps JOIN ps.project p JOIN ps.user u WHERE u.usrid = '"
							+ usr.getId() + "'").getResultList();
		} catch (IllegalStateException e) {
			throw new WaktuException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuException("General problem");
		} finally {
			em.close();
		}

		return activeProjectsOfUser;
	}

	@SuppressWarnings("unchecked")
	public List<Project> getAllProjects() throws WaktuException {
		EntityManager em = PersistenceController.getInstance("waktu").getEMF()
				.createEntityManager();
		
		if(!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}	
		
		List<Project> allProjects;
		try {
			allProjects = em.createQuery("SELECT p FROM Project p")
					.getResultList();
		} catch (IllegalStateException e) {
			throw new WaktuException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuException("General problem");
		} finally {
			em.close();
		}

		return allProjects;
	}

	@SuppressWarnings("unchecked")
	public List<Project> getInactiveProjects() throws WaktuException {
		EntityManager em = PersistenceController.getInstance("waktu").getEMF()
				.createEntityManager();
		
		if(!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}	
		
		List<Project> allInactiveProjects;
		try {
			allInactiveProjects = em.createQuery(
					"SELECT p FROM Project p WHERE p.active = FALSE")
					.getResultList();
		} catch (IllegalStateException e) {
			throw new WaktuException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuException("General problem");
		} finally {
			em.close();
		}

		return allInactiveProjects;
	}

	public Project getProject(int projectId) throws WaktuException {
		EntityManager em = PersistenceController.getInstance("waktu").getEMF()
				.createEntityManager();
		
		if(!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}	
		
		Project project;
		try {
			project = em.find(Project.class, projectId);
		} catch (IllegalStateException e) {
			throw new WaktuException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuException("General problem");
		} finally {
			em.close();
		}
		return project;
	}

	/**
	 * 
	 * @param projectIdentifier
	 * @param description
	 * @param plannedTime
	 * @throws WaktuException
	 */
	public Project addProject(String projectIdentifier, String description,
			int plannedTime) throws WaktuException {
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
	 * @throws WaktuException
	 */
	public Project addProject(String projectIdentifier, String description,
			Usr projectManager, int plannedTime) throws WaktuException {
		EntityManager em = PersistenceController.getInstance("waktu").getEMF()
				.createEntityManager();

		Project newProject = new Project(projectIdentifier, description,
				projectManager, plannedTime);
	
		if(!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}	
		
		BusinessRuleController.check(newProject);

		try {
			em.getTransaction().begin();
			em.persist(newProject);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new WaktuException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuException("General problem");
		} finally {
			em.close();
		}

		add.emit(newProject);
		logger.info("project " + newProject + " added");
		return newProject;
	}

	/**
	 * 
	 * @param project
	 */

	public void updateProject(Project project) throws WaktuException {
		EntityManager em = PersistenceController.getInstance("waktu").getEMF()
				.createEntityManager();
		
		if(!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}	
		
		BusinessRuleController.check(project);
		
		try {
			em.getTransaction().begin();
			Project updateProject = em.find(Project.class, project.getId());
			updateProject.setActiveState(project.isActive());
			updateProject.setDescription(project.getDescription());
			updateProject.setPlannedTime(project.getPlannedTime());
			updateProject.setProjectIdentifier(project.getProjectIdentifier());
			updateProject.setProjectManager(project.getProjectManager());
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new WaktuException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuException("General problem");
		} finally {
			em.close();
		}
		update.emit();
		logger.info("project " + project + " updated");
	}
}