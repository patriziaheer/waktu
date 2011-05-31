package ch.hsr.waktu.controller.datacontroller;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.BusinessRuleController;
import ch.hsr.waktu.controller.PermissionController;
import ch.hsr.waktu.controller.PersistenceController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.services.ExceptionHandling;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.QSignalEmitter;

/**
 * @author simon.staeheli
 * @version 1.0
 */
public class ProjectController extends QSignalEmitter {

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
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		if (!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}

		List<Project> allActiveProjects = null;
		try {
			allActiveProjects = em
					.createQuery(
							"SELECT p FROM Project p WHERE p.active = TRUE ORDER BY p.projectIdentifier ASC")
					.getResultList();
		} catch (Exception e) {
			ExceptionHandling.handleException(e);
		} finally {
			em.close();
		}

		return allActiveProjects;
	}

	/**
	 * 
	 * @param usr
	 * @return 
	 * @throws WaktuException
	 */
	
	@SuppressWarnings("unchecked")
	public List<Project> getActiveProjects(Usr usr) throws WaktuException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		if (!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}

		List<Project> activeProjectsOfUser = null;
		try {
			activeProjectsOfUser = em
					.createQuery(
							"SELECT p FROM ProjectStaff ps JOIN ps.project p JOIN ps.user u WHERE u.usrid = '"
									+ usr.getId()
									+ "' ORDER BY p.projectIdentifier ASC")
					.getResultList();
		} catch (Exception e) {
			ExceptionHandling.handleException(e);
		} finally {
			em.close();
		}

		return activeProjectsOfUser;
	}

	@SuppressWarnings("unchecked")
	public List<Project> getAllProjects() throws WaktuException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		if (!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}

		List<Project> allProjects = null;
		try {
			allProjects = em.createQuery(
					"SELECT p FROM Project p ORDER BY p.projectIdentifier ASC")
					.getResultList();
		} catch (Exception e) {
			ExceptionHandling.handleException(e);
		} finally {
			em.close();
		}

		return allProjects;
	}

	@SuppressWarnings("unchecked")
	public List<Project> getInactiveProjects() throws WaktuException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		if (!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}

		List<Project> allInactiveProjects = null;
		try {
			allInactiveProjects = em
					.createQuery(
							"SELECT p FROM Project p WHERE p.active = FALSE ORDER BY p.projectIdentifier ASC")
					.getResultList();
		} catch (Exception e) {
			ExceptionHandling.handleException(e);
		} finally {
			em.close();
		}

		return allInactiveProjects;
	}

	public Project getProject(int projectId) throws WaktuException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		if (!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}

		Project project = null;
		try {
			project = em.find(Project.class, projectId);
		} catch (Exception e) {
			ExceptionHandling.handleException(e);
		} finally {
			em.close();
		}
		return project;
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
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		if (!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}

		Project newProject = new Project(projectIdentifier, description,
				projectManager, plannedTime);

		BusinessRuleController.check(newProject);

		try {
			em.getTransaction().begin();
			em.persist(newProject);
			em.getTransaction().commit();
		} catch (Exception e) {
			ExceptionHandling.handleException(e);
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
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		if (!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}

		BusinessRuleController.check(project);

		try {
			em.getTransaction().begin();
			em.merge(project);
			em.getTransaction().commit();
		} catch (Exception e) {
			ExceptionHandling.handleException(e);
		} finally {
			em.close();
		}
		update.emit();
		logger.info("project " + project + " updated");
	}

	public Project getProject(String projectIdentifier) throws WaktuException {
		// TODO Auto-generated method stub
		return null;
	}
}