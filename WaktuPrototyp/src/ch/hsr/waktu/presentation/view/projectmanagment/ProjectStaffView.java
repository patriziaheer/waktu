package ch.hsr.waktu.presentation.view.projectmanagment;

import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.model.ProjectStaffModel;
import ch.hsr.waktu.presentation.view.projectmanagment.jui.Ui_ProjectStaff;

import com.trolltech.qt.gui.QWidget;

public class ProjectStaffView extends QWidget {
	
	private Ui_ProjectStaff ui = new Ui_ProjectStaff();
	
	public ProjectStaffView(Project project) {
		ui.setupUi(this);
		ui.tblWorkStaff.setModel(new ProjectStaffModel(project));
		ui.tblWorkStaff.horizontalHeader().setStretchLastSection(true);
	}

}
