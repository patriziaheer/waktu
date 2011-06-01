package ch.hsr.waktu.gui.qt.view.usermanagment;

import ch.hsr.waktu.controller.TimeController;
import ch.hsr.waktu.controller.datacontroller.ProjectController;
import ch.hsr.waktu.controller.datacontroller.WorkPackageController;
import ch.hsr.waktu.controller.datacontroller.WorkSessionController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.domain.WorkSession;
import ch.hsr.waktu.gui.qt.model.ComboBoxData;
import ch.hsr.waktu.gui.qt.model.TableSortFilterModel;
import ch.hsr.waktu.gui.qt.model.UserWorkSessionModel;
import ch.hsr.waktu.guicontroller.LanguageController;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.core.QDate;
import com.trolltech.qt.gui.QWidget;
import com.trolltech.qt.gui.QAbstractItemView.SelectionBehavior;
import com.trolltech.qt.gui.QAbstractItemView.SelectionMode;

public class UserWorkSessionsView extends QWidget {

    private Ui_UserWorkSessions ui = new Ui_UserWorkSessions();
    private UserWorkSessionModel workSessionModel;
    private TableSortFilterModel filterModel;
    private Usr usr;
    public Signal1<String> errorMessage = new Signal1<String>();

    public UserWorkSessionsView(final Usr usr) {
        this.usr = usr;
    }

    public void initialize() {
        ui.setupUi(this);

        projectCombo();
        workPackageCombo(null);
        try {
            workSessionModel = new UserWorkSessionModel(usr);
        } catch (WaktuException e) {
            errorMessage.emit(e.getMessage());
        }
        filterModel = new TableSortFilterModel();
        filterModel.setDynamicSortFilter(true);
        filterModel.setSourceModel(workSessionModel);
        ui.tblWorkSessions.setModel(filterModel);
        ui.tblWorkSessions.horizontalHeader().setStretchLastSection(true);
        ui.tblWorkSessions.resizeRowsToContents();
        ui.tblWorkSessions.setSelectionMode(SelectionMode.SingleSelection);
        ui.tblWorkSessions.setSelectionBehavior(SelectionBehavior.SelectRows);

        WorkSessionController.getInstance().add.connect(this,
                "added(WorkSession)");
        WorkSessionController.getInstance().removed.connect(this,
                "removed(WorkSession)");
        WorkSessionController.getInstance().update.connect(this, "updated()");

        ui.cmbProject.currentIndexChanged.connect(this, "projectChanged()");
        ui.cmbProject.setCurrentIndex(-1);
        ui.cmbWorkpackage.setCurrentIndex(-1);
        ui.btnAddFilter.clicked.connect(this, "addFilter()");
        ui.btnRemoveFilter.clicked.connect(this, "removeFilter()");

        try {
            ui.lblTotalTime.setText(""
                    + TimeController.calculateWorktime(usr, null, null, null,
                            null));
        } catch (WaktuException e) {
            errorMessage.emit(e.getMessage());
        }

        LanguageController.getInstance().languageChanged.connect(this,
                "translate()");

        WorkPackageController.getInstance().add.connect(this,
                "workPackageAdded(WorkPackage)");
        WorkPackageController.getInstance().update.connect(this,
                "workPackageUpdated()");

        ProjectController.getInstance().add.connect(this,
                "projectAdded(Project)");
        ProjectController.getInstance().update
                .connect(this, "projectUpdated()");
    }

    @SuppressWarnings("unused")
    private void addFilter() {
        filterModel.setProject((Project) ui.cmbProject.itemData(ui.cmbProject
                .currentIndex()));
        filterModel.setWorkPackage((WorkPackage) ui.cmbWorkpackage
                .itemData(ui.cmbWorkpackage.currentIndex()));
        if (ui.chkFilterDate.isChecked()) {
            QDate start = null;
            QDate end = null;

            if (!ui.txtStart.date().toString("dd.MM.yyyy").equals("01.01.2000")) {
                start = ui.txtStart.date();
            }
            if (!ui.txtEnd.date().toString("dd.MM.yyyy").equals("01.01.2000")) {
                end = ui.txtEnd.date();
            }
            filterModel.setStart(start);
            filterModel.setEnd(end);

            try {
                ui.lblTotalTime.setText(""
                        + TimeController.calculateWorktime(usr,
                                (Project) ui.cmbProject.itemData(ui.cmbProject
                                        .currentIndex()),
                                (WorkPackage) ui.cmbWorkpackage
                                        .itemData(ui.cmbWorkpackage
                                                .currentIndex()), start, end));
            } catch (WaktuException e) {
                errorMessage.emit(e.getMessage());
            }
        } else {
            filterModel.setStart(null);
            filterModel.setEnd(null);
            try {
                ui.lblTotalTime.setText(""
                        + TimeController.calculateWorktime(usr,
                                (Project) ui.cmbProject.itemData(ui.cmbProject
                                        .currentIndex()),
                                (WorkPackage) ui.cmbWorkpackage
                                        .itemData(ui.cmbWorkpackage
                                                .currentIndex()), null, null));
            } catch (WaktuException e) {
                errorMessage.emit(e.getMessage());
            }
        }
    }

    @SuppressWarnings("unused")
    private void removeFilter() {
        try {
            ui.lblTotalTime.setText(""
                    + TimeController.calculateWorktime(usr, null, null, null,
                            null));
        } catch (WaktuException e) {
            errorMessage.emit(e.getMessage());
        }
        ui.cmbProject.setCurrentIndex(-1);
        ui.cmbWorkpackage.setCurrentIndex(-1);
        ui.txtStart.setDate(null);
        ui.txtEnd.setDate(null);
        ui.chkFilterDate.setChecked(false);
        filterModel.setUsr(null);
        filterModel.setProject(null);
        filterModel.setWorkPackage(null);
        filterModel.setStart(null);
        filterModel.setEnd(null);
    }

    @SuppressWarnings("unused")
    private void projectChanged() {
        workPackageCombo((Project) ui.cmbProject.itemData(ui.cmbProject
                .currentIndex()));
    }

    @SuppressWarnings("unused")
    private void added(final WorkSession workSession) {
        updateWorkSessionTable();
    }

    @SuppressWarnings("unused")
    private void updated() {
        updateWorkSessionTable();
    }

    @SuppressWarnings("unused")
    private void removed(final WorkSession workSession) {
        updateWorkSessionTable();
    }

    private void updateWorkSessionTable() {
        try {
            if (workSessionModel != null) {
                workSessionModel.updateWorkSessionModel();
            } else {
                workSessionModel = new UserWorkSessionModel(usr);
            }
        } catch (WaktuException e) {
            errorMessage.emit(e.getMessage());
        }
        workSessionModel.layoutAboutToBeChanged.emit();
        workSessionModel.dataChanged.emit(workSessionModel.index(0, 0),
                workSessionModel.index(workSessionModel.rowCount(),
                        workSessionModel.columnCount()));
        workSessionModel.layoutChanged.emit();
    }

    @SuppressWarnings("unused")
    private void translate() {
        ui.retranslateUi(this);
    }

    private void workPackageCombo(final Project proj) {
        try {
            if (proj == null) {
                ComboBoxData.createAllWorkPackageComboBox(ui.cmbWorkpackage);
            } else {
                ComboBoxData.createAllWorkPackageComboBox(ui.cmbWorkpackage,
                        proj);
            }
            ui.cmbWorkpackage.setCurrentIndex(-1);
        } catch (WaktuException e) {
            errorMessage.emit(e.getMessage());
        }
    }

    public void projectCombo() {
        try {
            ComboBoxData.createAllProjectComboBox(ui.cmbProject);
        } catch (WaktuException e) {
            errorMessage.emit(e.getMessage());
        }
    }

    @SuppressWarnings("unused")
    private void workPackageAdded(final WorkPackage workPackage) {
        workPackageCombo((Project) ui.cmbProject.itemData(ui.cmbProject
                .currentIndex()));
    }

    @SuppressWarnings("unused")
    private void workPackageUpdated() {
        workPackageCombo((Project) ui.cmbProject.itemData(ui.cmbProject
                .currentIndex()));
    }

    @SuppressWarnings("unused")
    private void projectAdded(final Project project) {
        projectCombo();
    }

    @SuppressWarnings("unused")
    private void projectUpdated() {
        projectCombo();
    }

}
