package ch.hsr.waktu.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.datacontroller.WaktuGeneralException;
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

	private Logger logger = Logger.getLogger(PermissionController.class);
	public Signal0 update = new Signal0();
	public Signal1<Permission> add = new Signal1<Permission>();

	protected PermissionController() {
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

	public Permission addPermission(SystemRole systemRole) {

		Permission newPermission = new Permission(systemRole);
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		em.getTransaction().begin();
		em.persist(newPermission);
		em.flush();
		em.getTransaction().commit();
		add.emit(newPermission);

		return newPermission;

	}

	public ArrayList<PermissionNode> getPermissionNodes()
			throws IllegalArgumentException, IllegalAccessException {
		ArrayList<PermissionNode> list = new ArrayList<PermissionNode>();

		for (Permission p : getPermissionTable()) {
			Field[] allPermissions = Permission.class.getFields();
			for (Field f : allPermissions) {
				list.add(new PermissionNode(p.getSystemRole(), f.getName()
						.toString(), f.getBoolean(p)));
			}
		}

		return list;

	}

	public boolean checkPermission() throws WaktuGeneralException {
		StackTraceElement[] trace = new Throwable().getStackTrace();
		return checkPermission(trace[1].getMethodName());
	}

	public boolean checkPermission(String method) throws WaktuGeneralException {

		boolean permission = false;
		try {
			for (PermissionNode pn : getPermissionNodes()) {
				if ((pn.getSystemRole().equals(LoginController.getInstance()
						.getLoggedInUser().getSystemRole()))
						&& (pn.getMethod().equals(method))) {
					permission = pn.getPermission();
				}
			}
		} catch (Exception e) {
			throw new WaktuGeneralException("checkPermission() error");
		}

		if (permission) {
			logger.info("Permission allowed: "
					+ LoginController.getInstance().getLoggedInUser() + " "
					+ method + "()");
			return permission;
		}
		logger.info("Permission denied: "
				+ LoginController.getInstance().getLoggedInUser() + " "
				+ method + "()");
		return permission;
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