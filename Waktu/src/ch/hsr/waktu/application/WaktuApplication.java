package ch.hsr.waktu.application;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import ch.hsr.waktu.domain.Domain;
import ch.hsr.waktu.domain.User;
import ch.hsr.waktu.domain.UserProperties;
import ch.hsr.waktu.presentation.view.usermanagment.PrototypMainWIndow;


public class WaktuApplication {
	
	public static void main(String[] args) {
		WaktuApplication.initUsers();
		WaktuApplication.initProperties();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrototypMainWIndow window = new PrototypMainWIndow();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	private static void initProperties() {
		Domain.getInstance().addUserProperty(new UserProperties("Daten"));
		Domain.getInstance().addUserProperty(new UserProperties("Projekte"));
		Domain.getInstance().addUserProperty(new UserProperties("Stunden"));
	}
	
	private static void initUsers() {
		Domain.getInstance().addUser(new User("Patrizia Heer"));
		Domain.getInstance().addUser(new User("Simon Stäheli"));
		Domain.getInstance().addUser(new User("Michael Fisler"));
	}

}
