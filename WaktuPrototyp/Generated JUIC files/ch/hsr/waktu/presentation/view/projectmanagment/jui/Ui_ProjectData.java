/********************************************************************************
** Form generated from reading ui file 'ProjectData.jui'
**
** Created: Do 7. Apr 09:44:31 2011
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package ch.hsr.waktu.presentation.view.projectmanagment.jui;

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
    public QLineEdit txtProjectmanager;
    public QLabel lblPlannedTime;
    public QSpinBox txtPlannedTime;
    public QCheckBox checkBox;

    public Ui_ProjectData() { super(); }

    public void setupUi(QWidget ProjectData)
    {
        ProjectData.setObjectName("ProjectData");
        ProjectData.resize(new QSize(400, 300).expandedTo(ProjectData.minimumSizeHint()));
        gridLayout = new QGridLayout(ProjectData);
        gridLayout.setObjectName("gridLayout");
        lblProjectnumber = new QLabel(ProjectData);
        lblProjectnumber.setObjectName("lblProjectnumber");

        gridLayout.addWidget(lblProjectnumber, 0, 0, 1, 1);

        txtProjectnumber = new QLineEdit(ProjectData);
        txtProjectnumber.setObjectName("txtProjectnumber");

        gridLayout.addWidget(txtProjectnumber, 0, 2, 1, 1);

        lblDescription = new QLabel(ProjectData);
        lblDescription.setObjectName("lblDescription");

        gridLayout.addWidget(lblDescription, 1, 0, 1, 1);

        txtDescription = new QLineEdit(ProjectData);
        txtDescription.setObjectName("txtDescription");

        gridLayout.addWidget(txtDescription, 1, 2, 1, 1);

        lblProjectManager = new QLabel(ProjectData);
        lblProjectManager.setObjectName("lblProjectManager");

        gridLayout.addWidget(lblProjectManager, 2, 0, 1, 1);

        txtProjectmanager = new QLineEdit(ProjectData);
        txtProjectmanager.setObjectName("txtProjectmanager");

        gridLayout.addWidget(txtProjectmanager, 2, 2, 1, 1);

        lblPlannedTime = new QLabel(ProjectData);
        lblPlannedTime.setObjectName("lblPlannedTime");

        gridLayout.addWidget(lblPlannedTime, 3, 0, 1, 1);

        txtPlannedTime = new QSpinBox(ProjectData);
        txtPlannedTime.setObjectName("txtPlannedTime");

        gridLayout.addWidget(txtPlannedTime, 3, 2, 1, 1);

        checkBox = new QCheckBox(ProjectData);
        checkBox.setObjectName("checkBox");

        gridLayout.addWidget(checkBox, 4, 2, 1, 1);

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
        checkBox.setText(com.trolltech.qt.core.QCoreApplication.translate("ProjectData", "Inactiv", null));
    } // retranslateUi

}

