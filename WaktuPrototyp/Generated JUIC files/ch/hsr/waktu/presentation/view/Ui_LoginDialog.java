/********************************************************************************
** Form generated from reading ui file 'LoginDialog.jui'
**
** Created: Mi 4. Mai 08:14:56 2011
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package ch.hsr.waktu.presentation.view;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_LoginDialog implements com.trolltech.qt.QUiForm<QDialog>
{
    public QGridLayout gridLayout;
    public QLabel lblUsernmae;
    public QLineEdit txtUsername;
    public QLabel lblPassword;
    public QLineEdit txtPassword;
    public QPushButton btnCancel;
    public QPushButton btnLogin;
    public QLabel lblStatus;

    public Ui_LoginDialog() { super(); }

    public void setupUi(QDialog LoginDialog)
    {
        LoginDialog.setObjectName("LoginDialog");
        LoginDialog.resize(new QSize(400, 150).expandedTo(LoginDialog.minimumSizeHint()));
        QSizePolicy sizePolicy = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy.setHorizontalStretch((byte)0);
        sizePolicy.setVerticalStretch((byte)0);
        sizePolicy.setHeightForWidth(LoginDialog.sizePolicy().hasHeightForWidth());
        LoginDialog.setSizePolicy(sizePolicy);
        LoginDialog.setMinimumSize(new QSize(400, 150));
        LoginDialog.setMaximumSize(new QSize(400, 150));
        LoginDialog.setSizeGripEnabled(false);
        LoginDialog.setModal(false);
        gridLayout = new QGridLayout(LoginDialog);
        gridLayout.setObjectName("gridLayout");
        gridLayout.setContentsMargins(12, -1, -1, 0);
        lblUsernmae = new QLabel(LoginDialog);
        lblUsernmae.setObjectName("lblUsernmae");

        gridLayout.addWidget(lblUsernmae, 0, 0, 1, 1);

        txtUsername = new QLineEdit(LoginDialog);
        txtUsername.setObjectName("txtUsername");

        gridLayout.addWidget(txtUsername, 0, 1, 1, 2);

        lblPassword = new QLabel(LoginDialog);
        lblPassword.setObjectName("lblPassword");

        gridLayout.addWidget(lblPassword, 1, 0, 1, 1);

        txtPassword = new QLineEdit(LoginDialog);
        txtPassword.setObjectName("txtPassword");
        txtPassword.setEchoMode(com.trolltech.qt.gui.QLineEdit.EchoMode.Password);

        gridLayout.addWidget(txtPassword, 1, 1, 1, 2);

        btnCancel = new QPushButton(LoginDialog);
        btnCancel.setObjectName("btnCancel");

        gridLayout.addWidget(btnCancel, 2, 1, 1, 1);

        btnLogin = new QPushButton(LoginDialog);
        btnLogin.setObjectName("btnLogin");
        btnLogin.setDefault(true);

        gridLayout.addWidget(btnLogin, 2, 2, 1, 1);

        lblStatus = new QLabel(LoginDialog);
        lblStatus.setObjectName("lblStatus");

        gridLayout.addWidget(lblStatus, 3, 0, 1, 3);

        retranslateUi(LoginDialog);

        LoginDialog.connectSlotsByName();
    } // setupUi

    void retranslateUi(QDialog LoginDialog)
    {
        LoginDialog.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("LoginDialog", "Login", null));
        lblUsernmae.setText(com.trolltech.qt.core.QCoreApplication.translate("LoginDialog", "Username:", null));
        lblPassword.setText(com.trolltech.qt.core.QCoreApplication.translate("LoginDialog", "Password:", null));
        btnCancel.setText(com.trolltech.qt.core.QCoreApplication.translate("LoginDialog", "Cancel", null));
        btnLogin.setText(com.trolltech.qt.core.QCoreApplication.translate("LoginDialog", "Login", null));
        lblStatus.setText("");
    } // retranslateUi

}

