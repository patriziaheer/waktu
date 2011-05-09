package ch.hsr.waktu.controller;

import ch.hsr.waktu.controller.datacontroller.UserController;
import ch.hsr.waktu.controller.datacontroller.WaktuGeneralException;
import ch.hsr.waktu.domain.Usr;

public class UsernameController {
	
	public static String generateUsername(String firstname, String lastname) throws WaktuGeneralException {
		String username = firstname.toLowerCase() + lastname.toLowerCase().replaceAll(" ", "");
		int usernameOccurrence = 0;
		for(Usr u: UserController.getInstance().getAllUsers()) {
			if(u.getUsername().startsWith(username)) {
				usernameOccurrence += 1;
			}
		}
		if(usernameOccurrence > 0) {
			username = username + usernameOccurrence;
		}		
		return username;
	}
	
	public static boolean doesUsernameAlreadyExist(String usernameToCheck) {
		Usr userWithUsernameToCheck;
		try {
			userWithUsernameToCheck = UserController.getInstance().getUser(usernameToCheck);
		} catch(Exception e) {
			return false;
		}
		return (userWithUsernameToCheck != null);
	}
}
