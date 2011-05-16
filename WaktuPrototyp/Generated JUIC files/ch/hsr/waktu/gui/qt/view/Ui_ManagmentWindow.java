/********************************************************************************
** Form generated from reading ui file 'ManagmentWindow.jui'
**
** Created: Mo. Mai 16 08:50:42 2011
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package ch.hsr.waktu.gui.qt.view;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_ManagmentWindow implements com.trolltech.qt.QUiForm<QMainWindow>
{
    public QAction actionClose;
    public QAction actionAdd;
    public QAction actionAddProject;
    public QAction actionAddUser;
    public QAction actionEN;
    public QAction actionDE;
    public QWidget centralwidget;
    public QGridLayout gridLayout_2;
    public QTabWidget tabWidget;
    public QWidget tabProject;
    public QMenuBar menubar;
    public QMenu menuFile;
    public QMenu menuLanguage;
    public QStatusBar statusBar;

    public Ui_ManagmentWindow() { super(); }

    public void setupUi(QMainWindow ManagmentWindow)
    {
        ManagmentWindow.setObjectName("ManagmentWindow");
        ManagmentWindow.resize(new QSize(1115, 584).expandedTo(ManagmentWindow.minimumSizeHint()));
        ManagmentWindow.setMinimumSize(new QSize(650, 0));
        actionClose = new QAction(ManagmentWindow);
        actionClose.setObjectName("actionClose");
        actionAdd = new QAction(ManagmentWindow);
        actionAdd.setObjectName("actionAdd");
        actionAddProject = new QAction(ManagmentWindow);
        actionAddProject.setObjectName("actionAddProject");
        actionAddUser = new QAction(ManagmentWindow);
        actionAddUser.setObjectName("actionAddUser");
        actionEN = new QAction(ManagmentWindow);
        actionEN.setObjectName("actionEN");
        actionDE = new QAction(ManagmentWindow);
        actionDE.setObjectName("actionDE");
        centralwidget = new QWidget(ManagmentWindow);
        centralwidget.setObjectName("centralwidget");
        gridLayout_2 = new QGridLayout(centralwidget);
        gridLayout_2.setObjectName("gridLayout_2");
        tabWidget = new QTabWidget(centralwidget);
        tabWidget.setObjectName("tabWidget");
        tabProject = new QWidget();
        tabProject.setObjectName("tabProject");
        tabWidget.addTab(tabProject, com.trolltech.qt.core.QCoreApplication.translate("ManagmentWindow", "Projekte", null));

        gridLayout_2.addWidget(tabWidget, 0, 0, 1, 1);

        ManagmentWindow.setCentralWidget(centralwidget);
        menubar = new QMenuBar(ManagmentWindow);
        menubar.setObjectName("menubar");
        menubar.setGeometry(new QRect(0, 0, 1115, 22));
        menuFile = new QMenu(menubar);
        menuFile.setObjectName("menuFile");
        menuLanguage = new QMenu(menuFile);
        menuLanguage.setObjectName("menuLanguage");
        ManagmentWindow.setMenuBar(menubar);
        statusBar = new QStatusBar(ManagmentWindow);
        statusBar.setObjectName("statusBar");
        ManagmentWindow.setStatusBar(statusBar);

        menubar.addAction(menuFile.menuAction());
        menuFile.addAction(actionAddProject);
        menuFile.addAction(actionAddUser);
        menuFile.addAction(menuLanguage.menuAction());
        menuFile.addAction(actionClose);
        menuLanguage.addAction(actionEN);
        menuLanguage.addAction(actionDE);
        retranslateUi(ManagmentWindow);

        tabWidget.setCurrentIndex(0);


        ManagmentWindow.connectSlotsByName();
    } // setupUi

    void retranslateUi(QMainWindow ManagmentWindow)
    {
        ManagmentWindow.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("ManagmentWindow", "Managment Window", null));
        actionClose.setText(com.trolltech.qt.core.QCoreApplication.translate("ManagmentWindow", "Close", null));
        actionAdd.setText(com.trolltech.qt.core.QCoreApplication.translate("ManagmentWindow", "Add", null));
        actionAddProject.setText(com.trolltech.qt.core.QCoreApplication.translate("ManagmentWindow", "Add Project", null));
        actionAddUser.setText(com.trolltech.qt.core.QCoreApplication.translate("ManagmentWindow", "Add User", null));
        actionEN.setText(com.trolltech.qt.core.QCoreApplication.translate("ManagmentWindow", "EN", null));
        actionDE.setText(com.trolltech.qt.core.QCoreApplication.translate("ManagmentWindow", "DE", null));
        tabWidget.setTabText(tabWidget.indexOf(tabProject), com.trolltech.qt.core.QCoreApplication.translate("ManagmentWindow", "Projekte", null));
        menuFile.setTitle(com.trolltech.qt.core.QCoreApplication.translate("ManagmentWindow", "File", null));
        menuLanguage.setTitle(com.trolltech.qt.core.QCoreApplication.translate("ManagmentWindow", "Language", null));
    } // retranslateUi

}

