package ch.hsr.waktu.domain;

/**
 * @author simon.staeheli
 * @version 1.0
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

	@Override
	public String toString() {
		return id + " " + project.getProjectIdentifier() + " " + user.getUsername();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ProjectStaff) {
			ProjectStaff ps = (ProjectStaff)obj;
			if (ps.id == id &&
			ps.project.equals(project) &&
			ps.user.equals(user)) {
				return true;
			}
		}
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		int hashCode = 23;
		hashCode += 31 * id;
		hashCode += 31 * project.hashCode();
		hashCode += 31 * user.hashCode();
		return hashCode;
	}
}