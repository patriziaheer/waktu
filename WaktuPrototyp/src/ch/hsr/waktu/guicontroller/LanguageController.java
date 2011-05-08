package ch.hsr.waktu.guicontroller;

import com.trolltech.qt.QSignalEmitter;
import com.trolltech.qt.core.QTranslator;
import com.trolltech.qt.gui.QApplication;


public class LanguageController extends QSignalEmitter {
	public enum Language {
		EN,
		DE
	}
	
	private static LanguageController theInstance = null;

	public static LanguageController getInstance() {
		if (theInstance == null) {
			theInstance = new LanguageController();
		}
		return theInstance;
	}
	private Language currLanguage; 
	public Signal0 languageChanged = new Signal0();
	
	private LanguageController() {
		
	}

	public Language getCurrLanguage() {
		return currLanguage;
	}

	public void setCurrLanguage(Language currLanguage) {
		this.currLanguage = currLanguage;
		changeLanguage();
	}
	
	private void changeLanguage() {
        QTranslator translator = new QTranslator();
        if (currLanguage == Language.EN) {
        	translator.load("classpath:waktu_en.qm");
        } else if (currLanguage == Language.DE) {
        	translator.load("classpath:waktu_de.qm");
        }
        QApplication.installTranslator(translator);
        languageChanged.emit();
	}
	
	
}
