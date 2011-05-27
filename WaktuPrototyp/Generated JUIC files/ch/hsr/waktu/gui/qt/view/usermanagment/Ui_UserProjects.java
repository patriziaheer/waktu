/********************************************************************************
** Form generated from reading ui file 'UserProjects.jui'
**
** Created: Do 26. Mai 07:35:08 2011
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package ch.hsr.waktu.gui.qt.view.usermanagment;

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
        UserProjects.resize(new QSize(800, 360).expandedTo(UserProjects.minimumSizeHint()));
        QSizePolicy sizePolicy = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);
        sizePolicy.setHorizontalStretch((byte)0);
        sizePolicy.setVerticalStretch((byte)0);
        sizePolicy.setHeightForWidth(UserProjects.sizePolicy().hasHeightForWidth());
        UserProjects.setSizePolicy(sizePolicy);
        UserProjects.setMinimumSize(new QSize(800, 360));
        UserProjects.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.TabFocus);
        gridLayout = new QGridLayout(UserProjects);
        gridLayout.setMargin(12);
        gridLayout.setObjectName("gridLayout");
        tblProjects = new QTableView(UserProjects);
        tblProjects.setObjectName("tblProjects");

        gridLayout.addWidget(tblProjects, 0, 0, 1, 2);

        cmbProjects = new QComboBox(UserProjects);
        cmbProjects.setObjectName("cmbProjects");

        gridLayout.addWidget(cmbProjects, 1, 0, 1, 1);

        btnAdd = new QPushButton(UserProjects);
        btnAdd.setObjectName("btnAdd");
        QSizePolicy sizePolicy1 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy1.setHorizontalStretch((byte)0);
        sizePolicy1.setVerticalStretch((byte)0);
        sizePolicy1.setHeightForWidth(btnAdd.sizePolicy().hasHeightForWidth());
        btnAdd.setSizePolicy(sizePolicy1);
        btnAdd.setDefault(true);

        gridLayout.addWidget(btnAdd, 1, 1, 1, 1);

        QWidget.setTabOrder(tblProjects, cmbProjects);
        QWidget.setTabOrder(cmbProjects, btnAdd);
        retranslateUi(UserProjects);

        UserProjects.connectSlotsByName();
    } // setupUi

    void retranslateUi(QWidget UserProjects)
    {
        UserProjects.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("UserProjects", "UserProjects", null));
        btnAdd.setText(com.trolltech.qt.core.QCoreApplication.translate("UserProjects", "Add", null));
    } // retranslateUi

}

