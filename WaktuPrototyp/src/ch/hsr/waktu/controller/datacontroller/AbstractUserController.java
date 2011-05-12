package ch.hsr.waktu.controller.datacontroller;

import java.util.List;

import com.trolltech.qt.QSignalEmitter;

import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.services.WaktuException;

abstract class AbstractUserController  extends QSignalEmitter {
//	private static AbstractUserController theInstance = null;

//	public static AbstractUserController getInstance() {
//		if (theInstance == null) {
//			theInstance = new UserController();
//		}
//		return theInstance;
//	}
//
//	public static void setInstance(AbstractUserController userControllerInstance) {
//		theInstance = userControllerInstance;
//	}
	
	abstract Usr addUser(String firstname, String lastname,
			String password, int pensum, SystemRole role, double holiday) throws WaktuException;
	
	abstract List<Usr> getActiveUsers() throws WaktuException;

	abstract List<Usr> getAllUsers() throws WaktuException;

	abstract List<Usr> getInactiveUsers() throws WaktuException;
	
	abstract List<Usr> getProjectManagers() throws WaktuException;
	
	abstract Usr getUser(String username) throws WaktuException;

	abstract void updateUser(Usr user) throws WaktuException;
}
