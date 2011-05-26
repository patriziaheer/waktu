/********************************************************************************
** Form generated from reading ui file 'IcsImport.jui'
**
** Created: Do 26. Mai 07:32:54 2011
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package ch.hsr.waktu.gui.qt.view;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_IcsImport implements com.trolltech.qt.QUiForm<QWidget>
{
    public QGridLayout gridLayout;
    public QLabel label;
    public QComboBox cmbProject;
    public QLabel label_2;
    public QComboBox cmbWorkPackage;
    public QLabel label_3;
    public QDateTimeEdit dteStart;
    public QLabel label_4;
    public QDateTimeEdit dteEnd;
    public QPushButton btnDiscard;
    public QPushButton btnImport;
    public QLabel label_5;
    public QLineEdit txtDescription;

    public Ui_IcsImport() { super(); }

    public void setupUi(QWidget IcsImport)
    {
        IcsImport.setObjectName("IcsImport");
        IcsImport.resize(new QSize(735, 545).expandedTo(IcsImport.minimumSizeHint()));
        IcsImport.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.TabFocus);
        gridLayout = new QGridLayout(IcsImport);
        gridLayout.setObjectName("gridLayout");
        label = new QLabel(IcsImport);
        label.setObjectName("label");

        gridLayout.addWidget(label, 0, 0, 1, 1);

        cmbProject = new QComboBox(IcsImport);
        cmbProject.setObjectName("cmbProject");

        gridLayout.addWidget(cmbProject, 0, 1, 1, 2);

        label_2 = new QLabel(IcsImport);
        label_2.setObjectName("label_2");

        gridLayout.addWidget(label_2, 1, 0, 1, 1);

        cmbWorkPackage = new QComboBox(IcsImport);
        cmbWorkPackage.setObjectName("cmbWorkPackage");

        gridLayout.addWidget(cmbWorkPackage, 1, 1, 1, 2);

        label_3 = new QLabel(IcsImport);
        label_3.setObjectName("label_3");

        gridLayout.addWidget(label_3, 2, 0, 1, 1);

        dteStart = new QDateTimeEdit(IcsImport);
        dteStart.setObjectName("dteStart");

        gridLayout.addWidget(dteStart, 2, 1, 1, 2);

        label_4 = new QLabel(IcsImport);
        label_4.setObjectName("label_4");

        gridLayout.addWidget(label_4, 3, 0, 1, 1);

        dteEnd = new QDateTimeEdit(IcsImport);
        dteEnd.setObjectName("dteEnd");

        gridLayout.addWidget(dteEnd, 3, 1, 1, 2);

        btnDiscard = new QPushButton(IcsImport);
        btnDiscard.setObjectName("btnDiscard");

        gridLayout.addWidget(btnDiscard, 5, 1, 1, 1);

        btnImport = new QPushButton(IcsImport);
        btnImport.setObjectName("btnImport");

        gridLayout.addWidget(btnImport, 5, 2, 1, 1);

        label_5 = new QLabel(IcsImport);
        label_5.setObjectName("label_5");

        gridLayout.addWidget(label_5, 4, 0, 1, 1);

        txtDescription = new QLineEdit(IcsImport);
        txtDescription.setObjectName("txtDescription");

        gridLayout.addWidget(txtDescription, 4, 1, 1, 2);

        QWidget.setTabOrder(cmbProject, cmbWorkPackage);
        QWidget.setTabOrder(cmbWorkPackage, dteStart);
        QWidget.setTabOrder(dteStart, dteEnd);
        QWidget.setTabOrder(dteEnd, txtDescription);
        QWidget.setTabOrder(txtDescription, btnDiscard);
        QWidget.setTabOrder(btnDiscard, btnImport);
        retranslateUi(IcsImport);

        IcsImport.connectSlotsByName();
    } // setupUi

    void retranslateUi(QWidget IcsImport)
    {
        IcsImport.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("IcsImport", "Import", null));
        label.setText(com.trolltech.qt.core.QCoreApplication.translate("IcsImport", "Project:", null));
        label_2.setText(com.trolltech.qt.core.QCoreApplication.translate("IcsImport", "Workpackage:", null));
        label_3.setText(com.trolltech.qt.core.QCoreApplication.translate("IcsImport", "Start:", null));
        label_4.setText(com.trolltech.qt.core.QCoreApplication.translate("IcsImport", "End:", null));
        btnDiscard.setText(com.trolltech.qt.core.QCoreApplication.translate("IcsImport", "Discard Worksession", null));
        btnImport.setText(com.trolltech.qt.core.QCoreApplication.translate("IcsImport", "Import Worksession", null));
        label_5.setText(com.trolltech.qt.core.QCoreApplication.translate("IcsImport", "Description:", null));
    } // retranslateUi

}

