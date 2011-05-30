package ch.hsr.waktu.domain;

import java.util.GregorianCalendar;

/**
 * @author simon.staeheli
 * @version 1.0
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
	
	public WorkSession(Usr user, WorkPackage workPackage,
			GregorianCalendar startTime, GregorianCalendar endTime, String description){
		this.userRef = user;
		this.workPackageRef = workPackage;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
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
	
	@Override
	public String toString() {
		return id + ""; //+ " " + userRef + " " + workPackageRef.getDescription() + " " + TimeUtil.convertGregorianToQDateTime(startTime) + " - " + TimeUtil.convertGregorianToQDateTime(endTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof WorkSession) {
			WorkSession ws = (WorkSession)obj;
			if (ws.id == id &&
			ws.userRef.equals(userRef) &&
			ws.workPackageRef.equals(workPackageRef) &&
			ws.description.equals(description)) {
				return true;
			}
		}
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		int hashCode = 23;
		hashCode += 31 * id;
		hashCode += 31 * userRef.hashCode();
		hashCode += 31 * workPackageRef.hashCode();
		hashCode += 31 * description.hashCode();
		hashCode += 31 * startTime.hashCode();
		hashCode += 31 * endTime.hashCode();
		return hashCode;
	}
}