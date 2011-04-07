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
	}
	
}
