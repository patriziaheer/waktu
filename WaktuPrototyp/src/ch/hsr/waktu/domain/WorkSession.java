package ch.hsr.waktu.domain;

import com.trolltech.qt.core.QDateTime;

/**
 * @author simon.staeheli
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
 */
public class WorkSession {

	private QDateTime end;
	private QDateTime start;
	private Usr user;
	private String description;
	private WorkPackage workPackage;

	public WorkSession(){

	}

	public QDateTime getEnd() {
		return end;
	}

	public void setEnd(QDateTime end) {
		this.end = end;
	}

	public QDateTime getStart() {
		return start;
	}

	public void setStart(QDateTime start) {
		this.start = start;
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