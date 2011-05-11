package ch.hsr.waktu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import ch.hsr.waktu.domain.Permission;
import ch.hsr.waktu.domain.SystemRole;

import com.trolltech.qt.QSignalEmitter;

/**
 * @author pheer
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
 */
public class PermissionController extends QSignalEmitter {

	private static PermissionController theInstance = null;

	public static PermissionController getInstance() {
		if (theInstance == null) {
			theInstance = new PermissionController();
		}
		return theInstance;
	}

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(PermissionController.class);
	public Signal0 update = new Signal0();
	public Signal1<Permission> add = new Signal1<Permission>();

	private static List<Permission> permissionTable;

	protected PermissionController() {
		permissionTable = getPermissionTable();
	}

	private List<Permission> getPermissionTable() {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		@SuppressWarnings("unchecked")
		List<Permission> permissions = em.createQuery(
				"SELECT p FROM Permission p").getResultList();

		em.close();
		return permissions;
	}

	public Permission addPermission(SystemRole systemRole, boolean addUser,
			boolean updateUser, boolean addProject, boolean updateProject,
			boolean addFavorite, boolean updateFavorite, boolean deleteFavorite) {

		Permission newPermission = new Permission(systemRole, addUser,
				updateUser, addProject, updateProject, addFavorite,
				updateFavorite, deleteFavorite);
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		em.getTransaction().begin();
		em.persist(newPermission);
		em.flush();
		em.getTransaction().commit();
		add.emit(newPermission);

		return newPermission;

	}

	public ArrayList<PermissionNode> getPermissions() {
		ArrayList<PermissionNode> list = new ArrayList<PermissionNode>();

		for (Permission perm : permissionTable) {
			
			list.add(new PermissionNode(perm.getSystemRole(), "addFavorite", perm.getAddFavorite()));
			list.add(new PermissionNode(perm.getSystemRole(), "addProject", perm.getAddProject()));
			list.add(new PermissionNode(perm.getSystemRole(), "addUser", perm.getAddUser()));
			list.add(new PermissionNode(perm.getSystemRole(), "deleteFavorite", perm.getDeleteFavorite()));
			list.add(new PermissionNode(perm.getSystemRole(), "updateFavorite", perm.getUpdateFavorite()));
			list.add(new PermissionNode(perm.getSystemRole(), "updateProject", perm.getUpdateProject()));
			list.add(new PermissionNode(perm.getSystemRole(), "updateUser", perm.getUpdateUser()));
		}

		return list;

	}

	public boolean checkPermission(String method) {

		for (PermissionNode pn : getPermissions()) {
			if((pn.getSystemRole().equals(LoginController.getInstance().getLoggedInUser().getSystemRole())) && (pn.getMethod().equals(method))) {
				return pn.getPermission();
			}
		}

		return false;
	}
}

class PermissionNode {

	public PermissionNode(SystemRole systemRole, String method,
			boolean permission) {
		this.systemRole = systemRole;
		this.method = method;
		this.permission = permission;

	}

	private SystemRole systemRole;
	private String method;
	private boolean permission;

	public SystemRole getSystemRole() {
		return systemRole;
	}

	public boolean getPermission() {
		return permission;
	}
	
	public String getMethod() {
		return method;
	}

}