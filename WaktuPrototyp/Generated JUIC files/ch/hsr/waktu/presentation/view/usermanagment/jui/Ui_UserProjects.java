/********************************************************************************
** Form generated from reading ui file 'UserProjects.jui'
**
** Created: Mi 4. Mai 08:15:29 2011
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package ch.hsr.waktu.presentation.view.usermanagment.jui;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_UserProjects implements com.trolltech.qt.QUiForm<QWidget>
{
    public QGridLayout gridLayout;
    public QTableView tblProjects;
    public QComboBox cmbProjects;
    public QPushButton btnAdd;

    public Ui_UserProjects() { super(); }

    public void setupUi(QWidget UserProjects)
    {
        UserProjects.setObjectName("UserProjects");
        UserProjects.resize(new QSize(650, 338).expandedTo(UserProjects.minimumSizeHint()));
        UserProjects.setMinimumSize(new QSize(650, 0));
        gridLayout = new QGridLayout(UserProjects);
        gridLayout.setObjectName("gridLayout");
        gridLayout.setContentsMargins(12, 0, 0, 0);
        tblProjects = new QTableView(UserProjects);
        tblProjects.setObjectName("tblProjects");

        gridLayout.addWidget(tblProjects, 0, 0, 1, 2);

        cmbProjects = new QComboBox(UserProjects);
        cmbProjects.setObjectName("cmbProjects");

        gridLayout.addWidget(cmbProjects, 1, 0, 1, 1);

        btnAdd = new QPushButton(UserProjects);
        btnAdd.setObjectName("btnAdd");
        QSizePolicy sizePolicy = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy.setHorizontalStretch((byte)0);
        sizePolicy.setVerticalStretch((byte)0);
        sizePolicy.setHeightForWidth(btnAdd.sizePolicy().hasHeightForWidth());
        btnAdd.setSizePolicy(sizePolicy);

        gridLayout.addWidget(btnAdd, 1, 1, 1, 1);

        retranslateUi(UserProjects);

        UserProjects.connectSlotsByName();
    } // setupUi

    void retranslateUi(QWidget UserProjects)
    {
        UserProjects.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("UserProjects", "UserProjects", null));
        btnAdd.setText(com.trolltech.qt.core.QCoreApplication.translate("UserProjects", "Add", null));
    } // retranslateUi

}

