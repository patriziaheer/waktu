package ch.hsr.waktu.gui.qt.view;

import ch.hsr.waktu.controller.LoginController;
import ch.hsr.waktu.controller.datacontroller.WorkSessionController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.domain.WorkSession;
import ch.hsr.waktu.gui.qt.model.ComboBoxData;
import ch.hsr.waktu.services.TimeUtil;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.gui.QWidget;

public class IcsImportDetailView extends QWidget {

	private Ui_IcsImport ui = new Ui_IcsImport();
	private WorkSession workSession;
	public Signal1<String> errorMessage = new Signal1<String>();
	public Signal1<IcsImportDetailView> closeMe = new Signal1<IcsImportDetailView>();

	public IcsImportDetailView(WorkSession workSession) {
		this.workSession = workSession;
	}

	public void initialize() {
		ui.setupUi(this);
		try {
			ComboBoxData.createActiveProjectComboBox(ui.cmbProject);
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
		ui.cmbProject.setCurrentIndex(-1);
		ui.dteStart.setDateTime(TimeUtil
				.convertGregorianToQDateTime(workSession.getStart()));
		ui.dteEnd.setDateTime(TimeUtil.convertGregorianToQDateTime(workSession
				.getEnd()));
		ui.txtDescription.setText(workSession.getDescription());
		ui.cmbProject.currentIndexChanged.connect(this, "projectChanged()");
		ui.btnDiscard.clicked.connect(this, "discardClicked()");
		ui.btnImport.clicked.connect(this, "importClicked()");
	}

	@SuppressWarnings("unused")
	private void projectChanged() {
		try {
			ComboBoxData.createActiveWorkPackageComboBox(ui.cmbWorkPackage,
					(Project) ui.cmbProject.itemData(ui.cmbProject
							.currentIndex()), null);
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
	}

	@SuppressWarnings("unused")
	private void discardClicked() {
		closeMe.emit(this);
	}

	@SuppressWarnings("unused")
	private void importClicked() {
		try {
			if (ui.cmbProject.currentIndex() < 0) {
				errorMessage.emit("Project must be choosen");
			} else if (ui.cmbWorkPackage.currentIndex() < 0) {
				errorMessage.emit("WorkPackage must be choosen");
			} else if (ui.dteEnd.dateTime().compareTo(ui.dteStart.dateTime()) <= 0) {
				errorMessage.emit("Endtime must be greater then Starttime");
			} else {
				WorkSessionController
						.getInstance()
						.addWorkSession(
								LoginController.getInstance().getLoggedInUser(),
								(WorkPackage) ui.cmbWorkPackage
										.itemData(ui.cmbWorkPackage.currentIndex()),
								TimeUtil.convertQDateTimeToGregorian(ui.dteStart
										.dateTime()),
								TimeUtil.convertQDateTimeToGregorian(ui.dteEnd
										.dateTime()), ui.txtDescription.text());
				closeMe.emit(this);
			}
		} catch (WaktuException e) {
			errorMessage.emit(e.getMessage());
		}
	}
}
