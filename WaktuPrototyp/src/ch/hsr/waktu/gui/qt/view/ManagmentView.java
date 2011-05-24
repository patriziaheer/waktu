package ch.hsr.waktu.gui.qt.view;

import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.guicontroller.GuiController;
import ch.hsr.waktu.guicontroller.LanguageController;
import ch.hsr.waktu.guicontroller.LanguageController.Language;

import com.trolltech.qt.core.QCoreApplication;
import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QBrush;
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QContextMenuEvent;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QMenu;
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
		ui.tabWidget.addTab(projectDetails, QCoreApplication.translate("ManagmentView",("Projects"), null));
		userDetails = new UserDetails(); 
		userDetails.errorMessage.connect(this, "showErrorMessage(String)");
		ui.tabWidget.addTab(userDetails, QCoreApplication.translate("ManagmentView",("Users"), null));
		
		ui.actionAddUser.setVisible(GuiController.getInstance().canAddUser());
		ui.actionAddProject.setVisible(GuiController.getInstance().canAddProject());
		
		ui.actionAddProject.triggered.connect(this, "addProject()");
		ui.actionAddUser.triggered.connect(this, "addUser()");
		ui.actionClose.triggered.connect(this, "closeWindow()");

		ui.actionDE.triggered.connect(this, "translateDE()");
		ui.actionEN.triggered.connect(this, "translateEN()");
		
		ui.actionShow_Inactiv_Projects.changed.connect(this, "showInactivProjectsChanged()");
		ui.actionInactiv_Users.changed.connect(this, "showInactivUsersChanged()");
		
		
		
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

	@Override
	protected void contextMenuEvent(QContextMenuEvent event) {
		QMenu menu = new QMenu(this);
		QAction actionAddProject = new QAction(QCoreApplication.translate(
				"ManagmentView", ("Add Project"), null), menu);
		actionAddProject.triggered.connect(this, "addProject()");
		
		QAction actionAddUser = new QAction(QCoreApplication.translate(
				"ManagmentView", ("Add User"), null), menu);
		actionAddUser.triggered.connect(this, "addUser()");
		
		QMenu languageMenu = new QMenu(QCoreApplication.translate(
				"ManagmentView", ("Language"), null), menu);
		QAction actionDE = new QAction(QCoreApplication.translate(
				"ManagmentView", ("DE"), null), menu);
		actionDE.triggered.connect(this, "translateDE()");
		QAction actionEN = new QAction(QCoreApplication.translate(
				"ManagmentView", ("EN"), null), menu);
		actionEN.triggered.connect(this, "translateEN()");
		languageMenu.addAction(actionEN);
		languageMenu.addAction(actionDE);
		
		QAction closeAction = new QAction(QCoreApplication.translate(
				"ManagmentView", ("Close"), null), menu);
		closeAction.triggered.connect(this, "closeWindow()");
		
		menu.addAction(actionAddProject);
		menu.addAction(actionAddUser);
		menu.addMenu(languageMenu);
		menu.addAction(closeAction);
		menu.exec(event.globalPos());
	}
	
	@SuppressWarnings("unused")
	private void showInactivProjectsChanged() {
		projectDetails.showInactive(ui.actionShow_Inactiv_Projects.isChecked());
	}
	
	@SuppressWarnings("unused")
	private void showInactivUsersChanged() {
		userDetails.showInactive(ui.actionInactiv_Users.isChecked());
	}
}
