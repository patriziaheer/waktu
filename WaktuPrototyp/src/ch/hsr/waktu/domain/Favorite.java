package ch.hsr.waktu.domain;

import com.trolltech.qt.core.QTime;

/**
 * @author simon.staeheli
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
 */
public class Favorite {

	private QTime endTime;
	private QTime startTime;
	private Usr UserID;
	private WorkPackage WorkPackageID;

	public Favorite(){

	}

	public QTime getEndTime() {
		return endTime;
	}

	public void setEndTime(QTime endTime) {
		this.endTime = endTime;
	}

	public QTime getStartTime() {
		return startTime;
	}

	public void setStartTime(QTime startTime) {
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