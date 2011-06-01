/********************************************************************************
 ** Form generated from reading ui file 'UserWorkSessions.jui'
 **
 ** Created: Mo 30. Mai 13:11:13 2011
 **      by: Qt User Interface Compiler version 4.5.2
 **
 ** WARNING! All changes made in this file will be lost when recompiling ui file!
 ********************************************************************************/

package ch.hsr.waktu.gui.qt.view.usermanagment;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_UserWorkSessions implements com.trolltech.qt.QUiForm<QWidget> {
    public QGridLayout gridLayout;
    public QComboBox cmbProject;
    public QComboBox cmbWorkpackage;
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

    public Ui_UserWorkSessions() {
        super();
    }

    public void setupUi(QWidget UserWorkSessions) {
        UserWorkSessions.setObjectName("UserWorkSessions");
        UserWorkSessions.resize(new QSize(700, 360).expandedTo(UserWorkSessions
                .minimumSizeHint()));
        QSizePolicy sizePolicy = new QSizePolicy(
                com.trolltech.qt.gui.QSizePolicy.Policy.Minimum,
                com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);
        sizePolicy.setHorizontalStretch((byte) 0);
        sizePolicy.setVerticalStretch((byte) 0);
        sizePolicy.setHeightForWidth(UserWorkSessions.sizePolicy()
                .hasHeightForWidth());
        UserWorkSessions.setSizePolicy(sizePolicy);
        UserWorkSessions.setMinimumSize(new QSize(700, 360));
        UserWorkSessions
                .setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.TabFocus);
        gridLayout = new QGridLayout(UserWorkSessions);
        gridLayout.setMargin(12);
        gridLayout.setObjectName("gridLayout");
        cmbProject = new QComboBox(UserWorkSessions);
        cmbProject.setObjectName("cmbProject");
        QSizePolicy sizePolicy1 = new QSizePolicy(
                com.trolltech.qt.gui.QSizePolicy.Policy.Fixed,
                com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy1.setHorizontalStretch((byte) 0);
        sizePolicy1.setVerticalStretch((byte) 0);
        sizePolicy1.setHeightForWidth(cmbProject.sizePolicy()
                .hasHeightForWidth());
        cmbProject.setSizePolicy(sizePolicy1);
        cmbProject.setMinimumSize(new QSize(50, 0));

        gridLayout.addWidget(cmbProject, 0, 0, 1, 1);

        cmbWorkpackage = new QComboBox(UserWorkSessions);
        cmbWorkpackage.setObjectName("cmbWorkpackage");
        QSizePolicy sizePolicy2 = new QSizePolicy(
                com.trolltech.qt.gui.QSizePolicy.Policy.Fixed,
                com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy2.setHorizontalStretch((byte) 0);
        sizePolicy2.setVerticalStretch((byte) 0);
        sizePolicy2.setHeightForWidth(cmbWorkpackage.sizePolicy()
                .hasHeightForWidth());
        cmbWorkpackage.setSizePolicy(sizePolicy2);
        cmbWorkpackage.setMinimumSize(new QSize(50, 0));

        gridLayout.addWidget(cmbWorkpackage, 0, 1, 1, 1);

        tblWorkSessions = new QTableView(UserWorkSessions);
        tblWorkSessions.setObjectName("tblWorkSessions");

        gridLayout.addWidget(tblWorkSessions, 1, 0, 1, 5);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout.setObjectName("horizontalLayout");
        txtStart = new QDateEdit(UserWorkSessions);
        txtStart.setObjectName("txtStart");
        QSizePolicy sizePolicy3 = new QSizePolicy(
                com.trolltech.qt.gui.QSizePolicy.Policy.Fixed,
                com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy3.setHorizontalStretch((byte) 0);
        sizePolicy3.setVerticalStretch((byte) 0);
        sizePolicy3
                .setHeightForWidth(txtStart.sizePolicy().hasHeightForWidth());
        txtStart.setSizePolicy(sizePolicy3);
        txtStart.setMinimumSize(new QSize(80, 0));
        txtStart.setMaximumSize(new QSize(80, 16777215));

        horizontalLayout.addWidget(txtStart);

        lblBis = new QLabel(UserWorkSessions);
        lblBis.setObjectName("lblBis");
        QSizePolicy sizePolicy4 = new QSizePolicy(
                com.trolltech.qt.gui.QSizePolicy.Policy.Fixed,
                com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy4.setHorizontalStretch((byte) 0);
        sizePolicy4.setVerticalStretch((byte) 0);
        sizePolicy4.setHeightForWidth(lblBis.sizePolicy().hasHeightForWidth());
        lblBis.setSizePolicy(sizePolicy4);
        lblBis.setMaximumSize(new QSize(10, 16777215));

        horizontalLayout.addWidget(lblBis);

        txtEnd = new QDateEdit(UserWorkSessions);
        txtEnd.setObjectName("txtEnd");
        QSizePolicy sizePolicy5 = new QSizePolicy(
                com.trolltech.qt.gui.QSizePolicy.Policy.Fixed,
                com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy5.setHorizontalStretch((byte) 0);
        sizePolicy5.setVerticalStretch((byte) 0);
        sizePolicy5.setHeightForWidth(txtEnd.sizePolicy().hasHeightForWidth());
        txtEnd.setSizePolicy(sizePolicy5);
        txtEnd.setMinimumSize(new QSize(80, 0));
        txtEnd.setMaximumSize(new QSize(80, 16777215));

        horizontalLayout.addWidget(txtEnd);

        chkFilterDate = new QCheckBox(UserWorkSessions);
        chkFilterDate.setObjectName("chkFilterDate");

        horizontalLayout.addWidget(chkFilterDate);

        gridLayout.addLayout(horizontalLayout, 0, 2, 1, 1);

        btnAddFilter = new QPushButton(UserWorkSessions);
        btnAddFilter.setObjectName("btnAddFilter");
        btnAddFilter.setDefault(true);

        gridLayout.addWidget(btnAddFilter, 0, 3, 1, 1);

        btnRemoveFilter = new QPushButton(UserWorkSessions);
        btnRemoveFilter.setObjectName("btnRemoveFilter");

        gridLayout.addWidget(btnRemoveFilter, 0, 4, 1, 1);

        lblTotalTimeText = new QLabel(UserWorkSessions);
        lblTotalTimeText.setObjectName("lblTotalTimeText");
        lblTotalTimeText.setAlignment(com.trolltech.qt.core.Qt.AlignmentFlag
                .createQFlags(
                        com.trolltech.qt.core.Qt.AlignmentFlag.AlignRight,
                        com.trolltech.qt.core.Qt.AlignmentFlag.AlignVCenter));

        gridLayout.addWidget(lblTotalTimeText, 2, 3, 1, 1);

        lblTotalTime = new QLabel(UserWorkSessions);
        lblTotalTime.setObjectName("lblTotalTime");

        gridLayout.addWidget(lblTotalTime, 2, 4, 1, 1);

        QWidget.setTabOrder(cmbProject, cmbWorkpackage);
        QWidget.setTabOrder(cmbWorkpackage, txtStart);
        QWidget.setTabOrder(txtStart, txtEnd);
        QWidget.setTabOrder(txtEnd, chkFilterDate);
        QWidget.setTabOrder(chkFilterDate, btnAddFilter);
        QWidget.setTabOrder(btnAddFilter, btnRemoveFilter);
        QWidget.setTabOrder(btnRemoveFilter, tblWorkSessions);
        retranslateUi(UserWorkSessions);

        UserWorkSessions.connectSlotsByName();
    } // setupUi

    void retranslateUi(QWidget UserWorkSessions) {
        UserWorkSessions.setWindowTitle(com.trolltech.qt.core.QCoreApplication
                .translate("UserWorkSessions", "WorkSessions", null));
        lblBis.setText(com.trolltech.qt.core.QCoreApplication.translate(
                "UserWorkSessions", "-", null));
        chkFilterDate.setText(com.trolltech.qt.core.QCoreApplication.translate(
                "UserWorkSessions", "Datefilter", null));
        btnAddFilter.setText(com.trolltech.qt.core.QCoreApplication.translate(
                "UserWorkSessions", "Add Filter", null));
        btnRemoveFilter.setText(com.trolltech.qt.core.QCoreApplication
                .translate("UserWorkSessions", "Remove Filter", null));
        lblTotalTimeText.setText(com.trolltech.qt.core.QCoreApplication
                .translate("UserWorkSessions", "Total Time:", null));
        lblTotalTime.setText(com.trolltech.qt.core.QCoreApplication.translate(
                "UserWorkSessions", "0.0h", null));
    } // retranslateUi

}
