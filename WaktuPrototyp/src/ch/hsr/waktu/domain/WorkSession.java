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
	private Usr user;
	private String description;
	private WorkPackage workPackage;

	public WorkSession(){

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
		return user;
	}

	public void setUser(Usr user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public WorkPackage getWorkPackage() {
		return workPackage;
	}

	public void setWorkPackage(WorkPackage workPackage) {
		this.workPackage = workPackage;
	}

}