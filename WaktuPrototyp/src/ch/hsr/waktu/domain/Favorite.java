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
	private Usr UserId;
	private WorkPackage WorkPackageId;

	public Favorite(){

	}

	public Favorite(Usr userId, WorkPackage workPackage, GregorianCalendar startTime, GregorianCalendar endTime){
		this.UserId = userId;
		this.WorkPackageId = workPackage;
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
		return UserId;
	}

	public void setUserID(Usr userID) {
		UserId = userID;
	}

	public WorkPackage getWorkPackageID() {
		return WorkPackageId;
	}

	public void setWorkPackageID(WorkPackage workPackageID) {
		WorkPackageId = workPackageID;
	}


}