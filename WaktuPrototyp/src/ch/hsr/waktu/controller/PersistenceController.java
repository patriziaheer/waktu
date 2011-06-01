package ch.hsr.waktu.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import ch.hsr.waktu.services.WaktuException;

public class PersistenceController {
    private static PersistenceController theInstance = null;
    private static String PERSISTENCE_UNIT_NAME = null;

    public static PersistenceController getInstance() throws WaktuException {

        if (theInstance == null) {
            throw new WaktuException(
                    "Please initialize Controller with setInstance() first");
        }
        return theInstance;
    }

    public static void setInstance(final String persistenceUnit) {
        if (theInstance == null) {
            theInstance = new PersistenceController();
            PERSISTENCE_UNIT_NAME = persistenceUnit;
        }
    }

    private Logger logger = Logger.getLogger(PersistenceController.class);
    private EntityManagerFactory emf;
    protected EntityManager em;

    protected PersistenceController() {

    }

    public EntityManagerFactory getEMF() {
        if (emf == null) {
            emf = createEMF();
        }

        return emf;
    }

    private EntityManagerFactory createEMF() {
        try {
            return Persistence
                    .createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (RuntimeException e) {
            logger.error("Persistence.createEMF FAILED: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

}
