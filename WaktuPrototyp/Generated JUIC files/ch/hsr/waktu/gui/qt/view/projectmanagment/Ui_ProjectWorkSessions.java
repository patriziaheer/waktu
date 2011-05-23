/********************************************************************************
** Form generated from reading ui file 'ProjectWorkSessions.jui'
**
** Created: Mo. Mai 23 11:18:32 2011
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
    public QHBoxLayout horizontalLayout;
    public QDateEdit txtStart;
    public QLabel lblBis;
    public QDateEdit txtEnd;
    public QCheckBox chkFilterDate;
    public QPushButton btnAddFilter;
    public QPushButton btnRemoveFilter;
    public QLabel lblTotalTimeText;
    public QLabel lblTotalTime;

    public Ui_ProjectWorkSessions() { super(); }

    public void setupUi(QWidget ProjectWorkSessions)
    {
        ProjectWorkSessions.setObjectName("ProjectWorkSessions");
        ProjectWorkSessions.resize(new QSize(800, 360).expandedTo(ProjectWorkSessions.minimumSizeHint()));
        QSizePolicy sizePolicy = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);
        sizePolicy.setHorizontalStretch((byte)0);
        sizePolicy.setVerticalStretch((byte)0);
        sizePolicy.setHeightForWidth(ProjectWorkSessions.sizePolicy().hasHeightForWidth());
        ProjectWorkSessions.setSizePolicy(sizePolicy);
        ProjectWorkSessions.setMinimumSize(new QSize(800, 360));
        gridLayout = new QGridLayout(ProjectWorkSessions);
        gridLayout.setObjectName("gridLayout");
        cmbWorkpackage = new QComboBox(ProjectWorkSessions);
        cmbWorkpackage.setObjectName("cmbWorkpackage");
        QSizePolicy sizePolicy1 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy1.setHorizontalStretch((byte)0);
        sizePolicy1.setVerticalStretch((byte)0);
        sizePolicy1.setHeightForWidth(cmbWorkpackage.sizePolicy().hasHeightForWidth());
        cmbWorkpackage.setSizePolicy(sizePolicy1);
        cmbWorkpackage.setMinimumSize(new QSize(50, 0));
        cmbWorkpackage.setMaximumSize(new QSize(16777215, 16777215));

        gridLayout.addWidget(cmbWorkpackage, 0, 0, 1, 1);

        cmbUser = new QComboBox(ProjectWorkSessions);
        cmbUser.setObjectName("cmbUser");
        QSizePolicy sizePolicy2 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy2.setHorizontalStretch((byte)0);
        sizePolicy2.setVerticalStretch((byte)0);
        sizePolicy2.setHeightForWidth(cmbUser.sizePolicy().hasHeightForWidth());
        cmbUser.setSizePolicy(sizePolicy2);
        cmbUser.setMinimumSize(new QSize(50, 0));

        gridLayout.addWidget(cmbUser, 0, 1, 1, 1);

        tblWorkSessions = new QTableView(ProjectWorkSessions);
        tblWorkSessions.setObjectName("tblWorkSessions");

        gridLayout.addWidget(tblWorkSessions, 1, 0, 1, 6);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout.setObjectName("horizontalLayout");
        txtStart = new QDateEdit(ProjectWorkSessions);
        txtStart.setObjectName("txtStart");
        QSizePolicy sizePolicy3 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy3.setHorizontalStretch((byte)0);
        sizePolicy3.setVerticalStretch((byte)0);
        sizePolicy3.setHeightForWidth(txtStart.sizePolicy().hasHeightForWidth());
        txtStart.setSizePolicy(sizePolicy3);
        txtStart.setMinimumSize(new QSize(80, 0));
        txtStart.setMaximumSize(new QSize(80, 16777215));

        horizontalLayout.addWidget(txtStart);

        lblBis = new QLabel(ProjectWorkSessions);
        lblBis.setObjectName("lblBis");
        QSizePolicy sizePolicy4 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy4.setHorizontalStretch((byte)0);
        sizePolicy4.setVerticalStretch((byte)0);
        sizePolicy4.setHeightForWidth(lblBis.sizePolicy().hasHeightForWidth());
        lblBis.setSizePolicy(sizePolicy4);
        lblBis.setMaximumSize(new QSize(10, 16777215));

        horizontalLayout.addWidget(lblBis);

        txtEnd = new QDateEdit(ProjectWorkSessions);
        txtEnd.setObjectName("txtEnd");
        QSizePolicy sizePolicy5 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy5.setHorizontalStretch((byte)0);
        sizePolicy5.setVerticalStretch((byte)0);
        sizePolicy5.setHeightForWidth(txtEnd.sizePolicy().hasHeightForWidth());
        txtEnd.setSizePolicy(sizePolicy5);
        txtEnd.setMinimumSize(new QSize(80, 0));
        txtEnd.setMaximumSize(new QSize(80, 16777215));

        horizontalLayout.addWidget(txtEnd);

        chkFilterDate = new QCheckBox(ProjectWorkSessions);
        chkFilterDate.setObjectName("chkFilterDate");
        QSizePolicy sizePolicy6 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy6.setHorizontalStretch((byte)0);
        sizePolicy6.setVerticalStretch((byte)0);
        sizePolicy6.setHeightForWidth(chkFilterDate.sizePolicy().hasHeightForWidth());
        chkFilterDate.setSizePolicy(sizePolicy6);
        chkFilterDate.setMinimumSize(new QSize(90, 0));
        chkFilterDate.setMaximumSize(new QSize(90, 16777215));

        horizontalLayout.addWidget(chkFilterDate);


        gridLayout.addLayout(horizontalLayout, 0, 2, 1, 2);

        btnAddFilter = new QPushButton(ProjectWorkSessions);
        btnAddFilter.setObjectName("btnAddFilter");
        QSizePolicy sizePolicy7 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy7.setHorizontalStretch((byte)0);
        sizePolicy7.setVerticalStretch((byte)0);
        sizePolicy7.setHeightForWidth(btnAddFilter.sizePolicy().hasHeightForWidth());
        btnAddFilter.setSizePolicy(sizePolicy7);
        btnAddFilter.setMinimumSize(new QSize(100, 0));
        btnAddFilter.setMaximumSize(new QSize(100, 16777215));

        gridLayout.addWidget(btnAddFilter, 0, 4, 1, 1);

        btnRemoveFilter = new QPushButton(ProjectWorkSessions);
        btnRemoveFilter.setObjectName("btnRemoveFilter");
        QSizePolicy sizePolicy8 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy8.setHorizontalStretch((byte)0);
        sizePolicy8.setVerticalStretch((byte)0);
        sizePolicy8.setHeightForWidth(btnRemoveFilter.sizePolicy().hasHeightForWidth());
        btnRemoveFilter.setSizePolicy(sizePolicy8);
        btnRemoveFilter.setMinimumSize(new QSize(110, 0));
        btnRemoveFilter.setMaximumSize(new QSize(110, 16777215));

        gridLayout.addWidget(btnRemoveFilter, 0, 5, 1, 1);

        lblTotalTimeText = new QLabel(ProjectWorkSessions);
        lblTotalTimeText.setObjectName("lblTotalTimeText");
        lblTotalTimeText.setAlignment(com.trolltech.qt.core.Qt.AlignmentFlag.createQFlags(com.trolltech.qt.core.Qt.AlignmentFlag.AlignRight,com.trolltech.qt.core.Qt.AlignmentFlag.AlignVCenter));

        gridLayout.addWidget(lblTotalTimeText, 2, 4, 1, 1);

        lblTotalTime = new QLabel(ProjectWorkSessions);
        lblTotalTime.setObjectName("lblTotalTime");

        gridLayout.addWidget(lblTotalTime, 2, 5, 1, 1);

        retranslateUi(ProjectWorkSessions);

        ProjectWorkSessions.connectSlotsByName();
    } // setupUi

    void retranslateUi(QWidget ProjectWorkSessions)
    {
        ProjectWorkSessions.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("ProjectWorkSessions", "WorkSessions", null));
        txtStart.setDisplayFormat(com.trolltech.qt.core.QCoreApplication.translate("ProjectWorkSessions", "dd.MM.yy", null));
        lblBis.setText(com.trolltech.qt.core.QCoreApplication.translate("ProjectWorkSessions", "-", null));
        txtEnd.setDisplayFormat(com.trolltech.qt.core.QCoreApplication.translate("ProjectWorkSessions", "dd.MM.yy", null));
        chkFilterDate.setText(com.trolltech.qt.core.QCoreApplication.translate("ProjectWorkSessions", "Datefilter", null));
        btnAddFilter.setText(com.trolltech.qt.core.QCoreApplication.translate("ProjectWorkSessions", "Add Filter", null));
        btnRemoveFilter.setText(com.trolltech.qt.core.QCoreApplication.translate("ProjectWorkSessions", "Remove Filter", null));
        lblTotalTimeText.setText(com.trolltech.qt.core.QCoreApplication.translate("ProjectWorkSessions", "Total Time:", null));
        lblTotalTime.setText(com.trolltech.qt.core.QCoreApplication.translate("ProjectWorkSessions", "0.0h", null));
    } // retranslateUi

}

