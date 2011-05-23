/********************************************************************************
** Form generated from reading ui file 'ProjectStaff.jui'
**
** Created: Mo. Mai 23 11:05:20 2011
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package ch.hsr.waktu.gui.qt.view.projectmanagment;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_ProjectStaff implements com.trolltech.qt.QUiForm<QWidget>
{
    public QGridLayout gridLayout;
    public QTableView tblWorkStaff;
    public QComboBox cmbUsers;
    public QPushButton btnAdd;

    public Ui_ProjectStaff() { super(); }

    public void setupUi(QWidget ProjectStaff)
    {
        ProjectStaff.setObjectName("ProjectStaff");
        ProjectStaff.resize(new QSize(800, 360).expandedTo(ProjectStaff.minimumSizeHint()));
        QSizePolicy sizePolicy = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);
        sizePolicy.setHorizontalStretch((byte)0);
        sizePolicy.setVerticalStretch((byte)0);
        sizePolicy.setHeightForWidth(ProjectStaff.sizePolicy().hasHeightForWidth());
        ProjectStaff.setSizePolicy(sizePolicy);
        ProjectStaff.setMinimumSize(new QSize(800, 360));
        gridLayout = new QGridLayout(ProjectStaff);
        gridLayout.setObjectName("gridLayout");
        tblWorkStaff = new QTableView(ProjectStaff);
        tblWorkStaff.setObjectName("tblWorkStaff");

        gridLayout.addWidget(tblWorkStaff, 0, 0, 1, 2);

        cmbUsers = new QComboBox(ProjectStaff);
        cmbUsers.setObjectName("cmbUsers");

        gridLayout.addWidget(cmbUsers, 1, 0, 1, 1);

        btnAdd = new QPushButton(ProjectStaff);
        btnAdd.setObjectName("btnAdd");
        QSizePolicy sizePolicy1 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy1.setHorizontalStretch((byte)0);
        sizePolicy1.setVerticalStretch((byte)0);
        sizePolicy1.setHeightForWidth(btnAdd.sizePolicy().hasHeightForWidth());
        btnAdd.setSizePolicy(sizePolicy1);

        gridLayout.addWidget(btnAdd, 1, 1, 1, 1);

        retranslateUi(ProjectStaff);

        ProjectStaff.connectSlotsByName();
    } // setupUi

    void retranslateUi(QWidget ProjectStaff)
    {
        ProjectStaff.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("ProjectStaff", "WorkPackage", null));
        btnAdd.setText(com.trolltech.qt.core.QCoreApplication.translate("ProjectStaff", "Add", null));
    } // retranslateUi

}

