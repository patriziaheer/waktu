package ch.hsr.waktu.domain;

import java.util.GregorianCalendar;

/**
 * @author simon.staeheli
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
 */
public class Favorite {

	private int id;
	private GregorianCalendar endTime;
	private GregorianCalendar startTime;
	private Usr usrId;
	private WorkPackage workPackageId;

	public Favorite(){

	}

	public Favorite(Usr userId, WorkPackage workPackage, GregorianCalendar startTime, GregorianCalendar endTime){
		this.usrId = userId;
		this.workPackageId = workPackage;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public int getId() {
		return id;
	}

	public GregorianCalendar getEndTime() {
		return endTime;
	}

	public void setEndTime(GregorianCalendar endTime) {
		this.endTime = endTime;
	}

	public GregorianCalendar getStartTime() {
		return startTime;
	}

	public void setStartTime(GregorianCalendar startTime) {
		this.startTime = startTime;
	}

	public Usr getUserID() {
		return usrId;
	}

	public void setUserID(Usr userID) {
		usrId = userID;
	}

	public WorkPackage getWorkPackageID() {
		return workPackageId;
	}

	public void setWorkPackageID(WorkPackage workPackageID) {
		workPackageId = workPackageID;
	}


}