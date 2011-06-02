package ch.hsr.waktu.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import ch.hsr.waktu.domain.Permission;
import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.services.ExceptionHandling;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.QSignalEmitter;

public class PermissionController extends QSignalEmitter {
	
	private PermissionController() throws WaktuException {
		try {
			allPermissionNodes = getPermissionNodes();
		} catch (Exception e) {
			ExceptionHandling.handleException(e);
		} 
	}

    private static Logger logger = Logger.getLogger(PermissionController.class);
    public Signal0 update = new Signal0();
    public Signal1<Permission> add = new Signal1<Permission>();
    private static List<Permission> permissions = null;
    private static ArrayList<PermissionNode> allPermissionNodes = null;
    private static PermissionController theInstance = null;

    /**
     * 
     * @return PermissionController
     * @throws WaktuException
     */
    public static PermissionController getInstance() throws WaktuException {
        if (theInstance == null) {
            theInstance = new PermissionController();
        }
        return theInstance;
    }

    /**
     * 
     * @return List<Permission>
     * @throws WaktuException
     */
    private static List<Permission> getPermissionTable() throws WaktuException {
        if (permissions == null) {
            EntityManager em = PersistenceController.getInstance().getEMF()
                    .createEntityManager();

            @SuppressWarnings("unchecked")
            List<Permission> perm = em
                    .createQuery("SELECT p FROM Permission p").getResultList();

            em.close();
            permissions = perm;
        }
        return permissions;
    }

    /**
     * 
     * @param systemRole
     * @return Permission
     * @throws WaktuException
     */
    public Permission addPermission(final SystemRole systemRole)
            throws WaktuException {

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

    /**
     * 
     * @return ArrayList<PermissionNode>
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws SecurityException
     * @throws WaktuException
     */
    public static ArrayList<PermissionNode> getPermissionNodes()
            throws IllegalArgumentException, IllegalAccessException,
            SecurityException, WaktuException {
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

    /**
     * 
     * @throws WaktuException
     */
    public void reloadPermissions() throws WaktuException {
        try {
            permissions = null;
            allPermissionNodes = getPermissionNodes();
        } catch (IllegalAccessException e) {
            throw new WaktuException("General problem");
        } catch (IllegalArgumentException e) {
            throw new WaktuException("General problem");
        }

    }

    /**
     * 
     * @return
     * @throws WaktuException
     */
    public boolean checkPermission() throws WaktuException {
        StackTraceElement[] trace = new Throwable().getStackTrace();
        return checkPermission(trace[1].getMethodName());
    }

    /**
     * 
     * @param method
     * @return boolean
     * @throws WaktuException
     */
    public boolean checkPermission(final String method) throws WaktuException {
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

	/**
	 * 
	 * @param systemRole
	 * @param method
	 * @param permission
	 */
    public PermissionNode(final SystemRole systemRole, final String method,
            final boolean permission) {
        this.systemRole = systemRole;
        this.method = method;
        this.permission = permission;

    }

    private SystemRole systemRole;
    private String method;
    private boolean permission;

    /**
     * 
     * @return SystemRole
     */
    public SystemRole getSystemRole() {
        return systemRole;
    }

    /**
     * 
     * @return boolean
     */
    public boolean getPermission() {
        return permission;
    }

    /**
     * 
     * @return String
     */
    public String getMethod() {
        return method;
    }

}
