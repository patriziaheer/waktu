package ch.hsr.waktu.controller.datacontroller;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.PersistenceController;
import ch.hsr.waktu.domain.Favorite;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;

import com.trolltech.qt.QSignalEmitter;

/**
 * @author simon.staeheli
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
 */
public class FavoriteController extends QSignalEmitter {

	private static FavoriteController theInstance = null;

	public static FavoriteController getInstance() {
		if (theInstance == null) {
			theInstance = new FavoriteController();
		}
		return theInstance;
	}

	private Logger logger = Logger.getLogger(UserController.class);
	public Signal0 update = new Signal0();
	public Signal1<Favorite> add = new Signal1<Favorite>();
	public Signal1<Favorite> removed = new Signal1<Favorite>();

	private FavoriteController() {

	}

	/**
	 * 
	 * @param workPackage
	 * @param startTime
	 * @param endTime
	 */
	public Favorite addFavorite(Usr user, WorkPackage workPackage, GregorianCalendar startTime, GregorianCalendar endTime) {
		Favorite newFavorite = new Favorite(user, workPackage, startTime, endTime);
		EntityManager em = PersistenceController.getInstance().getEMF().createEntityManager();

		em.getTransaction().begin();
		em.persist(newFavorite);
		logger.info("FAVORITE ADDED");
		em.flush();
		em.getTransaction().commit();
		em.close();
		add.emit(newFavorite);

		return newFavorite;
	}

	/**
	 * 
	 * @param user
	 */
	public List<Favorite> getFavorites(Usr user) {
		EntityManager em = PersistenceController.getInstance().getEMF().createEntityManager();
		@SuppressWarnings("unchecked")
		List<Favorite> allFavorites = em.createQuery("SELECT f FROM Favorite f ORDER BY f.id").getResultList();
		return allFavorites;
	}

	/**
	 * 
	 * @param favorite
	 */
	
	// TODO: remove funktioniert noch nicht...
	
	public boolean removeFavorite(Favorite favorite) {
		EntityManager em = PersistenceController.getInstance().getEMF().createEntityManager();
		em.remove(favorite);
		removed.emit(favorite);
		return true;
	}

	public boolean updateFavorite(Favorite favorite) {
		// TODO: added by ph
		EntityManager em = PersistenceController.getInstance().getEMF().createEntityManager();

		em.getTransaction().begin();
		Favorite updateFav = (Favorite) em.createQuery("SELECT f FROM Favorite f WHERE f.id = " + favorite.getId()).getSingleResult();

		updateFav.setStartTime(favorite.getStartTime());
		updateFav.setEndTime(favorite.getEndTime());

		em.getTransaction().commit();
		logger.info("Favorite " + favorite.getId() + " updated");

		update.emit();
		return true;
	}

}