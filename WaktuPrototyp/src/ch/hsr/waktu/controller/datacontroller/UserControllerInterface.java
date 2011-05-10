package ch.hsr.waktu.controller.datacontroller;

import java.util.List;

import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.domain.Usr;

public interface UserControllerInterface {
	Usr addUser(String firstname, String lastname,
			String password, int pensum, SystemRole role, double holiday) throws WaktuGeneralException;
	
	List<Usr> getActiveUsers() throws WaktuGeneralException;

	List<Usr> getAllUsers() throws WaktuGeneralException;

	List<Usr> getInactiveUsers() throws WaktuGeneralException;
	
	List<Usr> getProjectManagers() throws WaktuGeneralException;
	
	Usr getUser(String username) throws WaktuGeneralException;

	void updateUser(Usr user) throws WaktuGeneralException;
}
