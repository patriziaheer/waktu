package ch.hsr.waktu.domain;

/**
 * @author simon.staeheli
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
 */
public class ProjectStaff {

	private int id;
	private Project ProjectID;
	private Usr UserID;

	public ProjectStaff(){

	}

	public int getId() {
		return id;
	}

	public Project getProjectID() {
		return ProjectID;
	}

	public void setProjectID(Project projectID) {
		ProjectID = projectID;
	}

	public Usr getUserID() {
		return UserID;
	}

	public void setUserID(Usr userID) {
		UserID = userID;
	}

}