package ch.hsr.waktu.controller;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.datacontroller.WaktuGeneralException;
import ch.hsr.waktu.domain.Usr;

public class BusinessRuleController {

	private Logger logger = Logger.getLogger(BusinessRuleController.class);
	
	/**
	 * Checks if the accordant user has access rights to the specified method.
	 * @param Usr usr
	 * @throws WaktuGeneralException
	 */
	public void check(Usr usr) throws WaktuGeneralException {

		if(usr.getHoliday() < 0) {
			logger.info("Negative holiday");
			throw new WaktuGeneralException("Negative holiday");
		}
		if(usr.getPensum() < 0) {
			logger.info("Negative pensum");
			throw new WaktuGeneralException("Negative pensum");
		}
		if(usr.getPensum() > 100) {
			logger.info("Pensum higher than 100%");
			throw new WaktuGeneralException("Pensum higher than 100%");
		}
		
	}
}
