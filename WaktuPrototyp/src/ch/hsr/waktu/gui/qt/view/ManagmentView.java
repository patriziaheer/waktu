package ch.hsr.waktu.gui.qt.view;

import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.guicontroller.GuiController;
import ch.hsr.waktu.guicontroller.LanguageController;
import ch.hsr.waktu.guicontroller.LanguageController.Language;

import com.trolltech.qt.core.QCoreApplication;
import com.trolltech.qt.gui.QBrush;
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QPalette;
import com.trolltech.qt.gui.QPalette.ColorRole;

public class ManagmentView extends QMainWindow {

	private Ui_ManagmentWindow ui = new Ui_ManagmentWindow();
	//private Usr currUsr;
	private ProjectDetails projectDetails;
	private UserDetails userDetails;
	
	public ManagmentView(Usr usr) {
		//currUsr = usr;
		
		ui.setupUi(this);
		ui.tabWidget.removeTab(0);
		projectDetails = new ProjectDetails();
		projectDetails.errorMessage.connect(this, "showErrorMessage(String)");
		ui.tabWidget.addTab(projectDetails, com.trolltech.qt.core.QCoreApplication.translate("ManagmentView",("Projects"), null));
		userDetails = new UserDetails(); 
		userDetails.errorMessage.connect(this, "showErrorMessage(String)");
		ui.tabWidget.addTab(userDetails, com.trolltech.qt.core.QCoreApplication.translate("ManagmentView",("Users"), null));
		
		ui.actionAddUser.setVisible(GuiController.getInstance().canAddUser());
		ui.actionAddProject.setVisible(GuiController.getInstance().canAddProject());
		
		ui.actionAddProject.triggered.connect(this, "addProject()");
		ui.actionAddUser.triggered.connect(this, "addUser()");
		ui.actionClose.triggered.connect(this, "closeWindow()");

		ui.actionDE.triggered.connect(this, "translateDE()");
		ui.actionEN.triggered.connect(this, "translateEN()");
		
		
		
		LanguageController.getInstance().languageChanged.connect(this, "translate()");
	}
	
	@SuppressWarnings("unused")
	private void addUser() {
		ui.tabWidget.setCurrentIndex(1);
		userDetails.addUser();
	}
	
	@SuppressWarnings("unused")
	private void addProject() {
		ui.tabWidget.setCurrentIndex(0);
		projectDetails.addProject();
		
	}
	
	@SuppressWarnings("unused")
	private void closeWindow() {
		close();
	}

	@SuppressWarnings("unused")
	private void translateDE() {
		LanguageController.getInstance().setCurrLanguage(Language.DE);
	}
	
	@SuppressWarnings("unused")
	private void translateEN() {
        LanguageController.getInstance().setCurrLanguage(Language.EN);
	}
	
	@SuppressWarnings("unused")
	private void translate() {
        ui.retranslateUi(this);
        changeText();
	}

	private void changeText() {
		ui.tabWidget.setTabText(0, QCoreApplication.translate("ManagmentView",("Projects"), null));
		ui.tabWidget.setTabText(1, QCoreApplication.translate("ManagmentView",("Users"), null));
	}

	private void setStatusBarText(String text) {
		ui.statusBar.showMessage(text, 2000);
		QPalette palette = ui.statusBar.palette();
		palette.setBrush(ColorRole.WindowText, new QBrush(QColor.red));
		ui.statusBar.setPalette(palette);
	}
	
	@SuppressWarnings("unused")
	private void showErrorMessage(String errorMessage) {
		setStatusBarText(errorMessage);
	}
	
}
