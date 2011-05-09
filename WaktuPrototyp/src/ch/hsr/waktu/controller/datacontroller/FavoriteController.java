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
	 * @param Usr user
	 * @throws WaktuGeneralException
	 */
	
	@SuppressWarnings("unchecked")
	public List<Favorite> getFavorites(Usr user) throws WaktuGeneralException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		
		List<Favorite> allFavoritesOfUsr;		
		try {
			allFavoritesOfUsr = em.createQuery("SELECT f FROM Favorite f ORDER BY f.id").getResultList();
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
	 * @param workPackage
	 * @param startTime
	 * @param endTime
	 */
	public Favorite addFavorite(Usr user, WorkPackage workPackage,
			GregorianCalendar startTime, GregorianCalendar endTime) throws WaktuGeneralException {
		EntityManager em = PersistenceController.getInstance().getEMF()
				.createEntityManager();
		Favorite newFavorite = new Favorite(user, workPackage, startTime,
				endTime);
		try {
			em.getTransaction().begin();
			em.persist(newFavorite);
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
		
		add.emit(newFavorite);
		logger.info("favorite " + newFavorite + " deleted");
		return newFavorite;
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
		logger.info("favorite " + favorite.getId() + " updated");
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
		removed.emit(favorite);
		logger.info("favorite " + favorite.getId() + " deleted");
	}

}