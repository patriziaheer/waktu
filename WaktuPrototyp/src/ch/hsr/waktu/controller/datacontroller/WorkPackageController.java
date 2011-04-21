package ch.hsr.waktu.controller.datacontroller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.trolltech.qt.QSignalEmitter;

import ch.hsr.waktu.controller.PersistenceController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.WorkPackage;

/**
 * @author simon.staeheli
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
 */
public class WorkPackageController extends QSignalEmitter{

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
	 * @param description
	 */
	public WorkPackage addWorkPackage(Project project, String description) {
		WorkPackage newWorkPackage = new WorkPackage(project, description);
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		em.getTransaction().begin();
		em.persist(newWorkPackage);
		em.flush();
		em.getTransaction().commit();
		// TODO: add.emit() wieder einschalten (Observer von QT)
		// add.emit(newUser);
		return newWorkPackage;
	}

	/**
	 * 
	 * @param project
	 */
	public List<WorkPackage> getActiveWorkPackages(Project project) {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		@SuppressWarnings("unchecked")
		List<WorkPackage> allActiveWorkPackages = em.createQuery(
				"SELECT wp FROM WorkPackage wp WHERE wp.active = TRUE")
				.getResultList();

		ArrayList<WorkPackage> workPackages = new ArrayList<WorkPackage>();
		for (WorkPackage wp : allActiveWorkPackages) {

			if (wp.getProject().equals(project)) {
				workPackages.add(wp);
				logger.info("WORKPACKAGE: " + wp.getProject().toString());
			}

		}

		em.close();
		return workPackages;
	}

	/**
	 * 
	 * @param project
	 */
	public List<WorkPackage> getAllWorkPackages(Project project) {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		@SuppressWarnings("unchecked")
		List<WorkPackage> allActiveWorkPackages = em.createQuery(
				"SELECT wp FROM WorkPackage wp").getResultList();

		ArrayList<WorkPackage> workPackages = new ArrayList<WorkPackage>();
		for (WorkPackage wp : allActiveWorkPackages) {

			if (wp.getProject().equals(project)) {
				workPackages.add(wp);
				logger.info("WORKPACKAGE: " + wp.getProject().toString());
			}

		}

		em.close();
		return workPackages;
	}

	/**
	 * 
	 * @param project
	 */
	public List<WorkPackage> getInactiveWorkPackages(Project project) {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		@SuppressWarnings("unchecked")
		List<WorkPackage> allActiveWorkPackages = em.createQuery(
				"SELECT wp FROM WorkPackage wp WHERE wp.active = FALSE")
				.getResultList();

		ArrayList<WorkPackage> workPackages = new ArrayList<WorkPackage>();
		for (WorkPackage wp : allActiveWorkPackages) {

			if (wp.getProject().equals(project)) {
				workPackages.add(wp);
				logger.info("WORKPACKAGE: " + wp.getProject().toString());
			}

		}

		em.close();
		return workPackages;
	}

	/**
	 * 
	 * @param workPackage
	 */
	public boolean updateWorkPackage(WorkPackage workPackage) {
		// TODO: Username wechseln erlaubt? Spezifizieren! Bring Probleme mit
		// sich, da Unique in DB.
		// TODO: Was wenn ID der Users nicht vorhanden? Exception?

		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		em.getTransaction().begin();
		WorkPackage updateWorkPackage = (WorkPackage) em.createQuery(
				"SELECT wp FROM WorkPackage wp WHERE wp.id = "
						+ workPackage.getId()).getSingleResult();

		updateWorkPackage.setDescription(workPackage.getDescription());
		updateWorkPackage.setProject(workPackage.getProject());
		updateWorkPackage.setActiveState(workPackage.isActive());
		
		em.getTransaction().commit();
		// TODO: update.emit() wieder einschalten (Observer von QT)
		// update.emit();

		return true;
	}

}