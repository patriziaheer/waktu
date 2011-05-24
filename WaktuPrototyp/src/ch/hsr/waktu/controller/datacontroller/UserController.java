package ch.hsr.waktu.controller.datacontroller;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.BusinessRuleController;
import ch.hsr.waktu.controller.PermissionController;
import ch.hsr.waktu.controller.PersistenceController;
import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.services.Md5;
import ch.hsr.waktu.services.UsernameUtil;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.QSignalEmitter;
import com.trolltech.qt.core.QCoreApplication;

/**
 * @author simon.staeheli
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
 */
public class UserController extends QSignalEmitter {
	
	public enum UserProperties {
		Data {
			@Override
			public String toString() {
				return QCoreApplication.translate("UserProperties", "Data");
			}
		}, 
		Projects {
			@Override
			public String toString() {
				return QCoreApplication.translate("UserProperties", "Projects");
			}
		}, 
		WorkSessions {
			@Override
			public String toString() {
				return QCoreApplication.translate("UserProperties", "WorkSessions");
			}
		}
	}

	private static UserController theInstance = null;

	public static UserController getInstance() {
		if (theInstance == null) {
			theInstance = new UserController();
		}
		return theInstance;
	}

	public static void setInstance(UserController userControllerInstance) {
		theInstance = userControllerInstance;
	}

	private Logger logger = Logger.getLogger(UserController.class);
	public Signal0 update = new Signal0();
	public Signal1<Usr> add = new Signal1<Usr>();

	protected UserController() {

	}

	@SuppressWarnings("unchecked")
	public List<Usr> getActiveUsers() throws WaktuException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		List<Usr> activeUsers = null;

		if (!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}

		try {
			activeUsers = em.createQuery(
					"SELECT u FROM Usr u WHERE u.active = 'TRUE' ORDER BY u.firstname")
					.getResultList();
		} catch (Exception e) {
			handleException(e);			
		} finally {
			em.close();
		}

		return activeUsers;
	}

	@SuppressWarnings("unchecked")
	public List<Usr> getAllUsers() throws WaktuException {
		EntityManager em = PersistenceController.getInstance("waktu").getEMF()
				.createEntityManager();

		List<Usr> allUsers = null;

		if (!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}

		try {
			allUsers = em.createQuery("SELECT u FROM Usr u ORDER BY u.firstname").getResultList();
		} catch (Exception e) {
			handleException(e);			
		} finally {
			em.close();
		}

		return allUsers;
	}

	@SuppressWarnings("unchecked")
	public List<Usr> getInactiveUsers() throws WaktuException {
		EntityManager em = PersistenceController.getInstance("waktu").getEMF()
				.createEntityManager();

		List<Usr> inactiveUsers = null;

		if (!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}

		try {
			inactiveUsers = em.createQuery(
					"SELECT u FROM Usr u WHERE u.active = 'FALSE' ORDER BY u.firstname")
					.getResultList();
		} catch (Exception e) {
			handleException(e);			
		} finally {
			em.close();
		}

		return inactiveUsers;
	}

	@SuppressWarnings("unchecked")
	public List<Usr> getProjectManagers() throws WaktuException {
		EntityManager em = PersistenceController.getInstance("waktu").getEMF()
				.createEntityManager();

		List<Usr> allProjectManagers = null;

		if (!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission denied");
		}

		try {
			allProjectManagers = em.createQuery(
					"SELECT u FROM Project p JOIN p.projectManager u ORDER BY u.firstname")
					.getResultList();
		} catch (Exception e) {
			handleException(e);			
		} finally {
			em.close();
		}

		return allProjectManagers;
	}

	/**
	 * 
	 * @param loginName
	 */
	public Usr getUser(String username) throws WaktuException {
		EntityManager em = PersistenceController.getInstance("waktu").getEMF()
				.createEntityManager();

		Usr usr = null;

		try {
			usr = (Usr) em
					.createQuery(
							"SELECT u FROM Usr u WHERE u.username = '"
									+ username + "'").getSingleResult();
		} catch (Exception e) {
			handleException(e);			
		} finally {
			em.close();
		}

		return usr;
	}

	/**
	 * 
	 * @param username
	 * @param name
	 * @param firstname
	 * @param pensum
	 * @param role
	 */
	public Usr addUser(String firstname, String lastname, String password,
			int pensum, SystemRole role, double holiday) throws WaktuException {
		EntityManager em = PersistenceController.getInstance("waktu").getEMF()
				.createEntityManager();

		Usr newUsr = new Usr(generateUsername(firstname, lastname), firstname,
				lastname, Md5.hash(password), pensum, role, holiday);

		if (!PermissionController.getInstance().checkPermission()) {
			throw new WaktuException("Permission deniedd");
		}

		BusinessRuleController.check(newUsr);

		try {
			em.getTransaction().begin();
			em.persist(newUsr);
			em.getTransaction().commit();
		} catch (Exception e) {
			handleException(e);			
		} finally {
			em.close();
		}

		add.emit(newUsr);
		logger.info("user " + newUsr + " added");
		return newUsr;
	}

	/**
	 * Attention, password must by hashed!
	 * 
	 * @param usr
	 */
	public void updateUser(Usr usr) throws WaktuException {
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
			handleException(e);			
		} finally {
			em.close();
		}
		update.emit();
		logger.info("user " + usr + " updated");

	}

	protected String generateUsername(String firstname, String name)
			throws WaktuException {
		return UsernameUtil.generateUsername(getAllUsers(), firstname, name);
	}
	
	private void handleException(Exception e) throws WaktuException{
		if(e instanceof IllegalArgumentException) {
			logger.error(e + e.getMessage());
			throw new WaktuException("Database problem");
		} else if (e instanceof IllegalStateException) {
			logger.error(e + e.getMessage());
			throw new WaktuException("Illegal argument");
		} else {
			logger.error(e + e.getMessage());
			throw new WaktuException("General Problem");
		}
	}

}