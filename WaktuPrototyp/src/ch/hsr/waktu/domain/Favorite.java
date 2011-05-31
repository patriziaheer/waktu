package ch.hsr.waktu.domain;

import java.util.GregorianCalendar;

/**
 * @author simon.staeheli
 * @version 1.0
 */
public class Favorite {

	private int id;
	private GregorianCalendar endTime;
	private GregorianCalendar startTime;
	private Usr usr;
	private WorkPackage workPackage;

	public Favorite() {

	}

	public Favorite(Usr userId, WorkPackage workPackage,
			GregorianCalendar startTime, GregorianCalendar endTime) {
		this.usr = userId;
		this.workPackage = workPackage;
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

	public Usr getUser() {
		return usr;
	}

	public void setUser(Usr usr) {
		this.usr = usr;
	}

	public WorkPackage getWorkPackageID() {
		return workPackage;
	}

	public void setWorkPackageID(WorkPackage workPackageID) {
		workPackage = workPackageID;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Favorite) {
			Favorite fav = (Favorite) obj;
			if (fav.id == id && fav.endTime.equals(endTime)
					&& fav.usr.equals(usr)
					&& fav.workPackage.equals(workPackage)) {
				return true;
			}
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		int hashCode = 23;
		hashCode += 31 * id;
		hashCode += 31 * endTime.hashCode();
		hashCode += 31 * startTime.hashCode();
		hashCode += 31 * usr.hashCode();
		hashCode += 31 * workPackage.hashCode();
		return hashCode;
	}

	@Override
	public String toString() {
		return id + startTime.toString() + endTime.toString()
				+ workPackage.getDescription() + usr.getUsername();
	}

}