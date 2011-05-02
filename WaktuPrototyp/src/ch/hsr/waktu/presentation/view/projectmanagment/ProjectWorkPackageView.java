package ch.hsr.waktu.presentation.view.projectmanagment;

import ch.hsr.waktu.controller.datacontroller.WorkPackageController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.model.ProjectWorkPackageModel;
import ch.hsr.waktu.presentation.view.projectmanagment.jui.Ui_ProjectWorkPackage;

import com.trolltech.qt.gui.QWidget;

public class ProjectWorkPackageView extends QWidget{
	
	private Ui_ProjectWorkPackage ui = new Ui_ProjectWorkPackage();
	private Project project;
	private ProjectWorkPackageModel workPackageModel;
	
	public ProjectWorkPackageView(Project project) {
		this.project = project;
		ui.setupUi(this);
		workPackageModel = new ProjectWorkPackageModel(project);
		ui.tblWorkPackages.setModel(workPackageModel);
		ui.tblWorkPackages.horizontalHeader().setStretchLastSection(true);
		
		ui.btnAdd.clicked.connect(this, "addClicked()");
		WorkPackageController.getInstance().add.connect(this, "addData(WorkPackage)");
	}
	
	@SuppressWarnings("unused")
	private void addClicked() {
		if (ui.txtDescription.text().isEmpty() == false) {
			WorkPackageController.getInstance().addWorkPackage(project, ui.txtDescription.text());
		}
	}
	
	@SuppressWarnings("unused")
	private void addData(WorkPackage workPackage) {
		workPackageModel.layoutAboutToBeChanged.emit();
		workPackageModel.dataChanged.emit(workPackageModel.index(0, 0), workPackageModel.index(workPackageModel.rowCount(), workPackageModel.columnCount()));
		workPackageModel.layoutChanged.emit();
	}

}
