package ch.hsr.waktu.controller.datacontroller;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.PermissionController;
import ch.hsr.waktu.controller.PersistenceController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.services.ExceptionHandling;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.QSignalEmitter;

/**
 * @author simon.staeheli
 * @version 1.0
 */
public class WorkPackageController extends QSignalEmitter {

    private static WorkPackageController theInstance = null;

    public static WorkPackageController getInstance() {
        if (theInstance == null) {
            theInstance = new WorkPackageController();
        }
        return theInstance;
    }

    private Logger logger = Logger.getLogger(WorkPackageController.class);

    public Signal0 update = new Signal0();
    public Signal1<WorkPackage> add = new Signal1<WorkPackage>();

    protected WorkPackageController() {

    }

    /**
     * 
     * @param workPackageId
     * @return WorkPackage workPackage
     * @throws WaktuException
     */
    public WorkPackage getWorkPackage(final int workPackageId)
            throws WaktuException {
        EntityManager em = PersistenceController.getInstance().getEMF()
                .createEntityManager();

        if (!PermissionController.getInstance().checkPermission()) {
            throw new WaktuException("Permission denied");
        }

        WorkPackage workPackage = null;
        try {
            workPackage = em.find(WorkPackage.class, workPackageId);
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        } finally {
            em.close();
        }
        return workPackage;
    }

    /**
     * 
     * @param project
     * @throws WaktuException
     */
    @SuppressWarnings("unchecked")
    public List<WorkPackage> getActiveWorkPackages(final Project project)
            throws WaktuException {
        EntityManager em = PersistenceController.getInstance().getEMF()
                .createEntityManager();

        List<WorkPackage> activeWorkPackages = null;

        if (!PermissionController.getInstance().checkPermission()) {
            throw new WaktuException("Permission denied");
        }

        try {
            activeWorkPackages = em
                    .createQuery(
                            "SELECT wp FROM WorkPackage wp JOIN wp.project p WHERE wp.active = TRUE AND p.projectid = '"
                                    + project.getId()
                                    + "' ORDER BY wp.description ASC")
                    .getResultList();
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        } finally {
            em.close();
        }
        return activeWorkPackages;
    }

    /**
     * 
     * @param project
     * @throws WaktuException
     */
    @SuppressWarnings("unchecked")
    public List<WorkPackage> getAllWorkPackages(final Project project)
            throws WaktuException {
        EntityManager em = PersistenceController.getInstance().getEMF()
                .createEntityManager();

        List<WorkPackage> allWorkPackages = null;

        if (!PermissionController.getInstance().checkPermission()) {
            throw new WaktuException("Permission denied");
        }

        try {
            allWorkPackages = em.createQuery(
                    "SELECT wp FROM WorkPackage wp JOIN wp.project p WHERE p.projectid = '"
                            + project.getId() + "'").getResultList();
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        } finally {
            em.close();
        }
        return allWorkPackages;
    }

    /**
     * 
     * @throws WaktuException
     */
    @SuppressWarnings("unchecked")
    public List<WorkPackage> getAllWorkPackages() throws WaktuException {
        EntityManager em = PersistenceController.getInstance().getEMF()
                .createEntityManager();

        List<WorkPackage> allWorkPackages = null;

        if (!PermissionController.getInstance().checkPermission()) {
            throw new WaktuException("Permission denied");
        }

        try {
            allWorkPackages = em
                    .createQuery(
                            "SELECT wp FROM WorkPackage wp JOIN wp.project p ORDER BY p.projectIdentifier ASC, wp.description ASC")
                    .getResultList();
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        } finally {
            em.close();
        }
        return allWorkPackages;
    }

    /**
     * 
     * @param project
     * @throws WaktuException
     */
    @SuppressWarnings("unchecked")
    public List<WorkPackage> getInactiveWorkPackages(final Project project)
            throws WaktuException {
        EntityManager em = PersistenceController.getInstance().getEMF()
                .createEntityManager();

        List<WorkPackage> inactiveWorkPackages = null;

        if (!PermissionController.getInstance().checkPermission()) {
            throw new WaktuException("Permission denied");
        }

        try {
            inactiveWorkPackages = em
                    .createQuery(
                            "SELECT wp FROM WorkPackage wp JOIN wp.project p WHERE wp.active = FALSE AND p.projectid = '"
                                    + project.getId() + "'").getResultList();
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        } finally {
            em.close();
        }
        return inactiveWorkPackages;
    }

    /**
     * 
     * @param project
     * @param description
     * @throws WaktuException
     */
    public WorkPackage addWorkPackage(final Project project,
            final String description) throws WaktuException {
        EntityManager em = PersistenceController.getInstance().getEMF()
                .createEntityManager();

        if (!PermissionController.getInstance().checkPermission()) {
            throw new WaktuException("Permission denied");
        }

        WorkPackage newWorkPackage = new WorkPackage(project, description);

        try {
            em.getTransaction().begin();
            em.persist(newWorkPackage);
            em.getTransaction().commit();
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        } finally {
            em.close();
        }

        add.emit(newWorkPackage);
        logger.info("workPackage " + newWorkPackage + " deleted");
        return newWorkPackage;
    }

    /**
     * 
     * @param workPackage
     * @throws WaktuException
     */
    public void updateWorkPackage(final WorkPackage workPackage)
            throws WaktuException {
        EntityManager em = PersistenceController.getInstance().getEMF()
                .createEntityManager();

        if (!PermissionController.getInstance().checkPermission()) {
            throw new WaktuException("Permission denied");
        }

        try {
            em.getTransaction().begin();
            em.merge(workPackage);

            em.getTransaction().commit();
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        } finally {
            em.close();
        }
        update.emit();
        logger.info("workPackage " + workPackage + " updated");
    }

    public WorkPackage getWorkPackage(final String description)
            throws WaktuException {
        // TODO Auto-generated method stub
        return null;
    }

    public static void setInstance(
            final WorkPackageController workPackageController) {
        theInstance = workPackageController;
    }

}
