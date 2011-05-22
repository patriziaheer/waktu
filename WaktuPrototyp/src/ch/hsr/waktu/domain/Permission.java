package ch.hsr.waktu.domain;

public class Permission {
	private int permissionid;
	private SystemRole systemRole;
	public boolean getActiveUsers = true;
	public boolean getAllUsers = true;
	public boolean getInactiveUsers = true;
	public boolean getProjectManagers = true;
	public boolean getUser = true;
	public boolean addUser = true;
	public boolean updateUser = true;
	public boolean getActiveProjects = true;
	public boolean getInactiveProjects = true;
	public boolean getProject = true;
	public boolean getAllProjects = true;
	public boolean addProject = true;
	public boolean updateProject = true;
	public boolean getProjects = true;
	public boolean getUsers = true;
	public boolean addProjectStaff = true;
	public boolean removeUser = true;
	public boolean getFavorites = true;
	public boolean addFavorite = true;
	public boolean updateFavorite = true;
	public boolean deleteFavorite = true;
	public boolean getActiveWorkPackages = true;
	public boolean getInactiveWorkPackages = true;
	public boolean getAllWorkPackages = true;
	public boolean addWorkPackage = true;
	public boolean updateWorkPackage = true;
	public boolean getWorkSessions = true;
	public boolean addWorkSession = true;
	public boolean updateWorkSession = true;
	public boolean removeWorkSession = true;

	public Permission() {

	}

	public Permission(SystemRole systemRole) {

		this.systemRole = systemRole;

	}

	public int getId() {
		return permissionid;
	}

	public SystemRole getSystemRole() {
		return systemRole;
	}
}
