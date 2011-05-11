package ch.hsr.waktu.services;

import java.util.List;

import ch.hsr.waktu.controller.datacontroller.WaktuGeneralException;
import ch.hsr.waktu.domain.Usr;

public class UsernameUtil {
	
	public static String generateUsername(List<Usr> userList, String firstname, String lastname) throws WaktuGeneralException {
		String username = firstname.toLowerCase().replaceAll(" ", "") + lastname.toLowerCase().replaceAll(" ", "");
		int usernameOccurrence = 0;
		for(Usr u: userList) {
			if(u.getUsername().startsWith(username)) {
				usernameOccurrence += 1;
			}
		}
		if(usernameOccurrence > 0) {
			username = username + usernameOccurrence;
		}		
		return username;
	}

}
