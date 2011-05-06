package ch.hsr.waktu.domain;

/**
 * @author simon.staeheli
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
 */
public class ProjectStaff {

	private int id;
	private Project project;
	private Usr user;

	public ProjectStaff() {
		
	}
	
	public ProjectStaff(Usr userId, Project projectId){
		this.project = projectId;
		this.user = userId;
	}

	public int getId() {
		return id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Usr getUser() {
		return user;
	}

	public void setUser(Usr user) {
		this.user = user;
	}

}