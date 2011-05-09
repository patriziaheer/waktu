package ch.hsr.waktu.gui.qt.view.usermanagment;

import ch.hsr.waktu.controller.datacontroller.ProjectStaffController;
import ch.hsr.waktu.controller.datacontroller.WaktuGeneralException;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.ProjectStaff;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.gui.qt.model.ComboBoxData;
import ch.hsr.waktu.gui.qt.model.UserProjectsModel;
import ch.hsr.waktu.gui.qt.view.IndexButton;
import ch.hsr.waktu.guicontroller.LanguageController;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QWidget;

public class UserProjectsView extends QWidget{
	
	private Ui_UserProjects ui = new Ui_UserProjects();
	private Usr usr;
	private UserProjectsModel projectsModel;
	
	public UserProjectsView(Usr usr) {
		ui.setupUi(this);
		this.usr = usr;
		projectsModel = new UserProjectsModel(usr);
		ui.tblProjects.setModel(projectsModel);
		ui.tblProjects.horizontalHeader().setStretchLastSection(true);
		ui.btnAdd.clicked.connect(this, "addProject()");
		ComboBoxData.createActiveProjectComboBox(ui.cmbProjects);
		

		ProjectStaffController.getInstance().add.connect(this, "added(ProjectStaff)");
		ProjectStaffController.getInstance().removed.connect(this, "removed(ProjectStaff)");
		
		LanguageController.getInstance().languageChanged.connect(this, "translate()");
		
		updateProjectModel();
	}
	
	private void updateProjectModel() {
		try {
			for (int i = 0; i < ProjectStaffController.getInstance().getProjects(usr).size(); i++) {
				QModelIndex currIndex = projectsModel.index(i, projectsModel.columnCount()-1);

				IndexButton deleteButton = new IndexButton(currIndex);
				deleteButton.setFixedHeight(20);
				deleteButton.setIcon(new QIcon("classpath:icons/delete_16x16.png"));
				deleteButton.actionClicked.connect(this, "deleteClicked(IndexButton)");
				
				ui.tblProjects.setIndexWidget(currIndex, deleteButton);
			}
		} catch (WaktuGeneralException e) {
			// TODO unhandled exception
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void deleteClicked(IndexButton btn) {
		try {
			Project proj = ProjectStaffController.getInstance().getProjects(usr).get(btn.getIndex().row());
			ProjectStaffController.getInstance().removeUser(usr, proj);
		} catch (WaktuGeneralException e) {
			// TODO exception handling
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void addProject() {
		try {
			ProjectStaffController.getInstance().addProjectStaff(usr, (Project)ui.cmbProjects.itemData(ui.cmbProjects.currentIndex()));
		} catch (WaktuGeneralException e) {
			// TODO exception handling
			e.printStackTrace();
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
		projectsModel.updateProjectsModel();
		projectsModel.layoutAboutToBeChanged.emit();
		projectsModel.dataChanged.emit(projectsModel.index(0, 0), projectsModel.index(projectsModel.rowCount(), projectsModel.columnCount()));
		projectsModel.layoutChanged.emit();
		updateProjectModel();
	}
	
	@SuppressWarnings("unused")
	private void translate() {
        ui.retranslateUi(this);
	}
}
