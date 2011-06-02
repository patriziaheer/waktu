package ch.hsr.waktu.controller.datacontroller;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.PermissionController;
import ch.hsr.waktu.controller.PersistenceController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.ProjectStaff;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.services.ExceptionHandling;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.QSignalEmitter;

public class ProjectStaffController extends QSignalEmitter {

	private ProjectStaffController() { }

	private static ProjectStaffController theInstance = null;
	
	private Logger logger = Logger.getLogger(ProjectStaffController.class);
	public Signal1<ProjectStaff> add = new Signal1<ProjectStaff>();
	public Signal1<ProjectStaff> removed = new Signal1<ProjectStaff>();

	/**
	 * 
	 * @return ProjectStaffController
	 */
    public static ProjectStaffController getInstance() {
        if (theInstance == null) {
            theInstance = new ProjectStaffController();
        }
        return theInstance;
    }

    /**
     * 
     * @param user
     * @return List<Project>
     * @throws WaktuException
     */
    @SuppressWarnings("unchecked")
    public List<Project> getProjects(final Usr user) throws WaktuException {
        EntityManager em = PersistenceController.getInstance().getEMF()
                .createEntityManager();

        List<Project> projects = null;

        if (!PermissionController.getInstance().checkPermission()) {
            throw new WaktuException("Permission denied");
        }

        try {
            projects = em
                    .createQuery(
                            "SELECT p FROM ProjectStaff ps JOIN ps.project p JOIN ps.user u WHERE u.usrid = '"
                                    + user.getId()
                                    + "' ORDER by p.projectIdentifier ASC")
                    .getResultList();
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        } finally {
            em.close();
        }
        return projects;
    }

    /**
     * 
     * @param user
     * @return List<Project>
     * @throws WaktuException
     */
    @SuppressWarnings("unchecked")
    public List<Project> getProjectsWhereUserIsNotMember(final Usr user)
            throws WaktuException {
        EntityManager em = PersistenceController.getInstance().getEMF()
                .createEntityManager();

        List<Project> nonUserProjects = null;

        if (!PermissionController.getInstance().checkPermission()) {
            throw new WaktuException("Permission denied");
        }

        try {
            nonUserProjects = em
                    .createQuery(
                            "SELECT p FROM Project p WHERE p.projectid NOT IN (SELECT pr.projectid FROM ProjectStaff ps JOIN ps.project pr JOIN ps.user u WHERE u.usrid = "
                                    + user.getId()
                                    + ")  ORDER by p.projectIdentifier ASC")
                    .getResultList();
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        } finally {
            em.close();
        }
        return nonUserProjects;
    }

    /**
     * 
     * @param project
     * @return List<Usr>
     * @throws WaktuException
     */
    @SuppressWarnings("unchecked")
    public List<Usr> getUsers(final Project project) throws WaktuException {
        EntityManager em = PersistenceController.getInstance().getEMF()
                .createEntityManager();

        List<Usr> users = null;

        if (!PermissionController.getInstance().checkPermission()) {
            throw new WaktuException("Permission denied");
        }

        try {
            users = em
                    .createQuery(
                            "SELECT u FROM ProjectStaff ps JOIN ps.project p JOIN ps.user u WHERE p.projectid = '"
                                    + project.getId() + "'").getResultList();
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        } finally {
            em.close();
        }
        return users;
    }

    /**
     * 
     * @param project
     * @return List<Usr>
     * @throws WaktuException
     */
    @SuppressWarnings("unchecked")
    public List<Usr> getUsersNotMemberOf(final Project project)
            throws WaktuException {
        EntityManager em = PersistenceController.getInstance().getEMF()
                .createEntityManager();

        List<Usr> users = null;

        if (!PermissionController.getInstance().checkPermission()) {
            throw new WaktuException("Permission denied");
        }

        try {
            users = em
                    .createQuery(
                            "SELECT u FROM Usr u WHERE u.usrid NOT IN (SELECT us.usrid FROM ProjectStaff ps JOIN ps.user us JOIN ps.project p WHERE p.projectid = "
                                    + project.getId() + ")").getResultList();
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        } finally {
            em.close();
        }
        return users;
    }

    /**
     * 
     * @param user
     * @param project
     * @return ProjectStaff
     * @throws WaktuException
     */
    public ProjectStaff addProjectStaff(final Usr user, final Project project)
            throws WaktuException {
        EntityManager em = PersistenceController.getInstance().getEMF()
                .createEntityManager();
        ProjectStaff newProjectStaff = new ProjectStaff(user, project);

        if (!PermissionController.getInstance().checkPermission()) {
            throw new WaktuException("Permission denied");
        }

        try {
            em.getTransaction().begin();
            em.persist(newProjectStaff);
            em.getTransaction().commit();
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
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
     * @throws WaktuException
     */
    public void removeUser(final Usr user, final Project project)
            throws WaktuException {
        EntityManager em = PersistenceController.getInstance().getEMF()
                .createEntityManager();

        ProjectStaff projectStaffToRemove = null;

        if (!PermissionController.getInstance().checkPermission()) {
            throw new WaktuException("Permission denied");
        }

        try {
            em.getTransaction().begin();
            projectStaffToRemove = (ProjectStaff) em
                    .createQuery(
                            "SELECT ps FROM ProjectStaff ps JOIN ps.project p JOIN ps.user u WHERE p.projectid = '"
                                    + project.getId()
                                    + "' AND u.usrid = '"
                                    + user.getId() + "'").getSingleResult();
            em.remove(projectStaffToRemove);
            em.getTransaction().commit();
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        } finally {
            em.close();
        }
        removed.emit(projectStaffToRemove);
        logger.info("projectStaff " + projectStaffToRemove + " deleted");
    }

}
