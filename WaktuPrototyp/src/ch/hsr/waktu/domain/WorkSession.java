package ch.hsr.waktu.domain;

import java.util.GregorianCalendar;

/**
 * @author simon.staeheli
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
 */
public class WorkSession {

	private int id;
	private GregorianCalendar endTime;
	private GregorianCalendar startTime;
	private Usr userRef;
	private String description;
	private WorkPackage workPackageRef;

	public WorkSession(){

	}

	public int getId() {
		return id;
	}

	public GregorianCalendar getEnd() {
		return endTime;
	}

	public void setEnd(GregorianCalendar end) {
		this.endTime = end;
	}

	public GregorianCalendar getStart() {
		return startTime;
	}

	public void setStart(GregorianCalendar start) {
		this.startTime = start;
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