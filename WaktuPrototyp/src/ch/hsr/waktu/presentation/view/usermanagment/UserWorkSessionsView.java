package ch.hsr.waktu.presentation.view.usermanagment;

import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.model.ComboBoxData;
import ch.hsr.waktu.model.UserWorkSessionModel;
import ch.hsr.waktu.presentation.view.usermanagment.jui.Ui_UserWorkSessions;

import com.trolltech.qt.gui.QWidget;

public class UserWorkSessionsView extends QWidget{ 
	
	private Ui_UserWorkSessions ui = new Ui_UserWorkSessions();
	
	public UserWorkSessionsView(Usr usr) {
		ui.setupUi(this);
		
		ui.tblWorkSessions.setModel(new UserWorkSessionModel(usr));
		ui.tblWorkSessions.horizontalHeader().setStretchLastSection(true);
		
		ComboBoxData.createActiveProjectComboBox(ui.cmbProject);
		ui.cmbProject.currentIndexChanged.connect(this, "projectChanged()");
	}
	
	@SuppressWarnings("unused")
	private void projectChanged() {
		ComboBoxData.createWorkPackageComboBox(ui.cmbWorkpackage, (Project)ui.cmbProject.itemData(ui.cmbProject.currentIndex()));
	}

}
