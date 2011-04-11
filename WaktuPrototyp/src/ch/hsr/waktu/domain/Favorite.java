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
	private Usr UserID;
	private WorkPackage WorkPackageID;

	public Favorite(){

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
		return UserID;
	}

	public void setUserID(Usr userID) {
		UserID = userID;
	}

	public WorkPackage getWorkPackageID() {
		return WorkPackageID;
	}

	public void setWorkPackageID(WorkPackage workPackageID) {
		WorkPackageID = workPackageID;
	}


}