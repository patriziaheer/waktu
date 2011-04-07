package ch.hsr.waktu.domain;


/**
 * @author simon.staeheli
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
 */
public class Project {

	private String projectIdentifier;
	private String description;
	private boolean inactive;
	private int plannedTime;

	public Project(){

	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isInactive() {
		return inactive;
	}

	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}

	public int getPlannedTime() {
		return plannedTime;
	}

	public void setPlannedTime(int plannedTime) {
		this.plannedTime = plannedTime;
	}

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}

	@Override
	public String toString() {
		return projectIdentifier + " " + description;
	}

}