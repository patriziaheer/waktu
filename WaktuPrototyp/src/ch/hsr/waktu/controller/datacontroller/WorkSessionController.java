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

import com.trolltech.qt.QSignalEmitter;
import com.trolltech.qt.core.QDate;

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
			GregorianCalendar startTime, GregorianCalendar endTime) {
		WorkSession newWorkSession = new WorkSession(user, workPackage,
				startTime, endTime);
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		em.getTransaction().begin();
		em.persist(newWorkSession);
		em.flush();
		em.getTransaction().commit();
		// TODO: add.emit() wieder einschalten (Observer von QT)
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
				logger.info("WORKSESSION: " + ws.toString());
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
	 return new ArrayList<WorkSession>();
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
				logger.info("WORKSESSION: " + ws.toString());
			}

		}

		em.close();
		return workSessions;
	}

	/**
	 * 
	 * @param workSession
	 */
	public boolean removeWorkSession(WorkSession workSession) {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		em.createQuery("DELETE WorkSession ws WHERE ws.id = "
				+ workSession.getId());
		em.close();
		return true;
	}

}