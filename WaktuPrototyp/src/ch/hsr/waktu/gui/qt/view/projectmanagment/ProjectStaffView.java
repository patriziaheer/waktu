package ch.hsr.waktu.gui.qt.view.projectmanagment;

import ch.hsr.waktu.controller.datacontroller.ProjectStaffController;
import ch.hsr.waktu.controller.datacontroller.UserController;
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
import com.trolltech.qt.gui.QAbstractItemView.SelectionBehavior;
import com.trolltech.qt.gui.QAbstractItemView.SelectionMode;

public class ProjectStaffView extends QWidget {

    private Ui_ProjectStaff ui = new Ui_ProjectStaff();
    private Project project;
    private ProjectStaffModel projectStaffModel;
    public Signal1<String> errorMessage = new Signal1<String>();

    public ProjectStaffView(final Project project) {
        this.project = project;

    }

    public void initialize() {
        ui.setupUi(this);
        try {
            ComboBoxData.createUserProjectStaffComboBox(ui.cmbUsers, project);
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
        ui.tblWorkStaff.setSelectionMode(SelectionMode.SingleSelection);
        ui.tblWorkStaff.setSelectionBehavior(SelectionBehavior.SelectRows);

        ui.btnAdd.clicked.connect(this, "addUser()");

        ProjectStaffController.getInstance().add.connect(this,
                "added(ProjectStaff)");
        ProjectStaffController.getInstance().removed.connect(this,
                "removed(ProjectStaff)");

        LanguageController.getInstance().languageChanged.connect(this,
                "translate()");

        UserController.getInstance().add.connect(this, "userAdded(Usr)");
        UserController.getInstance().update.connect(this, "userChanged()");

        if (!GuiController.getInstance().canAddProjectStaff()) {
            ui.cmbUsers.setVisible(false);
            ui.btnAdd.setVisible(false);
        } else {
            updateProjectStaffModel();
        }
    }

    private void updateProjectStaffModel() {
        try {
            for (int i = 0; i < ProjectStaffController.getInstance()
                    .getUsers(project).size(); i++) {
                QModelIndex currIndex = projectStaffModel.index(i,
                        projectStaffModel.columnCount() - 1);

                IndexButton deleteButton = new IndexButton(currIndex);
                deleteButton.setFixedHeight(20);
                deleteButton.setIcon(new QIcon(
                        "classpath:icons/delete_16x16.png"));
                deleteButton.actionClicked.connect(this,
                        "deleteClicked(IndexButton)");

                ui.tblWorkStaff.setIndexWidget(currIndex, deleteButton);
            }
        } catch (WaktuException e) {
            errorMessage.emit(e.getMessage());
        }
    }

    @SuppressWarnings("unused")
    private void deleteClicked(final IndexButton btn) {
        try {
            Usr user = ProjectStaffController.getInstance().getUsers(project)
                    .get(btn.getIndex().row());
            ProjectStaffController.getInstance().removeUser(user, project);
        } catch (WaktuException e) {
            errorMessage.emit(e.getMessage());
        }
    }

    @SuppressWarnings("unused")
    private void addUser() {
        try {
            Usr usr = (Usr) ui.cmbUsers.itemData(ui.cmbUsers.currentIndex());
            if (usr != null) {
                ProjectStaffController.getInstance().addProjectStaff(usr,
                        project);
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
            ComboBoxData.createUserProjectStaffComboBox(ui.cmbUsers, project);
        } catch (WaktuException e) {
            errorMessage.emit(e.getMessage());
        }
    }

    private void updateTable() {
        try {
            if (projectStaffModel != null) {
                projectStaffModel.updateProjectStaffModel();
            } else {
                projectStaffModel = new ProjectStaffModel(project);
            }
        } catch (WaktuException e) {
            errorMessage.emit(e.getMessage());
        }
        projectStaffModel.layoutAboutToBeChanged.emit();
        projectStaffModel.dataChanged.emit(projectStaffModel.index(0, 0),
                projectStaffModel.index(projectStaffModel.rowCount(),
                        projectStaffModel.columnCount()));
        projectStaffModel.layoutChanged.emit();
        if (GuiController.getInstance().canAddProjectStaff()) {
            updateProjectStaffModel();
        }
    }

    @SuppressWarnings("unused")
    private void translate() {
        ui.retranslateUi(this);
    }

    @SuppressWarnings("unused")
    private void userAdded(final Usr usr) {
        updateData();
    }

    @SuppressWarnings("unused")
    private void userChanged() {
        updateData();
    }

}
