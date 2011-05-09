package ch.hsr.waktu.gui.qt.view.usermanagment;

import ch.hsr.waktu.controller.TimeController;
import ch.hsr.waktu.controller.datacontroller.WorkSessionController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.domain.WorkSession;
import ch.hsr.waktu.gui.qt.model.ComboBoxData;
import ch.hsr.waktu.gui.qt.model.TableSortFilterModel;
import ch.hsr.waktu.gui.qt.model.UserWorkSessionModel;
import ch.hsr.waktu.gui.qt.view.usermanagment.jui.Ui_UserWorkSessions;

import com.trolltech.qt.core.QDate;
import com.trolltech.qt.gui.QWidget;

public class UserWorkSessionsView extends QWidget{ 
	
	private Ui_UserWorkSessions ui = new Ui_UserWorkSessions();
	private UserWorkSessionModel workSessionModel;
	private TableSortFilterModel filterModel;
	private Usr usr;
	
	public UserWorkSessionsView(Usr usr) {
		ui.setupUi(this);
		this.usr = usr;
		
		workSessionModel = new UserWorkSessionModel(usr);
		filterModel = new TableSortFilterModel();
		filterModel.setDynamicSortFilter(true);
		filterModel.setSourceModel(workSessionModel);
		ui.tblWorkSessions.setModel(filterModel);
		ui.tblWorkSessions.horizontalHeader().setStretchLastSection(true);
		
		WorkSessionController.getInstance().add.connect(this, "added(WorkSession)");
		WorkSessionController.getInstance().removed.connect(this, "removed(WorkSession)");
		WorkSessionController.getInstance().update.connect(this, "updated()");
		
		ComboBoxData.createActiveProjectComboBox(ui.cmbProject);
		ComboBoxData.createWorkPackageComboBox(ui.cmbWorkpackage, (Project)ui.cmbProject.itemData(ui.cmbProject.currentIndex()));
		ui.cmbProject.currentIndexChanged.connect(this, "projectChanged()");
		ui.cmbProject.setCurrentIndex(-1);
		ui.cmbWorkpackage.setCurrentIndex(-1);
		ui.btnAddFilter.clicked.connect(this, "addFilter()");
		ui.btnRemoveFilter.clicked.connect(this, "removeFilter()");
		//TODO
		//ui.lblTotalTime.setText(""+TimeController.calc(project, null, null, null, null));
	}
	
	@SuppressWarnings("unused")
	private void addFilter() {
		filterModel.setProject((Project)ui.cmbProject.itemData(ui.cmbProject.currentIndex()));
		filterModel.setWorkPackage((WorkPackage)ui.cmbWorkpackage.itemData(ui.cmbWorkpackage.currentIndex()));
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
		
		//TODO add filter
		ui.lblTotalTime.setText(""+TimeController.calculateWorktime(usr, start, end));
	}
	
	@SuppressWarnings("unused")
	private void removeFilter() {
		//TODO
		//ui.lblTotalTime.setText(""+TimeController.calculateWorktimeForProject(project, null, null, null, null));
		ui.cmbProject.setCurrentIndex(-1);
		ui.cmbWorkpackage.setCurrentIndex(-1);
		ui.txtStart.setDate(new QDate(01, 01, 1900));
		ui.txtEnd.setDate(new QDate(01, 01, 1900));
		filterModel.setUsr(null);
		filterModel.setWorkPackage(null);
		filterModel.setStart(null);
		filterModel.setEnd(null);
	}
	
	
	@SuppressWarnings("unused")
	private void projectChanged() {
		ComboBoxData.createWorkPackageComboBox(ui.cmbWorkpackage, (Project)ui.cmbProject.itemData(ui.cmbProject.currentIndex()));
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
		workSessionModel.layoutAboutToBeChanged.emit();
		workSessionModel.dataChanged.emit(workSessionModel.index(0, 0),
				workSessionModel.index(workSessionModel.rowCount(),
						workSessionModel.columnCount()));
		workSessionModel.layoutChanged.emit();
	}
	
}
