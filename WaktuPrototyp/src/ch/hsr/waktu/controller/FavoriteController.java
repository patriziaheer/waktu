package ch.hsr.waktu.controller;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import ch.hsr.waktu.domain.Favorite;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;



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
	public Favorite addFavorite(WorkPackage workPackage, Time startTime, Time endTime){
		return null;
	}

	/**
	 * 
	 * @param user
	 */
	public List<Favorite> getFavorites(Usr user){
		return new ArrayList<Favorite>();
	}

	/**
	 * 
	 * @param favorite
	 */
	public boolean removeFavorite(Favorite favorite){
		return false;
	}

}