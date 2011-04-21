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
}