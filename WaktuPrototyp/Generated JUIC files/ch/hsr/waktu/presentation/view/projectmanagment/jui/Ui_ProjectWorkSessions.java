/********************************************************************************
** Form generated from reading ui file 'ProjectWorkSessions.jui'
**
** Created: Mi 4. Mai 08:15:29 2011
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package ch.hsr.waktu.presentation.view.projectmanagment.jui;

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
    public QLabel lblTotalTimeText;
    public QLabel lblTotalTime;

    public Ui_ProjectWorkSessions() { super(); }

    public void setupUi(QWidget ProjectWorkSessions)
    {
        ProjectWorkSessions.setObjectName("ProjectWorkSessions");
        ProjectWorkSessions.resize(new QSize(649, 358).expandedTo(ProjectWorkSessions.minimumSizeHint()));
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

        gridLayout.addWidget(tblWorkSessions, 1, 0, 1, 5);

        txtStart = new QDateEdit(ProjectWorkSessions);
        txtStart.setObjectName("txtStart");
        QSizePolicy sizePolicy2 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy2.setHorizontalStretch((byte)0);
        sizePolicy2.setVerticalStretch((byte)0);
        sizePolicy2.setHeightForWidth(txtStart.sizePolicy().hasHeightForWidth());
        txtStart.setSizePolicy(sizePolicy2);
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
        txtEnd.setMaximumSize(new QSize(90, 16777215));

        gridLayout.addWidget(txtEnd, 0, 4, 1, 1);

        lblTotalTimeText = new QLabel(ProjectWorkSessions);
        lblTotalTimeText.setObjectName("lblTotalTimeText");
        lblTotalTimeText.setAlignment(com.trolltech.qt.core.Qt.AlignmentFlag.createQFlags(com.trolltech.qt.core.Qt.AlignmentFlag.AlignRight,com.trolltech.qt.core.Qt.AlignmentFlag.AlignVCenter));

        gridLayout.addWidget(lblTotalTimeText, 2, 2, 1, 1);

        lblTotalTime = new QLabel(ProjectWorkSessions);
        lblTotalTime.setObjectName("lblTotalTime");

        gridLayout.addWidget(lblTotalTime, 2, 4, 1, 1);

        retranslateUi(ProjectWorkSessions);

        ProjectWorkSessions.connectSlotsByName();
    } // setupUi

    void retranslateUi(QWidget ProjectWorkSessions)
    {
        ProjectWorkSessions.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("ProjectWorkSessions", "WorkSessions", null));
        lblBis.setText(com.trolltech.qt.core.QCoreApplication.translate("ProjectWorkSessions", "-", null));
        lblTotalTimeText.setText(com.trolltech.qt.core.QCoreApplication.translate("ProjectWorkSessions", "Total Time:", null));
        lblTotalTime.setText(com.trolltech.qt.core.QCoreApplication.translate("ProjectWorkSessions", "0.0h", null));
    } // retranslateUi

}

