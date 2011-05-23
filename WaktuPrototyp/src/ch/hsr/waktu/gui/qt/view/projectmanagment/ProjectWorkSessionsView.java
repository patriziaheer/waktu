package ch.hsr.waktu.gui.qt.view.projectmanagment;

import ch.hsr.waktu.controller.TimeController;
import ch.hsr.waktu.controller.datacontroller.WorkSessionController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.domain.WorkSession;
import ch.hsr.waktu.gui.qt.model.ComboBoxData;
import ch.hsr.waktu.gui.qt.model.ProjectWorkSessionModel;
import ch.hsr.waktu.gui.qt.model.TableSortFilterModel;
import ch.hsr.waktu.guicontroller.LanguageController;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.core.QDate;
import com.trolltech.qt.gui.QWidget;

public class ProjectWorkSessionsView extends QWidget {
	
	private Ui_ProjectWorkSessions ui = new Ui_ProjectWorkSessions();
	private Project project;
	private ProjectWorkSessionModel workSessionModel;
	private TableSortFilterModel filterModel = new TableSortFilterModel();
	public Signal1<String> errorMessage = new Signal1<String>();
	
	public ProjectWorkSessionsView(Project project) {
		this.project = project;
	}

	public void initialize() {
		ui.setupUi(this);
		try {
			ComboBoxData.createUserComboBox(ui.cmbUser);
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
		try {
			ComboBoxData.createAllWorkPackageComboBox(ui.cmbWorkpackage, project);
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
		try {
			workSessionModel = new ProjectWorkSessionModel(this.project);
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
		filterModel.setDynamicSortFilter(true);
		filterModel.setSourceModel(workSessionModel);
		ui.tblWorkSessions.setModel(filterModel);
		ui.tblWorkSessions.horizontalHeader().setStretchLastSection(true);
		ui.tblWorkSessions.resizeRowsToContents();

		ui.cmbUser.setCurrentIndex(-1);
		ui.cmbWorkpackage.setCurrentIndex(-1);
		ui.btnAddFilter.clicked.connect(this, "addFilter()");
		ui.btnRemoveFilter.clicked.connect(this, "removeFilter()");
		try {
			ui.lblTotalTime.setText(""+TimeController.calculateWorktimeForProject(project, null, null, null, null));
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
		
		WorkSessionController.getInstance().add.connect(this, "added(WorkSession)");
		WorkSessionController.getInstance().removed.connect(this, "removed(WorkSession)");
		WorkSessionController.getInstance().update.connect(this, "updated()");
		ui.txtStart.setDate(new QDate(01,01,1900));
		ui.txtEnd.setDate(new QDate(01,01,1900));

		LanguageController.getInstance().languageChanged.connect(this, "translate()");
	}
	
	@SuppressWarnings("unused")
	private void addFilter() {
		filterModel.setUsr((Usr)ui.cmbUser.itemData(ui.cmbUser.currentIndex()));
		filterModel.setWorkPackage((WorkPackage)ui.cmbWorkpackage.itemData(ui.cmbWorkpackage.currentIndex()));
		if (ui.chkFilterDate.isChecked()) {
			QDate start = null;
			QDate end = null;
			
			if (ui.txtStart.date().toString("dd.MM.yyyy").equals("01.01.2000") == false) {
				start = ui.txtStart.date(); 
			}
			if (ui.txtEnd.date().toString("dd.MM.yyyy").equals("01.01.2000") == false) {
				end = ui.txtEnd.date();
			}
			filterModel.setStart(start);
			filterModel.setEnd(end);
			
			try {
				ui.lblTotalTime.setText(""+TimeController.calculateWorktimeForProject(project, (WorkPackage)ui.cmbWorkpackage.itemData(ui.cmbWorkpackage.currentIndex()), (Usr)ui.cmbUser.itemData(ui.cmbUser.currentIndex()), start, end));
			} catch (WaktuException e) {
				errorMessage.emit(e.getMessage());
			}
		} else {
			filterModel.setStart(null);
			filterModel.setEnd(null);
			try {
				ui.lblTotalTime.setText(""+TimeController.calculateWorktimeForProject(project, (WorkPackage)ui.cmbWorkpackage.itemData(ui.cmbWorkpackage.currentIndex()), (Usr)ui.cmbUser.itemData(ui.cmbUser.currentIndex()), null, null));
			} catch (WaktuException e) {
				errorMessage.emit(e.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	private void removeFilter() {
		try {
			ui.lblTotalTime.setText(""+TimeController.calculateWorktimeForProject(project, null, null, null, null));
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
		ui.cmbUser.setCurrentIndex(-1);
		ui.cmbWorkpackage.setCurrentIndex(-1);
		ui.txtStart.setDate(new QDate(01, 01, 1900));
		ui.txtEnd.setDate(new QDate(01, 01, 1900));
		ui.chkFilterDate.setChecked(false);
		filterModel.setUsr(null);
		filterModel.setWorkPackage(null);
		filterModel.setStart(null);
		filterModel.setEnd(null);
	}
	
	@SuppressWarnings("unused")
	private void added(WorkSession workSession) {
		updateWorkSessionTable();
	}
	
	@SuppressWarnings("unused")
	private void updated() {
		updateWorkSessionTable();
	}
	
	@SuppressWarnings("unused")
	private void removed(WorkSession workSession) {
		updateWorkSessionTable();
	}
	
	private void updateWorkSessionTable() {
		try {
			workSessionModel.updateWorkSessionModel();
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
	

}
