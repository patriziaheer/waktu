/********************************************************************************
** Form generated from reading ui file 'LoginWindow.jui'
**
** Created: Do. Apr 7 19:27:14 2011
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package ch.hsr.waktu.presentation.view.jui;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_LoginWindow implements com.trolltech.qt.QUiForm<QMainWindow>
{
    public QWidget centralwidget;
    public QGridLayout gridLayout;
    public QLabel lblUsernmae;
    public QLineEdit txtUsername;
    public QLabel lblPassword;
    public QLineEdit txtPassword;
    public QPushButton btnCancel;
    public QPushButton btnLogin;
    public QStatusBar statusbar;

    public Ui_LoginWindow() { super(); }

    public void setupUi(QMainWindow LoginWindow)
    {
        LoginWindow.setObjectName("LoginWindow");
        LoginWindow.resize(new QSize(553, 187).expandedTo(LoginWindow.minimumSizeHint()));
        centralwidget = new QWidget(LoginWindow);
        centralwidget.setObjectName("centralwidget");
        gridLayout = new QGridLayout(centralwidget);
        gridLayout.setObjectName("gridLayout");
        lblUsernmae = new QLabel(centralwidget);
        lblUsernmae.setObjectName("lblUsernmae");

        gridLayout.addWidget(lblUsernmae, 0, 0, 1, 1);

        txtUsername = new QLineEdit(centralwidget);
        txtUsername.setObjectName("txtUsername");

        gridLayout.addWidget(txtUsername, 0, 1, 1, 2);

        lblPassword = new QLabel(centralwidget);
        lblPassword.setObjectName("lblPassword");

        gridLayout.addWidget(lblPassword, 1, 0, 1, 1);

        txtPassword = new QLineEdit(centralwidget);
        txtPassword.setObjectName("txtPassword");

        gridLayout.addWidget(txtPassword, 1, 1, 1, 2);

        btnCancel = new QPushButton(centralwidget);
        btnCancel.setObjectName("btnCancel");

        gridLayout.addWidget(btnCancel, 2, 1, 1, 1);

        btnLogin = new QPushButton(centralwidget);
        btnLogin.setObjectName("btnLogin");

        gridLayout.addWidget(btnLogin, 2, 2, 1, 1);

        LoginWindow.setCentralWidget(centralwidget);
        statusbar = new QStatusBar(LoginWindow);
        statusbar.setObjectName("statusbar");
        LoginWindow.setStatusBar(statusbar);
        retranslateUi(LoginWindow);

        LoginWindow.connectSlotsByName();
    } // setupUi

    void retranslateUi(QMainWindow LoginWindow)
    {
        LoginWindow.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("LoginWindow", "Login Window", null));
        lblUsernmae.setText(com.trolltech.qt.core.QCoreApplication.translate("LoginWindow", "Username:", null));
        lblPassword.setText(com.trolltech.qt.core.QCoreApplication.translate("LoginWindow", "Password:", null));
        btnCancel.setText(com.trolltech.qt.core.QCoreApplication.translate("LoginWindow", "Cancel", null));
        btnLogin.setText(com.trolltech.qt.core.QCoreApplication.translate("LoginWindow", "Login", null));
    } // retranslateUi

}

