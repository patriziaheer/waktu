package ch.hsr.waktu.controller.datacontroller;

import java.util.List;

import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.domain.Usr;

public interface UserControllerInterface {
	Usr addUser(String firstname, String lastname,
			String password, int pensum, SystemRole role, double holiday);
	
	List<Usr> getActiveUsers();

	List<Usr> getAllUsers();

	List<Usr> getInactiveUsers();
	
	List<Usr> getProjectManagers();
	
	Usr getUser(String username);

	boolean updateUser(Usr user);
}
