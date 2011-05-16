/********************************************************************************
** Form generated from reading ui file 'ProjectData.jui'
**
** Created: Mo. Mai 16 09:40:49 2011
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package ch.hsr.waktu.gui.qt.view.projectmanagment;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_ProjectData implements com.trolltech.qt.QUiForm<QWidget>
{
    public QGridLayout gridLayout;
    public QLabel lblProjectnumber;
    public QLineEdit txtProjectnumber;
    public QLabel lblDescription;
    public QLineEdit txtDescription;
    public QLabel lblProjectManager;
    public QLabel lblPlannedTime;
    public QPushButton btnAdd;
    public QSpacerItem horizontalSpacer;
    public QCheckBox checkBox;
    public QSpinBox txtPlannedTime;
    public QComboBox cmbProjectManager;
    public QSpacerItem verticalSpacer;
    public QLineEdit txtUnvisibleField;

    public Ui_ProjectData() { super(); }

    public void setupUi(QWidget ProjectData)
    {
        ProjectData.setObjectName("ProjectData");
        ProjectData.resize(new QSize(400, 300).expandedTo(ProjectData.minimumSizeHint()));
        gridLayout = new QGridLayout(ProjectData);
        gridLayout.setObjectName("gridLayout");
        lblProjectnumber = new QLabel(ProjectData);
        lblProjectnumber.setObjectName("lblProjectnumber");

        gridLayout.addWidget(lblProjectnumber, 1, 0, 1, 1);

        txtProjectnumber = new QLineEdit(ProjectData);
        txtProjectnumber.setObjectName("txtProjectnumber");

        gridLayout.addWidget(txtProjectnumber, 1, 1, 1, 2);

        lblDescription = new QLabel(ProjectData);
        lblDescription.setObjectName("lblDescription");

        gridLayout.addWidget(lblDescription, 2, 0, 1, 1);

        txtDescription = new QLineEdit(ProjectData);
        txtDescription.setObjectName("txtDescription");

        gridLayout.addWidget(txtDescription, 2, 1, 1, 2);

        lblProjectManager = new QLabel(ProjectData);
        lblProjectManager.setObjectName("lblProjectManager");

        gridLayout.addWidget(lblProjectManager, 3, 0, 1, 1);

        lblPlannedTime = new QLabel(ProjectData);
        lblPlannedTime.setObjectName("lblPlannedTime");

        gridLayout.addWidget(lblPlannedTime, 4, 0, 1, 1);

        btnAdd = new QPushButton(ProjectData);
        btnAdd.setObjectName("btnAdd");

        gridLayout.addWidget(btnAdd, 6, 2, 1, 1);

        horizontalSpacer = new QSpacerItem(40, 20, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);

        gridLayout.addItem(horizontalSpacer, 6, 1, 1, 1);

        checkBox = new QCheckBox(ProjectData);
        checkBox.setObjectName("checkBox");

        gridLayout.addWidget(checkBox, 5, 1, 1, 1);

        txtPlannedTime = new QSpinBox(ProjectData);
        txtPlannedTime.setObjectName("txtPlannedTime");

        gridLayout.addWidget(txtPlannedTime, 4, 1, 1, 2);

        cmbProjectManager = new QComboBox(ProjectData);
        cmbProjectManager.setObjectName("cmbProjectManager");

        gridLayout.addWidget(cmbProjectManager, 3, 1, 1, 2);

        verticalSpacer = new QSpacerItem(20, 40, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);

        gridLayout.addItem(verticalSpacer, 7, 2, 1, 1);

        txtUnvisibleField = new QLineEdit(ProjectData);
        txtUnvisibleField.setObjectName("txtUnvisibleField");
        txtUnvisibleField.setEnabled(false);

        gridLayout.addWidget(txtUnvisibleField, 0, 1, 1, 1);

        retranslateUi(ProjectData);

        ProjectData.connectSlotsByName();
    } // setupUi

    void retranslateUi(QWidget ProjectData)
    {
        ProjectData.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("ProjectData", "ProjectData", null));
        lblProjectnumber.setText(com.trolltech.qt.core.QCoreApplication.translate("ProjectData", "Projectnumber:", null));
        lblDescription.setText(com.trolltech.qt.core.QCoreApplication.translate("ProjectData", "Projectdescription:", null));
        lblProjectManager.setText(com.trolltech.qt.core.QCoreApplication.translate("ProjectData", "Projectmanager:", null));
        lblPlannedTime.setText(com.trolltech.qt.core.QCoreApplication.translate("ProjectData", "Planned Time:", null));
        btnAdd.setText(com.trolltech.qt.core.QCoreApplication.translate("ProjectData", "add", null));
        checkBox.setText(com.trolltech.qt.core.QCoreApplication.translate("ProjectData", "Inactiv", null));
    } // retranslateUi

}

