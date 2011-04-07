package ch.hsr.waktu.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

public class PersistenceController {
	private static PersistenceController theInstance = null;
	
	public static PersistenceController getInstance() {
		if (theInstance == null) {
			theInstance = new PersistenceController();
		}
		return theInstance;
	}
	
	private Logger logger = Logger.getLogger(PersistenceController.class);
	private static String PERSISTENCE_UNIT_NAME = "waktu";
	private EntityManagerFactory emf;
	protected EntityManager em;
	
	private  PersistenceController() {
	}
	
	public EntityManagerFactory getEMF() {
		if (emf == null) {
			emf = createEMF();
		}

		return emf;
	}

	public EntityManagerFactory createEMF() {
		try {
			return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		} catch (RuntimeException e) {
			logger.error("Persistence.createEMF FAILED: "
					+ e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	
	
}
