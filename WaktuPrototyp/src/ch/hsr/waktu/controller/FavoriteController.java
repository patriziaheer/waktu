package ch.hsr.waktu.controller;

import java.sql.Time;
import java.util.List;

import ch.hsr.waktu.domain.Favorite;
import ch.hsr.waktu.domain.WorkPackage;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;



/**
 * @author simon.staeheli
 * @version 1.0
 * @created 01-Apr-2011 15:36:30
 */
public class FavoriteController {

	private static FavoriteController theInstance = null;
	
	public static FavoriteController getInstance() {
		if (theInstance == null) {
			theInstance = new FavoriteController();
		}
		return theInstance;
	}
	
	private FavoriteController(){

	}

	/**
	 * 
	 * @param workPackage
	 * @param startTime
	 * @param endTime
	 */
	public boolean addFavorite(WorkPackage workPackage, Time startTime, Time endTime){
		return false;
	}

	/**
	 * 
	 * @param user
	 */
	public List<Favorite> getFavorites(User user){
		return null;
	}

	/**
	 * 
	 * @param favorite
	 */
	public boolean removeFavorite(Favorite favorite){
		return false;
	}

}