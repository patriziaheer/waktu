package ch.hsr.waktu.application;




import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ch.hsr.waktu.gui.qt.view.LoginView;
import ch.hsr.waktu.guicontroller.LanguageController;
import ch.hsr.waktu.guicontroller.LanguageController.Language;

import com.trolltech.qt.core.QFile;
import com.trolltech.qt.core.QIODevice;
import com.trolltech.qt.core.QTextStream;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QPixmap;
import com.trolltech.qt.gui.QSplashScreen;


public class WaktuApplication {
	
	private static Logger logger = Logger.getRootLogger();
	

    public static void main(String[] args) {
    	//ClassLoader loader = WaktuApplication.class.getClassLoader();
		//PropertyConfigurator.configure(loader.getResource("settings").getFile());
		ClassLoader loader = WaktuApplication.class.getClassLoader();
		URL url = loader.getResource("settings");
    	PropertyConfigurator.configure(url);
    	logger.info("Initialize Application");
    	//QApplication.initialize(args);
    	//QApplication.setStyle(new QPlastiqueStyle());
        QApplication app = new QApplication(args);
        QApplication.setWindowIcon(new QIcon("classpath:icons/logo_without_text.png"));
        
        QSplashScreen splashScreen = new QSplashScreen(new QPixmap("classpath:icons/logo.gif"));
        splashScreen.show();
        splashScreen.showMessage("Initialize Application...");
        QApplication.processEvents();
        
        String sheet = getStyleSheet("classpath:stylesheet.txt");
        app.setStyleSheet(sheet);
    	LanguageController.getInstance().setCurrLanguage(Language.DE);
        

       	LoginView loginView = new LoginView();
       	loginView.show();
       	
       	splashScreen.finish(loginView);

        QApplication.exec();
    }
	
    
    private static String getStyleSheet(String path) {
    	logger.info("Initialize Stylesheet");
        QFile file = new QFile(path);
        if (!file.open(new QIODevice.OpenMode(QIODevice.OpenModeFlag.ReadOnly, QIODevice.OpenModeFlag.Text))) {
            return null;
        }

        QTextStream in = new QTextStream(file);
        
        StringBuffer buffer = new StringBuffer();
        
        
        while (in.atEnd() == false) {
        	buffer.append(in.readLine());
        }
        
        file.close();
    	return buffer.toString();
    }

}
