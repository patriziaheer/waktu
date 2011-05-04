/********************************************************************************
** Form generated from reading ui file 'ManagmentWindow.jui'
**
** Created: Mi 4. Mai 08:14:56 2011
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package ch.hsr.waktu.presentation.view;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_ManagmentWindow implements com.trolltech.qt.QUiForm<QMainWindow>
{
    public QAction actionClose;
    public QAction actionAdd;
    public QAction actionAddProject;
    public QAction actionAddUser;
    public QWidget centralwidget;
    public QGridLayout gridLayout_2;
    public QTabWidget tabWidget;
    public QWidget tabProject;
    public QMenuBar menubar;
    public QMenu menuFile;

    public Ui_ManagmentWindow() { super(); }

    public void setupUi(QMainWindow ManagmentWindow)
    {
        ManagmentWindow.setObjectName("ManagmentWindow");
        ManagmentWindow.resize(new QSize(650, 571).expandedTo(ManagmentWindow.minimumSizeHint()));
        ManagmentWindow.setMinimumSize(new QSize(650, 0));
        actionClose = new QAction(ManagmentWindow);
        actionClose.setObjectName("actionClose");
        actionAdd = new QAction(ManagmentWindow);
        actionAdd.setObjectName("actionAdd");
        actionAddProject = new QAction(ManagmentWindow);
        actionAddProject.setObjectName("actionAddProject");
        actionAddUser = new QAction(ManagmentWindow);
        actionAddUser.setObjectName("actionAddUser");
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
        menubar.setGeometry(new QRect(0, 0, 650, 22));
        menuFile = new QMenu(menubar);
        menuFile.setObjectName("menuFile");
        ManagmentWindow.setMenuBar(menubar);

        menubar.addAction(menuFile.menuAction());
        menuFile.addAction(actionAddProject);
        menuFile.addAction(actionAddUser);
        menuFile.addAction(actionClose);
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
        tabWidget.setTabText(tabWidget.indexOf(tabProject), com.trolltech.qt.core.QCoreApplication.translate("ManagmentWindow", "Projekte", null));
        menuFile.setTitle(com.trolltech.qt.core.QCoreApplication.translate("ManagmentWindow", "File", null));
    } // retranslateUi

}

