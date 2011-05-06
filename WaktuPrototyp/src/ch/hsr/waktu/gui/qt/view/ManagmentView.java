package ch.hsr.waktu.gui.qt.view;

import ch.hsr.waktu.controller.GuiController;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.gui.qt.view.projectmanagment.ProjectDetails;
import ch.hsr.waktu.gui.qt.view.usermanagment.UserDetails;

import com.trolltech.qt.gui.QMainWindow;

public class ManagmentView extends QMainWindow {

	private Ui_ManagmentWindow ui = new Ui_ManagmentWindow();
	private Usr currUsr;
	private ProjectDetails projectDetails;
	private UserDetails userDetails;
	
	public ManagmentView(Usr usr) {
		currUsr = usr;
		
		ui.setupUi(this);
		ui.tabWidget.removeTab(0);
		projectDetails = new ProjectDetails();
		ui.tabWidget.addTab(projectDetails, tr("Projects"));
		userDetails = new UserDetails(); 
		ui.tabWidget.addTab(userDetails, tr("Users"));
		
		ui.actionAddUser.setVisible(GuiController.getInstance().canAddUser());
		ui.actionAddProject.setVisible(GuiController.getInstance().canAddProject(currUsr));
		
		ui.actionAddProject.triggered.connect(this, "addProject()");
		ui.actionAddUser.triggered.connect(this, "addUser()");
		ui.actionClose.triggered.connect(this, "closeWindow()");
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
	
}
