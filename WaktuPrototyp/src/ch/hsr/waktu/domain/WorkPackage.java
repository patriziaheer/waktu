package ch.hsr.waktu.domain;

/**
 * @author simon.staeheli
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
 */
public class WorkPackage {

	private int id;
	private String description;
	private boolean active = true;
	private Project project;
	
	public WorkPackage(){
	}
	
	public WorkPackage(Project project, String description){
		this.project = project;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActiveState(boolean toActive) {
		this.active = toActive;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	@Override
	public String toString() {
		return description;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof WorkPackage) {
			WorkPackage wp = (WorkPackage)obj;
			if (wp.id == id &&
			wp.description.equals(description) &&
			wp.active == active &&
			wp.project.equals(project)) {
				return true;
			}
		}
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		int hashCode = 23;
		hashCode = 31 * id;
		hashCode = 31 * description.hashCode();
		hashCode = 31 * project.hashCode();
		return hashCode;
	}
}