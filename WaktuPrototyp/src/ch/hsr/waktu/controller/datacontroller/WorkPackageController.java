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
	 * @param loginName
	 */
	public WorkPackage getWorkPackage(int workPackageId) throws WaktuException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		
		if(!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}	
		
		WorkPackage workPackage = null;
		try {
			workPackage = em.find(WorkPackage.class, workPackageId);
		} catch (Exception e) {
			handleException(e);			
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
	public List<WorkPackage> getActiveWorkPackages(Project project) throws WaktuException {
		//TODO filter nach projekt
		EntityManager em = PersistenceController.getInstance("waktu").getEMF()
				.createEntityManager();

		List<WorkPackage> activeWorkPackages = null;
		
		if(!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}
		
		try {
			activeWorkPackages = em.createQuery(
					"SELECT wp FROM WorkPackage wp JOIN wp.project p WHERE wp.active = TRUE AND p.projectid = '" + project.getId() + "'").getResultList();
		} catch (Exception e) {
			handleException(e);			
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
		EntityManager em = PersistenceController.getInstance("waktu").getEMF()
				.createEntityManager();

		List<WorkPackage> allWorkPackages = null;
		
		if(!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}
		
		try {
			allWorkPackages = em.createQuery(
					"SELECT wp FROM WorkPackage wp JOIN wp.project p WHERE p.projectid = '" + project.getId() + "'").getResultList();
		} catch (Exception e) {
			handleException(e);			
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
		EntityManager em = PersistenceController.getInstance("waktu").getEMF()
				.createEntityManager();

		List<WorkPackage> inactiveWorkPackages = null;
		
		if(!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}
		
		try {
			inactiveWorkPackages = em.createQuery(
					"SELECT wp FROM WorkPackage wp JOIN wp.project p WHERE wp.active = FALSE AND p.projectid = '" + project.getId() + "'")
					.getResultList();
		} catch (Exception e) {
			handleException(e);			
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
		
		if(!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}
		
		WorkPackage newWorkPackage = new WorkPackage(project, description);
		
		try {
			em.getTransaction().begin();
			em.persist(newWorkPackage);
			em.getTransaction().commit();
		} catch (Exception e) {
			handleException(e);			
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
		EntityManager em = PersistenceController.getInstance("waktu").getEMF()
				.createEntityManager();
		
		if(!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}
		
		try {
			em.getTransaction().begin();
			em.merge(workPackage);

			em.getTransaction().commit();
		} catch (Exception e) {
			handleException(e);			
		} finally {
			em.close();
		}
		update.emit();
		logger.info("workPackage " + workPackage + " updated");
	}
	
	private void handleException(Exception e) throws WaktuException{
		if(e instanceof IllegalArgumentException) {
			logger.error(e + e.getMessage());
			throw new WaktuException("Database problem");
		} else if (e instanceof IllegalStateException) {
			logger.error(e + e.getMessage());
			throw new WaktuException("Illegal argument");
		} else {
			logger.error(e + e.getMessage());
			throw new WaktuException("General Problem");
		}
	}

}