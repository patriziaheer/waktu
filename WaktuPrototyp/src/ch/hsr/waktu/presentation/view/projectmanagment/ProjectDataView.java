package ch.hsr.waktu.presentation.view.projectmanagment;

import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.presentation.view.projectmanagment.jui.Ui_ProjectData;

import com.trolltech.qt.gui.QWidget;

public class ProjectDataView extends QWidget {

	private Ui_ProjectData ui = new Ui_ProjectData();
	private Project project;
	
	public ProjectDataView(Project project) {
		this.project = project;
		ui.setupUi(this);
		setFields();
	}
	
	private void setFields() {
		if (project != null) {
			ui.btnAdd.setVisible(false);
			
			ui.txtDescription.setText(project.getDescription());
			ui.txtDescription.setEnabled(false);
			
			ui.txtProjectnumber.setText(project.getProjectIdentifier());
			ui.txtProjectnumber.setEnabled(false);
			
			ui.txtPlannedTime.setValue(project.getPlannedTime());
			ui.txtPlannedTime.setEnabled(false);
			
			ui.txtPlannedTime.setValue(project.getPlannedTime());
			ui.txtPlannedTime.setEnabled(false);
		} else {
			ui.btnAdd.setVisible(true);

			ui.txtDescription.setEnabled(true);
			ui.txtProjectnumber.setEnabled(true);
			ui.txtPlannedTime.setEnabled(true);
			ui.txtPlannedTime.setEnabled(true);
		}
	}
	
}
