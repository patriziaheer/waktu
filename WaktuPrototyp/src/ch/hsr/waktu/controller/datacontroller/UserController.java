package ch.hsr.waktu.controller.datacontroller;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.BusinessRuleController;
import ch.hsr.waktu.controller.PersistenceController;
import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.services.Md5;
import ch.hsr.waktu.services.UsernameUtil;

import com.trolltech.qt.QSignalEmitter;

/**
 * @author simon.staeheli
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
 */
public class UserController  extends QSignalEmitter {

	public enum UserProperties {
		Data, Projects, WorkSessions
	}
	
	private static BusinessRuleController bc;

	private static UserController theInstance = null;

	public static UserController getInstance() {
		if (theInstance == null) {
			theInstance = new UserController();
			bc = new BusinessRuleController();
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
	public List<Usr> getActiveUsers() throws WaktuGeneralException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		List<Usr> activeUsers;
		try {
			activeUsers = em.createQuery(
					"SELECT u FROM Usr u WHERE u.active = 'TRUE'")
					.getResultList();
		} catch (IllegalStateException e) {
			throw new WaktuGeneralException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuGeneralException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuGeneralException("General problem");
		} finally {
			em.close();
		}

		return activeUsers;
	}

	@SuppressWarnings("unchecked")
	public List<Usr> getAllUsers() throws WaktuGeneralException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		List<Usr> allUsers;
		try {
			allUsers = em.createQuery("SELECT u FROM Usr u")
					.getResultList();
		} catch (IllegalStateException e) {
			throw new WaktuGeneralException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuGeneralException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuGeneralException("General problem");
		} finally {
			em.close();
		}

		return allUsers;
	}

	@SuppressWarnings("unchecked")
	public List<Usr> getInactiveUsers() throws WaktuGeneralException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		List<Usr> inactiveUsers;
		try {
			inactiveUsers = em.createQuery(
					"SELECT u FROM Usr u WHERE u.active = 'FALSE'")
					.getResultList();
		} catch (IllegalStateException e) {
			throw new WaktuGeneralException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuGeneralException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuGeneralException("General problem");
		} finally {
			em.close();
		}

		return inactiveUsers;
	}

	@SuppressWarnings("unchecked")
	public List<Usr> getProjectManagers() throws WaktuGeneralException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		List<Usr> allProjectManagers;
		try {
			allProjectManagers = em.createQuery(
					"SELECT u FROM Project p JOIN p.projectManager u")
					.getResultList();
		} catch (IllegalStateException e) {
			throw new WaktuGeneralException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuGeneralException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuGeneralException("General problem");
		} finally {
			em.close();
		}

		return allProjectManagers;
	}

	/**
	 * 
	 * @param loginName
	 */
	public Usr getUser(String username) throws WaktuGeneralException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		Usr usr;
		try {
			usr = (Usr) em
					.createQuery(
							"SELECT u FROM Usr u WHERE u.username = '"
									+ username + "'").getSingleResult();
		} catch (IllegalStateException e) {
			throw new WaktuGeneralException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuGeneralException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuGeneralException("General problem");
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
			int pensum, SystemRole role, double holiday)
			throws WaktuGeneralException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		Usr newUsr = new Usr(generateUsername(firstname,
				lastname), firstname, lastname, Md5.hash(password), pensum,
				role, holiday);
		
		bc.check(newUsr);

		try {
			em.getTransaction().begin();
			em.persist(newUsr);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new WaktuGeneralException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuGeneralException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuGeneralException("General problem");
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
	public void updateUser(Usr usr) throws WaktuGeneralException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		try {
			em.getTransaction().begin();
			Usr updateUsr = em.find(Usr.class, usr.getId());

			updateUsr.setName(usr.getName());
			updateUsr.setFirstname(usr.getFirstname());
			updateUsr.setHoliday(usr.getHoliday());
			updateUsr.setActiveState(usr.isActive());
			updateUsr.setPasswordHash(usr.getPasswordHash());
			updateUsr.setPensum(usr.getPensum());
			updateUsr.setSystemRole(usr.getSystemRole());

			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new WaktuGeneralException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuGeneralException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuGeneralException("General problem");
		} finally {
			em.close();
		}
		update.emit();
		logger.info("user " + usr + " updated");

	}
	
	private String generateUsername(String firstname, String name) throws WaktuGeneralException {
		return UsernameUtil.generateUsername(getAllUsers(), firstname, name);
	}

}