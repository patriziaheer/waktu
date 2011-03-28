/********************************************************************************
** Form generated from reading ui file 'WaktuMainWindow.jui'
**
** Created: Mo. Mrz 28 12:28:28 2011
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_WaktuMainWindow implements com.trolltech.qt.QUiForm<QMainWindow>
{
    public QWidget centralwidget;
    public QGridLayout gridLayout_2;
    public QTabWidget tabWidget;
    public QWidget tab;
    public QMenuBar menubar;

    public Ui_WaktuMainWindow() { super(); }

    public void setupUi(QMainWindow WaktuMainWindow)
    {
        WaktuMainWindow.setObjectName("WaktuMainWindow");
        WaktuMainWindow.resize(new QSize(500, 500).expandedTo(WaktuMainWindow.minimumSizeHint()));
        centralwidget = new QWidget(WaktuMainWindow);
        centralwidget.setObjectName("centralwidget");
        gridLayout_2 = new QGridLayout(centralwidget);
        gridLayout_2.setObjectName("gridLayout_2");
        tabWidget = new QTabWidget(centralwidget);
        tabWidget.setObjectName("tabWidget");
        tab = new QWidget();
        tab.setObjectName("tab");
        tabWidget.addTab(tab, com.trolltech.qt.core.QCoreApplication.translate("WaktuMainWindow", "Tab 2", null));

        gridLayout_2.addWidget(tabWidget, 0, 0, 1, 1);

        WaktuMainWindow.setCentralWidget(centralwidget);
        menubar = new QMenuBar(WaktuMainWindow);
        menubar.setObjectName("menubar");
        menubar.setGeometry(new QRect(0, 0, 500, 22));
        WaktuMainWindow.setMenuBar(menubar);
        retranslateUi(WaktuMainWindow);

        WaktuMainWindow.connectSlotsByName();
    } // setupUi

    void retranslateUi(QMainWindow WaktuMainWindow)
    {
        WaktuMainWindow.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("WaktuMainWindow", "MainWindow", null));
        tabWidget.setTabText(tabWidget.indexOf(tab), com.trolltech.qt.core.QCoreApplication.translate("WaktuMainWindow", "Tab 2", null));
    } // retranslateUi

}

