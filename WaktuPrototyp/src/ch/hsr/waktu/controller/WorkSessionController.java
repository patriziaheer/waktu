package ch.hsr.waktu.controller;

import java.util.List;

import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.domain.WorkSession;

import com.trolltech.qt.core.QDateTime;

/**
 * @author simon.staeheli
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
 */
public class WorkSessionController {

	private static WorkSessionController theInstance = null;
	
	public static WorkSessionController getInstance() {
		if (theInstance == null) {
			theInstance = new WorkSessionController();
		}
		return theInstance;
	}
	
	private WorkSessionController(){

	}


	/**
	 * 
	 * @param user
	 * @param workPackage
	 * @param startTime
	 * @param endTime
	 */
	public boolean addWorkSession(Usr user, WorkPackage workPackage, QDateTime startTime, QDateTime endTime){
		return false;
	}

	/**
	 * 
	 * @param user
	 */
	public List<WorkSession> getWorkSessions(Usr user){
		return null;
	}

	/**
	 * 
	 * @param user
	 * @param date
	 */
	public List<WorkSession> getWorkSessions(Usr user, QDateTime date){
		return null;
	}

	/**
	 * 
	 * @param workSession
	 */
	public boolean removeWorkSession(WorkSession workSession){
		return false;
	}

}