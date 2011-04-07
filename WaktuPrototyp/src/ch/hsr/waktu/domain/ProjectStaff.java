package ch.hsr.waktu.domain;

/**
 * @author simon.staeheli
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
 */
public class ProjectStaff {

	private Project ProjectID;
	private Usr UserID;
	public Project m_Project;
	public Usr m_Usr;

	public ProjectStaff(){

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

	public Project getM_Project() {
		return m_Project;
	}

	public void setM_Project(Project m_Project) {
		this.m_Project = m_Project;
	}

	public Usr getM_Usr() {
		return m_Usr;
	}

	public void setM_Usr(Usr m_Usr) {
		this.m_Usr = m_Usr;
	}

}