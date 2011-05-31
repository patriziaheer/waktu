package ch.hsr.waktu.controller.datacontroller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.services.WaktuException;
import ch.hsr.waktu.services.XmlUtil;

public class UserControllerXml extends UserController {
	
	private String userFilePath = "./test/testdata/users.xml";
	
	private static UserControllerXml theInstance = null;

	public static UserControllerXml getInstance() {
		if (theInstance == null) {
			theInstance = new UserControllerXml();
		}
		return theInstance;
	}
	
	public static void setInstance(
			UserControllerXml userControllerInstance) {
		theInstance = userControllerInstance;
	}
	
	public UserControllerXml() {
		
	}
	
//	@Override
//	public Usr addUser(String firstname, String lastname, String password,
//			int pensum, SystemRole role, double holiday) throws WaktuException {
//		LinkedList<Usr> allUsers = getAllUsers();
//		Usr newUser = new Usr(UsernameUtil.generateUsername(getAllUsers(), firstname, lastname), firstname, lastname, password, pensum, role, holiday);
//		allUsers.add(newUser);
//		XmlUtil.saveUsersToXml(userFilePath, allUsers);
//		return newUser;
//	}

	@Override
	public LinkedList<Usr> getActiveUsers() throws WaktuException {
		LinkedList<Usr> activeUsers = new LinkedList<Usr>();
		for(Usr u: getAllUsers()) {
			if(u.isActive()) {
				activeUsers.add(u);
			}
		}
		return activeUsers;
	}

	@Override
	public List<Usr> getAllUsers() throws WaktuException {
			return XmlUtil.getUsersFromXml(userFilePath);
	}

	@Override
	public List<Usr> getInactiveUsers() throws WaktuException {
		ArrayList<Usr> inactiveUsers = new ArrayList<Usr>();
		for(Usr u: getAllUsers()) {
			if(!u.isActive()) {
				inactiveUsers.add(u);
			}
		}
		return inactiveUsers;
	}

	@Override
	public List<Usr> getProjectManagers() throws WaktuException {
		ArrayList<Usr> projectManagers = new ArrayList<Usr>();
		for(Usr u: getAllUsers()) {
			if(u.getSystemRole().equals(SystemRole.PROJECTMANAGER)) {
				projectManagers.add(u);
			}
		}
		return projectManagers;
	}

	@Override
	public Usr getUser(String username) throws WaktuException {
		for(Usr u: getAllUsers()) {
			if(u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}

}
