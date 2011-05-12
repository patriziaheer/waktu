package ch.hsr.waktu.gui.qt.view.projectmanagment;

import ch.hsr.waktu.controller.datacontroller.WorkPackageController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.gui.qt.model.ProjectWorkPackageModel;
import ch.hsr.waktu.guicontroller.GuiController;
import ch.hsr.waktu.guicontroller.LanguageController;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.gui.QWidget;

public class ProjectWorkPackageView extends QWidget{
	
	private Ui_ProjectWorkPackage ui = new Ui_ProjectWorkPackage();
	private Project project;
	private ProjectWorkPackageModel workPackageModel;
	public Signal1<String> errorMessage = new Signal1<String>();
	
	public ProjectWorkPackageView(Project project) {
		this.project = project;
		ui.setupUi(this);
		workPackageModel = new ProjectWorkPackageModel(project);
		workPackageModel.errorMessage.connect(this, "showErrorMessage(String)");
		
		ui.tblWorkPackages.setModel(workPackageModel);
		ui.tblWorkPackages.horizontalHeader().setStretchLastSection(true);
		
		ui.btnAdd.clicked.connect(this, "addClicked()");
		WorkPackageController.getInstance().add.connect(this, "addData(WorkPackage)");
		
		WorkPackageController.getInstance().update.connect(this, "updated()");
		
		if (GuiController.getInstance().canAddWorkPackage(project) == false) {
			ui.btnAdd.setVisible(false);
			ui.txtDescription.setVisible(false);
		}

		LanguageController.getInstance().languageChanged.connect(this, "translate()");
	}
	
	@SuppressWarnings("unused")
	private void addClicked() {
		if (ui.txtDescription.text().isEmpty() == false) {
			try {
				WorkPackageController.getInstance().addWorkPackage(project, ui.txtDescription.text());
			} catch (WaktuException e) {
				showErrorMessage(e.getMessage());
			}
			ui.txtDescription.setText("");
		}
	}
	
	@SuppressWarnings("unused")
	private void addData(WorkPackage workPackage) {
		workPackageModel.updateWorkPackageModel();
		workPackageModel.layoutAboutToBeChanged.emit();
		workPackageModel.dataChanged.emit(workPackageModel.index(0, 0), workPackageModel.index(workPackageModel.rowCount(), workPackageModel.columnCount()));
		workPackageModel.layoutChanged.emit();
	}
	
	@SuppressWarnings("unused")
	private void updated() {
		workPackageModel.updateWorkPackageModel();
		workPackageModel.layoutAboutToBeChanged.emit();
		workPackageModel.dataChanged.emit(workPackageModel.index(0, 0), workPackageModel.index(workPackageModel.rowCount(), workPackageModel.columnCount()));
		workPackageModel.layoutChanged.emit();
	}
	
	@SuppressWarnings("unused")
	private void translate() {
        ui.retranslateUi(this);
	}

	private void showErrorMessage(String errorMessageString) {
		errorMessage.emit(errorMessageString);
	}
}
