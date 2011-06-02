package ch.hsr.waktu.controller.datacontroller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.BusinessRuleController;
import ch.hsr.waktu.controller.PermissionController;
import ch.hsr.waktu.controller.PersistenceController;
import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.services.ExceptionHandling;
import ch.hsr.waktu.services.Md5;
import ch.hsr.waktu.services.UsernameUtil;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.QSignalEmitter;

public class UserController extends QSignalEmitter {
	
	protected UserController() { }

    private static UserController theInstance = null;
    
    private Logger logger = Logger.getLogger(UserController.class);
    public Signal0 update = new Signal0();
    public Signal1<Usr> add = new Signal1<Usr>();

    /**
     * 
     * @return UserController
     */
    public static UserController getInstance() {
        if (theInstance == null) {
            theInstance = new UserController();
        }
        return theInstance;
    }
    
    /**
     * 
     * @param userControllerInstance
     */
    public static void setInstance(final UserController userControllerInstance) {
        theInstance = userControllerInstance;
    }

    /**
     * 
     * @return List<Usr>
     * @throws WaktuException
     */
    @SuppressWarnings("unchecked")
    public List<Usr> getActiveUsers() throws WaktuException {
        EntityManager em = PersistenceController.getInstance().getEMF()
                .createEntityManager();

        List<Usr> activeUsers = null;

        if (!PermissionController.getInstance().checkPermission()) {
            throw new WaktuException("Permission denied");
        }

        try {
            activeUsers = em
                    .createQuery(
                            "SELECT u FROM Usr u WHERE u.active = 'TRUE' ORDER BY u.firstname")
                    .getResultList();
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        } finally {
            em.close();
        }

        return activeUsers;
    }

    /**
     * 
     * @return List<Usr>
     * @throws WaktuException
     */
    @SuppressWarnings("unchecked")
    public List<Usr> getAllUsers() throws WaktuException {
        EntityManager em = PersistenceController.getInstance().getEMF()
                .createEntityManager();

        List<Usr> allUsers = null;

        if (!PermissionController.getInstance().checkPermission()) {
            throw new WaktuException("Permission denied");
        }

        try {
            allUsers = em.createQuery(
                    "SELECT u FROM Usr u ORDER BY u.firstname").getResultList();
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        } finally {
            em.close();
        }

        return allUsers;
    }

    /**
     * 
     * @return List<Usr>
     * @throws WaktuException
     */
    @SuppressWarnings("unchecked")
    public List<Usr> getInactiveUsers() throws WaktuException {
        EntityManager em = PersistenceController.getInstance().getEMF()
                .createEntityManager();

        List<Usr> inactiveUsers = null;

        if (!PermissionController.getInstance().checkPermission()) {
            throw new WaktuException("Permission denied");
        }

        try {
            inactiveUsers = em
                    .createQuery(
                            "SELECT u FROM Usr u WHERE u.active = 'FALSE' ORDER BY u.firstname")
                    .getResultList();
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        } finally {
            em.close();
        }

        return inactiveUsers;
    }

    /**
     * 
     * @return List<Usr>
     * @throws WaktuException
     */
    @SuppressWarnings("unchecked")
    public List<Usr> getProjectManagers() throws WaktuException {
        EntityManager em = PersistenceController.getInstance().getEMF()
                .createEntityManager();

        List<Usr> allProjectManagers = null;

        if (!PermissionController.getInstance().checkPermission()) {
            throw new WaktuException("Permission denied");
        }

        try {
            allProjectManagers = em
                    .createQuery(
                            "SELECT u FROM Usr u JOIN u.systemRole s WHERE u.systemRole = ch.hsr.waktu.domain.SystemRole.PROJECTMANAGER OR u.systemRole = ch.hsr.waktu.domain.SystemRole.ADMIN ORDER BY u.firstname")
                    .getResultList();
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        } finally {
            em.close();
        }

        return allProjectManagers;
    }

    /**
     * 
     * @param username
     * @return Usr
     * @throws WaktuException
     */
    public Usr getUser(final String username) throws WaktuException {
        EntityManager em = PersistenceController.getInstance().getEMF()
                .createEntityManager();

        Usr usr = null;

        try {
            usr = (Usr) em
                    .createQuery(
                            "SELECT u FROM Usr u WHERE u.username = '"
                                    + username + "'").getSingleResult();
        } catch (NoResultException e) {
            throw new WaktuException("User or password wrong!");
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        } finally {
            em.close();
        }

        return usr;
    }

    /**
     * 
     * @param firstname
     * @param lastname
     * @param password
     * @param pensum
     * @param role
     * @param holiday
     * @return Usr
     * @throws WaktuException
     */
    public Usr addUser(final String firstname, final String lastname,
            final String password, final int pensum, final SystemRole role,
            final double holiday) throws WaktuException {
        EntityManager em = PersistenceController.getInstance().getEMF()
                .createEntityManager();

        Usr newUsr = new Usr(generateUsername(firstname, lastname), firstname,
                lastname, Md5.hash(password), pensum, role, holiday);

        if (!PermissionController.getInstance().checkPermission()) {
            throw new WaktuException("Permission denied");
        }

        BusinessRuleController.check(newUsr);

        try {
            em.getTransaction().begin();
            em.persist(newUsr);
            em.getTransaction().commit();
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        } finally {
            em.close();
        }

        add.emit(newUsr);
        logger.info("user " + newUsr + " added");
        return newUsr;
    }

    /**
     * 
     * @param usr
     * @throws WaktuException
     */
    public void updateUser(final Usr usr) throws WaktuException {
        EntityManager em = PersistenceController.getInstance().getEMF()
                .createEntityManager();

        if (!PermissionController.getInstance().checkPermission()) {
            throw new WaktuException("Permission denied");
        }

        BusinessRuleController.check(usr);

        try {
            em.getTransaction().begin();
            em.merge(usr);
            em.getTransaction().commit();
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        } finally {
            em.close();
        }
        update.emit();
        logger.info("user " + usr + " updated");

    }

    /**
     * 
     * @param firstname
     * @param name
     * @return String
     * @throws WaktuException
     */
    protected String generateUsername(final String firstname, final String name)
            throws WaktuException {
        return UsernameUtil.generateUsername(getAllUsers(), firstname, name);
    }

}
