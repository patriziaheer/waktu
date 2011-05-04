package ch.hsr.waktu.controller.datacontroller;

import java.util.LinkedList;

import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.services.XmlUtil;

public class UserControllerXml extends UserController implements UserControllerInterface{
	
	private String userFilePath = "./test/testdata/users.xml";
	
	public UserControllerXml() {
		
	}
	
	@Override
	public Usr addUser(String firstname, String lastname, String password,
			int pensum, SystemRole role, double holiday) {
		LinkedList<Usr> allUsers = getAllUsers();
		Usr newUser = new Usr(generateUsername(firstname, lastname), firstname, lastname, password, pensum, role, holiday);
		allUsers.add(newUser);
		XmlUtil.saveUsersToXml(userFilePath, allUsers);
		return newUser;
	}

	@Override
	public LinkedList<Usr> getActiveUsers() {
		LinkedList<Usr> activeUsers = getAllUsers();
		for(Usr u: activeUsers) {
			if(!u.isActive()) {
				activeUsers.remove(u);
			}
		}
		return activeUsers;
	}

	@Override
	public LinkedList<Usr> getAllUsers() {
		return XmlUtil.getUsersFromXml(userFilePath);
	}

	@Override
	public LinkedList<Usr> getInactiveUsers() {
		LinkedList<Usr> inactiveUsers = getAllUsers();
		for(Usr u: inactiveUsers) {
			if(u.isActive()) {
				inactiveUsers.remove(u);
			}
		}
		return inactiveUsers;
	}

	@Override
	public LinkedList<Usr> getProjectManagers() {
		LinkedList<Usr> projectManagers = getAllUsers();
		for(Usr u: projectManagers) {
			if(u.isActive()) {
				projectManagers.remove(u);
			}
		}
		return null;
	}

	@Override
	public Usr getUser(String username) {
		LinkedList<Usr> allUsers = getAllUsers();
		for(Usr u: allUsers) {
			if(u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}

	@Override
	public boolean updateUser(Usr user) {
		LinkedList<Usr> allUsers = getAllUsers();
		for(Usr u: allUsers) {
			if(u.equals(user)) {
				u.setActiveState(user.isActive());
				u.setFirstname(user.getFirstname());
				u.setHoliday(user.getHoliday());
				u.setName(user.getName());
				u.setPassword(user.getPasswordHash());
				u.setPensum(user.getPensum());
				u.setRole(user.getSystemRole());
				u.setUsername(user.getUsername());
//TODO update XML-File
				return true;
			}
		}
		return false;
	}
}
