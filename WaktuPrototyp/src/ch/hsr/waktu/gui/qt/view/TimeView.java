package ch.hsr.waktu.gui.qt.view;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.TimeController;
import ch.hsr.waktu.controller.datacontroller.FavoriteController;
import ch.hsr.waktu.controller.datacontroller.ProjectController;
import ch.hsr.waktu.controller.datacontroller.ProjectStaffController;
import ch.hsr.waktu.controller.datacontroller.WorkPackageController;
import ch.hsr.waktu.controller.datacontroller.WorkSessionController;
import ch.hsr.waktu.domain.Favorite;
import ch.hsr.waktu.domain.Project;
import ch.hsr.waktu.domain.ProjectStaff;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.domain.WorkPackage;
import ch.hsr.waktu.domain.WorkSession;
import ch.hsr.waktu.gui.qt.model.ComboBoxData;
import ch.hsr.waktu.gui.qt.model.FavoriteModel;
import ch.hsr.waktu.gui.qt.model.WorkSessionModel;
import ch.hsr.waktu.gui.qt.view.IndexButton.EditStatus;
import ch.hsr.waktu.guicontroller.LanguageController;
import ch.hsr.waktu.guicontroller.LanguageController.Language;
import ch.hsr.waktu.services.TimeUtil;
import ch.hsr.waktu.services.WaktuException;

import com.trolltech.qt.core.QCoreApplication;
import com.trolltech.qt.core.QDate;
import com.trolltech.qt.core.QDateTime;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.QTime;
import com.trolltech.qt.core.Qt.Orientation;
import com.trolltech.qt.gui.QAbstractItemView.SelectionBehavior;
import com.trolltech.qt.gui.QAbstractItemView.SelectionMode;
import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QBrush;
import com.trolltech.qt.gui.QCloseEvent;
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QComboBox;
import com.trolltech.qt.gui.QContextMenuEvent;
import com.trolltech.qt.gui.QFileDialog;
import com.trolltech.qt.gui.QHBoxLayout;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QItemSelectionModel.SelectionFlag;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QMenu;
import com.trolltech.qt.gui.QPalette;
import com.trolltech.qt.gui.QPalette.ColorRole;
import com.trolltech.qt.gui.QSplitter;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;

public class TimeView extends QMainWindow {

    private Logger logger = Logger.getLogger(TimeView.class);

    private Ui_TimeWindow ui = new Ui_TimeWindow();
    private Usr currUser;
    private WorkSessionModel workSessionModel;
    private FavoriteModel favoriteModel;
    private ManagmentView managmentView;
    private CalendarWidget calendar;

    public TimeView(final Usr usr) {
        currUser = usr;
        managmentView = new ManagmentView(currUser);
        calendar = new CalendarWidget();
        calendar.dayChanged.connect(this, "dayChanged()");

        ui.setupUi(this);
        QSplitter splitter = new QSplitter(Orientation.Vertical);
        QWidget widget = new QWidget();
        QVBoxLayout layout = new QVBoxLayout();
        widget.setLayout(layout);
        layout.addWidget(calendar);
        layout.addWidget(ui.widget);
        layout.addWidget(ui.grpWorksessions);
        layout.setMargin(0);
        splitter.addWidget(widget);
        splitter.addWidget(ui.grpOverview);
        ui.gridLayout.addWidget(splitter);
        splitter.setCollapsible(0, false);

        try {
            ComboBoxData.createProjectForUserComboBox(ui.cmbProject, currUser,
                    null);
        } catch (WaktuException e) {
            setStatusBarText(e.getMessage());
        }
        try {
            workSessionModel = new WorkSessionModel(currUser,
                    calendar.getCurrentDate());
        } catch (WaktuException e) {
            setStatusBarText(e.getMessage());
        }
        try {
            favoriteModel = new FavoriteModel(currUser);
        } catch (WaktuException e) {
            setStatusBarText(e.getMessage());
        }
        ui.cmbProject.setCurrentIndex(-1);
        ui.cmbProject.currentIndexChanged.connect(this, "projectChanged()");

        ui.btnReset.clicked.connect(this, "resetClicked()");
        ui.btnCreate.clicked.connect(this, "createWorkSessionClicked()");
        ui.btnTimeOnly.clicked.connect(this, "timeOnlyClicked()");
        ui.btnCreateFavorite.clicked.connect(this, "createFavoriteClicked()");
        ui.btnWorkPackageOnly.clicked.connect(this, "workPackageOnlyClicked()");

        ui.tblFavorites.horizontalHeader().setStretchLastSection(true);
        ui.tblFavorites.setSelectionMode(SelectionMode.SingleSelection);
        ui.tblFavorites.setSelectionBehavior(SelectionBehavior.SelectRows);

        ui.tblWorksessions.horizontalHeader().setStretchLastSection(true);
        ui.tblWorksessions.setSelectionMode(SelectionMode.SingleSelection);
        ui.tblWorksessions.setSelectionBehavior(SelectionBehavior.SelectRows);
        ui.tblWorksessions.horizontalHeader().resizeSection(0, 200);

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

        ProjectStaffController.getInstance().add.connect(this,
                "projectStaffChanged(ProjectStaff)");
        ProjectStaffController.getInstance().removed.connect(this,
                "projectStaffChanged(ProjectStaff)");
        ProjectController.getInstance().add.connect(this,
                "projectsAdded(Project)");
        ProjectController.getInstance().update.connect(this,
                "projectsUpdated()");
        WorkPackageController.getInstance().add.connect(this,
                "workPackageAdded(WorkPackage)");
        WorkPackageController.getInstance().update.connect(this,
                "workPackageUpdated()");

        ui.tblWorksessions.setModel(workSessionModel);
        ui.tblFavorites.setModel(favoriteModel);
        ui.tblFavorites.resizeColumnsToContents();
        ui.tblWorksessions.resizeColumnsToContents();

        ui.actionAdd_to_Favorites.triggered.connect(this, "addToFavorites()");
        ui.actionOpenManagment.triggered.connect(this, "managmentClicked()");
        ui.actionClose.triggered.connect(this, "closeApp()");
        ui.actionDE.triggered.connect(this, "translateDE()");
        ui.actionEN.triggered.connect(this, "translateEN()");
        ui.actionIcs_Import.triggered.connect(this, "icsImportClicked()");
        ui.actionLogout.triggered.connect(this, "logoutClicked()");

        setLanguageChecked();

        LanguageController.getInstance().languageChanged.connect(this,
                "translate()");
        managmentView.logout.connect(this, "logoutClicked()");

        ui.txtStart.setDisplayFormat("HH:mm");
        ui.txtEnd.setDisplayFormat("HH:mm");

        updateWorkSessionModel();
        updateFavoriteModel();
        updateTimeInfos();
    }

    private void setStatusBarText(final String text) {
        ui.statusBar.showMessage(text, 5000);
        QPalette palette = ui.statusBar.palette();
        palette.setBrush(ColorRole.WindowText, new QBrush(QColor.red));
        ui.statusBar.setPalette(palette);
    }

    private void updateWorkSessionModel() {
        try {
            for (int i = 0; i < WorkSessionController.getInstance()
                    .getWorkSessions(currUser, calendar.getCurrentDate())
                    .size(); i++) {
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
                deleteButton.setIcon(new QIcon(
                        "classpath:icons/delete_16x16.png"));
                deleteButton.actionClicked.connect(this,
                        "workSessionDeleteClicked(IndexButton)");
                w.layout().addWidget(editButton);
                w.layout().addWidget(deleteButton);

                ui.tblWorksessions.setIndexWidget(currIndex, w);
            }
        } catch (WaktuException e) {
            setStatusBarText(e.getMessage());
        }
    }

    private void updateFavoriteModel() {
        try {
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
                deleteButton.setIcon(new QIcon(
                        "classpath:icons/delete_16x16.png"));
                deleteButton.actionClicked.connect(this,
                        "favoriteDeleteClicked(IndexButton)");
                w.layout().addWidget(editButton);
                w.layout().addWidget(deleteButton);

                ui.tblFavorites.setIndexWidget(currIndex, w);
            }
        } catch (WaktuException e) {
            setStatusBarText(e.getMessage());
        }
    }

    private void updateFavoriteTable() {
        updateFavoriteModel();
        try {
            if (favoriteModel != null) {
                favoriteModel.updateFavoriteModel();
            } else {
                favoriteModel = new FavoriteModel(currUser);
            }
        } catch (WaktuException e) {

            setStatusBarText(e.getMessage());
        }
        favoriteModel.layoutAboutToBeChanged.emit();
        favoriteModel.dataChanged.emit(
                favoriteModel.index(0, 0),
                favoriteModel.index(favoriteModel.rowCount(),
                        favoriteModel.columnCount()));
        favoriteModel.layoutChanged.emit();
        updateTimeInfos();
    }

    private void updateWorkSessionTable() {
        updateWorkSessionModel();
        try {
            if (workSessionModel != null) {
                workSessionModel.updateModel(currUser,
                        calendar.getCurrentDate());
            } else {
                workSessionModel = new WorkSessionModel(currUser,
                        calendar.getCurrentDate());
            }
        } catch (WaktuException e) {
            setStatusBarText(e.getMessage());
        }
        workSessionModel.layoutAboutToBeChanged.emit();
        workSessionModel.dataChanged.emit(workSessionModel.index(0, 0),
                workSessionModel.index(workSessionModel.rowCount(),
                        workSessionModel.columnCount()));
        workSessionModel.layoutChanged.emit();
        updateTimeInfos();
    }

    private void updateTimeInfos() {
        ui.lblPlannedDay.setText("" + TimeController.HOURS_PER_WORKDAY);
        ui.lblPlannedMonth.setText(""
                + TimeController.getPlannedTime(currUser,
                        calendar.getCurrentDate()));
        try {
            ui.lblToday.setText(""
                    + TimeController.calculateWorktime(currUser, null, null,
                            calendar.getCurrentDate(),
                            calendar.getCurrentDate()));
            ui.lblCurrentWeek.setText(""
                    + TimeController.calculateWorktimeForWeek(currUser,
                            calendar.getCurrentDate()));
            ui.lblCurrentMonth.setText(""
                    + TimeController.calculateWorktimeForMonth(currUser,
                            calendar.getCurrentDate()));
            ui.lblOvertime.setText(""
                    + TimeController.calculateOvertime(currUser, new QDate(01,
                            01, 1900), new QDate(31, 12, 2999)));
            ui.lblHolidays.setText(currUser.getHoliday() + "");
        } catch (WaktuException e) {
            setStatusBarText(e.getMessage());
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
            setStatusBarText(com.trolltech.qt.core.QCoreApplication.translate(
                    "TimeView", ("Endtime must be greater then Starttime"),
                    null));
        } else if (ui.cmbProject.currentIndex() < 0) {
            setStatusBarText(com.trolltech.qt.core.QCoreApplication.translate(
                    "TimeView", ("Project must be choosen"), null));
        } else if (ui.cmbWorkpackage.currentIndex() < 0) {
            setStatusBarText(com.trolltech.qt.core.QCoreApplication.translate(
                    "TimeView", ("WorkPackage must be choosen"), null));
        } else {
            Project project;
            try {
                WorkPackage workPackage = (WorkPackage) ui.cmbWorkpackage
                        .itemData(ui.cmbWorkpackage.currentIndex());
                QDateTime start = new QDateTime();
                start.setDate(calendar.getCurrentDate());
                start.setTime(ui.txtStart.time());
                QDateTime end = new QDateTime();
                end.setDate(calendar.getCurrentDate());
                end.setTime(ui.txtEnd.time());
                WorkSessionController.getInstance().addWorkSession(currUser,
                        workPackage,
                        TimeUtil.convertQDateTimeToGregorian(start),
                        TimeUtil.convertQDateTimeToGregorian(end),
                        ui.txtDescription.text());
            } catch (WaktuException e) {
                setStatusBarText(e.getMessage());
            }
        }
    }

    @SuppressWarnings("unused")
    private void timeOnlyClicked() {
        if (ui.tblFavorites.selectionModel().selectedRows().size() > 0) {
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
        if (ui.tblFavorites.selectionModel().selectedRows().size() > 0) {
            Favorite favorite = favoriteModel.getFavorite(ui.tblFavorites
                    .selectionModel().selectedRows().get(0).row());
            WorkPackage wp = favorite.getWorkPackageID();
            Project project = wp.getProject();
            try {
                ComboBoxData.createProjectForUserComboBox(ui.cmbProject,
                        currUser, project);
            } catch (WaktuException e) {
                setStatusBarText(e.getMessage());
            }
            try {
                ComboBoxData.createActiveWorkPackageComboBox(ui.cmbWorkpackage,
                        project, wp);
            } catch (WaktuException e) {
                setStatusBarText(e.getMessage());
            }
        } else {
            setStatusBarText("Select a Favorite");
        }
    }

    @SuppressWarnings("unused")
    private void createFavoriteClicked() {
        if (ui.tblFavorites.selectionModel().selectedRows().size() > 0) {
            Favorite favorite = favoriteModel.getFavorite(ui.tblFavorites
                    .selectionModel().selectedRows().get(0).row());
            WorkPackage wp = favorite.getWorkPackageID();

            QDateTime start = new QDateTime();
            start.setDate(calendar.getCurrentDate());
            start.setTime(TimeUtil.convertGregorianToQDateTime(
                    favorite.getStartTime()).time());

            QDateTime end = new QDateTime();
            end.setDate(calendar.getCurrentDate());
            end.setTime(TimeUtil.convertGregorianToQDateTime(
                    favorite.getEndTime()).time());

            try {
                WorkSessionController.getInstance().addWorkSession(currUser,
                        wp, TimeUtil.convertQDateTimeToGregorian(start),
                        TimeUtil.convertQDateTimeToGregorian(end), "");
            } catch (WaktuException e) {
                setStatusBarText(e.getMessage());
            }
            ui.tblFavorites.clearSelection();
        } else {
            setStatusBarText("Select a Favorite");
        }
    }

    @SuppressWarnings("unused")
    private void addToFavorites() {
        if (ui.txtEnd.time().compareTo(ui.txtStart.time()) <= 0) {
            setStatusBarText(com.trolltech.qt.core.QCoreApplication.translate(
                    "TimeView", ("Endtime must be greater then Starttime"),
                    null));
        } else if (ui.cmbProject.currentIndex() < 0) {
            setStatusBarText(com.trolltech.qt.core.QCoreApplication.translate(
                    "TimeView", ("Project must be choosen"), null));
        } else if (ui.cmbWorkpackage.currentIndex() < 0) {
            setStatusBarText(com.trolltech.qt.core.QCoreApplication.translate(
                    "TimeView", ("WorkPackage must be choosen"), null));
        } else {

            try {
                WorkPackage workPackage = (WorkPackage) ui.cmbWorkpackage
                        .itemData(ui.cmbWorkpackage.currentIndex());
                QDateTime start = new QDateTime();
                start.setDate(calendar.getCurrentDate());
                start.setTime(ui.txtStart.time());
                QDateTime end = new QDateTime();
                end.setDate(calendar.getCurrentDate());
                end.setTime(ui.txtEnd.time());

                FavoriteController.getInstance().addFavorite(currUser,
                        workPackage,
                        TimeUtil.convertQDateTimeToGregorian(start),
                        TimeUtil.convertQDateTimeToGregorian(end));
            } catch (WaktuException e) {
                setStatusBarText(e.getMessage());
            }
        }
    }

    @SuppressWarnings("unused")
    private void projectChanged() {
        try {
            ComboBoxData.createActiveWorkPackageComboBox(ui.cmbWorkpackage,
                    (Project) ui.cmbProject.itemData(ui.cmbProject
                            .currentIndex()), null);
            ui.cmbWorkpackage.setCurrentIndex(-1);
        } catch (WaktuException e) {
            setStatusBarText(e.getMessage());
        }
    }

    @SuppressWarnings("unused")
    private void managmentClicked() {
        managmentView.show();
        managmentView.setFocus();
    }

    @SuppressWarnings("unused")
    private void translateDE() {
        LanguageController.getInstance().setCurrLanguage(Language.DE);
    }

    @SuppressWarnings("unused")
    private void translateEN() {
        LanguageController.getInstance().setCurrLanguage(Language.EN);
    }

    @SuppressWarnings("unused")
    private void closeApp() {
        QApplication.exit();
    }

    @SuppressWarnings("unused")
    private void dayChanged() {
        updateWorkSessionTable();
        updateTimeInfos();
    }

    @SuppressWarnings("unused")
    private void favoriteAdded(final Favorite favorite) {
        updateFavoriteTable();
        updateTimeInfos();
    }

    @SuppressWarnings("unused")
    private void favoriteUpdated() {
        updateFavoriteTable();
        updateTimeInfos();
    }

    @SuppressWarnings("unused")
    private void favoriteRemoved(final Favorite favorite) {
        updateFavoriteTable();
        updateTimeInfos();
    }

    @SuppressWarnings("unused")
    private void workSessionAdded(final WorkSession workSession) {
        updateWorkSessionTable();
        updateTimeInfos();
    }

    @SuppressWarnings("unused")
    private void workSessionUpdated() {
        updateWorkSessionTable();
    }

    @SuppressWarnings("unused")
    private void workSessionRemoved(final WorkSession workSession) {
        updateWorkSessionTable();
        updateTimeInfos();
    }

    @SuppressWarnings("unused")
    private void workSessionEditClicked(final IndexButton btn) {
        logger.info("EditClicked for " + btn);
        if (workSessionModel.getEditable() != null
                && EditStatus.Edit == btn.getStatus()) {
            setStatusBarText(com.trolltech.qt.core.QCoreApplication.translate(
                    "TimeView", ("Save first current edit row"), null));
        } else {
            if (EditStatus.Edit == btn.getStatus()) {
                btn.setStatus(EditStatus.Save);
                workSessionModel.setEditable(btn.getIndex());
                btn.setIcon(new QIcon("classpath:icons/save_16x16.png"));
                ui.tblWorksessions.selectionModel().select(btn.getIndex(),
                        SelectionFlag.Rows);
            } else {
                workSessionModel.setEditable(null);
                btn.setIcon(new QIcon("classpath:icons/edit_16x16.png"));
                btn.setStatus(EditStatus.Edit);
                try {
                    WorkSessionController.getInstance().updateWorkSession(
                            workSessionModel.getWorkSession(btn.getIndex()
                                    .row()));
                } catch (WaktuException e) {
                    setStatusBarText(e.getMessage());
                }
            }
        }
    }

    @SuppressWarnings("unused")
    private void workSessionDeleteClicked(final IndexButton btn) {
        logger.info("EditClicked for " + btn);
        try {
            WorkSessionController.getInstance().removeWorkSession(
                    WorkSessionController
                            .getInstance()
                            .getWorkSessions(currUser,
                                    calendar.getCurrentDate())
                            .get(btn.getIndex().row()));
        } catch (WaktuException e) {
            setStatusBarText(e.getMessage());
        }

    }

    @SuppressWarnings("unused")
    private void favoriteEditClicked(final IndexButton btn) {
        logger.info("EditClicked for favorite " + btn);
        if (favoriteModel.getEditable() != null
                && EditStatus.Edit == btn.getStatus()) {
            setStatusBarText(com.trolltech.qt.core.QCoreApplication.translate(
                    "TimeView", ("Save first current edit row"), null));
        } else {
            if (EditStatus.Edit == btn.getStatus()) {
                btn.setStatus(EditStatus.Save);
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
    private void favoriteDeleteClicked(final IndexButton btn) {
        logger.info("EditClicked for favorite " + btn);
        try {
            FavoriteController.getInstance().removeFavorite(
                    FavoriteController.getInstance().getFavorites(currUser)
                            .get(btn.getIndex().row()));
        } catch (WaktuException e) {
            setStatusBarText(e.getMessage());
        }
    }

    @SuppressWarnings("unused")
    private void translate() {
        ui.retranslateUi(this);
        setLanguageChecked();
    }

    @SuppressWarnings("unused")
    private void icsImportClicked() {
        String fileName = QFileDialog.getOpenFileName(this,
                tr("Open ICS File"), "", new QFileDialog.Filter(
                        tr("Ics Files (*.ics)")));
        if (!fileName.isEmpty()) {
            IcsImportView icsImport = new IcsImportView(fileName);
            icsImport.show();
        }
    }

    @SuppressWarnings("unused")
    private void logoutClicked() {
        managmentView.close();
        LoginView login = new LoginView();
        login.show();
        setVisible(false);
    }

    @Override
    protected void closeEvent(final QCloseEvent arg__1) {
        QApplication.exit();
    }

    @Override
    protected void contextMenuEvent(final QContextMenuEvent event) {
        QMenu menu = new QMenu(this);

        QAction addToFavorites = new QAction(QCoreApplication.translate(
                "TimeView", "Add to Favorites"), menu);
        addToFavorites.triggered.connect(this, "addToFavorites()");

        QAction managmentAction = new QAction(QCoreApplication.translate(
                "TimeView", "Open Managment"), menu);
        managmentAction.triggered.connect(this, "managmentClicked()");

        QAction icsImport = new QAction(QCoreApplication.translate("TimeView",
                "Ics Import"), menu);
        icsImport.triggered.connect(this, "icsImportClicked()");

        QMenu languageMenu = new QMenu(QCoreApplication.translate("TimeView",
                "Language"), menu);
        QAction actionDE = new QAction(QCoreApplication.translate("TimeView",
                "DE"), menu);
        actionDE.setCheckable(true);
        actionDE.triggered.connect(this, "translateDE()");
        QAction actionEN = new QAction(QCoreApplication.translate("TimeView",
                "EN"), menu);
        actionEN.setCheckable(true);
        actionEN.triggered.connect(this, "translateEN()");
        languageMenu.addAction(actionEN);
        languageMenu.addAction(actionDE);
        if (LanguageController.getInstance().getCurrLanguage() == Language.DE) {
            actionDE.setChecked(true);
            actionEN.setChecked(false);
        } else if (LanguageController.getInstance().getCurrLanguage() == Language.EN) {
            actionDE.setChecked(false);
            actionEN.setChecked(true);
        }

        QAction logoutAction = new QAction(QCoreApplication.translate(
                "TimeView", "Logout"), menu);
        logoutAction.triggered.connect(this, "logoutClicked()");

        QAction closeAction = new QAction(QCoreApplication.translate(
                "TimeView", "Close"), menu);
        closeAction.triggered.connect(this, "closeApp()");

        menu.addAction(addToFavorites);
        menu.addAction(managmentAction);
        menu.addAction(icsImport);
        menu.addMenu(languageMenu);
        menu.addAction(logoutAction);
        menu.addAction(closeAction);

        menu.exec(event.globalPos());
    }

    @SuppressWarnings("unused")
    private void projectStaffChanged(final ProjectStaff projectStaff) {
        projectCombo();
    }

    @SuppressWarnings("unused")
    private void projectsAdded(final Project project) {
        projectCombo();
    }

    @SuppressWarnings("unused")
    private void projectsUpdated() {
        projectCombo();
    }

    @SuppressWarnings("unused")
    private void workPackageAdded(final WorkPackage workPackage) {
        workPackageCombo();
    }

    @SuppressWarnings("unused")
    private void workPackageUpdated() {
        workPackageCombo();
    }

    private void projectCombo() {
        Project project = (Project) ui.cmbProject.itemData(ui.cmbProject
                .currentIndex());
        try {
            ComboBoxData.createProjectForUserComboBox(ui.cmbProject, currUser,
                    project);
        } catch (WaktuException e) {
            setStatusBarText(e.getMessage());
        }
        if (project != null) {
            try {
                ComboBoxData.createActiveWorkPackageComboBox(ui.cmbWorkpackage,
                        project, null);
            } catch (WaktuException e) {
                setStatusBarText(e.getMessage());
            }
        }
    }

    private void workPackageCombo() {
        Project project = (Project) ui.cmbProject.itemData(ui.cmbProject
                .currentIndex());
        WorkPackage wp = (WorkPackage) ui.cmbWorkpackage
                .itemData(ui.cmbWorkpackage.currentIndex());
        try {
            ComboBoxData.createActiveWorkPackageComboBox(ui.cmbWorkpackage,
                    project, wp);
        } catch (WaktuException e) {
            setStatusBarText(e.getMessage());
        }
    }

    private void setLanguageChecked() {
        if (LanguageController.getInstance().getCurrLanguage() == Language.DE) {
            ui.actionDE.setChecked(true);
            ui.actionEN.setChecked(false);
        } else if (LanguageController.getInstance().getCurrLanguage() == Language.EN) {
            ui.actionDE.setChecked(false);
            ui.actionEN.setChecked(true);
        }
    }
}
