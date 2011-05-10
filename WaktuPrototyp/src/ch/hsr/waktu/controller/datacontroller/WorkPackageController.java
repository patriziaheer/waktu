package ch.hsr.waktu.controller.datacontroller;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.PersistenceController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.WorkPackage;

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

	private Logger logger = Logger.getLogger(UserController.class);

	public Signal0 update = new Signal0();
	public Signal1<WorkPackage> add = new Signal1<WorkPackage>();

	private WorkPackageController() {

	}

	/**
	 * 
	 * @param project
	 * @throws WaktuGeneralException 
	 */
	@SuppressWarnings("unchecked")
	public List<WorkPackage> getActiveWorkPackages(Project project) throws WaktuGeneralException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		List<WorkPackage> activeWorkPackages;
		try {
			activeWorkPackages = em.createQuery(
					"SELECT wp FROM WorkPackage wp WHERE wp.active = TRUE").getResultList();
		} catch (IllegalStateException e) {
			throw new WaktuGeneralException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuGeneralException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuGeneralException("General problem");
		} finally {
			em.close();
		}
		return activeWorkPackages;
	}

	/**
	 * 
	 * @param project
	 * @throws WaktuGeneralException
	 */
	@SuppressWarnings("unchecked")
	public List<WorkPackage> getAllWorkPackages(Project project)
			throws WaktuGeneralException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		List<WorkPackage> allWorkPackages;
		try {
			allWorkPackages = em.createQuery(
					"SELECT wp FROM WorkPackage wp").getResultList();
		} catch (IllegalStateException e) {
			throw new WaktuGeneralException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuGeneralException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuGeneralException("General problem");
		} finally {
			em.close();
		}
		return allWorkPackages;
	}

	/**
	 * 
	 * @param project
	 * @throws WaktuGeneralException
	 */
	@SuppressWarnings("unchecked")
	public List<WorkPackage> getInactiveWorkPackages(Project project)
			throws WaktuGeneralException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		List<WorkPackage> inactiveWorkPackages;
		try {
			inactiveWorkPackages = em.createQuery(
					"SELECT wp FROM WorkPackage wp WHERE wp.active = FALSE")
					.getResultList();
		} catch (IllegalStateException e) {
			throw new WaktuGeneralException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuGeneralException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuGeneralException("General problem");
		} finally {
			em.close();
		}
		return inactiveWorkPackages;
	}

	/**
	 * 
	 * @param project
	 * @param description
	 * @throws WaktuGeneralException
	 */
	public WorkPackage addWorkPackage(Project project, String description)
			throws WaktuGeneralException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		WorkPackage newWorkPackage = new WorkPackage(project, description);
		try {
			em.getTransaction().begin();
			em.persist(newWorkPackage);
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

		add.emit(newWorkPackage);
		logger.info("workPackage " + newWorkPackage + " deleted");
		return newWorkPackage;
	}

	/**
	 * 
	 * @param workPackage
	 * @throws WaktuGeneralException
	 */
	public void updateWorkPackage(WorkPackage workPackage)
			throws WaktuGeneralException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		WorkPackage updateWorkPackage;
		try {
			em.getTransaction().begin();
			updateWorkPackage = em.find(WorkPackage.class, workPackage.getId());

			updateWorkPackage.setDescription(workPackage.getDescription());
			updateWorkPackage.setProject(workPackage.getProject());
			updateWorkPackage.setActiveState(workPackage.isActive());

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
		update.emit();
		logger.info("workPackage " + updateWorkPackage + " updated");
	}

}