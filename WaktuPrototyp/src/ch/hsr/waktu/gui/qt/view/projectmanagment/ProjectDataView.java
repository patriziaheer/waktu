package ch.hsr.waktu.gui.qt.view.projectmanagment;

import ch.hsr.waktu.controller.datacontroller.ProjectController;
import ch.hsr.waktu.controller.datacontroller.WaktuException;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.gui.qt.model.ComboBoxData;

import com.trolltech.qt.gui.QWidget;

public class ProjectDataView extends QWidget {

	private Ui_ProjectData ui = new Ui_ProjectData();
	private Project project;
	
	public ProjectDataView(Project project) {
		this.project = project;
		ui.setupUi(this);
		ui.btnAdd.clicked.connect(this, "addClicked()");
		ComboBoxData.createProjectManagerComboBox(ui.cmbProjectManager);
		ui.checkBox.stateChanged.connect(this, "inactivChanged()");

		ProjectController.getInstance().update.connect(this, "updateData()");
		ProjectController.getInstance().add.connect(this, "addData(Project)");
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
	
	@SuppressWarnings("unused")
	private void addClicked() {
		try {
			project = ProjectController.getInstance().addProject(ui.txtProjectnumber.text(), ui.txtDescription.text(), (Usr)ui.cmbProjectManager.itemData(ui.cmbProjectManager.currentIndex()), ui.txtPlannedTime.value());
		} catch (WaktuException e) {
			// TODO NoAccess Exception muss gefangen und behandelt werden..
			e.printStackTrace();
		}
		setFields();
	}
	
	@SuppressWarnings("unused")
	private void inactivChanged() {
		if (project != null) {
			project.setActiveState(!ui.checkBox.isChecked());
			ProjectController.getInstance().updateProject(project);
		}
	}
	
	@SuppressWarnings("unused")
	private void updateData() {
		setFields();
	}
	
	@SuppressWarnings("unused")
	private void addData(Project project) {
		setFields();
	}
}
