/********************************************************************************
** Form generated from reading ui file 'ProjectStaff.jui'
**
** Created: Di. Mai 3 11:46:33 2011
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package ch.hsr.waktu.presentation.view.projectmanagment.jui;

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
        ProjectStaff.resize(new QSize(506, 338).expandedTo(ProjectStaff.minimumSizeHint()));
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
        QSizePolicy sizePolicy = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy.setHorizontalStretch((byte)0);
        sizePolicy.setVerticalStretch((byte)0);
        sizePolicy.setHeightForWidth(btnAdd.sizePolicy().hasHeightForWidth());
        btnAdd.setSizePolicy(sizePolicy);

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

