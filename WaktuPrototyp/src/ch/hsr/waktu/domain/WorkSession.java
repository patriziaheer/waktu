package ch.hsr.waktu.domain;

import java.util.GregorianCalendar;

/**
 * @author simon.staeheli
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
 */
public class WorkSession {

	private int id;
	private GregorianCalendar end;
	private GregorianCalendar start;
	private Usr userRef;
	private String description;
	private WorkPackage workPackageRef;

	public WorkSession(){

	}
	
	public WorkSession(Usr user, WorkPackage workPackage,
			GregorianCalendar startTime, GregorianCalendar endTime){
		this.userRef = user;
		this.workPackageRef = workPackage;
		this.start = startTime;
		this.end = endTime;
	}

	public int getId() {
		return id;
	}

	public GregorianCalendar getEnd() {
		return end;
	}

	public void setEnd(GregorianCalendar end) {
		this.end = end;
	}

	public GregorianCalendar getStart() {
		return start;
	}

	public void setStart(GregorianCalendar start) {
		this.start = start;
	}

	public Usr getUser() {
		return userRef;
	}

	public void setUser(Usr user) {
		this.userRef = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public WorkPackage getWorkPackage() {
		return workPackageRef;
	}

	public void setWorkPackage(WorkPackage workPackage) {
		this.workPackageRef = workPackage;
	}

}