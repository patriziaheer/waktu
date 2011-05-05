package ch.hsr.waktu.presentation.view.projectmanagment;

import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.model.ComboBoxData;
import ch.hsr.waktu.model.ProjectWorkSessionModel;
import ch.hsr.waktu.model.TableSortFilterModel;
import ch.hsr.waktu.presentation.view.projectmanagment.jui.Ui_ProjectWorkSessions;
import ch.hsr.waktu.services.TimeUtil;

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
		ui.btnAddFilter.clicked.connect(this, "addFilter()");
		ui.btnRemoveFilter.clicked.connect(this, "removeFilter()");
		ui.lblTotalTime.setText(""+TimeUtil.calculateWorktimeForProject(project, null, null, null, null));
	}
	
	@SuppressWarnings("unused")
	private void addFilter() {
		filterModel.setUsr((Usr)ui.cmbUser.itemData(ui.cmbUser.currentIndex()));
		
		ui.lblTotalTime.setText(""+TimeUtil.calculateWorktimeForProject(project, (WorkPackage)ui.cmbWorkpackage.itemData(ui.cmbWorkpackage.currentIndex()), (Usr)ui.cmbUser.itemData(ui.cmbUser.currentIndex()), ui.txtStart.dateTime(), ui.txtEnd.dateTime()));
	}
	
	@SuppressWarnings("unused")
	private void removeFilter() {
		ui.lblTotalTime.setText(""+TimeUtil.calculateWorktimeForProject(project, null, null, null, null));
		
	}
	

}
