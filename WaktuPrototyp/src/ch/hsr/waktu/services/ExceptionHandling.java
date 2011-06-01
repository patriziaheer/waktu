package ch.hsr.waktu.services;

import org.apache.log4j.Logger;

public class ExceptionHandling {

    private ExceptionHandling() {

    }

    private static Logger logger = Logger.getLogger(ExceptionHandling.class);

    public static void handleException(final Exception e) throws WaktuException {
        if (e instanceof IllegalArgumentException) {
            logger.error(e + e.getMessage());
            throw new WaktuException("Illegal argument");
        } else if (e instanceof IllegalStateException) {
            logger.error(e + e.getMessage());
            throw new WaktuException("Database problem");
        } else {
            logger.error(e + e.getMessage());
            throw new WaktuException("General Problem");
        }
    }
}
