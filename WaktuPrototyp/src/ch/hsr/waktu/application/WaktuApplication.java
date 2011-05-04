package ch.hsr.waktu.application;




import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ch.hsr.waktu.presentation.view.LoginView;

import com.trolltech.qt.core.QFile;
import com.trolltech.qt.core.QTranslator;
import com.trolltech.qt.gui.QApplication;


public class WaktuApplication {
	
	private static Logger logger = Logger.getRootLogger();
	

    public static void main(String[] args) {
    	//ClassLoader loader = WaktuApplication.class.getClassLoader();
		//PropertyConfigurator.configure(loader.getResource("settings").getFile());
    	PropertyConfigurator.configure("settings");
    	logger.info("Initialize Application");
    	QApplication.initialize(args);
    	//QApplication.setStyle(new QCleanlooksStyle());
        //QApplication app = new QApplication(args);
        //String sheet = getStyleSheet("classpath:stylesheet.txt");
        //app.setStyleSheet(sheet);
        QTranslator translator = new QTranslator();
        //QFile file = new QFile("classpath:config/Login_de.qm");
        translator.load("classpath:Login_de.qm");
        QApplication.installTranslator(translator);
        

       	LoginView loginView = new LoginView();
       	loginView.show();
       	

        QApplication.exec();
    }
	
    
    /*private static String getStyleSheet(String path) {
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
    }*/

}
