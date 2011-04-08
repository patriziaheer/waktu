package ch.hsr.waktu.presentation.view;

import ch.hsr.waktu.controller.ProjectController;
import ch.hsr.waktu.controller.WorkPackageController;
import ch.hsr.waktu.controller.WorkSessionController;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.model.FavoriteModel;
import ch.hsr.waktu.model.WorkPackageComboBoxModel;
import ch.hsr.waktu.model.WorkSessionModel;
import ch.hsr.waktu.presentation.view.jui.Ui_TimeWindow;

import com.trolltech.qt.core.QDate;
import com.trolltech.qt.core.QDateTime;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.QTime;
import com.trolltech.qt.gui.QAbstractItemView.SelectionBehavior;
import com.trolltech.qt.gui.QAbstractItemView.SelectionMode;
import com.trolltech.qt.gui.QBrush;
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QComboBox;
import com.trolltech.qt.gui.QHBoxLayout;
import com.trolltech.qt.gui.QItemSelectionModel.SelectionFlag;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QPalette;
import com.trolltech.qt.gui.QPalette.ColorRole;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QWidget;

public class TimeView extends QMainWindow{
	
	private Ui_TimeWindow ui = new Ui_TimeWindow();
	private QDate currDate = QDate.currentDate();
	private Usr currUser;
	private WorkSessionModel workSessionModel;
	
	public TimeView(Usr usr) {
		currUser = usr;
		
		ui.setupUi(this);
		ui.btnDown.clicked.connect(this, "downClicked()");
		ui.calendar.setVisible(false);


		ui.actionOpenManagment.triggered.connect(this, "managmentClicked()");
		ui.actionClose.triggered.connect(this, "closeApp()");
		
		
		//calendar slots
		ui.calendar.selectionChanged.connect(this, "calendarSelectionChanged()");
		ui.btnMo.clicked.connect(this, "moClicked()");
		ui.btnDi.clicked.connect(this, "diClicked()");
		ui.btnMi.clicked.connect(this, "miClicked()");
		ui.btnDo.clicked.connect(this, "doClicked()");
		ui.btnFri.clicked.connect(this, "friClicked()");
		ui.btnSa.clicked.connect(this, "saClicked()");
		ui.btnSo.clicked.connect(this, "soClicked()");
		ui.btnLeft.clicked.connect(this, "leftClicked()");
		ui.btnRight.clicked.connect(this, "rightClicked()");
		
		//worksession slots
		ui.btnReset.clicked.connect(this, "resetClicked()");
		ui.btnCreate.clicked.connect(this, "createWorkSessionClicked()");
		
		ui.tblFavorites.setModel(new FavoriteModel(currUser));
		ui.tblFavorites.horizontalHeader().setStretchLastSection(true);
		ui.tblWorksessions.horizontalHeader().setStretchLastSection(true);
		ui.tblWorksessions.setSelectionMode(SelectionMode.SingleSelection);
		ui.tblWorksessions.setSelectionBehavior(SelectionBehavior.SelectRows);
		
		workSessionModel = new WorkSessionModel(currUser, currDate);
		ui.calendar.setSelectedDate(currDate);
		calendarSelectionChanged();
		
		for (int i = 0; i < WorkSessionController.getInstance().getWorkSessions(currUser, currDate).size(); i++) {
			QModelIndex currIndex = workSessionModel.index(i, workSessionModel.columnCount()-1);
			QWidget w = new QWidget();
			w.setLayout(new QHBoxLayout());
			EditButton editButton = new EditButton(tr("Edit"), currIndex);
			editButton.editClicked.connect(this, "editClicked(EditButton)");
			
			QPushButton deleteButton = new QPushButton(tr("Delete"));
			deleteButton.clicked.connect(this, "deleteClicked()");
			w.layout().addWidget(editButton);
			w.layout().addWidget(deleteButton);
			
			ui.tblWorksessions.setIndexWidget(currIndex, w);
		}
	}
	
	@SuppressWarnings("unused")
	private void managmentClicked() {
		ManagmentView managmentView = new ManagmentView(currUser);
		managmentView.show();
	}
	
	@SuppressWarnings("unused")
	private void closeApp() {
		System.exit(0);
	}
	
	@SuppressWarnings("unused")
	private void downClicked() {
		if (ui.calendar.isVisible()) {
			ui.btnDown.setText("v");
			ui.calendar.setVisible(false);
			setButtonVisibility(true);
		} else {
			ui.btnDown.setText("^");
			setButtonVisibility(false);
			ui.calendar.setVisible(true);
		}
	}

	private void setButtonVisibility(boolean visible) {
		ui.btnLeft.setVisible(visible);
		ui.btnMo.setVisible(visible);
		ui.btnDi.setVisible(visible);
		ui.btnMi.setVisible(visible);
		ui.btnDo.setVisible(visible);
		ui.btnFri.setVisible(visible);
		ui.btnSa.setVisible(visible);
		ui.btnSo.setVisible(visible);
		ui.btnRight.setVisible(visible);
		ui.lblStart.setVisible(visible);
		ui.lblEnd.setVisible(visible);
		ui.lblBis.setVisible(visible);
	}
	
	private void calendarSelectionChanged() {
		currDate = ui.calendar.selectedDate();
		workSessionModel = new WorkSessionModel(currUser, currDate);
		ui.tblWorksessions.setModel(workSessionModel);
		resetButtonEnabled();
		
		QDate startDate = new QDate();
		QDate endDate = new QDate();
		
		switch (currDate.dayOfWeek()) {
		case 1:  {
			ui.btnMo.setEnabled(false);
			startDate = currDate;
			endDate = currDate.addDays(6);
		}
		break;
		case 2: {
			ui.btnDi.setEnabled(false);
			startDate = currDate.addDays(-1);
			endDate = currDate.addDays(5);
		}
		break;
		case 3: {
			ui.btnMi.setEnabled(false);
			startDate = currDate.addDays(-2);
			endDate = currDate.addDays(4);
		}
		break;
		case 4: {
			ui.btnDo.setEnabled(false);
			startDate = currDate.addDays(-3);
			endDate = currDate.addDays(3);
		}
		break;
		case 5: {
			ui.btnFri.setEnabled(false);
			startDate = currDate.addDays(-4);
			endDate = currDate.addDays(2);
		}
		break;
		case 6: {
			ui.btnSa.setEnabled(false);
			startDate = currDate.addDays(-5);
			endDate = currDate.addDays(1);
		}
		break;
		case 0: {
			ui.btnSo.setEnabled(false);
			startDate = currDate.addDays(-6);
			endDate = currDate;
		}
		break;
		}
		ui.lblStart.setText(startDate.toString());
		ui.lblEnd.setText(endDate.toString());
	}
	
	private void  resetButtonEnabled() {
		ui.btnMo.setEnabled(true);
		ui.btnDi.setEnabled(true);
		ui.btnMi.setEnabled(true);
		ui.btnDo.setEnabled(true);
		ui.btnFri.setEnabled(true);
		ui.btnSa.setEnabled(true);
		ui.btnSo.setEnabled(true);
	}

	@SuppressWarnings("unused")
	private void moClicked() {
		resetButtonEnabled();
		ui.btnMo.setEnabled(false);
		
		if (currDate.dayOfWeek() == 0) {
			currDate = currDate.addDays(-6);
		} else {
			currDate = currDate.addDays((currDate.dayOfWeek()-1)*-1);
		}
		System.out.println(currDate.toString());
		updateCalendar();
	}

	@SuppressWarnings("unused")
	private void diClicked() {
		resetButtonEnabled();
		ui.btnDi.setEnabled(false);
		
		switch (currDate.dayOfWeek()) {
		case 0: {
			currDate = currDate.addDays(-5);
		}
		break;
		case 1: {
			currDate = currDate.addDays(1);
		}
		break;
		default: {
				currDate = currDate.addDays((currDate.dayOfWeek()-2)*-1);
		}
		}
		System.out.println(currDate.toString());
		updateCalendar();
	}
	
	@SuppressWarnings("unused")
	private void miClicked() {
		resetButtonEnabled();
		ui.btnMi.setEnabled(false);

		
		switch (currDate.dayOfWeek()) {
		case 0: {
			currDate = currDate.addDays(-4);
		}
		break;
		case 1:
		case 2:
			currDate = currDate.addDays(3-currDate.dayOfWeek());
		break;
		default: {
				currDate = currDate.addDays((currDate.dayOfWeek()-3)*-1);
		}
		}
		System.out.println(currDate.toString());
		updateCalendar();
	}

	@SuppressWarnings("unused")
	private void doClicked() {
		resetButtonEnabled();
		ui.btnDo.setEnabled(false);

		switch (currDate.dayOfWeek()) {
		case 0: {
			currDate = currDate.addDays(-3);
		}
		break;
		case 1:
		case 2:
		case 3:
			currDate = currDate.addDays(4-currDate.dayOfWeek());
			break;
		default: {
				currDate = currDate.addDays((currDate.dayOfWeek()-4)*-1);
		}
		}
		System.out.println(currDate.toString());
		updateCalendar();
	}

	@SuppressWarnings("unused")
	private void friClicked() {
		resetButtonEnabled();
		ui.btnFri.setEnabled(false);

		switch (currDate.dayOfWeek()) {
		case 0: {
			currDate = currDate.addDays(-2);
		}
		break;
		case 1:
		case 2:
		case 3:
		case 4:
			currDate = currDate.addDays(5-currDate.dayOfWeek());
			break;
		default: {
				currDate = currDate.addDays((currDate.dayOfWeek()-5)*-1);
		}
		}
		System.out.println(currDate.toString());
		updateCalendar();
	}

	@SuppressWarnings("unused")
	private void saClicked() {
		resetButtonEnabled();
		ui.btnSa.setEnabled(false);
		
		switch (currDate.dayOfWeek()) {
		case 0: {
			currDate = currDate.addDays(-1);
		}
		break;
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
			currDate = currDate.addDays(6-currDate.dayOfWeek());
			break;
		default: {
				currDate = currDate.addDays((currDate.dayOfWeek()-6)*-1);
		}
		}
		System.out.println(currDate.toString());
		updateCalendar();
	}

	@SuppressWarnings("unused")
	private void soClicked() {
		resetButtonEnabled();
		ui.btnSo.setEnabled(false);
		
		currDate = currDate.addDays(7-currDate.dayOfWeek());
		System.out.println(currDate.toString());
		updateCalendar();
	}
	
	@SuppressWarnings("unused")
	private void leftClicked() {
		currDate = currDate.addDays(-7);
		ui.calendar.setSelectedDate(currDate);
		calendarSelectionChanged();
	}
	
	@SuppressWarnings("unused")
	private void rightClicked() {
		currDate = currDate.addDays(7);
		ui.calendar.setSelectedDate(currDate);
		calendarSelectionChanged();
	}
	
	private void updateCalendar() {
		ui.calendar.setSelectedDate(currDate);
		workSessionModel = new WorkSessionModel(currUser, currDate);
		ui.tblWorksessions.setModel(workSessionModel);
	}

	@SuppressWarnings("unused")
	private void resetClicked() {
		ui.txtStart.setTime(new QTime(0,0,0));
		ui.txtEnd.setTime(new QTime(0,0,0));
		ui.cmbProject.setCurrentIndex(-1);
		ui.cmbWorkpackage = new QComboBox();
	}
	
	@SuppressWarnings("unused")
	private void createWorkSessionClicked() {
		if (ui.txtEnd.time().compareTo(ui.txtStart.time()) < 0) {
			setStatusBarText(tr("Endtime must be greater then Starttime"));
		} else {
			Project project = ProjectController.getInstance().getActiveProjects().get(ui.cmbProject.currentIndex());
			WorkPackage workPackage = WorkPackageController.getInstance().getActiveWorkPackages(project).get(ui.cmbWorkpackage.currentIndex());
			QDateTime start = new QDateTime(currDate);
			start.setTime(ui.txtStart.time());
			QDateTime end = new QDateTime(currDate);
			end.setTime(ui.txtEnd.time());
			WorkSessionController.getInstance().addWorkSession(currUser, workPackage, start, end);
		}
	}
	
	@SuppressWarnings("unused")
	private void projectChanged() {
		ui.cmbWorkpackage.setModel(new WorkPackageComboBoxModel(ProjectController.getInstance().getActiveProjects().get(ui.cmbProject.currentIndex())));
	}
	
	@SuppressWarnings("unused")
	private void editClicked(EditButton btn) {
		if (workSessionModel.getEditable() != null && btn.text().equals("Edit")) {
			setStatusBarText(tr("Save first current edit row"));
		} else {
			if (btn.text().equals(tr("Edit"))) {
				for (int i = 0; i < workSessionModel.columnCount(); i++) {
					ui.tblWorksessions.edit(workSessionModel.index(btn.getIndex().row(), i));
				}
				workSessionModel.setEditable(btn.getIndex());
				btn.setText(tr("Save"));
				ui.tblWorksessions.selectionModel().select(btn.getIndex(), SelectionFlag.Rows);
			} else {
				workSessionModel.setEditable(null);
				btn.setText(tr("Edit"));
			}
			workSessionModel.layoutAboutToBeChanged.emit();
			workSessionModel.dataChanged.emit(workSessionModel.index(0, 0), workSessionModel.index(workSessionModel.rowCount(), workSessionModel.columnCount()));
			workSessionModel.layoutChanged.emit();
		}
	}
	
	@SuppressWarnings("unused")
	private void deleteClicked() {
		
	}
	
	private void setStatusBarText(String text) {
		ui.statusBar.showMessage(text, 2000);
		QPalette palette = ui.statusBar.palette();
		palette.setBrush(ColorRole.WindowText, new QBrush(QColor.red));
		ui.statusBar.setPalette(palette);
	}
}
