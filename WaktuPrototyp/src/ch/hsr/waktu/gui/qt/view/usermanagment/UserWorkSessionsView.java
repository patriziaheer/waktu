package ch.hsr.waktu.gui.qt.view.usermanagment;

import ch.hsr.waktu.controller.datacontroller.WorkSessionController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkSession;
import ch.hsr.waktu.gui.qt.model.ComboBoxData;
import ch.hsr.waktu.gui.qt.model.UserWorkSessionModel;
import ch.hsr.waktu.gui.qt.view.usermanagment.jui.Ui_UserWorkSessions;

import com.trolltech.qt.gui.QWidget;

public class UserWorkSessionsView extends QWidget{ 
	
	private Ui_UserWorkSessions ui = new Ui_UserWorkSessions();
	private UserWorkSessionModel workSessionModel;
	
	public UserWorkSessionsView(Usr usr) {
		ui.setupUi(this);
		
		workSessionModel = new UserWorkSessionModel(usr);
		ui.tblWorkSessions.setModel(new UserWorkSessionModel(usr));
		ui.tblWorkSessions.horizontalHeader().setStretchLastSection(true);
		
		WorkSessionController.getInstance().add.connect(this, "added(WorkSession)");
		WorkSessionController.getInstance().removed.connect(this, "removed(WorkSession)");
		WorkSessionController.getInstance().update.connect(this, "updated()");
		
		ComboBoxData.createActiveProjectComboBox(ui.cmbProject);
		ComboBoxData.createWorkPackageComboBox(ui.cmbWorkpackage, (Project)ui.cmbProject.itemData(ui.cmbProject.currentIndex()));
		ui.cmbProject.currentIndexChanged.connect(this, "projectChanged()");
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
	private void updated(WorkSession workSession) {
		updateWorkSessionTable();
	}
	
	@SuppressWarnings("unused")
	private void removed() {
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
