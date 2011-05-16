/********************************************************************************
** Form generated from reading ui file 'ProjectWorkSessions.jui'
**
** Created: Mo. Mai 16 09:12:25 2011
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package ch.hsr.waktu.gui.qt.view.projectmanagment;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_ProjectWorkSessions implements com.trolltech.qt.QUiForm<QWidget>
{
    public QGridLayout gridLayout;
    public QComboBox cmbWorkpackage;
    public QComboBox cmbUser;
    public QTableView tblWorkSessions;
    public QDateEdit txtStart;
    public QLabel lblBis;
    public QDateEdit txtEnd;
    public QPushButton btnAddFilter;
    public QPushButton btnRemoveFilter;
    public QLabel lblTotalTimeText;
    public QCheckBox chkFilterDate;
    public QLabel lblTotalTime;

    public Ui_ProjectWorkSessions() { super(); }

    public void setupUi(QWidget ProjectWorkSessions)
    {
        ProjectWorkSessions.setObjectName("ProjectWorkSessions");
        ProjectWorkSessions.resize(new QSize(795, 359).expandedTo(ProjectWorkSessions.minimumSizeHint()));
        gridLayout = new QGridLayout(ProjectWorkSessions);
        gridLayout.setObjectName("gridLayout");
        cmbWorkpackage = new QComboBox(ProjectWorkSessions);
        cmbWorkpackage.setObjectName("cmbWorkpackage");
        QSizePolicy sizePolicy = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy.setHorizontalStretch((byte)0);
        sizePolicy.setVerticalStretch((byte)0);
        sizePolicy.setHeightForWidth(cmbWorkpackage.sizePolicy().hasHeightForWidth());
        cmbWorkpackage.setSizePolicy(sizePolicy);

        gridLayout.addWidget(cmbWorkpackage, 0, 0, 1, 1);

        cmbUser = new QComboBox(ProjectWorkSessions);
        cmbUser.setObjectName("cmbUser");
        QSizePolicy sizePolicy1 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy1.setHorizontalStretch((byte)0);
        sizePolicy1.setVerticalStretch((byte)0);
        sizePolicy1.setHeightForWidth(cmbUser.sizePolicy().hasHeightForWidth());
        cmbUser.setSizePolicy(sizePolicy1);

        gridLayout.addWidget(cmbUser, 0, 1, 1, 1);

        tblWorkSessions = new QTableView(ProjectWorkSessions);
        tblWorkSessions.setObjectName("tblWorkSessions");

        gridLayout.addWidget(tblWorkSessions, 1, 0, 1, 8);

        txtStart = new QDateEdit(ProjectWorkSessions);
        txtStart.setObjectName("txtStart");
        QSizePolicy sizePolicy2 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy2.setHorizontalStretch((byte)0);
        sizePolicy2.setVerticalStretch((byte)0);
        sizePolicy2.setHeightForWidth(txtStart.sizePolicy().hasHeightForWidth());
        txtStart.setSizePolicy(sizePolicy2);
        txtStart.setMinimumSize(new QSize(90, 0));
        txtStart.setMaximumSize(new QSize(90, 16777215));

        gridLayout.addWidget(txtStart, 0, 2, 1, 1);

        lblBis = new QLabel(ProjectWorkSessions);
        lblBis.setObjectName("lblBis");
        QSizePolicy sizePolicy3 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy3.setHorizontalStretch((byte)0);
        sizePolicy3.setVerticalStretch((byte)0);
        sizePolicy3.setHeightForWidth(lblBis.sizePolicy().hasHeightForWidth());
        lblBis.setSizePolicy(sizePolicy3);
        lblBis.setMaximumSize(new QSize(10, 16777215));

        gridLayout.addWidget(lblBis, 0, 3, 1, 1);

        txtEnd = new QDateEdit(ProjectWorkSessions);
        txtEnd.setObjectName("txtEnd");
        QSizePolicy sizePolicy4 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy4.setHorizontalStretch((byte)0);
        sizePolicy4.setVerticalStretch((byte)0);
        sizePolicy4.setHeightForWidth(txtEnd.sizePolicy().hasHeightForWidth());
        txtEnd.setSizePolicy(sizePolicy4);
        txtEnd.setMinimumSize(new QSize(90, 0));
        txtEnd.setMaximumSize(new QSize(90, 16777215));

        gridLayout.addWidget(txtEnd, 0, 4, 1, 1);

        btnAddFilter = new QPushButton(ProjectWorkSessions);
        btnAddFilter.setObjectName("btnAddFilter");
        QSizePolicy sizePolicy5 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy5.setHorizontalStretch((byte)0);
        sizePolicy5.setVerticalStretch((byte)0);
        sizePolicy5.setHeightForWidth(btnAddFilter.sizePolicy().hasHeightForWidth());
        btnAddFilter.setSizePolicy(sizePolicy5);
        btnAddFilter.setMinimumSize(new QSize(100, 0));
        btnAddFilter.setMaximumSize(new QSize(100, 16777215));

        gridLayout.addWidget(btnAddFilter, 0, 6, 1, 1);

        btnRemoveFilter = new QPushButton(ProjectWorkSessions);
        btnRemoveFilter.setObjectName("btnRemoveFilter");
        QSizePolicy sizePolicy6 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy6.setHorizontalStretch((byte)0);
        sizePolicy6.setVerticalStretch((byte)0);
        sizePolicy6.setHeightForWidth(btnRemoveFilter.sizePolicy().hasHeightForWidth());
        btnRemoveFilter.setSizePolicy(sizePolicy6);
        btnRemoveFilter.setMinimumSize(new QSize(110, 0));
        btnRemoveFilter.setMaximumSize(new QSize(110, 16777215));

        gridLayout.addWidget(btnRemoveFilter, 0, 7, 1, 1);

        lblTotalTimeText = new QLabel(ProjectWorkSessions);
        lblTotalTimeText.setObjectName("lblTotalTimeText");
        lblTotalTimeText.setAlignment(com.trolltech.qt.core.Qt.AlignmentFlag.createQFlags(com.trolltech.qt.core.Qt.AlignmentFlag.AlignRight,com.trolltech.qt.core.Qt.AlignmentFlag.AlignVCenter));

        gridLayout.addWidget(lblTotalTimeText, 2, 6, 1, 1);

        chkFilterDate = new QCheckBox(ProjectWorkSessions);
        chkFilterDate.setObjectName("chkFilterDate");
        QSizePolicy sizePolicy7 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy7.setHorizontalStretch((byte)0);
        sizePolicy7.setVerticalStretch((byte)0);
        sizePolicy7.setHeightForWidth(chkFilterDate.sizePolicy().hasHeightForWidth());
        chkFilterDate.setSizePolicy(sizePolicy7);
        chkFilterDate.setMinimumSize(new QSize(90, 0));
        chkFilterDate.setMaximumSize(new QSize(90, 16777215));

        gridLayout.addWidget(chkFilterDate, 0, 5, 1, 1);

        lblTotalTime = new QLabel(ProjectWorkSessions);
        lblTotalTime.setObjectName("lblTotalTime");

        gridLayout.addWidget(lblTotalTime, 2, 7, 1, 1);

        retranslateUi(ProjectWorkSessions);

        ProjectWorkSessions.connectSlotsByName();
    } // setupUi

    void retranslateUi(QWidget ProjectWorkSessions)
    {
        ProjectWorkSessions.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("ProjectWorkSessions", "WorkSessions", null));
        txtStart.setDisplayFormat(com.trolltech.qt.core.QCoreApplication.translate("ProjectWorkSessions", "dd.MM.yy", null));
        lblBis.setText(com.trolltech.qt.core.QCoreApplication.translate("ProjectWorkSessions", "-", null));
        txtEnd.setDisplayFormat(com.trolltech.qt.core.QCoreApplication.translate("ProjectWorkSessions", "dd.MM.yy", null));
        btnAddFilter.setText(com.trolltech.qt.core.QCoreApplication.translate("ProjectWorkSessions", "Add Filter", null));
        btnRemoveFilter.setText(com.trolltech.qt.core.QCoreApplication.translate("ProjectWorkSessions", "Remove Filter", null));
        lblTotalTimeText.setText(com.trolltech.qt.core.QCoreApplication.translate("ProjectWorkSessions", "Total Time:", null));
        chkFilterDate.setText(com.trolltech.qt.core.QCoreApplication.translate("ProjectWorkSessions", "Datefilter?", null));
        lblTotalTime.setText(com.trolltech.qt.core.QCoreApplication.translate("ProjectWorkSessions", "0.0h", null));
    } // retranslateUi

}

