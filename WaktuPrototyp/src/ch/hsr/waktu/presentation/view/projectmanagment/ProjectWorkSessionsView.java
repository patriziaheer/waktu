package ch.hsr.waktu.presentation.view.projectmanagment;

import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.presentation.view.projectmanagment.jui.Ui_ProjectWorkSessions;

import com.trolltech.qt.gui.QWidget;

public class ProjectWorkSessionsView extends QWidget {
	
	private Ui_ProjectWorkSessions ui = new Ui_ProjectWorkSessions();
	private Project project;
	
	public ProjectWorkSessionsView(Project project) {
		this.project = project;
		ui.setupUi(this);
	}

}
