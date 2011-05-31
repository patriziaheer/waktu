package ch.hsr.waktu.controller.datacontroller;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.BusinessRuleController;
import ch.hsr.waktu.controller.PermissionController;
import ch.hsr.waktu.controller.PersistenceController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.domain.WorkSession;
import ch.hsr.waktu.services.ExceptionHandling;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.QSignalEmitter;
import com.trolltech.qt.core.QDate;

/**
 * @author simon.staeheli
 * @version 1.0
 */
public class WorkSessionController extends QSignalEmitter {

	private static WorkSessionController theInstance = null;

	public static WorkSessionController getInstance() {
		if (theInstance == null) {
			theInstance = new WorkSessionController();
		}
		return theInstance;
	}

	public static void setInstance(WorkSessionController workSessionController) {
		theInstance = workSessionController;
	}

	private Logger logger = Logger.getLogger(WorkSessionController.class);
	public Signal0 update = new Signal0();
	public Signal1<WorkSession> add = new Signal1<WorkSession>();
	public Signal1<WorkSession> removed = new Signal1<WorkSession>();

	protected WorkSessionController() {

	}

	/**
	 * 
	 * @param user
	 * @throws WaktuException
	 */
	@SuppressWarnings("unchecked")
	public List<WorkSession> getWorkSessions(Usr user) throws WaktuException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		List<WorkSession> workSessionsByUser = null;

		if (!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}

		try {
			workSessionsByUser = em.createQuery(
					"SELECT ws FROM WorkSession ws JOIN ws.userRef u WHERE u.usrid = '"
							+ user.getId() + "'").getResultList();
		} catch (Exception e) {
			ExceptionHandling.handleException(e);
		} finally {
			em.close();
		}
		return workSessionsByUser;
	}

	/**
	 * 
	 * @param user
	 * @param date
	 * @throws WaktuException
	 */
	@SuppressWarnings("unchecked")
	public List<WorkSession> getWorkSessions(Usr user, QDate date)
			throws WaktuException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		List<WorkSession> workSessionsByDate = null;

		if (!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}

		try {
			Query q = em
					.createQuery("SELECT ws FROM WorkSession ws JOIN ws.userRef u WHERE ws.startTime >= '"
							+ date.toString("yyyy-MM-dd")
							+ " 00:00:00"
							+ "' AND ws.endTime <= '"
							+ date.toString("yyyy-MM-dd")
							+ " 23:59:59"
							+ "' AND u.usrid = '" + user.getId() + "'");
			workSessionsByDate = q.getResultList();
		} catch (Exception e) {
			ExceptionHandling.handleException(e);
		} finally {
			em.close();
		}
		return workSessionsByDate;
	}

	@SuppressWarnings("unchecked")
	public List<WorkSession> getWorkSessions(Usr user, QDate fromDate,
			QDate toDate) throws WaktuException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		List<WorkSession> workSessionsByUserAndDate = null;

		if (!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}

		try {
			Query q = em
					.createQuery("SELECT ws FROM WorkSession ws JOIN ws.userRef u WHERE ws.startTime >= '"
							+ fromDate.toString("yyyy-MM-dd")
							+ " 00:00:00"
							+ "' AND ws.endTime <= '"
							+ toDate.toString("yyyy-MM-dd")
							+ " 23:59:59"
							+ "' AND u.usrid = '" + user.getId() + "'");
			workSessionsByUserAndDate = q.getResultList();
		} catch (Exception e) {
			ExceptionHandling.handleException(e);
		} finally {
			em.close();
		}
		return workSessionsByUserAndDate;

	}

	@SuppressWarnings("unchecked")
	public List<WorkSession> getWorkSessions(WorkPackage workPackage)
			throws WaktuException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		List<WorkSession> workSessionsByProject = null;

		if (!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}

		try {
			workSessionsByProject = em.createQuery(
					"SELECT ws FROM WorkSession ws JOIN ws.workPackageRef wp WHERE wp.id = '"
							+ workPackage.getId() + "'").getResultList();
		} catch (Exception e) {
			ExceptionHandling.handleException(e);
		} finally {
			em.close();
		}
		return workSessionsByProject;
	}

	@SuppressWarnings("unchecked")
	public List<WorkSession> getWorkSessions(WorkPackage workPackage, Usr usr)
			throws WaktuException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		List<WorkSession> workSessionsByProject = null;

		if (!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}

		try {
			workSessionsByProject = em
					.createQuery(
							"SELECT ws FROM WorkSession ws JOIN ws.workPackageRef wp JOIN ws.userRef u WHERE wp.id = '"
									+ workPackage.getId()
									+ "' AND u.usrid = '"
									+ usr.getId() + "'").getResultList();
		} catch (Exception e) {
			ExceptionHandling.handleException(e);
		} finally {
			em.close();
		}
		return workSessionsByProject;
	}

	@SuppressWarnings("unchecked")
	public List<WorkSession> getWorkSessions(WorkPackage workPackage,
			QDate fromDate, QDate toDate) throws WaktuException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		List<WorkSession> workSessionsByDateAndWorkPackage = null;

		if (!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}

		try {
			Query q = em
					.createQuery("SELECT ws FROM WorkSession ws JOIN ws.workPackageRef wp WHERE ws.startTime >= '"
							+ fromDate.toString("yyyy-MM-dd")
							+ " 00:00:00"
							+ "' AND ws.endTime <= '"
							+ toDate.toString("yyyy-MM-dd")
							+ " 23:59:59"
							+ "' AND wp.id = '" + workPackage.getId() + "'");
			workSessionsByDateAndWorkPackage = q.getResultList();
		} catch (Exception e) {
			ExceptionHandling.handleException(e);
		} finally {
			em.close();
		}
		return workSessionsByDateAndWorkPackage;
	}

	@SuppressWarnings("unchecked")
	public List<WorkSession> getWorkSessions(Project project)
			throws WaktuException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		List<WorkSession> workSessionsByProject = null;

		if (!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}

		try {
			workSessionsByProject = em
					.createQuery(
							"SELECT ws FROM WorkSession ws JOIN ws.workPackageRef wp JOIN wp.project p WHERE p.projectid = '"
									+ project.getId() + "'").getResultList();
		} catch (Exception e) {
			ExceptionHandling.handleException(e);
		} finally {
			em.close();
		}
		return workSessionsByProject;
	}

	@SuppressWarnings("unchecked")
	public List<WorkSession> getWorkSessions(Project project, Usr usr)
			throws WaktuException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		List<WorkSession> workSessionsByProject = null;

		if (!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}

		try {
			workSessionsByProject = em
					.createQuery(
							"SELECT ws FROM WorkSession ws JOIN ws.workPackageRef wp JOIN wp.project p JOIN ws.userRef u WHERE p.projectid = '"
									+ project.getId()
									+ "' AND u.usrid = '"
									+ usr.getId() + "'").getResultList();
		} catch (Exception e) {
			ExceptionHandling.handleException(e);
		} finally {
			em.close();
		}
		return workSessionsByProject;
	}

	/**
	 * 
	 * @param project
	 * @param start
	 * @param end
	 * @return List<WorkSession>
	 * @throws WaktuException
	 */
	@SuppressWarnings("unchecked")
	public List<WorkSession> getWorkSessions(Project project, QDate start,
			QDate end) throws WaktuException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		List<WorkSession> workSessionsByDate = null;

		if (!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}

		try {
			Query q = em
					.createQuery("SELECT ws FROM WorkSession ws JOIN ws.workPackageRef wp JOIN wp.project p WHERE ws.startTime >= '"
							+ start.toString("yyyy-MM-dd")
							+ " 00:00:00"
							+ "' AND ws.endTime <= '"
							+ end.toString("yyyy-MM-dd")
							+ " 23:59:59"
							+ "' AND p.projectid = '" + project.getId() + "'");
			workSessionsByDate = q.getResultList();
		} catch (Exception e) {
			ExceptionHandling.handleException(e);
		} finally {
			em.close();
		}
		return workSessionsByDate;
	}

	/**
	 * 
	 * @param project
	 * @param usr
	 * @param start
	 * @param end
	 * @return List<WorkSession> workSessions
	 * @throws WaktuException
	 */
	@SuppressWarnings("unchecked")
	public List<WorkSession> getWorkSessions(Project project, Usr usr,
			QDate start, QDate end) throws WaktuException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		List<WorkSession> workSessionsByDate = null;

		if (!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}

		try {
			Query q = em
					.createQuery("SELECT ws FROM WorkSession ws JOIN ws.userRef u JOIN ws.workPackageRef wp JOIN wp.project p WHERE p.projectid = '"
							+ project.getId()
							+ "' AND u.usrid = '"
							+ usr.getId()
							+ "' AND ws.startTime >= '"
							+ start.toString("yyyy-MM-dd")
							+ " 00:00:00"
							+ "' AND ws.endTime <= '"
							+ end.toString("yyyy-MM-dd") + " 23:59:59" + "'");
			workSessionsByDate = q.getResultList();
		} catch (Exception e) {
			ExceptionHandling.handleException(e);
		} finally {
			em.close();
		}
		return workSessionsByDate;
	}

	/**
	 * 
	 * @param user
	 * @param workPackage
	 * @param startTime
	 * @param endTime
	 * @throws WaktuException
	 */
	public WorkSession addWorkSession(Usr user, WorkPackage workPackage,
			GregorianCalendar startTime, GregorianCalendar endTime,
			String description) throws WaktuException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		WorkSession newWorkSession = new WorkSession(user, workPackage,
				startTime, endTime, description);

		newWorkSession.getStart().set(GregorianCalendar.MONTH,
				newWorkSession.getStart().get(GregorianCalendar.MONTH) - 1);
		newWorkSession.getEnd().set(GregorianCalendar.MONTH,
				newWorkSession.getEnd().get(GregorianCalendar.MONTH) - 1);

		if (!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}

		BusinessRuleController.check(newWorkSession);

		try {
			em.getTransaction().begin();
			em.persist(newWorkSession);
			em.getTransaction().commit();
		} catch (Exception e) {
			ExceptionHandling.handleException(e);
		} finally {
			em.close();
		}

		add.emit(newWorkSession);
		logger.info("favorite " + newWorkSession + " deleted");
		return newWorkSession;
	}

	public void updateWorkSession(WorkSession workSession)
			throws WaktuException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		if (!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}

		workSession.getStart().set(GregorianCalendar.MONTH,
				workSession.getStart().get(GregorianCalendar.MONTH) - 1);
		workSession.getEnd().set(GregorianCalendar.MONTH,
				workSession.getEnd().get(GregorianCalendar.MONTH) - 1);

		BusinessRuleController.check(workSession);

		try {
			em.getTransaction().begin();
			em.find(WorkSession.class, workSession.getId());
			em.merge(workSession);
			em.getTransaction().commit();
		} catch (Exception e) {
			ExceptionHandling.handleException(e);
		} finally {
			em.close();
		}
		update.emit();
		logger.info("workSession " + workSession + " updated");
	}

	/**
	 * 
	 * @param workSession
	 */
	public void removeWorkSession(WorkSession workSession)
			throws WaktuException {

		if (!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}

		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(em.find(WorkSession.class, workSession.getId()));
			em.getTransaction().commit();
		} catch (Exception e) {
			ExceptionHandling.handleException(e);
		} finally {
			em.close();
		}
		removed.emit(workSession);
		logger.info("workSession " + workSession + " deleted");
	}

}