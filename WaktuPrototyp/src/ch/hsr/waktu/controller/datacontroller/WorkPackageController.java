package ch.hsr.waktu.controller.datacontroller;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.PermissionController;
import ch.hsr.waktu.controller.PersistenceController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.QSignalEmitter;

/**
 * @author simon.staeheli
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
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

	private WorkPackageController() {

	}

	/**
	 * 
	 * @param project
	 * @throws WaktuException 
	 */
	@SuppressWarnings("unchecked")
	public List<WorkPackage> getActiveWorkPackages(Project project) throws WaktuException {
		//TODO filter nach projekt
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		List<WorkPackage> activeWorkPackages;
		
		if(!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}
		
		try {
			activeWorkPackages = em.createQuery(
					"SELECT wp FROM WorkPackage wp WHERE wp.active = TRUE").getResultList();
		} catch (IllegalStateException e) {
			throw new WaktuException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuException("General problem");
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
	public List<WorkPackage> getAllWorkPackages(Project project)
			throws WaktuException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		List<WorkPackage> allWorkPackages;
		
		if(!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}
		
		try {
			allWorkPackages = em.createQuery(
					"SELECT wp FROM WorkPackage wp").getResultList();
		} catch (IllegalStateException e) {
			throw new WaktuException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuException("General problem");
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
	public List<WorkPackage> getInactiveWorkPackages(Project project)
			throws WaktuException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		List<WorkPackage> inactiveWorkPackages;
		
		if(!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}
		
		try {
			inactiveWorkPackages = em.createQuery(
					"SELECT wp FROM WorkPackage wp WHERE wp.active = FALSE")
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
		return inactiveWorkPackages;
	}

	/**
	 * 
	 * @param project
	 * @param description
	 * @throws WaktuException
	 */
	public WorkPackage addWorkPackage(Project project, String description)
			throws WaktuException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		WorkPackage newWorkPackage = new WorkPackage(project, description);
		
		if(!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}
		
		try {
			em.getTransaction().begin();
			em.persist(newWorkPackage);
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

		add.emit(newWorkPackage);
		logger.info("workPackage " + newWorkPackage + " deleted");
		return newWorkPackage;
	}

	/**
	 * 
	 * @param workPackage
	 * @throws WaktuException
	 */
	public void updateWorkPackage(WorkPackage workPackage)
			throws WaktuException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		WorkPackage updateWorkPackage;
		
		if(!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}
		
		try {
			em.getTransaction().begin();
			updateWorkPackage = em.find(WorkPackage.class, workPackage.getId());

			updateWorkPackage.setDescription(workPackage.getDescription());
			updateWorkPackage.setProject(workPackage.getProject());
			updateWorkPackage.setActiveState(workPackage.isActive());

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
		logger.info("workPackage " + updateWorkPackage + " updated");
	}

}