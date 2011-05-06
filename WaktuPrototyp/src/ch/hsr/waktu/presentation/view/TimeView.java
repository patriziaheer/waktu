package ch.hsr.waktu.presentation.view;

import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.datacontroller.FavoriteController;
import ch.hsr.waktu.controller.datacontroller.ProjectController;
import ch.hsr.waktu.controller.datacontroller.WorkPackageController;
import ch.hsr.waktu.controller.datacontroller.WorkSessionController;
import ch.hsr.waktu.domain.Favorite;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.domain.WorkSession;
import ch.hsr.waktu.model.ComboBoxData;
import ch.hsr.waktu.model.FavoriteModel;
import ch.hsr.waktu.model.WorkSessionModel;
import ch.hsr.waktu.presentation.view.IndexButton.EditStatus;
import ch.hsr.waktu.services.TimeUtil;

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
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QItemSelectionModel.SelectionFlag;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QPalette;
import com.trolltech.qt.gui.QPalette.ColorRole;
import com.trolltech.qt.gui.QWidget;

public class TimeView extends QMainWindow {

	private Logger logger = Logger.getLogger(TimeView.class);

	private Ui_TimeWindow ui = new Ui_TimeWindow();
	private QDate currDate = QDate.currentDate();
	private Usr currUser;
	private WorkSessionModel workSessionModel;
	private FavoriteModel favoriteModel;
	private ManagmentView managmentView;

	public TimeView(Usr usr) {
		currUser = usr;
		managmentView = new ManagmentView(currUser);

		ui.setupUi(this);
		ComboBoxData.createProjectForUserComboBox(ui.cmbProject, currUser);
		ComboBoxData.createWorkPackageComboBox(ui.cmbWorkpackage,
				(Project) ui.cmbProject.itemData(ui.cmbProject.currentIndex()));
		ui.cmbProject.currentIndexChanged.connect(this, "projectChanged()");

		ui.actionOpenManagment.triggered.connect(this, "managmentClicked()");
		ui.actionClose.triggered.connect(this, "closeApp()");

		// calendar slots
		ui.btnMo.clicked.connect(this, "moClicked()");
		ui.btnDi.clicked.connect(this, "diClicked()");
		ui.btnMi.clicked.connect(this, "miClicked()");
		ui.btnDo.clicked.connect(this, "doClicked()");
		ui.btnFri.clicked.connect(this, "friClicked()");
		ui.btnSa.clicked.connect(this, "saClicked()");
		ui.btnSo.clicked.connect(this, "soClicked()");
		ui.btnLeft.clicked.connect(this, "leftClicked()");
		ui.btnRight.clicked.connect(this, "rightClicked()");

		// worksession slots
		ui.btnReset.clicked.connect(this, "resetClicked()");
		ui.btnCreate.clicked.connect(this, "createWorkSessionClicked()");
		ui.btnTimeOnly.clicked.connect(this, "timeOnlyClicked()");
		ui.btnCreateFavorite.clicked.connect(this, "createFavoriteClicked()");
		ui.btnWorkPackageOnly.clicked.connect(this, "workPackageOnlyClicked()");

		ui.tblFavorites.setModel(new FavoriteModel(currUser));
		ui.tblFavorites.horizontalHeader().setStretchLastSection(true);
		ui.tblFavorites.resizeColumnsToContents();
		ui.tblFavorites.setSelectionMode(SelectionMode.SingleSelection);
		ui.tblFavorites.setSelectionBehavior(SelectionBehavior.SelectRows);
		
		ui.tblWorksessions.horizontalHeader().setStretchLastSection(true);
		ui.tblWorksessions.resizeColumnsToContents();
		ui.tblWorksessions.setSelectionMode(SelectionMode.SingleSelection);
		ui.tblWorksessions.setSelectionBehavior(SelectionBehavior.SelectRows);

		ui.actionAdd_to_Favorites.triggered.connect(this, "addToFavorites()");

		FavoriteController.getInstance().add.connect(this,
				"favoriteAdded(Favorite)");
		FavoriteController.getInstance().update.connect(this,
				"favoriteUpdated()");
		FavoriteController.getInstance().removed.connect(this,
				"favoriteRemoved(Favorite)");

		WorkSessionController.getInstance().add.connect(this,
				"workSessionAdded(WorkSession)");
		WorkSessionController.getInstance().update.connect(this,
				"workSessionUpdated()");
		WorkSessionController.getInstance().removed.connect(this,
				"workSessionRemoved(WorkSession)");

		workSessionModel = new WorkSessionModel(currUser, currDate);
		favoriteModel = new FavoriteModel(currUser);
		ui.tblFavorites.setModel(favoriteModel);
		updateCalendar();
		updateWorkSessionModel();
		updateFavoriteModel();
	}

	@SuppressWarnings("unused")
	private void projectChanged() {
		ComboBoxData.createWorkPackageComboBox(ui.cmbWorkpackage,
				(Project) ui.cmbProject.itemData(ui.cmbProject.currentIndex()));
	}

	@SuppressWarnings("unused")
	private void managmentClicked() {
		managmentView.show();
		managmentView.setFocus();
	}

	@SuppressWarnings("unused")
	private void closeApp() {
		System.exit(0);
	}

	private void resetButtonEnabled() {
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

		if (currDate.dayOfWeek() == 7) {
			currDate = currDate.addDays(-6);
		} else {
			currDate = currDate.addDays((currDate.dayOfWeek() - 1) * -1);
		}
		System.out.println(currDate.toString());
		updateCalendar();
	}

	@SuppressWarnings("unused")
	private void diClicked() {
		resetButtonEnabled();
		ui.btnDi.setEnabled(false);

		switch (currDate.dayOfWeek()) {
		case 7: {
			currDate = currDate.addDays(-5);
		}
			break;
		case 1: {
			currDate = currDate.addDays(1);
		}
			break;
		default: {
			currDate = currDate.addDays((currDate.dayOfWeek() - 2) * -1);
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
		case 7: {
			currDate = currDate.addDays(-4);
		}
			break;
		case 1:
		case 2:
			currDate = currDate.addDays(3 - currDate.dayOfWeek());
			break;
		default: {
			currDate = currDate.addDays((currDate.dayOfWeek() - 3) * -1);
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
		case 7: {
			currDate = currDate.addDays(-3);
		}
			break;
		case 1:
		case 2:
		case 3:
			currDate = currDate.addDays(4 - currDate.dayOfWeek());
			break;
		default: {
			currDate = currDate.addDays((currDate.dayOfWeek() - 4) * -1);
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
		case 7: {
			currDate = currDate.addDays(-2);
		}
			break;
		case 1:
		case 2:
		case 3:
		case 4:
			currDate = currDate.addDays(5 - currDate.dayOfWeek());
			break;
		default: {
			currDate = currDate.addDays((currDate.dayOfWeek() - 5) * -1);
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
		case 7: {
			currDate = currDate.addDays(-1);
		}
			break;
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
			currDate = currDate.addDays(6 - currDate.dayOfWeek());
			break;
		default: {
			currDate = currDate.addDays((currDate.dayOfWeek() - 6) * -1);
		}
		}
		System.out.println(currDate.toString());
		updateCalendar();
	}

	@SuppressWarnings("unused")
	private void soClicked() {
		resetButtonEnabled();
		ui.btnSo.setEnabled(false);

		currDate = currDate.addDays(7 - currDate.dayOfWeek());
		System.out.println(currDate.toString());
		updateCalendar();
	}

	@SuppressWarnings("unused")
	private void leftClicked() {
		currDate = currDate.addDays(-7);
		updateCalendar();
	}

	@SuppressWarnings("unused")
	private void rightClicked() {
		currDate = currDate.addDays(7);
		updateCalendar();
	}

	private void updateCalendar() {
		ui.lblCurrDate.setText(currDate.toString("dd.MM.yy"));
		QDate startDate = new QDate();
		QDate endDate = new QDate();

		switch (currDate.dayOfWeek()) {
		case 1: {
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
		case 7: {
			ui.btnSo.setEnabled(false);
			startDate = currDate.addDays(-6);
			endDate = currDate;
		}
			break;
		}
		ui.lblStart.setText(startDate.toString("dd.MM.yy"));
		ui.lblEnd.setText(endDate.toString("dd.MM.yy"));
		updateWorkSessionModel();
	}

	private void updateWorkSessionModel() {
		workSessionModel = new WorkSessionModel(currUser, currDate);
		ui.tblWorksessions.setModel(workSessionModel);
		for (int i = 0; i < WorkSessionController.getInstance()
				.getWorkSessions(currUser, currDate).size(); i++) {
			QModelIndex currIndex = workSessionModel.index(i,
					workSessionModel.columnCount() - 1);
			QWidget w = new QWidget();
			w.setLayout(new QHBoxLayout());
			IndexButton editButton = new IndexButton(currIndex);
			editButton.setFixedHeight(20);
			editButton.setIcon(new QIcon("classpath:icons/edit_16x16.png"));
			editButton.actionClicked.connect(this,
					"workSessionEditClicked(IndexButton)");

			IndexButton deleteButton = new IndexButton(currIndex);
			deleteButton.setFixedHeight(20);
			deleteButton.setIcon(new QIcon("classpath:icons/delete_16x16.png"));
			deleteButton.actionClicked.connect(this,
					"workSessionDeleteClicked(IndexButton)");
			w.layout().addWidget(editButton);
			w.layout().addWidget(deleteButton);

			ui.tblWorksessions.setIndexWidget(currIndex, w);
		}
	}

	private void updateFavoriteModel() {
		for (int i = 0; i < FavoriteController.getInstance()
				.getFavorites(currUser).size(); i++) {
			QModelIndex currIndex = favoriteModel.index(i,
					favoriteModel.columnCount() - 1);
			QWidget w = new QWidget();
			w.setLayout(new QHBoxLayout());
			IndexButton editButton = new IndexButton(currIndex);
			editButton.setFixedHeight(20);
			editButton.setIcon(new QIcon("classpath:icons/edit_16x16.png"));
			editButton.actionClicked.connect(this,
					"favoriteEditClicked(IndexButton)");

			IndexButton deleteButton = new IndexButton(currIndex);
			deleteButton.setFixedHeight(20);
			deleteButton.setIcon(new QIcon("classpath:icons/delete_16x16.png"));
			deleteButton.actionClicked.connect(this,
					"favoriteDeleteClicked(IndexButton)");
			w.layout().addWidget(editButton);
			w.layout().addWidget(deleteButton);

			ui.tblFavorites.setIndexWidget(currIndex, w);
		}
	}

	@SuppressWarnings("unused")
	private void resetClicked() {
		ui.txtStart.setTime(new QTime(0, 0, 0));
		ui.txtEnd.setTime(new QTime(0, 0, 0));
		ui.txtDescription.setText("");
		ui.cmbProject.setCurrentIndex(-1);
		ui.cmbWorkpackage = new QComboBox();
	}

	@SuppressWarnings("unused")
	private void createWorkSessionClicked() {
		if (ui.txtEnd.time().compareTo(ui.txtStart.time()) <= 0) {
			setStatusBarText(tr("Endtime must be greater then Starttime"));
		} else if (ui.cmbProject.currentIndex() < 0) {
			setStatusBarText(tr("Project must be choosen"));
		} else if (ui.cmbWorkpackage.currentIndex() < 0) {
			setStatusBarText(tr("WorkPackage must be choosen"));
		} else {
			Project project = ProjectController.getInstance()
					.getActiveProjects().get(ui.cmbProject.currentIndex());
			WorkPackage workPackage = WorkPackageController.getInstance()
					.getActiveWorkPackages(project)
					.get(ui.cmbWorkpackage.currentIndex());
			QDateTime start = new QDateTime(currDate);
			start.setTime(ui.txtStart.time());
			QDateTime end = new QDateTime(currDate);
			end.setTime(ui.txtEnd.time());
			WorkSessionController.getInstance().addWorkSession(currUser,
					workPackage, TimeUtil.convertQDateTimeToGregorian(start),
					TimeUtil.convertQDateTimeToGregorian(end),
					ui.txtDescription.text());
		}
	}

	@SuppressWarnings("unused")
	private void timeOnlyClicked() {
		if (ui.tblFavorites.selectionModel().selectedRows().size() >= 0) {
			Favorite favorite = favoriteModel.getFavorite(ui.tblFavorites
					.selectionModel().selectedRows().get(0).row());
			ui.txtStart.setTime(TimeUtil.convertGregorianToQDateTime(
					favorite.getStartTime()).time());
			ui.txtEnd.setTime(TimeUtil.convertGregorianToQDateTime(
					favorite.getEndTime()).time());
		} else {
			setStatusBarText("Select a Favorite");
		}
	}

	@SuppressWarnings("unused")
	private void workPackageOnlyClicked() {
		if (ui.tblFavorites.selectionModel().selectedRows().size() >= 0) {
			Favorite favorite = favoriteModel.getFavorite(ui.tblFavorites
					.selectionModel().selectedRows().get(0).row());
			WorkPackage wp = favorite.getWorkPackageID();
			Project project = wp.getProject();
			ui.cmbProject.setCurrentIndex(ui.cmbProject.findData(project));
			ui.cmbWorkpackage.setCurrentIndex(ui.cmbWorkpackage.findData(wp));
		} else {
			setStatusBarText("Select a Favorite");
		}
	}

	@SuppressWarnings("unused")
	private void createFavoriteClicked() {
		if (ui.tblFavorites.selectionModel().selectedRows().size() >= 0) {
			Favorite favorite = favoriteModel.getFavorite(ui.tblFavorites
					.selectionModel().selectedRows().get(0).row());
			WorkPackage wp = favorite.getWorkPackageID();
			GregorianCalendar start = favorite.getStartTime();
			GregorianCalendar end = favorite.getEndTime();
			WorkSessionController.getInstance().addWorkSession(currUser, wp,
					start, end, "");
		} else {
			setStatusBarText("Select a Favorite");
		}
	}

	@SuppressWarnings("unused")
	private void addToFavorites() {
		if (ui.txtEnd.time().compareTo(ui.txtStart.time()) <= 0) {
			setStatusBarText(tr("Endtime must be greater then Starttime"));
		} else if (ui.cmbProject.currentIndex() < 0) {
			setStatusBarText(tr("Project must be choosen"));
		} else if (ui.cmbWorkpackage.currentIndex() < 0) {
			setStatusBarText(tr("WorkPackage must be choosen"));
		} else {
			Project project = ProjectController.getInstance()
					.getActiveProjects().get(ui.cmbProject.currentIndex());
			WorkPackage workPackage = WorkPackageController.getInstance()
					.getActiveWorkPackages(project)
					.get(ui.cmbWorkpackage.currentIndex());
			QDateTime start = new QDateTime(currDate);
			start.setTime(ui.txtStart.time());
			QDateTime end = new QDateTime(currDate);
			end.setTime(ui.txtEnd.time());
			FavoriteController.getInstance().addFavorite(currUser, workPackage,
					TimeUtil.convertQDateTimeToGregorian(start),
					TimeUtil.convertQDateTimeToGregorian(end));
		}
	}

	@SuppressWarnings("unused")
	private void favoriteAdded(Favorite favorite) {
		updateFavoriteTable();
	}

	@SuppressWarnings("unused")
	private void favoriteUpdated() {
		updateFavoriteTable();
	}

	@SuppressWarnings("unused")
	private void favoriteRemoved(Favorite favorite) {
		updateFavoriteTable();
	}

	private void updateFavoriteTable() {
		updateFavoriteModel();
		favoriteModel.updateFavoriteModel();
		favoriteModel.layoutAboutToBeChanged.emit();
		favoriteModel.dataChanged.emit(
				favoriteModel.index(0, 0),
				favoriteModel.index(favoriteModel.rowCount(),
						favoriteModel.columnCount()));
		favoriteModel.layoutChanged.emit();
	}

	@SuppressWarnings("unused")
	private void workSessionAdded(WorkSession workSession) {
		updateWorkSessionTable();
	}

	@SuppressWarnings("unused")
	private void workSessionUpdated() {
		workSessionModel.updateModel();
		workSessionModel.layoutAboutToBeChanged.emit();
		workSessionModel.dataChanged.emit(workSessionModel.index(0, 0),
				workSessionModel.index(workSessionModel.rowCount(),
						workSessionModel.columnCount()));
		workSessionModel.layoutChanged.emit();
	}

	@SuppressWarnings("unused")
	private void workSessionRemoved(WorkSession workSession) {
		updateWorkSessionTable();
	}

	private void updateWorkSessionTable() {
		updateWorkSessionModel();
		workSessionModel.updateModel();
		workSessionModel.layoutAboutToBeChanged.emit();
		workSessionModel.dataChanged.emit(workSessionModel.index(0, 0),
				workSessionModel.index(workSessionModel.rowCount(),
						workSessionModel.columnCount()));
		workSessionModel.layoutChanged.emit();
	}

	@SuppressWarnings("unused")
	private void workSessionEditClicked(IndexButton btn) {
		logger.info("EditClicked for " + btn);
		if (workSessionModel.getEditable() != null
				&& EditStatus.Edit == btn.getStatus()) {
			setStatusBarText(tr("Save first current edit row"));
		} else {
			if (EditStatus.Edit == btn.getStatus()) {
				btn.setStatus(EditStatus.Save);
				/*
				 * for (int i = 0; i < workSessionModel.columnCount(); i++) {
				 * ui.tblWorksessions.edit(workSessionModel.index(btn
				 * .getIndex().row(), i)); }
				 */
				workSessionModel.setEditable(btn.getIndex());
				btn.setIcon(new QIcon("classpath:icons/save_16x16.png"));
				ui.tblWorksessions.selectionModel().select(btn.getIndex(),
						SelectionFlag.Rows);
			} else {
				workSessionModel.setEditable(null);
				btn.setIcon(new QIcon("classpath:icons/edit_16x16.png"));
				btn.setStatus(EditStatus.Edit);
				WorkSessionController.getInstance().updateWorkSession(
						workSessionModel.getWorkSession(btn.getIndex().row()));
			}
			/*
			 * workSessionModel.layoutAboutToBeChanged.emit();
			 * workSessionModel.dataChanged.emit(workSessionModel.index(0, 0),
			 * workSessionModel.index(workSessionModel.rowCount(),
			 * workSessionModel.columnCount()));
			 * workSessionModel.layoutChanged.emit();
			 */
		}
	}

	@SuppressWarnings("unused")
	private void workSessionDeleteClicked(IndexButton btn) {
		logger.info("EditClicked for " + btn);
		WorkSessionController.getInstance().removeWorkSession(
				WorkSessionController.getInstance()
						.getWorkSessions(currUser, currDate)
						.get(btn.getIndex().row()));

	}

	@SuppressWarnings("unused")
	private void favoriteEditClicked(IndexButton btn) {
		logger.info("EditClicked for favorite " + btn);
		if (favoriteModel.getEditable() != null
				&& EditStatus.Edit == btn.getStatus()) {
			setStatusBarText(tr("Save first current edit row"));
		} else {
			if (EditStatus.Edit == btn.getStatus()) {
				btn.setStatus(EditStatus.Save);
				/*
				 * for (int i = 0; i < favoriteModel.columnCount(); i++) {
				 * ui.tblFavorites.edit(favoriteModel.index(btn.getIndex()
				 * .row(), i)); }
				 */
				favoriteModel.setEditable(btn.getIndex());
				btn.setIcon(new QIcon("classpath:icons/save_16x16.png"));
				ui.tblFavorites.selectionModel().select(btn.getIndex(),
						SelectionFlag.Rows);
			} else {
				favoriteModel.setEditable(null);
				btn.setIcon(new QIcon("classpath:icons/edit_16x16.png"));
				btn.setStatus(EditStatus.Edit);
			}
			favoriteModel.layoutAboutToBeChanged.emit();
			favoriteModel.layoutChanged.emit();
		}
	}

	@SuppressWarnings("unused")
	private void favoriteDeleteClicked(IndexButton btn) {
		logger.info("EditClicked for favorite " + btn);
		FavoriteController.getInstance().removeFavorite(
				FavoriteController.getInstance().getFavorites(currUser)
						.get(btn.getIndex().row()));
	}

	private void setStatusBarText(String text) {
		ui.statusBar.showMessage(text, 2000);
		QPalette palette = ui.statusBar.palette();
		palette.setBrush(ColorRole.WindowText, new QBrush(QColor.red));
		ui.statusBar.setPalette(palette);
	}
}
