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
	public Favorite addFavorite(Usr user, WorkPackage workPackage,
			GregorianCalendar startTime, GregorianCalendar endTime) {
		Favorite newFavorite = new Favorite(user, workPackage, startTime,
				endTime);
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();

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
	 * @param Usr user
	 * @throws WaktuGeneralException
	 */
	
	public List<Favorite> getFavorites(Usr user) throws WaktuGeneralException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		
		List<Favorite> allFavoritesOfUsr;
		
		try {
			@SuppressWarnings("unchecked")
			List<Favorite> allFavoritesOfUsrAsList = em.createQuery("SELECT f FROM Favorite f ORDER BY f.id").getResultList();
			allFavoritesOfUsr = allFavoritesOfUsrAsList;
		} catch (IllegalStateException e) {
			throw new WaktuGeneralException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuGeneralException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuGeneralException("General problem");
		} finally {
			em.close();
		}
		return allFavoritesOfUsr;		
	}

	/**
	 * 
	 * @param favorite
	 * @throws WaktuGeneralException
	 */

	public void removeFavorite(Favorite favorite) throws WaktuGeneralException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(em.find(Favorite.class, favorite.getId()));
			removed.emit(favorite);
			em.getTransaction().commit();
			logger.info("favorite " + favorite.getId() + " deleted");
		} catch (IllegalStateException e) {
			throw new WaktuGeneralException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuGeneralException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuGeneralException("General problem");
		} finally {
			em.close();
		}
	}
	
	/**
	 * 
	 * @param favorite
	 * @throws WaktuGeneralException
	 */

	public void updateFavorite(Favorite favorite) throws WaktuGeneralException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		try {
			em.getTransaction().begin();
			Favorite updateFavorite = em.find(Favorite.class, favorite.getId());
			updateFavorite.setStartTime(favorite.getStartTime());
			updateFavorite.setEndTime(favorite.getEndTime());
			update.emit();
			em.getTransaction().commit();
			logger.info("favorite " + favorite.getId() + " updated");
		} catch (IllegalStateException e) {
			throw new WaktuGeneralException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuGeneralException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuGeneralException("General problem");
		} finally {
			em.close();
		}
	}
}