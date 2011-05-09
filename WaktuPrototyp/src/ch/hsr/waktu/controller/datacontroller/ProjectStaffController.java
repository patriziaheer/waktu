package ch.hsr.waktu.controller.datacontroller;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

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
public class ProjectStaffController extends QSignalEmitter {

	private static ProjectStaffController theInstance = null;

	public static ProjectStaffController getInstance() {
		if (theInstance == null) {
			theInstance = new ProjectStaffController();
		}
		return theInstance;
	}

	private Logger logger = Logger.getLogger(UserController.class);
	public Signal1<ProjectStaff> add = new Signal1<ProjectStaff>();
	public Signal1<ProjectStaff> removed = new Signal1<ProjectStaff>();

	private ProjectStaffController() {
		logger.info("Constructor");
	}

	/**
	 * 
	 * @param user
	 */
	@SuppressWarnings("unchecked")
	public List<Project> getProjects(Usr user) throws WaktuGeneralException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		List<Project> projects;

		try {
			projects = em
					.createQuery(
							"SELECT p FROM ProjectStaff ps JOIN ps.project p JOIN ps.user u WHERE u.usrid = '"
									+ user.getId() + "'").getResultList();
		} catch (IllegalStateException e) {
			throw new WaktuGeneralException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuGeneralException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuGeneralException("General problem");
		} finally {
			em.close();
		}
		return projects;
	}

	/**
	 * 
	 * @param project
	 */
	@SuppressWarnings("unchecked")
	public List<Usr> getUsers(Project project) throws WaktuGeneralException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		List<Usr> users;

		try {
			users = em
					.createQuery(
							"SELECT u FROM ProjectStaff ps JOIN ps.project p JOIN ps.user u WHERE p.projectid = '"
									+ project.getId() + "'").getResultList();
		} catch (IllegalStateException e) {
			throw new WaktuGeneralException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuGeneralException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuGeneralException("General problem");
		} finally {
			em.close();
		}
		return users;
	}

	/**
	 * 
	 * @param user
	 * @param project
	 */
	public ProjectStaff addProjectStaff(Usr user, Project project)
			throws WaktuGeneralException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		ProjectStaff newProjectStaff = new ProjectStaff(user, project);

		try {
			em.getTransaction().begin();
			em.persist(newProjectStaff);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new WaktuGeneralException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuGeneralException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuGeneralException("General problem");
		} finally {
			em.close();
		}

		add.emit(newProjectStaff);
		logger.info("projectStaff " + newProjectStaff + " added");
		return newProjectStaff;
	}

	/**
	 * 
	 * @param user
	 * @param project
	 * @return
	 */
	public void removeUser(Usr user, Project project)
			throws WaktuGeneralException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		ProjectStaff projectStaffToRemove;

		try {
			em.getTransaction().begin();
			projectStaffToRemove = (ProjectStaff) em
					.createQuery(
							"SELECT ps FROM ProjectStaff ps JOIN ps.project p JOIN ps.user u WHERE p.projectid = '"
									+ project.getId()
									+ "' AND u.usrid = '"
									+ user.getId() + "'").getSingleResult();
			// ProjectStaff projectStaffToRemove = em.find(ProjectStaff.class,
			// projStaff.getId());
			em.remove(projectStaffToRemove);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new WaktuGeneralException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuGeneralException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuGeneralException("General problem");
		} finally {
			em.close();
		}
		removed.emit(projectStaffToRemove);
		logger.info("projectStaff " + projectStaffToRemove + " deleted");
	}

}