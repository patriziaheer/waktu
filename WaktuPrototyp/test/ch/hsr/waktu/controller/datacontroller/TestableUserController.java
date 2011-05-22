package ch.hsr.waktu.controller.datacontroller;

import javax.persistence.EntityManager;

import ch.hsr.waktu.controller.PersistenceController;
import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.services.Md5;
import ch.hsr.waktu.services.WaktuException;

public class TestableUserController extends UserController {
	
	private static TestableUserController theInstance = null;

	public static TestableUserController getInstance() {
		if (theInstance == null) {
			theInstance = new TestableUserController();
		}
		return theInstance;
	}
	
	@Override
	public Usr addUser(String firstname, String lastname, String password,
			int pensum, SystemRole role, double holiday) throws WaktuException {
		EntityManager em = PersistenceController.getInstance("waktutest").getEMF()
				.createEntityManager();

		Usr newUsr = new Usr(firstname+lastname, firstname,
				lastname, Md5.hash(password), pensum, role, holiday);
		try {
			em.getTransaction().begin();
			em.persist(newUsr);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new WaktuException("Database problem");
		} catch (IllegalArgumentException e) {
			throw new WaktuException("Illegal Argument");
		} catch (Exception e) {
			throw new WaktuException("General problem:" + e.getMessage());
		} finally {
			em.close();
		}
		return newUsr;
	}
}
