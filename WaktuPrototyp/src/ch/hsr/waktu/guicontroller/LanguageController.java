package ch.hsr.waktu.guicontroller;

import com.trolltech.qt.QSignalEmitter;
import com.trolltech.qt.core.QTranslator;
import com.trolltech.qt.gui.QApplication;
/**
 * A Class for handle the Languages
 * @author patriziaheer
 * @version 1.0
 */
public class LanguageController extends QSignalEmitter {
	/**
	 * 
	 * Language Enum
	 *
	 */
	public enum Language {
		EN, DE
	}

	private static LanguageController theInstance = null;
	
	/**
	 * 
	 * @return The only instance of LanguageController
	 */
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

	/**
	 * 
	 * @return the current selected language
	 */
	public Language getCurrLanguage() {
		return currLanguage;
	}

	/**
	 * Changes the selected language to currlanguage
	 * @param currLanguage
	 */
	public void setCurrLanguage(final Language currLanguage) {
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
