/********************************************************************************
** Form generated from reading ui file 'ProjectData.jui'
**
** Created: Di. Mai 24 10:26:33 2011
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

    public Ui_ProjectData() { super(); }

    public void setupUi(QWidget ProjectData)
    {
        ProjectData.setObjectName("ProjectData");
        ProjectData.resize(new QSize(800, 360).expandedTo(ProjectData.minimumSizeHint()));
        QSizePolicy sizePolicy = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);
        sizePolicy.setHorizontalStretch((byte)0);
        sizePolicy.setVerticalStretch((byte)0);
        sizePolicy.setHeightForWidth(ProjectData.sizePolicy().hasHeightForWidth());
        ProjectData.setSizePolicy(sizePolicy);
        ProjectData.setMinimumSize(new QSize(800, 360));
        gridLayout = new QGridLayout(ProjectData);
        gridLayout.setObjectName("gridLayout");
        lblProjectnumber = new QLabel(ProjectData);
        lblProjectnumber.setObjectName("lblProjectnumber");

        gridLayout.addWidget(lblProjectnumber, 0, 0, 1, 1);

        txtProjectnumber = new QLineEdit(ProjectData);
        txtProjectnumber.setObjectName("txtProjectnumber");

        gridLayout.addWidget(txtProjectnumber, 0, 1, 1, 2);

        lblDescription = new QLabel(ProjectData);
        lblDescription.setObjectName("lblDescription");

        gridLayout.addWidget(lblDescription, 1, 0, 1, 1);

        txtDescription = new QLineEdit(ProjectData);
        txtDescription.setObjectName("txtDescription");

        gridLayout.addWidget(txtDescription, 1, 1, 1, 2);

        lblProjectManager = new QLabel(ProjectData);
        lblProjectManager.setObjectName("lblProjectManager");

        gridLayout.addWidget(lblProjectManager, 2, 0, 1, 1);

        lblPlannedTime = new QLabel(ProjectData);
        lblPlannedTime.setObjectName("lblPlannedTime");

        gridLayout.addWidget(lblPlannedTime, 3, 0, 1, 1);

        btnAdd = new QPushButton(ProjectData);
        btnAdd.setObjectName("btnAdd");

        gridLayout.addWidget(btnAdd, 5, 2, 1, 1);

        horizontalSpacer = new QSpacerItem(40, 20, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);

        gridLayout.addItem(horizontalSpacer, 5, 1, 1, 1);

        checkBox = new QCheckBox(ProjectData);
        checkBox.setObjectName("checkBox");

        gridLayout.addWidget(checkBox, 4, 1, 1, 1);

        txtPlannedTime = new QSpinBox(ProjectData);
        txtPlannedTime.setObjectName("txtPlannedTime");
        txtPlannedTime.setMaximum(1000000);

        gridLayout.addWidget(txtPlannedTime, 3, 1, 1, 2);

        cmbProjectManager = new QComboBox(ProjectData);
        cmbProjectManager.setObjectName("cmbProjectManager");

        gridLayout.addWidget(cmbProjectManager, 2, 1, 1, 2);

        verticalSpacer = new QSpacerItem(20, 40, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);

        gridLayout.addItem(verticalSpacer, 6, 2, 1, 1);

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

