/********************************************************************************
** Form generated from reading ui file 'UserWorkSessions.jui'
**
** Created: Mo. Mai 9 13:41:05 2011
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package ch.hsr.waktu.gui.qt.view.usermanagment;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_UserWorkSessions implements com.trolltech.qt.QUiForm<QWidget>
{
    public QGridLayout gridLayout;
    public QComboBox cmbProject;
    public QComboBox cmbWorkpackage;
    public QTableView tblWorkSessions;
    public QDateEdit txtStart;
    public QLabel lblBis;
    public QDateEdit txtEnd;
    public QPushButton btnAddFilter;
    public QPushButton btnRemoveFilter;
    public QLabel lblTotalTime;
    public QLabel lblTotalTimeText;

    public Ui_UserWorkSessions() { super(); }

    public void setupUi(QWidget UserWorkSessions)
    {
        UserWorkSessions.setObjectName("UserWorkSessions");
        UserWorkSessions.resize(new QSize(650, 358).expandedTo(UserWorkSessions.minimumSizeHint()));
        UserWorkSessions.setMinimumSize(new QSize(650, 0));
        gridLayout = new QGridLayout(UserWorkSessions);
        gridLayout.setObjectName("gridLayout");
        gridLayout.setContentsMargins(12, 0, 0, 0);
        cmbProject = new QComboBox(UserWorkSessions);
        cmbProject.setObjectName("cmbProject");
        QSizePolicy sizePolicy = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy.setHorizontalStretch((byte)0);
        sizePolicy.setVerticalStretch((byte)0);
        sizePolicy.setHeightForWidth(cmbProject.sizePolicy().hasHeightForWidth());
        cmbProject.setSizePolicy(sizePolicy);

        gridLayout.addWidget(cmbProject, 0, 0, 1, 1);

        cmbWorkpackage = new QComboBox(UserWorkSessions);
        cmbWorkpackage.setObjectName("cmbWorkpackage");
        QSizePolicy sizePolicy1 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy1.setHorizontalStretch((byte)0);
        sizePolicy1.setVerticalStretch((byte)0);
        sizePolicy1.setHeightForWidth(cmbWorkpackage.sizePolicy().hasHeightForWidth());
        cmbWorkpackage.setSizePolicy(sizePolicy1);

        gridLayout.addWidget(cmbWorkpackage, 0, 1, 1, 1);

        tblWorkSessions = new QTableView(UserWorkSessions);
        tblWorkSessions.setObjectName("tblWorkSessions");

        gridLayout.addWidget(tblWorkSessions, 1, 0, 1, 7);

        txtStart = new QDateEdit(UserWorkSessions);
        txtStart.setObjectName("txtStart");
        QSizePolicy sizePolicy2 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy2.setHorizontalStretch((byte)0);
        sizePolicy2.setVerticalStretch((byte)0);
        sizePolicy2.setHeightForWidth(txtStart.sizePolicy().hasHeightForWidth());
        txtStart.setSizePolicy(sizePolicy2);
        txtStart.setMaximumSize(new QSize(90, 16777215));

        gridLayout.addWidget(txtStart, 0, 2, 1, 1);

        lblBis = new QLabel(UserWorkSessions);
        lblBis.setObjectName("lblBis");
        QSizePolicy sizePolicy3 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy3.setHorizontalStretch((byte)0);
        sizePolicy3.setVerticalStretch((byte)0);
        sizePolicy3.setHeightForWidth(lblBis.sizePolicy().hasHeightForWidth());
        lblBis.setSizePolicy(sizePolicy3);
        lblBis.setMaximumSize(new QSize(10, 16777215));

        gridLayout.addWidget(lblBis, 0, 3, 1, 1);

        txtEnd = new QDateEdit(UserWorkSessions);
        txtEnd.setObjectName("txtEnd");
        QSizePolicy sizePolicy4 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy4.setHorizontalStretch((byte)0);
        sizePolicy4.setVerticalStretch((byte)0);
        sizePolicy4.setHeightForWidth(txtEnd.sizePolicy().hasHeightForWidth());
        txtEnd.setSizePolicy(sizePolicy4);
        txtEnd.setMaximumSize(new QSize(90, 16777215));

        gridLayout.addWidget(txtEnd, 0, 4, 1, 1);

        btnAddFilter = new QPushButton(UserWorkSessions);
        btnAddFilter.setObjectName("btnAddFilter");

        gridLayout.addWidget(btnAddFilter, 0, 5, 1, 1);

        btnRemoveFilter = new QPushButton(UserWorkSessions);
        btnRemoveFilter.setObjectName("btnRemoveFilter");

        gridLayout.addWidget(btnRemoveFilter, 0, 6, 1, 1);

        lblTotalTime = new QLabel(UserWorkSessions);
        lblTotalTime.setObjectName("lblTotalTime");

        gridLayout.addWidget(lblTotalTime, 2, 6, 1, 1);

        lblTotalTimeText = new QLabel(UserWorkSessions);
        lblTotalTimeText.setObjectName("lblTotalTimeText");
        lblTotalTimeText.setAlignment(com.trolltech.qt.core.Qt.AlignmentFlag.createQFlags(com.trolltech.qt.core.Qt.AlignmentFlag.AlignRight,com.trolltech.qt.core.Qt.AlignmentFlag.AlignVCenter));

        gridLayout.addWidget(lblTotalTimeText, 2, 5, 1, 1);

        retranslateUi(UserWorkSessions);

        UserWorkSessions.connectSlotsByName();
    } // setupUi

    void retranslateUi(QWidget UserWorkSessions)
    {
        UserWorkSessions.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("UserWorkSessions", "WorkSessions", null));
        lblBis.setText(com.trolltech.qt.core.QCoreApplication.translate("UserWorkSessions", "-", null));
        btnAddFilter.setText(com.trolltech.qt.core.QCoreApplication.translate("UserWorkSessions", "Add Filter", null));
        btnRemoveFilter.setText(com.trolltech.qt.core.QCoreApplication.translate("UserWorkSessions", "Remove Filter", null));
        lblTotalTime.setText(com.trolltech.qt.core.QCoreApplication.translate("UserWorkSessions", "0.0h", null));
        lblTotalTimeText.setText(com.trolltech.qt.core.QCoreApplication.translate("UserWorkSessions", "Total Time:", null));
    } // retranslateUi

}

