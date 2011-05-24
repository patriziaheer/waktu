/********************************************************************************
** Form generated from reading ui file 'TimeWindow.jui'
**
** Created: Di. Mai 24 13:33:31 2011
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package ch.hsr.waktu.gui.qt.view;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_TimeWindow implements com.trolltech.qt.QUiForm<QMainWindow>
{
    public QAction actionClose;
    public QAction actionOpenManagment;
    public QAction actionAdd_to_Favorites;
    public QAction actionEN;
    public QAction actionDE;
    public QAction actionIcs_Import;
    public QAction actionLogout;
    public QWidget centralwidget;
    public QGridLayout gridLayout;
    public QGroupBox grpWorksessions;
    public QGridLayout gridLayout_4;
    public QTableView tblWorksessions;
    public QGroupBox grpOverview;
    public QGridLayout gridLayout_5;
    public QLabel lblPlannedDayText;
    public QLabel lblTodayText;
    public QLabel lblCurrentWeekText;
    public QLabel lblOvertimeText;
    public QLabel lblCurrentMonthText;
    public QLabel lblPlannedMonthText;
    public QLabel lblHolidaysText;
    public QLabel lblPlannedDay;
    public QLabel lblPlannedMonth;
    public QLabel lblToday;
    public QLabel lblCurrentWeek;
    public QLabel lblCurrentMonth;
    public QLabel lblOvertime;
    public QLabel lblHolidays;
    public QWidget widget;
    public QHBoxLayout horizontalLayout_3;
    public QGroupBox grpCreateWorksession;
    public QGridLayout gridLayout_2;
    public QLabel lblTime;
    public QHBoxLayout horizontalLayout;
    public QTimeEdit txtStart;
    public QLabel lblBis2;
    public QTimeEdit txtEnd;
    public QSpacerItem horizontalSpacer_2;
    public QLabel lblProject;
    public QComboBox cmbProject;
    public QLabel lblWorkPackage;
    public QComboBox cmbWorkpackage;
    public QLabel lblDescription;
    public QLineEdit txtDescription;
    public QPushButton btnReset;
    public QPushButton btnCreate;
    public QGroupBox grpFavorites;
    public QGridLayout gridLayout_3;
    public QTableView tblFavorites;
    public QPushButton btnCreateFavorite;
    public QPushButton btnWorkPackageOnly;
    public QPushButton btnTimeOnly;
    public QStatusBar statusBar;
    public QMenuBar menuBar;
    public QMenu menuManagment;
    public QMenu menuFile;
    public QMenu menuLanguage;

    public Ui_TimeWindow() { super(); }

    public void setupUi(QMainWindow TimeWindow)
    {
        TimeWindow.setObjectName("TimeWindow");
        TimeWindow.resize(new QSize(784, 731).expandedTo(TimeWindow.minimumSizeHint()));
        actionClose = new QAction(TimeWindow);
        actionClose.setObjectName("actionClose");
        actionOpenManagment = new QAction(TimeWindow);
        actionOpenManagment.setObjectName("actionOpenManagment");
        actionAdd_to_Favorites = new QAction(TimeWindow);
        actionAdd_to_Favorites.setObjectName("actionAdd_to_Favorites");
        actionEN = new QAction(TimeWindow);
        actionEN.setObjectName("actionEN");
        actionEN.setCheckable(true);
        actionDE = new QAction(TimeWindow);
        actionDE.setObjectName("actionDE");
        actionDE.setCheckable(true);
        actionIcs_Import = new QAction(TimeWindow);
        actionIcs_Import.setObjectName("actionIcs_Import");
        actionLogout = new QAction(TimeWindow);
        actionLogout.setObjectName("actionLogout");
        centralwidget = new QWidget(TimeWindow);
        centralwidget.setObjectName("centralwidget");
        gridLayout = new QGridLayout(centralwidget);
        gridLayout.setObjectName("gridLayout");
        grpWorksessions = new QGroupBox(centralwidget);
        grpWorksessions.setObjectName("grpWorksessions");
        grpWorksessions.setMinimumSize(new QSize(0, 150));
        gridLayout_4 = new QGridLayout(grpWorksessions);
        gridLayout_4.setObjectName("gridLayout_4");
        tblWorksessions = new QTableView(grpWorksessions);
        tblWorksessions.setObjectName("tblWorksessions");

        gridLayout_4.addWidget(tblWorksessions, 0, 0, 1, 1);


        gridLayout.addWidget(grpWorksessions, 3, 0, 1, 2);

        grpOverview = new QGroupBox(centralwidget);
        grpOverview.setObjectName("grpOverview");
        gridLayout_5 = new QGridLayout(grpOverview);
        gridLayout_5.setObjectName("gridLayout_5");
        lblPlannedDayText = new QLabel(grpOverview);
        lblPlannedDayText.setObjectName("lblPlannedDayText");

        gridLayout_5.addWidget(lblPlannedDayText, 0, 0, 1, 1);

        lblTodayText = new QLabel(grpOverview);
        lblTodayText.setObjectName("lblTodayText");

        gridLayout_5.addWidget(lblTodayText, 0, 2, 1, 1);

        lblCurrentWeekText = new QLabel(grpOverview);
        lblCurrentWeekText.setObjectName("lblCurrentWeekText");

        gridLayout_5.addWidget(lblCurrentWeekText, 1, 2, 1, 1);

        lblOvertimeText = new QLabel(grpOverview);
        lblOvertimeText.setObjectName("lblOvertimeText");

        gridLayout_5.addWidget(lblOvertimeText, 0, 4, 1, 1);

        lblCurrentMonthText = new QLabel(grpOverview);
        lblCurrentMonthText.setObjectName("lblCurrentMonthText");

        gridLayout_5.addWidget(lblCurrentMonthText, 2, 2, 1, 1);

        lblPlannedMonthText = new QLabel(grpOverview);
        lblPlannedMonthText.setObjectName("lblPlannedMonthText");

        gridLayout_5.addWidget(lblPlannedMonthText, 2, 0, 1, 1);

        lblHolidaysText = new QLabel(grpOverview);
        lblHolidaysText.setObjectName("lblHolidaysText");

        gridLayout_5.addWidget(lblHolidaysText, 2, 4, 1, 1);

        lblPlannedDay = new QLabel(grpOverview);
        lblPlannedDay.setObjectName("lblPlannedDay");

        gridLayout_5.addWidget(lblPlannedDay, 0, 1, 1, 1);

        lblPlannedMonth = new QLabel(grpOverview);
        lblPlannedMonth.setObjectName("lblPlannedMonth");

        gridLayout_5.addWidget(lblPlannedMonth, 2, 1, 1, 1);

        lblToday = new QLabel(grpOverview);
        lblToday.setObjectName("lblToday");

        gridLayout_5.addWidget(lblToday, 0, 3, 1, 1);

        lblCurrentWeek = new QLabel(grpOverview);
        lblCurrentWeek.setObjectName("lblCurrentWeek");

        gridLayout_5.addWidget(lblCurrentWeek, 1, 3, 1, 1);

        lblCurrentMonth = new QLabel(grpOverview);
        lblCurrentMonth.setObjectName("lblCurrentMonth");

        gridLayout_5.addWidget(lblCurrentMonth, 2, 3, 1, 1);

        lblOvertime = new QLabel(grpOverview);
        lblOvertime.setObjectName("lblOvertime");

        gridLayout_5.addWidget(lblOvertime, 0, 5, 1, 1);

        lblHolidays = new QLabel(grpOverview);
        lblHolidays.setObjectName("lblHolidays");

        gridLayout_5.addWidget(lblHolidays, 2, 5, 1, 1);


        gridLayout.addWidget(grpOverview, 4, 0, 1, 2);

        widget = new QWidget(centralwidget);
        widget.setObjectName("widget");
        horizontalLayout_3 = new QHBoxLayout(widget);
        horizontalLayout_3.setMargin(0);
        horizontalLayout_3.setObjectName("horizontalLayout_3");
        grpCreateWorksession = new QGroupBox(widget);
        grpCreateWorksession.setObjectName("grpCreateWorksession");
        QSizePolicy sizePolicy = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Preferred, com.trolltech.qt.gui.QSizePolicy.Policy.Preferred);
        sizePolicy.setHorizontalStretch((byte)0);
        sizePolicy.setVerticalStretch((byte)0);
        sizePolicy.setHeightForWidth(grpCreateWorksession.sizePolicy().hasHeightForWidth());
        grpCreateWorksession.setSizePolicy(sizePolicy);
        grpCreateWorksession.setMinimumSize(new QSize(350, 0));
        gridLayout_2 = new QGridLayout(grpCreateWorksession);
        gridLayout_2.setObjectName("gridLayout_2");
        lblTime = new QLabel(grpCreateWorksession);
        lblTime.setObjectName("lblTime");

        gridLayout_2.addWidget(lblTime, 0, 0, 1, 1);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout.setObjectName("horizontalLayout");
        txtStart = new QTimeEdit(grpCreateWorksession);
        txtStart.setObjectName("txtStart");
        QSizePolicy sizePolicy1 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy1.setHorizontalStretch((byte)0);
        sizePolicy1.setVerticalStretch((byte)0);
        sizePolicy1.setHeightForWidth(txtStart.sizePolicy().hasHeightForWidth());
        txtStart.setSizePolicy(sizePolicy1);
        txtStart.setMinimumSize(new QSize(65, 0));
        txtStart.setMaximumSize(new QSize(65, 16777215));

        horizontalLayout.addWidget(txtStart);

        lblBis2 = new QLabel(grpCreateWorksession);
        lblBis2.setObjectName("lblBis2");

        horizontalLayout.addWidget(lblBis2);

        txtEnd = new QTimeEdit(grpCreateWorksession);
        txtEnd.setObjectName("txtEnd");
        txtEnd.setMinimumSize(new QSize(65, 0));
        txtEnd.setMaximumSize(new QSize(65, 16777215));

        horizontalLayout.addWidget(txtEnd);

        horizontalSpacer_2 = new QSpacerItem(40, 20, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);

        horizontalLayout.addItem(horizontalSpacer_2);


        gridLayout_2.addLayout(horizontalLayout, 0, 2, 1, 6);

        lblProject = new QLabel(grpCreateWorksession);
        lblProject.setObjectName("lblProject");

        gridLayout_2.addWidget(lblProject, 2, 0, 1, 1);

        cmbProject = new QComboBox(grpCreateWorksession);
        cmbProject.setObjectName("cmbProject");

        gridLayout_2.addWidget(cmbProject, 2, 2, 1, 6);

        lblWorkPackage = new QLabel(grpCreateWorksession);
        lblWorkPackage.setObjectName("lblWorkPackage");

        gridLayout_2.addWidget(lblWorkPackage, 3, 0, 1, 1);

        cmbWorkpackage = new QComboBox(grpCreateWorksession);
        cmbWorkpackage.setObjectName("cmbWorkpackage");

        gridLayout_2.addWidget(cmbWorkpackage, 3, 2, 1, 6);

        lblDescription = new QLabel(grpCreateWorksession);
        lblDescription.setObjectName("lblDescription");

        gridLayout_2.addWidget(lblDescription, 4, 0, 1, 1);

        txtDescription = new QLineEdit(grpCreateWorksession);
        txtDescription.setObjectName("txtDescription");

        gridLayout_2.addWidget(txtDescription, 4, 2, 1, 6);

        btnReset = new QPushButton(grpCreateWorksession);
        btnReset.setObjectName("btnReset");

        gridLayout_2.addWidget(btnReset, 5, 2, 1, 3);

        btnCreate = new QPushButton(grpCreateWorksession);
        btnCreate.setObjectName("btnCreate");

        gridLayout_2.addWidget(btnCreate, 5, 5, 1, 3);


        horizontalLayout_3.addWidget(grpCreateWorksession);

        grpFavorites = new QGroupBox(widget);
        grpFavorites.setObjectName("grpFavorites");
        grpFavorites.setMinimumSize(new QSize(350, 0));
        gridLayout_3 = new QGridLayout(grpFavorites);
        gridLayout_3.setObjectName("gridLayout_3");
        tblFavorites = new QTableView(grpFavorites);
        tblFavorites.setObjectName("tblFavorites");

        gridLayout_3.addWidget(tblFavorites, 0, 0, 1, 3);

        btnCreateFavorite = new QPushButton(grpFavorites);
        btnCreateFavorite.setObjectName("btnCreateFavorite");

        gridLayout_3.addWidget(btnCreateFavorite, 1, 2, 1, 1);

        btnWorkPackageOnly = new QPushButton(grpFavorites);
        btnWorkPackageOnly.setObjectName("btnWorkPackageOnly");

        gridLayout_3.addWidget(btnWorkPackageOnly, 1, 1, 1, 1);

        btnTimeOnly = new QPushButton(grpFavorites);
        btnTimeOnly.setObjectName("btnTimeOnly");

        gridLayout_3.addWidget(btnTimeOnly, 1, 0, 1, 1);


        horizontalLayout_3.addWidget(grpFavorites);


        gridLayout.addWidget(widget, 0, 0, 1, 2);

        TimeWindow.setCentralWidget(centralwidget);
        statusBar = new QStatusBar(TimeWindow);
        statusBar.setObjectName("statusBar");
        TimeWindow.setStatusBar(statusBar);
        menuBar = new QMenuBar(TimeWindow);
        menuBar.setObjectName("menuBar");
        menuBar.setGeometry(new QRect(0, 0, 784, 22));
        menuManagment = new QMenu(menuBar);
        menuManagment.setObjectName("menuManagment");
        menuFile = new QMenu(menuBar);
        menuFile.setObjectName("menuFile");
        menuLanguage = new QMenu(menuFile);
        menuLanguage.setObjectName("menuLanguage");
        TimeWindow.setMenuBar(menuBar);

        menuBar.addAction(menuFile.menuAction());
        menuBar.addAction(menuManagment.menuAction());
        menuManagment.addAction(actionOpenManagment);
        menuFile.addAction(actionAdd_to_Favorites);
        menuFile.addAction(menuLanguage.menuAction());
        menuFile.addAction(actionIcs_Import);
        menuFile.addAction(actionLogout);
        menuFile.addAction(actionClose);
        menuLanguage.addAction(actionEN);
        menuLanguage.addAction(actionDE);
        retranslateUi(TimeWindow);

        TimeWindow.connectSlotsByName();
    } // setupUi

    void retranslateUi(QMainWindow TimeWindow)
    {
        TimeWindow.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Time Window", null));
        actionClose.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Close", null));
        actionOpenManagment.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Open", null));
        actionAdd_to_Favorites.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Add to Favorites", null));
        actionEN.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "EN", null));
        actionDE.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "DE", null));
        actionIcs_Import.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Ics Import", null));
        actionLogout.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Logout", null));
        grpWorksessions.setTitle(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Worksessions", null));
        grpOverview.setTitle(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Overview", null));
        lblPlannedDayText.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Worktime per day:", null));
        lblTodayText.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Worktime today:", null));
        lblCurrentWeekText.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Worktime current Week:", null));
        lblOvertimeText.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Overtime:", null));
        lblCurrentMonthText.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Worktime current Month:", null));
        lblPlannedMonthText.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Worktime for March:", null));
        lblHolidaysText.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Holidays", null));
        lblPlannedDay.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "8.2h", null));
        lblPlannedMonth.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "164.4h", null));
        lblToday.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "0.0h", null));
        lblCurrentWeek.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "20.4h", null));
        lblCurrentMonth.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "45.3h", null));
        lblOvertime.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "2.4h", null));
        lblHolidays.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "12.3d", null));
        grpCreateWorksession.setTitle(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Create Worksession", null));
        lblTime.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Time:", null));
        lblBis2.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "-", null));
        lblProject.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Project:", null));
        lblWorkPackage.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Work Package:", null));
        lblDescription.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Description:", null));
        btnReset.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "reset", null));
        btnCreate.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "create", null));
        grpFavorites.setTitle(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Favorites", null));
        btnCreateFavorite.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "create", null));
        btnWorkPackageOnly.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Workpackage only", null));
        btnTimeOnly.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Time only", null));
        menuManagment.setTitle(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Managment", null));
        menuFile.setTitle(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "File", null));
        menuLanguage.setTitle(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Language", null));
    } // retranslateUi

}

