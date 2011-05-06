package ch.hsr.waktu.domain;

public class Permission {
	private int permissionid;
	@SuppressWarnings("unused")
	private SystemRole systemRole;
	private boolean addUser;
	private boolean updateUser;
	private boolean addProject;
	private boolean updateProject;
	private boolean addFavorite;
	private boolean updateFavorite;
	private boolean deleteFavorite;

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

	public boolean getAddUser() {
		return addUser;
	}

	public void setAddUser(boolean addUser) {
		this.addUser = addUser;
	}

	public boolean getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(boolean updateUser) {
		this.updateUser = updateUser;
	}

	public boolean getAddProject() {
		return addProject;
	}

	public void setAddProject(boolean addProject) {
		this.addProject = addProject;
	}

	public boolean getUpdateProject() {
		return updateProject;
	}

	public void setUpdateProject(boolean updateProject) {
		this.updateProject = updateProject;
	}

	public boolean getAddFavorite() {
		return addFavorite;
	}

	public void setAddFavorite(boolean addFavorite) {
		this.addFavorite = addFavorite;
	}

	public boolean getUpdateFavorite() {
		return updateFavorite;
	}

	public void setUpdateFavorite(boolean updateFavorite) {
		this.updateFavorite = updateFavorite;
	}

	public boolean getDeleteFavorite() {
		return deleteFavorite;
	}

	public void setDeleteFavorite(boolean deleteFavorite) {
		this.deleteFavorite = deleteFavorite;
	}

}
