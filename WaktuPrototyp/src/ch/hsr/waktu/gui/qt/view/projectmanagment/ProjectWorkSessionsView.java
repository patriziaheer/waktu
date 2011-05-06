package ch.hsr.waktu.gui.qt.view.projectmanagment;

import ch.hsr.waktu.controller.datacontroller.WorkSessionController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.domain.WorkSession;
import ch.hsr.waktu.gui.qt.model.ComboBoxData;
import ch.hsr.waktu.gui.qt.model.ProjectWorkSessionModel;
import ch.hsr.waktu.gui.qt.model.TableSortFilterModel;
import ch.hsr.waktu.gui.qt.view.projectmanagment.jui.Ui_ProjectWorkSessions;
import ch.hsr.waktu.services.TimeUtil;

import com.trolltech.qt.core.QDate;
import com.trolltech.qt.gui.QWidget;

public class ProjectWorkSessionsView extends QWidget {
	
	private Ui_ProjectWorkSessions ui = new Ui_ProjectWorkSessions();
	private Project project;
	private ProjectWorkSessionModel workSessionModel;
	private TableSortFilterModel filterModel = new TableSortFilterModel();
	
	public ProjectWorkSessionsView(Project project) {
		ui.setupUi(this);
		this.project = project;
		workSessionModel = new ProjectWorkSessionModel(this.project);
		filterModel.setDynamicSortFilter(true);
		filterModel.setSourceModel(workSessionModel);
		ui.tblWorkSessions.setModel(filterModel);
		ui.tblWorkSessions.horizontalHeader().setStretchLastSection(true);

		ComboBoxData.createUserComboBox(ui.cmbUser);
		ComboBoxData.createWorkPackageComboBox(ui.cmbWorkpackage, project);
		ui.cmbUser.setCurrentIndex(-1);
		ui.cmbWorkpackage.setCurrentIndex(-1);
		ui.btnAddFilter.clicked.connect(this, "addFilter()");
		ui.btnRemoveFilter.clicked.connect(this, "removeFilter()");
		ui.lblTotalTime.setText(""+TimeUtil.calculateWorktimeForProject(project, null, null, null, null));
		
		WorkSessionController.getInstance().add.connect(this, "added(WorkSession)");
		WorkSessionController.getInstance().removed.connect(this, "removed(WorkSession)");
		WorkSessionController.getInstance().update.connect(this, "updated()");
	}
	
	@SuppressWarnings("unused")
	private void addFilter() {
		filterModel.setUsr((Usr)ui.cmbUser.itemData(ui.cmbUser.currentIndex()));
		filterModel.setWorkPackage((WorkPackage)ui.cmbUser.itemData(ui.cmbWorkpackage.currentIndex()));
		filterModel.setStart(ui.txtStart.date());
		filterModel.setEnd(ui.txtEnd.date());
		
		ui.lblTotalTime.setText(""+TimeUtil.calculateWorktimeForProject(project, (WorkPackage)ui.cmbWorkpackage.itemData(ui.cmbWorkpackage.currentIndex()), (Usr)ui.cmbUser.itemData(ui.cmbUser.currentIndex()), ui.txtStart.dateTime(), ui.txtEnd.dateTime()));
	}
	
	@SuppressWarnings("unused")
	private void removeFilter() {
		ui.lblTotalTime.setText(""+TimeUtil.calculateWorktimeForProject(project, null, null, null, null));
		ui.cmbUser.setCurrentIndex(-1);
		ui.cmbUser.setCurrentIndex(-1);
		ui.txtStart.setDate(new QDate(01, 01, 1900));
		ui.txtEnd.setDate(new QDate(31, 12, 2999));
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
