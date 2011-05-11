package ch.hsr.waktu.domain;

public class Permission {
	private int permissionid;
	private SystemRole systemRole;
	public boolean addUser;
	public boolean updateUser;
	public boolean addProject;
	public boolean updateProject;
	public boolean addFavorite;
	public boolean updateFavorite;
	public boolean deleteFavorite;

	public Permission() {

	}

	public Permission(SystemRole systemRole, boolean addUser,
			boolean updateUser, boolean addProject, boolean updateProject,
			boolean addFavorite, boolean updateFavorite, boolean deleteFavorite) {

		this.systemRole = systemRole;
		this.addUser = addUser;
		this.updateUser = updateUser;
		this.addProject = addProject;
		this.updateProject = updateProject;
		this.addFavorite = addFavorite;
		this.updateFavorite = updateFavorite;
		this.deleteFavorite = deleteFavorite;
	}

	public int getId() {
		return permissionid;
	}

	public SystemRole getSystemRole() {
		return systemRole;
	}
}
