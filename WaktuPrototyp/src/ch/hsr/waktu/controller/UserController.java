package ch.hsr.waktu.controller;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import ch.hsr.waktu.domain.Usr;

import com.trolltech.qt.QSignalEmitter;

/**
 * @author simon.staeheli
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
 */
public class UserController extends QSignalEmitter {

	public enum UserProperties {
		Data, Projects, WorkSessions
	}

	private static UserController theInstance = null;

	public static UserController getInstance() {
		if (theInstance == null) {
			theInstance = new UserController();
		}
		return theInstance;
	}

	private Logger logger = Logger.getLogger(UserController.class);
	public Signal0 update = new Signal0();
	public Signal1<Usr> add = new Signal1<Usr>();

	private UserController() {

	}

	/**
	 * 
	 * @param username
	 * @param name
	 * @param firstname
	 * @param pensum
	 * @param role
	 */
	public Usr addUser(String username, String firstname, String lastname,
			String password, int pensum, int role, double holiday) {
		Usr newUser = new Usr(username, firstname, lastname, password, pensum,
				role, holiday);
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		em.getTransaction().begin();
		em.persist(newUser);
		em.flush();
		em.getTransaction().commit();
		// TODO: add.emit() wieder einschalten (Observer von QT)
		// add.emit(newUser);
		return newUser;
	}

	public List<Usr> getActiveUsers() {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		@SuppressWarnings("unchecked")
		List<Usr> usrs = em.createQuery(
				"SELECT u FROM Usr u WHERE u.inactive = FALSE").getResultList();

		for (Usr usr : usrs) {
			logger.info("ACTIVE USER: " + usr.toString());
		}

		em.close();
		return usrs;
	}

	public List<Usr> getAllUsers() {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		@SuppressWarnings("unchecked")
		List<Usr> usrs = em.createQuery("SELECT u FROM Usr u").getResultList();

		for (Usr usr : usrs) {
			logger.info(usr.toString());
		}

		em.close();
		return usrs;
	}

	public List<Usr> getInactiveUsers() {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		@SuppressWarnings("unchecked")
		List<Usr> usrs = em.createQuery(
				"SELECT u FROM Usr u WHERE u.inactive = TRUE").getResultList();

		for (Usr usr : usrs) {
			logger.info("INACTIVE USERS: " + usr.toString());
		}

		em.close();
		return usrs;
	}

	/**
	 * 
	 * @param loginName
	 */
	public Usr getUser(String username) {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		Usr user = (Usr) em.createQuery(
				"SELECT u FROM Usr u WHERE u.username = '" + username + "'")
				.getSingleResult();

		logger.info(user.toString());

		em.close();
		return user;
	}

	/**
	 * 
	 * @param user
	 */
	public boolean updateUser(Usr user) {
		// TODO: Username wechseln erlaubt? Spezifizieren! Bring Probleme mit
		// sich, da Unique in DB.
		// TODO: Was wenn ID der Users nicht vorhanden? Exception?

		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

		em.getTransaction().begin();
		Usr updateUsr = (Usr) em.createQuery(
				"SELECT u FROM Usr u WHERE u.id = " + user.getId())
				.getSingleResult();
		updateUsr.setName(user.getName());
		updateUsr.setFirstname(user.getFirstname());
		updateUsr.setHoliday(user.getHoliday());
		updateUsr.setInactive(user.isInactive());
		updateUsr.setPassword(user.getPassword());
		updateUsr.setPensum(user.getPensum());
		updateUsr.setRole(user.getRole());
		updateUsr.setUsername(user.getUsername());

		em.getTransaction().commit();
		// TODO: update.emit() wieder einschalten (Observer von QT)
		// update.emit();

		return true;
	}

}