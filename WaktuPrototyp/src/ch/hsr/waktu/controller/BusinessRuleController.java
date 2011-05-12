package ch.hsr.waktu.controller;

import org.apache.log4j.Logger;

import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.services.WaktuException;

public class BusinessRuleController {

	private Logger logger = Logger.getLogger(BusinessRuleController.class);
	
	/**
	 * Checks if the accordant user has access rights to the specified method.
	 * @param Usr usr
	 * @throws WaktuException
	 */
	public void check(Usr usr) throws WaktuException {

		if(usr.getHoliday() < 0) {
			logger.info("Negative holiday");
			throw new WaktuException("Negative holiday");
		}
		if(usr.getPensum() < 0) {
			logger.info("Negative pensum");
			throw new WaktuException("Negative pensum");
		}
		if(usr.getPensum() > 100) {
			logger.info("Pensum higher than 100%");
			throw new WaktuException("Pensum higher than 100%");
		}
		
	}
}
