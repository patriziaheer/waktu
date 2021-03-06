package ch.hsr.waktu.gui.qt.view.projectmanagment;

import java.util.List;

import ch.hsr.waktu.controller.datacontroller.WorkPackageController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.gui.qt.model.ProjectWorkPackageModel;
import ch.hsr.waktu.gui.qt.view.IndexCheckbox;
import ch.hsr.waktu.guicontroller.GuiController;
import ch.hsr.waktu.guicontroller.LanguageController;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.gui.QWidget;
import com.trolltech.qt.gui.QAbstractItemView.SelectionBehavior;
import com.trolltech.qt.gui.QAbstractItemView.SelectionMode;

public class ProjectWorkPackageView extends QWidget {

    private Ui_ProjectWorkPackage ui = new Ui_ProjectWorkPackage();
    private Project project;
    private ProjectWorkPackageModel workPackageModel;
    public Signal1<String> errorMessage = new Signal1<String>();

    public ProjectWorkPackageView(final Project project) {
        this.project = project;
    }

    public void initialize() {
        ui.setupUi(this);
        workPackageModel = new ProjectWorkPackageModel(project);
        workPackageModel.errorMessage.connect(this, "showErrorMessage(String)");

        ui.tblWorkPackages.setModel(workPackageModel);
        ui.tblWorkPackages.horizontalHeader().setStretchLastSection(true);
        ui.tblWorkPackages.resizeRowsToContents();
        ui.tblWorkPackages.setSelectionMode(SelectionMode.SingleSelection);
        ui.tblWorkPackages.setSelectionBehavior(SelectionBehavior.SelectRows);

        ui.btnAdd.clicked.connect(this, "addClicked()");
        WorkPackageController.getInstance().add.connect(this,
                "addData(WorkPackage)");

        WorkPackageController.getInstance().update.connect(this, "updated()");

        if (!GuiController.getInstance().canAddWorkPackage(project)) {
            ui.btnAdd.setVisible(false);
            ui.txtDescription.setVisible(false);
        }

        LanguageController.getInstance().languageChanged.connect(this,
                "translate()");

        updateWorkPackageModel();
    }

    private void updateWorkPackageModel() {
        try {
            List<WorkPackage> wps = WorkPackageController.getInstance()
                    .getAllWorkPackages(project);
            for (int i = 0; i < wps.size(); i++) {
                QModelIndex currIndex = workPackageModel.index(i,
                        workPackageModel.columnCount() - 1);
                WorkPackage wp = workPackageModel.getWorkPackage(currIndex
                        .row());
                IndexCheckbox chk = new IndexCheckbox(currIndex, wp,
                        !wp.isActive());
                chk.errorMessage.connect(this, "showErrorMessage(String)");

                ui.tblWorkPackages.setIndexWidget(currIndex, chk);
            }
        } catch (WaktuException e) {
            errorMessage.emit(e.getMessage());
        }
    }

    @SuppressWarnings("unused")
    private void addClicked() {
        if (!ui.txtDescription.text().isEmpty()) {
            try {
                WorkPackageController.getInstance().addWorkPackage(project,
                        ui.txtDescription.text());
            } catch (WaktuException e) {
                showErrorMessage(e.getMessage());
            }
            ui.txtDescription.setText("");
        }
    }

    public void updateTable() {
        if (workPackageModel != null) {
            workPackageModel.updateWorkPackageModel();
        } else {
            workPackageModel = new ProjectWorkPackageModel(project);
        }
        workPackageModel.layoutAboutToBeChanged.emit();
        workPackageModel.dataChanged.emit(workPackageModel.index(0, 0),
                workPackageModel.index(workPackageModel.rowCount(),
                        workPackageModel.columnCount()));
        workPackageModel.layoutChanged.emit();
        updateWorkPackageModel();
    }

    @SuppressWarnings("unused")
    private void addData(final WorkPackage workPackage) {
        updateTable();
    }

    @SuppressWarnings("unused")
    private void updated() {
        updateTable();
    }

    @SuppressWarnings("unused")
    private void translate() {
        ui.retranslateUi(this);
    }

    private void showErrorMessage(final String errorMessageString) {
        errorMessage.emit(errorMessageString);
    }

}
