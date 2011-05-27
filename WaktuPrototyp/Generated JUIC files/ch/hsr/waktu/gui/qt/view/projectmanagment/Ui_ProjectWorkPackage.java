/********************************************************************************
** Form generated from reading ui file 'ProjectWorkPackage.jui'
**
** Created: Fr. Mai 27 11:08:26 2011
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package ch.hsr.waktu.gui.qt.view.projectmanagment;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_ProjectWorkPackage implements com.trolltech.qt.QUiForm<QWidget>
{
    public QGridLayout gridLayout;
    public QTableView tblWorkPackages;
    public QLineEdit txtDescription;
    public QPushButton btnAdd;

    public Ui_ProjectWorkPackage() { super(); }

    public void setupUi(QWidget ProjectWorkPackage)
    {
        ProjectWorkPackage.setObjectName("ProjectWorkPackage");
        ProjectWorkPackage.resize(new QSize(700, 363).expandedTo(ProjectWorkPackage.minimumSizeHint()));
        QSizePolicy sizePolicy = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);
        sizePolicy.setHorizontalStretch((byte)0);
        sizePolicy.setVerticalStretch((byte)0);
        sizePolicy.setHeightForWidth(ProjectWorkPackage.sizePolicy().hasHeightForWidth());
        ProjectWorkPackage.setSizePolicy(sizePolicy);
        ProjectWorkPackage.setMinimumSize(new QSize(700, 360));
        ProjectWorkPackage.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.TabFocus);
        gridLayout = new QGridLayout(ProjectWorkPackage);
        gridLayout.setObjectName("gridLayout");
        tblWorkPackages = new QTableView(ProjectWorkPackage);
        tblWorkPackages.setObjectName("tblWorkPackages");

        gridLayout.addWidget(tblWorkPackages, 0, 0, 1, 2);

        txtDescription = new QLineEdit(ProjectWorkPackage);
        txtDescription.setObjectName("txtDescription");

        gridLayout.addWidget(txtDescription, 1, 0, 1, 1);

        btnAdd = new QPushButton(ProjectWorkPackage);
        btnAdd.setObjectName("btnAdd");

        gridLayout.addWidget(btnAdd, 1, 1, 1, 1);

        retranslateUi(ProjectWorkPackage);

        ProjectWorkPackage.connectSlotsByName();
    } // setupUi

    void retranslateUi(QWidget ProjectWorkPackage)
    {
        ProjectWorkPackage.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("ProjectWorkPackage", "WorkPackage", null));
        btnAdd.setText(com.trolltech.qt.core.QCoreApplication.translate("ProjectWorkPackage", "Add", null));
    } // retranslateUi

}

