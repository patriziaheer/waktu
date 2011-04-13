/********************************************************************************
** Form generated from reading ui file 'TimeWindow.jui'
**
** Created: Mi 13. Apr 10:38:04 2011
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package ch.hsr.waktu.presentation.view.jui;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_TimeWindow implements com.trolltech.qt.QUiForm<QMainWindow>
{
    public QAction actionClose;
    public QAction actionOpenManagment;
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
    public QGroupBox grpFavorites;
    public QGridLayout gridLayout_3;
    public QTableView tblFavorites;
    public QPushButton btnCreateFavorite;
    public QPushButton btnWorkPackageOnly;
    public QPushButton btnTimeOnly;
    public QGroupBox grpCreateWorksession;
    public QGridLayout gridLayout_2;
    public QLabel lblTime;
    public QLabel lblProject;
    public QComboBox cmbProject;
    public QComboBox cmbWorkpackage;
    public QLabel lblWorkPackage;
    public QLabel lblDescription;
    public QLineEdit txtDescription;
    public QTimeEdit txtStart;
    public QTimeEdit txtEnd;
    public QLabel lblBis2;
    public QPushButton btnCreate;
    public QPushButton btnReset;
    public QWidget calendarWidget;
    public QGridLayout gridLayout_6;
    public QSpacerItem horizontalSpacer;
    public QCalendarWidget calendar;
    public QPushButton btnLeft;
    public QPushButton btnMo;
    public QPushButton btnDi;
    public QPushButton btnMi;
    public QPushButton btnDo;
    public QPushButton btnFri;
    public QPushButton btnSa;
    public QPushButton btnSo;
    public QPushButton btnDown;
    public QPushButton btnRight;
    public QLabel lblBis;
    public QLabel lblEnd;
    public QLabel lblStart;
    public QDateEdit dtCurrDate;
    public QStatusBar statusBar;
    public QMenuBar menuBar;
    public QMenu menuManagment;
    public QMenu menuFile;

    public Ui_TimeWindow() { super(); }

    public void setupUi(QMainWindow TimeWindow)
    {
        TimeWindow.setObjectName("TimeWindow");
        TimeWindow.resize(new QSize(740, 743).expandedTo(TimeWindow.minimumSizeHint()));
        actionClose = new QAction(TimeWindow);
        actionClose.setObjectName("actionClose");
        actionOpenManagment = new QAction(TimeWindow);
        actionOpenManagment.setObjectName("actionOpenManagment");
        centralwidget = new QWidget(TimeWindow);
        centralwidget.setObjectName("centralwidget");
        gridLayout = new QGridLayout(centralwidget);
        gridLayout.setObjectName("gridLayout");
        grpWorksessions = new QGroupBox(centralwidget);
        grpWorksessions.setObjectName("grpWorksessions");
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

        grpFavorites = new QGroupBox(centralwidget);
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


        gridLayout.addWidget(grpFavorites, 1, 1, 1, 1);

        grpCreateWorksession = new QGroupBox(centralwidget);
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

        lblProject = new QLabel(grpCreateWorksession);
        lblProject.setObjectName("lblProject");

        gridLayout_2.addWidget(lblProject, 2, 0, 1, 1);

        cmbProject = new QComboBox(grpCreateWorksession);
        cmbProject.setObjectName("cmbProject");

        gridLayout_2.addWidget(cmbProject, 2, 1, 1, 6);

        cmbWorkpackage = new QComboBox(grpCreateWorksession);
        cmbWorkpackage.setObjectName("cmbWorkpackage");

        gridLayout_2.addWidget(cmbWorkpackage, 3, 1, 1, 6);

        lblWorkPackage = new QLabel(grpCreateWorksession);
        lblWorkPackage.setObjectName("lblWorkPackage");

        gridLayout_2.addWidget(lblWorkPackage, 3, 0, 1, 1);

        lblDescription = new QLabel(grpCreateWorksession);
        lblDescription.setObjectName("lblDescription");

        gridLayout_2.addWidget(lblDescription, 4, 0, 1, 1);

        txtDescription = new QLineEdit(grpCreateWorksession);
        txtDescription.setObjectName("txtDescription");

        gridLayout_2.addWidget(txtDescription, 4, 1, 1, 6);

        txtStart = new QTimeEdit(grpCreateWorksession);
        txtStart.setObjectName("txtStart");
        QSizePolicy sizePolicy1 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy1.setHorizontalStretch((byte)0);
        sizePolicy1.setVerticalStretch((byte)0);
        sizePolicy1.setHeightForWidth(txtStart.sizePolicy().hasHeightForWidth());
        txtStart.setSizePolicy(sizePolicy1);
        txtStart.setMaximumSize(new QSize(150, 16777215));

        gridLayout_2.addWidget(txtStart, 0, 1, 1, 3);

        txtEnd = new QTimeEdit(grpCreateWorksession);
        txtEnd.setObjectName("txtEnd");
        txtEnd.setMaximumSize(new QSize(150, 16777215));

        gridLayout_2.addWidget(txtEnd, 0, 5, 1, 2);

        lblBis2 = new QLabel(grpCreateWorksession);
        lblBis2.setObjectName("lblBis2");

        gridLayout_2.addWidget(lblBis2, 0, 4, 1, 1);

        btnCreate = new QPushButton(grpCreateWorksession);
        btnCreate.setObjectName("btnCreate");

        gridLayout_2.addWidget(btnCreate, 5, 4, 1, 3);

        btnReset = new QPushButton(grpCreateWorksession);
        btnReset.setObjectName("btnReset");

        gridLayout_2.addWidget(btnReset, 5, 1, 1, 3);


        gridLayout.addWidget(grpCreateWorksession, 1, 0, 1, 1);

        calendarWidget = new QWidget(centralwidget);
        calendarWidget.setObjectName("calendarWidget");
        QSizePolicy sizePolicy2 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy2.setHorizontalStretch((byte)0);
        sizePolicy2.setVerticalStretch((byte)0);
        sizePolicy2.setHeightForWidth(calendarWidget.sizePolicy().hasHeightForWidth());
        calendarWidget.setSizePolicy(sizePolicy2);
        gridLayout_6 = new QGridLayout(calendarWidget);
        gridLayout_6.setObjectName("gridLayout_6");
        horizontalSpacer = new QSpacerItem(40, 20, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);

        gridLayout_6.addItem(horizontalSpacer, 0, 0, 1, 1);

        calendar = new QCalendarWidget(calendarWidget);
        calendar.setObjectName("calendar");
        QSizePolicy sizePolicy3 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy3.setHorizontalStretch((byte)0);
        sizePolicy3.setVerticalStretch((byte)0);
        sizePolicy3.setHeightForWidth(calendar.sizePolicy().hasHeightForWidth());
        calendar.setSizePolicy(sizePolicy3);
        calendar.setFirstDayOfWeek(com.trolltech.qt.core.Qt.DayOfWeek.Monday);
        calendar.setHorizontalHeaderFormat(com.trolltech.qt.gui.QCalendarWidget.HorizontalHeaderFormat.SingleLetterDayNames);
        calendar.setVerticalHeaderFormat(com.trolltech.qt.gui.QCalendarWidget.VerticalHeaderFormat.ISOWeekNumbers);

        gridLayout_6.addWidget(calendar, 0, 2, 2, 1);

        btnLeft = new QPushButton(calendarWidget);
        btnLeft.setObjectName("btnLeft");
        btnLeft.setMinimumSize(new QSize(30, 30));
        btnLeft.setMaximumSize(new QSize(30, 30));

        gridLayout_6.addWidget(btnLeft, 1, 3, 1, 1);

        btnMo = new QPushButton(calendarWidget);
        btnMo.setObjectName("btnMo");
        btnMo.setMinimumSize(new QSize(30, 30));
        btnMo.setMaximumSize(new QSize(30, 30));

        gridLayout_6.addWidget(btnMo, 1, 4, 1, 1);

        btnDi = new QPushButton(calendarWidget);
        btnDi.setObjectName("btnDi");
        btnDi.setMinimumSize(new QSize(30, 30));
        btnDi.setMaximumSize(new QSize(30, 30));

        gridLayout_6.addWidget(btnDi, 1, 5, 1, 1);

        btnMi = new QPushButton(calendarWidget);
        btnMi.setObjectName("btnMi");
        btnMi.setMinimumSize(new QSize(30, 30));
        btnMi.setMaximumSize(new QSize(30, 30));

        gridLayout_6.addWidget(btnMi, 1, 6, 1, 1);

        btnDo = new QPushButton(calendarWidget);
        btnDo.setObjectName("btnDo");
        btnDo.setMinimumSize(new QSize(30, 30));
        btnDo.setMaximumSize(new QSize(30, 30));

        gridLayout_6.addWidget(btnDo, 1, 7, 1, 1);

        btnFri = new QPushButton(calendarWidget);
        btnFri.setObjectName("btnFri");
        btnFri.setMinimumSize(new QSize(30, 30));
        btnFri.setMaximumSize(new QSize(30, 30));

        gridLayout_6.addWidget(btnFri, 1, 8, 1, 1);

        btnSa = new QPushButton(calendarWidget);
        btnSa.setObjectName("btnSa");
        btnSa.setMinimumSize(new QSize(30, 30));
        btnSa.setMaximumSize(new QSize(30, 30));

        gridLayout_6.addWidget(btnSa, 1, 9, 1, 1);

        btnSo = new QPushButton(calendarWidget);
        btnSo.setObjectName("btnSo");
        btnSo.setMinimumSize(new QSize(30, 30));
        btnSo.setMaximumSize(new QSize(30, 30));

        gridLayout_6.addWidget(btnSo, 1, 10, 1, 1);

        btnDown = new QPushButton(calendarWidget);
        btnDown.setObjectName("btnDown");
        btnDown.setMinimumSize(new QSize(30, 30));
        btnDown.setMaximumSize(new QSize(30, 30));

        gridLayout_6.addWidget(btnDown, 1, 11, 1, 1);

        btnRight = new QPushButton(calendarWidget);
        btnRight.setObjectName("btnRight");
        btnRight.setMinimumSize(new QSize(30, 30));
        btnRight.setMaximumSize(new QSize(30, 30));

        gridLayout_6.addWidget(btnRight, 1, 12, 1, 1);

        lblBis = new QLabel(calendarWidget);
        lblBis.setObjectName("lblBis");

        gridLayout_6.addWidget(lblBis, 0, 7, 1, 1);

        lblEnd = new QLabel(calendarWidget);
        lblEnd.setObjectName("lblEnd");

        gridLayout_6.addWidget(lblEnd, 0, 8, 1, 4);

        lblStart = new QLabel(calendarWidget);
        lblStart.setObjectName("lblStart");

        gridLayout_6.addWidget(lblStart, 0, 3, 1, 4);

        dtCurrDate = new QDateEdit(calendarWidget);
        dtCurrDate.setObjectName("dtCurrDate");
        dtCurrDate.setEnabled(false);

        gridLayout_6.addWidget(dtCurrDate, 1, 1, 1, 1);


        gridLayout.addWidget(calendarWidget, 0, 0, 1, 2);

        TimeWindow.setCentralWidget(centralwidget);
        statusBar = new QStatusBar(TimeWindow);
        statusBar.setObjectName("statusBar");
        TimeWindow.setStatusBar(statusBar);
        menuBar = new QMenuBar(TimeWindow);
        menuBar.setObjectName("menuBar");
        menuBar.setGeometry(new QRect(0, 0, 740, 22));
        menuManagment = new QMenu(menuBar);
        menuManagment.setObjectName("menuManagment");
        menuFile = new QMenu(menuBar);
        menuFile.setObjectName("menuFile");
        TimeWindow.setMenuBar(menuBar);

        menuBar.addAction(menuFile.menuAction());
        menuBar.addAction(menuManagment.menuAction());
        menuManagment.addAction(actionOpenManagment);
        menuFile.addAction(actionClose);
        retranslateUi(TimeWindow);

        TimeWindow.connectSlotsByName();
    } // setupUi

    void retranslateUi(QMainWindow TimeWindow)
    {
        TimeWindow.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Time Window", null));
        actionClose.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Close", null));
        actionOpenManagment.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Open", null));
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
        grpFavorites.setTitle(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Favorites", null));
        btnCreateFavorite.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "create", null));
        btnWorkPackageOnly.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Workpackage only", null));
        btnTimeOnly.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Time only", null));
        grpCreateWorksession.setTitle(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Create Worksession", null));
        lblTime.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Time:", null));
        lblProject.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Project:", null));
        lblWorkPackage.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Work Package:", null));
        lblDescription.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Description:", null));
        lblBis2.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "-", null));
        btnCreate.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "create", null));
        btnReset.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "reset", null));
        btnLeft.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "<", null));
        btnMo.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "MO", null));
        btnDi.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "DI", null));
        btnMi.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "MI", null));
        btnDo.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "DO", null));
        btnFri.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "FRI", null));
        btnSa.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "SA", null));
        btnSo.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "SO", null));
        btnDown.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "v", null));
        btnRight.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", ">", null));
        lblBis.setText(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "-", null));
        lblEnd.setText("");
        lblStart.setText("");
        menuManagment.setTitle(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "Managment", null));
        menuFile.setTitle(com.trolltech.qt.core.QCoreApplication.translate("TimeWindow", "File", null));
    } // retranslateUi

}

