package ch.hsr.waktu.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import ch.hsr.waktu.domain.Permission;
import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.QSignalEmitter;

/**
 * @author pheer
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
 */
public class PermissionController extends QSignalEmitter {

	private static Logger logger = Logger.getLogger(PermissionController.class);
	public Signal0 update = new Signal0();
	public Signal1<Permission> add = new Signal1<Permission>();
	private static List<Permission> permissions = null;
	private static ArrayList<PermissionNode> allPermissionNodes = null;
	private static PermissionController theInstance = null;

	public static PermissionController getInstance() throws WaktuException {
		if (theInstance == null) {
			theInstance = new PermissionController();
		}
		return theInstance;
	}
	
	private PermissionController() {
		try {
			allPermissionNodes = getPermissionNodes();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	private static List<Permission> getPermissionTable() {
		if (permissions == null) {
			EntityManager em = PersistenceController.getInstance().getEMF()
					.createEntityManager();

			@SuppressWarnings("unchecked")
			List<Permission> perm = em.createQuery(
					"SELECT p FROM Permission p").getResultList();

			em.close();
			permissions = perm;
		}
		return permissions;
	}

	public Permission addPermission(SystemRole systemRole) {

		Permission newPermission = new Permission(systemRole);
		EntityManager em = PersistenceController.getInstance("waktu").getEMF()
				.createEntityManager();
		em.getTransaction().begin();
		em.persist(newPermission);
		em.flush();
		em.getTransaction().commit();
		add.emit(newPermission);

		return newPermission;

	}

	public static ArrayList<PermissionNode> getPermissionNodes() throws IllegalArgumentException, IllegalAccessException {
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
	
	public void reloadPermissions() throws WaktuException {
		try {
			permissions = null;
			allPermissionNodes = getPermissionNodes();
		} catch(IllegalAccessException e) {
			throw new WaktuException("General problem");
		} catch(IllegalArgumentException e) {
			throw new WaktuException("General problem");
		}

	}

	public boolean checkPermission() throws WaktuException {
		StackTraceElement[] trace = new Throwable().getStackTrace();
		return checkPermission(trace[1].getMethodName());
	}

	public boolean checkPermission(String method) throws WaktuException {
		boolean permission = false;
		try {
			for (PermissionNode pn : allPermissionNodes) {
				if ((pn.getSystemRole().equals(LoginController.getInstance()
						.getLoggedInUser().getSystemRole()))
						&& (pn.getMethod().equals(method))) {
					permission = pn.getPermission();
				}
			}
		} catch (Exception e) {
			throw new WaktuException("checkPermission() error");
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