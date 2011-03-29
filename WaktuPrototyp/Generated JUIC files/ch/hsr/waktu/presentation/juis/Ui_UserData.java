/********************************************************************************
** Form generated from reading ui file 'UserData.jui'
**
** Created: Mo. Mrz 28 15:30:25 2011
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package ch.hsr.waktu.presentation.juis;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_UserData implements com.trolltech.qt.QUiForm<QWidget>
{
    public QGridLayout gridLayout;
    public QLabel label;
    public QLabel lblUser;

    public Ui_UserData() { super(); }

    public void setupUi(QWidget UserData)
    {
        UserData.setObjectName("UserData");
        UserData.resize(new QSize(400, 300).expandedTo(UserData.minimumSizeHint()));
        gridLayout = new QGridLayout(UserData);
        gridLayout.setObjectName("gridLayout");
        label = new QLabel(UserData);
        label.setObjectName("label");

        gridLayout.addWidget(label, 0, 0, 1, 1);

        lblUser = new QLabel(UserData);
        lblUser.setObjectName("lblUser");

        gridLayout.addWidget(lblUser, 0, 1, 1, 1);

        retranslateUi(UserData);

        UserData.connectSlotsByName();
    } // setupUi

    void retranslateUi(QWidget UserData)
    {
        UserData.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("UserData", "Form", null));
        label.setText(com.trolltech.qt.core.QCoreApplication.translate("UserData", "Data for", null));
        lblUser.setText("");
    } // retranslateUi

}

