package ch.hsr.waktu.controller.datacontroller;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.PermissionController;
import ch.hsr.waktu.controller.PersistenceController;
import ch.hsr.waktu.domain.Favorite;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.services.ExceptionHandling;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.QSignalEmitter;

public class FavoriteController extends QSignalEmitter {
	
	private FavoriteController() {
		
	}

    private static FavoriteController theInstance = null;
    
    private Logger logger = Logger.getLogger(FavoriteController.class);
    public Signal0 update = new Signal0();
    public Signal1<Favorite> add = new Signal1<Favorite>();
    public Signal1<Favorite> removed = new Signal1<Favorite>();

    /**
     * 
     * @return FavoriteController
     */
    public static FavoriteController getInstance() {
        if (theInstance == null) {
            theInstance = new FavoriteController();
        }
        return theInstance;
    }

    /**
     * 
     * @param user
     * @return List<Favorite>
     * @throws WaktuException
     */
    @SuppressWarnings("unchecked")
    public List<Favorite> getFavorites(final Usr user) throws WaktuException {
        EntityManager em = PersistenceController.getInstance().getEMF()
                .createEntityManager();

        if (!PermissionController.getInstance().checkPermission()) {
            throw new WaktuException("Permission denied");
        }

        List<Favorite> allFavoritesOfUsr = null;
        try {
            allFavoritesOfUsr = em.createQuery(
                    "SELECT f FROM Favorite f JOIN f.user u WHERE u.id = '"
                            + user.getId() + "' ORDER BY f.id").getResultList();
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        } finally {
            em.close();
        }
        return allFavoritesOfUsr;
    }

    /**
     * 
     * @param user
     * @param workPackage
     * @param startTime
     * @param endTime
     * @return Favorite
     * @throws WaktuException
     */
    public Favorite addFavorite(final Usr user, final WorkPackage workPackage,
            final GregorianCalendar startTime, final GregorianCalendar endTime)
            throws WaktuException {
        EntityManager em = PersistenceController.getInstance().getEMF()
                .createEntityManager();

        if (!PermissionController.getInstance().checkPermission()) {
            throw new WaktuException("Permission denied");
        }

        Favorite newFavorite = new Favorite(user, workPackage, startTime,
                endTime);

        try {
            em.getTransaction().begin();
            em.persist(newFavorite);
            em.getTransaction().commit();
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
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
     * @throws WaktuException
     */

    public void updateFavorite(final Favorite favorite) throws WaktuException {
        EntityManager em = PersistenceController.getInstance().getEMF()
                .createEntityManager();

        if (!PermissionController.getInstance().checkPermission()) {
            throw new WaktuException("Permission denied");
        }

        try {
            em.getTransaction().begin();
            em.find(Favorite.class, favorite.getId());
            em.merge(favorite);
            em.getTransaction().commit();
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        } finally {
            em.close();
        }
        update.emit();
        logger.info("favorite " + favorite.getId() + " updated");
    }

    /**
     * 
     * @param favorite
     * @throws WaktuException
     */

    public void removeFavorite(final Favorite favorite) throws WaktuException {
        EntityManager em = PersistenceController.getInstance().getEMF()
                .createEntityManager();

        if (!PermissionController.getInstance().checkPermission()) {
            throw new WaktuException("Permission denied");
        }

        try {
            em.getTransaction().begin();
            em.remove(em.find(Favorite.class, favorite.getId()));
            em.getTransaction().commit();
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        } finally {
            em.close();
        }
        removed.emit(favorite);
        logger.info("favorite " + favorite + " deleted");
    }
}
