package ch.hsr.waktu.controller.datacontroller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.PersistenceController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.domain.WorkSession;
import ch.hsr.waktu.services.TimeUtil;

import com.trolltech.qt.QSignalEmitter;
import com.trolltech.qt.core.QDate;
import com.trolltech.qt.core.QDateTime;

/**
 * @author simon.staeheli
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
 */
public class WorkSessionController extends QSignalEmitter {

	private static WorkSessionController theInstance = null;

	public static WorkSessionController getInstance() {
		if (theInstance == null) {
			theInstance = new WorkSessionController();
		}
		return theInstance;
	}

	private Logger logger = Logger.getLogger(UserController.class);
	public Signal0 update = new Signal0();
	public Signal1<WorkSession> add = new Signal1<WorkSession>();
	public Signal1<WorkSession> removed = new Signal1<WorkSession>();

	private WorkSessionController() {

	}

	/**
	 * 
	 * @param user
	 * @param workPackage
	 * @param startTime
	 * @param endTime
	 */
	public WorkSession addWorkSession(Usr user, WorkPackage workPackage,
			GregorianCalendar startTime, GregorianCalendar endTime, String description) {
		WorkSession newWorkSession = new WorkSession(user, workPackage,
				startTime, endTime, description);
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		em.getTransaction().begin();
		em.persist(newWorkSession);
		em.flush();
		em.getTransaction().commit();
		logger.info("WorkSession added");
		add.emit(newWorkSession);
		return newWorkSession;
	}

	/**
	 * 
	 * @param user
	 */
	public List<WorkSession> getWorkSessions(Usr user) {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		@SuppressWarnings("unchecked")
		List<WorkSession> workSessionsByUser = em.createQuery(
				"SELECT ws FROM WorkSession ws").getResultList();

		ArrayList<WorkSession> workSessions = new ArrayList<WorkSession>();
		for (WorkSession ws : workSessionsByUser) {

			if (ws.getUser().equals(user)) {
				workSessions.add(ws);
				//logger.info("WORKSESSION: " + ws.toString());
			}

		}

		em.close();
		return workSessions;
	}

	/**
	 * 
	 * @param user
	 * @param date
	 */
	public List<WorkSession> getWorkSessions(Usr user, QDate date) {
		//TODO filter by date
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		@SuppressWarnings("unchecked")
		List<WorkSession> workSessionsByUser = em.createQuery(
				"SELECT ws FROM WorkSession ws ORDER BY ws.id").getResultList();

		ArrayList<WorkSession> workSessions = new ArrayList<WorkSession>();
		for (WorkSession ws : workSessionsByUser) {

			if (ws.getUser().equals(user)) {
				workSessions.add(ws);
				//logger.info("WORKSESSION: " + ws.toString());
			}

		}

		em.close();
		return workSessions;
	}
	
	public List<WorkSession> getWorkSessionsStartingAt(Usr user, QDate startDate) {
		EntityManager em = PersistenceController.getInstance().getEMF()
		.createEntityManager();

		@SuppressWarnings("unchecked")
		List<WorkSession> workSessionsByUser = em.createQuery(
		"SELECT ws FROM WorkSession ws ORDER BY ws.id").getResultList();

		ArrayList<WorkSession> workSessions = new ArrayList<WorkSession>();
		for (WorkSession ws : workSessionsByUser) {
			if (ws.getUser().equals(user) && ws.getStart().equals(TimeUtil.convertQDateTimeToGregorian(new QDateTime(startDate, null)))) {
				workSessions.add(ws);
				//logger.info("WORKSESSION: " + ws.toString());
			}

		}

		em.close();
		return workSessions;
	}

	public List<WorkSession> getWorkSessions(Project project) {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		@SuppressWarnings("unchecked")
		List<WorkSession> workSessionsByUser = em.createQuery(
				"SELECT ws FROM WorkSession ws").getResultList();

		ArrayList<WorkSession> workSessions = new ArrayList<WorkSession>();
		for (WorkSession ws : workSessionsByUser) {

			if (ws.getWorkPackage().getProject().equals(project)) {
				workSessions.add(ws);
				//logger.info("WORKSESSION: " + ws.toString());
			}

		}

		em.close();
		return workSessions;
	}

	/**
	 * 
	 * @param workSession
	 */
	
	
	// TODO: funktioniert noch nicht..
	
	public boolean removeWorkSession(WorkSession workSession) {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		em.getTransaction().begin();
		em.remove(workSession);
		em.getTransaction().commit();
		em.close();
		removed.emit(workSession);
		return true;
	}

	public boolean updateWorkSession(WorkSession workSession) {
		//TODO: added by ph
		EntityManager em = PersistenceController.getInstance().getEMF().createEntityManager();

		em.getTransaction().begin();
		WorkSession updateWorkSession = (WorkSession) em.createQuery("SELECT ws FROM WorkSession ws WHERE ws.id = " + workSession.getId()).getSingleResult();

		updateWorkSession.setDescription(workSession.getDescription());
		updateWorkSession.setStart(workSession.getStart());
		updateWorkSession.setEnd(workSession.getEnd());

		em.getTransaction().commit();
		logger.info("Favorite " + workSession.getId() + " updated");
		
		update.emit();
		return true;
	}

}