/********************************************************************************
** Form generated from reading ui file 'UserData.jui'
**
** Created: Do 7. Apr 10:12:24 2011
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package ch.hsr.waktu.presentation.view.usermanagment.jui;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_UserData implements com.trolltech.qt.QUiForm<QWidget>
{
    public QGridLayout gridLayout;
    public QLabel lblName;
    public QLineEdit txtName;
    public QLabel lblFirstname;
    public QLineEdit txtFirstname;
    public QLabel lblPensum;
    public QSpinBox txtPensum;
    public QLabel lblRole;
    public QComboBox cmbRole;
    public QLabel lblHolidaysText2;
    public QGroupBox grpOverview;
    public QGridLayout gridLayout_5;
    public QLabel lblPlannedDayText;
    public QLabel lblOvertimeText;
    public QLabel lblHolidaysText;
    public QLabel lblPlannedDay;
    public QLabel lblOvertime;
    public QLabel lblHolidays;
    public QPushButton btnAdd;
    public QSpacerItem horizontalSpacer;
    public QDoubleSpinBox txtHolidays;
    public QSpacerItem verticalSpacer;
    public QCheckBox checkBox;

    public Ui_UserData() { super(); }

    public void setupUi(QWidget UserData)
    {
        UserData.setObjectName("UserData");
        UserData.resize(new QSize(650, 338).expandedTo(UserData.minimumSizeHint()));
        UserData.setMinimumSize(new QSize(650, 0));
        gridLayout = new QGridLayout(UserData);
        gridLayout.setObjectName("gridLayout");
        gridLayout.setContentsMargins(12, 0, 0, 0);
        lblName = new QLabel(UserData);
        lblName.setObjectName("lblName");

        gridLayout.addWidget(lblName, 0, 0, 1, 1);

        txtName = new QLineEdit(UserData);
        txtName.setObjectName("txtName");

        gridLayout.addWidget(txtName, 0, 1, 1, 3);

        lblFirstname = new QLabel(UserData);
        lblFirstname.setObjectName("lblFirstname");

        gridLayout.addWidget(lblFirstname, 1, 0, 1, 1);

        txtFirstname = new QLineEdit(UserData);
        txtFirstname.setObjectName("txtFirstname");

        gridLayout.addWidget(txtFirstname, 1, 1, 1, 3);

        lblPensum = new QLabel(UserData);
        lblPensum.setObjectName("lblPensum");

        gridLayout.addWidget(lblPensum, 2, 0, 1, 1);

        txtPensum = new QSpinBox(UserData);
        txtPensum.setObjectName("txtPensum");

        gridLayout.addWidget(txtPensum, 2, 1, 1, 3);

        lblRole = new QLabel(UserData);
        lblRole.setObjectName("lblRole");

        gridLayout.addWidget(lblRole, 3, 0, 1, 1);

        cmbRole = new QComboBox(UserData);
        cmbRole.setObjectName("cmbRole");

        gridLayout.addWidget(cmbRole, 3, 1, 1, 3);

        lblHolidaysText2 = new QLabel(UserData);
        lblHolidaysText2.setObjectName("lblHolidaysText2");

        gridLayout.addWidget(lblHolidaysText2, 4, 0, 1, 1);

        grpOverview = new QGroupBox(UserData);
        grpOverview.setObjectName("grpOverview");
        grpOverview.setMinimumSize(new QSize(650, 0));
        gridLayout_5 = new QGridLayout(grpOverview);
        gridLayout_5.setObjectName("gridLayout_5");
        lblPlannedDayText = new QLabel(grpOverview);
        lblPlannedDayText.setObjectName("lblPlannedDayText");

        gridLayout_5.addWidget(lblPlannedDayText, 0, 0, 1, 1);

        lblOvertimeText = new QLabel(grpOverview);
        lblOvertimeText.setObjectName("lblOvertimeText");

        gridLayout_5.addWidget(lblOvertimeText, 0, 2, 1, 1);

        lblHolidaysText = new QLabel(grpOverview);
        lblHolidaysText.setObjectName("lblHolidaysText");

        gridLayout_5.addWidget(lblHolidaysText, 1, 2, 1, 1);

        lblPlannedDay = new QLabel(grpOverview);
        lblPlannedDay.setObjectName("lblPlannedDay");

        gridLayout_5.addWidget(lblPlannedDay, 0, 1, 1, 1);

        lblOvertime = new QLabel(grpOverview);
        lblOvertime.setObjectName("lblOvertime");

        gridLayout_5.addWidget(lblOvertime, 0, 3, 1, 1);

        lblHolidays = new QLabel(grpOverview);
        lblHolidays.setObjectName("lblHolidays");

        gridLayout_5.addWidget(lblHolidays, 1, 3, 1, 1);


        gridLayout.addWidget(grpOverview, 8, 0, 1, 4);

        btnAdd = new QPushButton(UserData);
        btnAdd.setObjectName("btnAdd");

        gridLayout.addWidget(btnAdd, 6, 3, 1, 1);

        horizontalSpacer = new QSpacerItem(40, 20, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);

        gridLayout.addItem(horizontalSpacer, 6, 1, 1, 2);

        txtHolidays = new QDoubleSpinBox(UserData);
        txtHolidays.setObjectName("txtHolidays");

        gridLayout.addWidget(txtHolidays, 4, 1, 1, 3);

        verticalSpacer = new QSpacerItem(20, 40, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);

        gridLayout.addItem(verticalSpacer, 7, 2, 1, 1);

        checkBox = new QCheckBox(UserData);
        checkBox.setObjectName("checkBox");

        gridLayout.addWidget(checkBox, 5, 1, 1, 1);

        retranslateUi(UserData);

        UserData.connectSlotsByName();
    } // setupUi

    void retranslateUi(QWidget UserData)
    {
        UserData.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("UserData", "UserData", null));
        lblName.setText(com.trolltech.qt.core.QCoreApplication.translate("UserData", "Name:", null));
        lblFirstname.setText(com.trolltech.qt.core.QCoreApplication.translate("UserData", "FIrstname:", null));
        lblPensum.setText(com.trolltech.qt.core.QCoreApplication.translate("UserData", "Pensum:", null));
        lblRole.setText(com.trolltech.qt.core.QCoreApplication.translate("UserData", "Role:", null));
        lblHolidaysText2.setText(com.trolltech.qt.core.QCoreApplication.translate("UserData", "Holidays:", null));
        grpOverview.setTitle(com.trolltech.qt.core.QCoreApplication.translate("UserData", "Overview", null));
        lblPlannedDayText.setText(com.trolltech.qt.core.QCoreApplication.translate("UserData", "Worktime per day:", null));
        lblOvertimeText.setText(com.trolltech.qt.core.QCoreApplication.translate("UserData", "Overtime:", null));
        lblHolidaysText.setText(com.trolltech.qt.core.QCoreApplication.translate("UserData", "Holidays", null));
        lblPlannedDay.setText(com.trolltech.qt.core.QCoreApplication.translate("UserData", "8.2h", null));
        lblOvertime.setText(com.trolltech.qt.core.QCoreApplication.translate("UserData", "2.4h", null));
        lblHolidays.setText(com.trolltech.qt.core.QCoreApplication.translate("UserData", "12.3d", null));
        btnAdd.setText(com.trolltech.qt.core.QCoreApplication.translate("UserData", "add", null));
        checkBox.setText(com.trolltech.qt.core.QCoreApplication.translate("UserData", "Inactiv", null));
    } // retranslateUi

}

