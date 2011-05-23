package ch.hsr.waktu.gui.qt.view.projectmanagment;

import ch.hsr.waktu.controller.datacontroller.ProjectStaffController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.ProjectStaff;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.gui.qt.model.ComboBoxData;
import ch.hsr.waktu.gui.qt.model.ProjectStaffModel;
import ch.hsr.waktu.gui.qt.view.IndexButton;
import ch.hsr.waktu.guicontroller.GuiController;
import ch.hsr.waktu.guicontroller.LanguageController;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QWidget;

public class ProjectStaffView extends QWidget {
	
	private Ui_ProjectStaff ui = new Ui_ProjectStaff();
	private Project project;
	private ProjectStaffModel projectStaffModel;
	public Signal1<String> errorMessage = new Signal1<String>();
	
	public ProjectStaffView(Project project) {
		this.project = project;
		
	}

	public void initialize() {
		ui.setupUi(this);
		try {
			ComboBoxData.createUserComboBox(ui.cmbUsers);
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
		try {
			projectStaffModel = new ProjectStaffModel(project);
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
		ui.tblWorkStaff.setModel(projectStaffModel);
		ui.tblWorkStaff.horizontalHeader().setStretchLastSection(true);
		ui.tblWorkStaff.resizeRowsToContents();
		ui.btnAdd.clicked.connect(this, "addUser()");
		
		ProjectStaffController.getInstance().add.connect(this, "added(ProjectStaff)");
		ProjectStaffController.getInstance().removed.connect(this, "removed(ProjectStaff)");

		LanguageController.getInstance().languageChanged.connect(this, "translate()");
		
		if (GuiController.getInstance().canAddProjectStaff() == false) {
			ui.cmbUsers.setVisible(false);
			ui.btnAdd.setVisible(false);
		} else {
			updateProjectStaffModel();
		}
	}
	
	private void updateProjectStaffModel() {
		try {
			for (int i = 0; i < ProjectStaffController.getInstance().getUsers(project).size(); i++) {
				QModelIndex currIndex = projectStaffModel.index(i, projectStaffModel.columnCount()-1);

				IndexButton deleteButton = new IndexButton(currIndex);
				deleteButton.setFixedHeight(20);
				deleteButton.setIcon(new QIcon("classpath:icons/delete_16x16.png"));
				deleteButton.actionClicked.connect(this, "deleteClicked(IndexButton)");
				
				ui.tblWorkStaff.setIndexWidget(currIndex, deleteButton);
			}
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
	}
	
	@SuppressWarnings("unused")
	private void deleteClicked(IndexButton btn) {
		try {
			Usr user = ProjectStaffController.getInstance().getUsers(project).get(btn.getIndex().row());
			ProjectStaffController.getInstance().removeUser(user, project);
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
	}
	
	@SuppressWarnings("unused")
	private void addUser() {
		try {
			ProjectStaffController.getInstance().addProjectStaff((Usr)ui.cmbUsers.itemData(ui.cmbUsers.currentIndex()), project);
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
	}
	
	@SuppressWarnings("unused")
	private void added(ProjectStaff projectStaff) {
		updateTable();
	}

	@SuppressWarnings("unused")
	private void removed(ProjectStaff projectStaff) {
		updateTable();
	}
	
	private void updateTable() {
		try {
			projectStaffModel.updateProjectStaffModel();
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
		projectStaffModel.layoutAboutToBeChanged.emit();
		projectStaffModel.dataChanged.emit(projectStaffModel.index(0, 0), projectStaffModel.index(projectStaffModel.rowCount(), projectStaffModel.columnCount()));
		projectStaffModel.layoutChanged.emit();
		if (GuiController.getInstance().canAddProjectStaff() == true) {
			updateProjectStaffModel();
		}
	}
	
	@SuppressWarnings("unused")
	private void translate() {
        ui.retranslateUi(this);
	}
	
}
