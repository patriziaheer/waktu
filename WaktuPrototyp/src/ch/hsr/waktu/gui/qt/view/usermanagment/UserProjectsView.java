package ch.hsr.waktu.gui.qt.view.usermanagment;

import ch.hsr.waktu.controller.datacontroller.ProjectController;
import ch.hsr.waktu.controller.datacontroller.ProjectStaffController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.ProjectStaff;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.gui.qt.model.ComboBoxData;
import ch.hsr.waktu.gui.qt.model.UserProjectsModel;
import ch.hsr.waktu.gui.qt.view.IndexButton;
import ch.hsr.waktu.guicontroller.GuiController;
import ch.hsr.waktu.guicontroller.LanguageController;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QWidget;
import com.trolltech.qt.gui.QAbstractItemView.SelectionBehavior;
import com.trolltech.qt.gui.QAbstractItemView.SelectionMode;

public class UserProjectsView extends QWidget {

	private Ui_UserProjects ui = new Ui_UserProjects();
	private Usr usr;
	private UserProjectsModel projectsModel;
	public Signal1<String> errorMessage = new Signal1<String>();

	public UserProjectsView(final Usr usr) {
		this.usr = usr;
	}

	public void initialize() {
		ui.setupUi(this);
		try {
			ComboBoxData.createProjectProjectStaffComboBox(ui.cmbProjects, usr);
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
		try {
			projectsModel = new UserProjectsModel(usr);
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
		ui.tblProjects.setModel(projectsModel);
		ui.tblProjects.horizontalHeader().setStretchLastSection(true);
		ui.tblProjects.resizeRowsToContents();
		ui.tblProjects.setSelectionMode(SelectionMode.SingleSelection);
		ui.tblProjects.setSelectionBehavior(SelectionBehavior.SelectRows);

		ui.btnAdd.clicked.connect(this, "addProject()");

		ProjectStaffController.getInstance().add.connect(this,
				"added(ProjectStaff)");
		ProjectStaffController.getInstance().removed.connect(this,
				"removed(ProjectStaff)");

		LanguageController.getInstance().languageChanged.connect(this,
				"translate()");

		if (!GuiController.getInstance().canAddProjectStaff()) {
			ui.btnAdd.setVisible(false);
			ui.cmbProjects.setVisible(false);
		} else {
			updateProjectModel();
		}

		ProjectController.getInstance().add.connect(this,
				"projectAdded(Project)");
		ProjectController.getInstance().update
				.connect(this, "projectUpdated()");
	}

	private void updateProjectModel() {
		try {
			for (int i = 0; i < ProjectStaffController.getInstance()
					.getProjects(usr).size(); i++) {
				QModelIndex currIndex = projectsModel.index(i,
						projectsModel.columnCount() - 1);

				IndexButton deleteButton = new IndexButton(currIndex);
				deleteButton.setFixedHeight(20);
				deleteButton.setIcon(new QIcon(
						"classpath:icons/delete_16x16.png"));
				deleteButton.actionClicked.connect(this,
						"deleteClicked(IndexButton)");

				ui.tblProjects.setIndexWidget(currIndex, deleteButton);
			}
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
	}

	@SuppressWarnings("unused")
	private void deleteClicked(final IndexButton btn) {
		try {
			Project proj = ProjectStaffController.getInstance()
					.getProjects(usr).get(btn.getIndex().row());
			ProjectStaffController.getInstance().removeUser(usr, proj);
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
	}

	@SuppressWarnings("unused")
	private void addProject() {
		try {
			Project proj = (Project) ui.cmbProjects.itemData(ui.cmbProjects
					.currentIndex());
			if (proj != null) {
				ProjectStaffController.getInstance().addProjectStaff(usr, proj);
			}
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
	}

	@SuppressWarnings("unused")
	private void added(final ProjectStaff projectStaff) {
		updateData();
	}

	@SuppressWarnings("unused")
	private void removed(final ProjectStaff projectStaff) {
		updateData();
	}

	private void updateData() {
		updateTable();
		try {
			ComboBoxData.createProjectProjectStaffComboBox(ui.cmbProjects, usr);
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
	}

	private void updateTable() {
		try {
			if (projectsModel != null) {
				projectsModel.updateProjectsModel();
			} else {
				projectsModel = new UserProjectsModel(usr);
			}
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
		projectsModel.layoutAboutToBeChanged.emit();
		projectsModel.dataChanged.emit(
				projectsModel.index(0, 0),
				projectsModel.index(projectsModel.rowCount(),
						projectsModel.columnCount()));
		projectsModel.layoutChanged.emit();
		if (GuiController.getInstance().canAddProjectStaff()) {
			updateProjectModel();
		}
	}

	@SuppressWarnings("unused")
	private void translate() {
		ui.retranslateUi(this);
	}

	@SuppressWarnings("unused")
	private void projectAdded(final Project proj) {
		updateData();
	}

	@SuppressWarnings("unused")
	private void projectUpdated() {
		updateData();
	}
}
