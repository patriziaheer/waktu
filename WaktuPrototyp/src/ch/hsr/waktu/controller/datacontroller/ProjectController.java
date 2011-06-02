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

public class ProjectController extends QSignalEmitter {
	
	protected ProjectController() { }

    private static ProjectController theInstance = null;

    private Logger logger = Logger.getLogger(ProjectController.class);
    public Signal0 update = new Signal0();
    public Signal1<Project> add = new Signal1<Project>();
    
    /**
     * 
     * @return ProjectController
     */
    public static ProjectController getInstance() {
    	if (theInstance == null) {
    		theInstance = new ProjectController();
    	}
    	return theInstance;
    }
    
    /**
     * 
     * @param projectControllerInstance
     */
    public static void setInstance(
    		final ProjectController projectControllerInstance) {
    	theInstance = projectControllerInstance;
    }

    /**
     * 
     * @return List<Project>
     * @throws WaktuException
     */
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
     * @return List<Project>
     * @throws WaktuException
     */
    @SuppressWarnings("unchecked")
    public List<Project> getActiveProjects(final Usr usr) throws WaktuException {
        EntityManager em = PersistenceController.getInstance().getEMF()
                .createEntityManager();

        if (!PermissionController.getInstance().checkPermission()) {
            throw new WaktuException("Permission denied");
        }

        List<Project> activeProjectsOfUser = null;
        try {
            activeProjectsOfUser = em
                    .createQuery(
                            "SELECT p FROM ProjectStaff ps JOIN ps.project p JOIN ps.user u WHERE u.id = '"
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

    /**
     * 
     * @return List<Project>
     * @throws WaktuException
     */
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

    /**
     * 
     * @return List<Project>
     * @throws WaktuException
     */
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

    /**
     * 
     * @param projectId
     * @return Project
     * @throws WaktuException
     */
    public Project getProject(final int projectId) throws WaktuException {
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
     * @param projectIdentifier
     * @return Project
     * @throws WaktuException
     */
    public Project getProject(final String projectIdentifier)
    throws WaktuException {
    	return null;
    }

    /**
     * 
     * @param projectIdentifier
     * @param description
     * @param projectManager
     * @param plannedTime
     * @return Project
     * @throws WaktuException
     */
    public Project addProject(final String projectIdentifier,
            final String description, final Usr projectManager,
            final int plannedTime) throws WaktuException {
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
     * @throws WaktuException
     */

    public void updateProject(final Project project) throws WaktuException {
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
}
