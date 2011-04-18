package ch.hsr.waktu.domain;

/**
 * @author simon.staeheli
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
 */
public class ProjectStaff {

	private int id;
	private Project Project;
	private Usr User;

	public ProjectStaff() {
		
	}
	
	public ProjectStaff(Usr userId, Project projectId){
		this.Project = projectId;
		this.User = userId;
	}

	public int getId() {
		return id;
	}

	public Project getProject() {
		return Project;
	}

	public void setProject(Project project) {
		Project = project;
	}

	public Usr getUser() {
		return User;
	}

	public void setUser(Usr user) {
		User = user;
	}

}