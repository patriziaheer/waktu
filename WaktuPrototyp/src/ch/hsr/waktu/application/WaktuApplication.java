package ch.hsr.waktu.application;




import ch.hsr.waktu.domain.Domain;
import ch.hsr.waktu.domain.User;
import ch.hsr.waktu.domain.UserProperties;
import ch.hsr.waktu.presentation.WaktuMainWindow;

import com.trolltech.qt.gui.QApplication;


public class WaktuApplication {
	

    public static void main(String[] args) {
        QApplication.initialize(args);
        
        initProperties();
        initUsers();

        WaktuMainWindow testWaktuMainWindow = new WaktuMainWindow();
        testWaktuMainWindow.show();

        QApplication.exec();
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
