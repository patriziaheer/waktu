package ch.hsr.waktu.data;

import org.junit.Test;

import com.trolltech.qt.gui.QApplication;

import ch.hsr.waktu.controller.datacontroller.UserController;
import ch.hsr.waktu.domain.SystemRole;
import ch.hsr.waktu.services.WaktuException;


public class GenerateData {
	
	@Test
	public void generate() {
		String[] args = {};
		QApplication.initialize(args);
		try {
		UserController.getInstance().addUser("usr1", "usr1", "usr1", 0, SystemRole.ADMIN, 1.0);
		UserController.getInstance().addUser("usr2", "usr2", "usr2", 0, SystemRole.ADMIN, 1.0);
		UserController.getInstance().addUser("usr3", "usr3", "usr3", 0, SystemRole.ADMIN, 1.0);
		UserController.getInstance().addUser("usr4", "usr4", "usr4", 0, SystemRole.ADMIN, 1.0);
		UserController.getInstance().addUser("usr5", "usr5", "usr5", 0, SystemRole.ADMIN, 1.0);
		} catch(WaktuException e){
			System.out.println(e);
		}
	}

}
