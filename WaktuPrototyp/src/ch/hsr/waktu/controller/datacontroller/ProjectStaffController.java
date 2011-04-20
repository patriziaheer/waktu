package ch.hsr.waktu.controller.datacontroller;

import java.util.ArrayList;
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
	public Signal0 update = new Signal0();
	public Signal1<Usr> add = new Signal1<Usr>();

	private ProjectStaffController() {

	}

	/**
	 * 
	 * @param user
	 * @param project
	 */
	public ProjectStaff addProjectStaff(Usr user, Project project) {
		ProjectStaff newProjectStaff = new ProjectStaff(user, project);
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		em.getTransaction().begin();
		em.persist(newProjectStaff);
		em.flush();
		em.getTransaction().commit();
		// add.emit(newProjectStaff);
		return newProjectStaff;
	}

	/**
	 * 
	 * @param user
	 */
	public List<Project> getProjects(Usr user) {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		@SuppressWarnings("unchecked")
		List<ProjectStaff> projStaff = em.createQuery(
				"SELECT ps FROM ProjectStaff ps ").getResultList();

		ArrayList<Project> projects = new ArrayList<Project>();
		for (ProjectStaff ps : projStaff) {

			if (ps.getUser().getId() == user.getId()) {
				projects.add(ps.getProject());
				logger.info("PROJECT: " + ps.getProject().toString());
			}

		}

		em.close();
		return projects;
	}

	/**
	 * 
	 * @param project
	 */
	public List<Usr> getUsers(Project project) {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		@SuppressWarnings("unchecked")
		List<ProjectStaff> projStaff = em.createQuery(
				"SELECT ps FROM ProjectStaff ps ").getResultList();

		ArrayList<Usr> usrs = new ArrayList<Usr>();
		for (ProjectStaff ps : projStaff) {

			if (ps.getProject().getId() == project.getId()) {
				usrs.add(ps.getUser());
				logger.info("USER: " + ps.getUser().toString());
			}

		}

		em.close();
		return usrs;
	}

	/**
	 * 
	 * @param user
	 * @param project
	 */
	public boolean removeUser(Usr user, Project project) {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		@SuppressWarnings("unchecked")
		List<ProjectStaff> projStaff = em.createQuery(
				"SELECT ps FROM ProjectStaff ps ").getResultList();

		ProjectStaff projectStaffToRemove;

		for (ProjectStaff ps : projStaff) {

			if ((ps.getProject().getId() == project.getId())
					&& (ps.getUser().getId() == user.getId())) {
				projectStaffToRemove = ps;
				em.createQuery("DELETE ProjectStaff ps WHERE ps.id = "
						+ projectStaffToRemove.getId());
				em.close();
				return true;
			}
		}

		em.close();
		return false;
	}
}