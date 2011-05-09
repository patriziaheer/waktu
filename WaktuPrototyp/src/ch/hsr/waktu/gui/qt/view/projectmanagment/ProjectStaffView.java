package ch.hsr.waktu.gui.qt.view.projectmanagment;

import ch.hsr.waktu.controller.datacontroller.ProjectStaffController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.ProjectStaff;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.gui.qt.model.ComboBoxData;
import ch.hsr.waktu.gui.qt.model.ProjectStaffModel;
import ch.hsr.waktu.gui.qt.view.IndexButton;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QWidget;

public class ProjectStaffView extends QWidget {
	
	private Ui_ProjectStaff ui = new Ui_ProjectStaff();
	private Project project;
	private ProjectStaffModel projectStaffModel;
	
	public ProjectStaffView(Project project) {
		ui.setupUi(this);
		this.project = project;
		projectStaffModel = new ProjectStaffModel(project);
		ui.tblWorkStaff.setModel(projectStaffModel);
		ui.tblWorkStaff.horizontalHeader().setStretchLastSection(true);
		ui.btnAdd.clicked.connect(this, "addUser()");
		ComboBoxData.createUserComboBox(ui.cmbUsers);
		
		ProjectStaffController.getInstance().add.connect(this, "added(ProjectStaff)");
		ProjectStaffController.getInstance().removed.connect(this, "removed(ProjectStaff)");
		updateProjectStaffModel();
	}
	
	private void updateProjectStaffModel() {
		for (int i = 0; i < ProjectStaffController.getInstance().getUsers(project).size(); i++) {
			QModelIndex currIndex = projectStaffModel.index(i, projectStaffModel.columnCount()-1);

			IndexButton deleteButton = new IndexButton(currIndex);
			deleteButton.setFixedHeight(20);
			deleteButton.setIcon(new QIcon("classpath:icons/delete_16x16.png"));
			deleteButton.actionClicked.connect(this, "deleteClicked(IndexButton)");
			
			ui.tblWorkStaff.setIndexWidget(currIndex, deleteButton);
		}
	}
	
	@SuppressWarnings("unused")
	private void deleteClicked(IndexButton btn) {
		Usr user = ProjectStaffController.getInstance().getUsers(project).get(btn.getIndex().row());
		ProjectStaffController.getInstance().removeUser(user, project);
	}
	
	@SuppressWarnings("unused")
	private void addUser() {
		ProjectStaffController.getInstance().addProjectStaff((Usr)ui.cmbUsers.itemData(ui.cmbUsers.currentIndex()), project);
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
		projectStaffModel.updateProjectStaffModel();
		projectStaffModel.layoutAboutToBeChanged.emit();
		projectStaffModel.dataChanged.emit(projectStaffModel.index(0, 0), projectStaffModel.index(projectStaffModel.rowCount(), projectStaffModel.columnCount()));
		projectStaffModel.layoutChanged.emit();
		updateProjectStaffModel();
	}
	
}
