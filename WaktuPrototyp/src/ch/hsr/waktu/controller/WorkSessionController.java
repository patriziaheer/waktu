package ch.hsr.waktu.controller;

import java.util.ArrayList;
import java.util.List;

import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.domain.WorkSession;

import com.trolltech.qt.core.QDate;
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
	public WorkSession addWorkSession(Usr user, WorkPackage workPackage, QDateTime startTime, QDateTime endTime){
		return null;
	}

	/**
	 * 
	 * @param user
	 */
	public List<WorkSession> getWorkSessions(Usr user){
		return new ArrayList<WorkSession>();
	}

	/**
	 * 
	 * @param user
	 * @param date
	 */
	public List<WorkSession> getWorkSessions(Usr user, QDate date){
		return new ArrayList<WorkSession>();
	}

	public List<WorkSession> getWorkSessions(Project project) {
		return new ArrayList<WorkSession>();
	}

	/**
	 * 
	 * @param workSession
	 */
	public boolean removeWorkSession(WorkSession workSession){
		return false;
	}

}