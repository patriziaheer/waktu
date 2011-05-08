/********************************************************************************
** Form generated from reading ui file 'CalendarWidget.jui'
**
** Created: Sa. Mai 7 08:53:35 2011
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package ch.hsr.waktu.gui.qt.view;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_Form implements com.trolltech.qt.QUiForm<QWidget>
{
    public QGridLayout gridLayout;
    public QPushButton btnMo;
    public QPushButton btnMi;
    public QPushButton btnFri;
    public QPushButton btnSo;
    public QPushButton btnSa;
    public QPushButton btnDo;
    public QPushButton btnDi;
    public QLabel lblBis;
    public QPushButton btnRight;
    public QPushButton btnLeft;
    public QLabel lblEnd;
    public QLabel lblStart;
    public QSpacerItem horizontalSpacer;
    public QLabel lblCurrDate;

    public Ui_Form() { super(); }

    public void setupUi(QWidget Form)
    {
        Form.setObjectName("Form");
        Form.resize(new QSize(646, 100).expandedTo(Form.minimumSizeHint()));
        QSizePolicy sizePolicy = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Preferred, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy.setHorizontalStretch((byte)0);
        sizePolicy.setVerticalStretch((byte)0);
        sizePolicy.setHeightForWidth(Form.sizePolicy().hasHeightForWidth());
        Form.setSizePolicy(sizePolicy);
        Form.setMinimumSize(new QSize(0, 100));
        Form.setMaximumSize(new QSize(16777215, 100));
        gridLayout = new QGridLayout(Form);
        gridLayout.setObjectName("gridLayout");
        btnMo = new QPushButton(Form);
        btnMo.setObjectName("btnMo");
        QSizePolicy sizePolicy1 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy1.setHorizontalStretch((byte)0);
        sizePolicy1.setVerticalStretch((byte)0);
        sizePolicy1.setHeightForWidth(btnMo.sizePolicy().hasHeightForWidth());
        btnMo.setSizePolicy(sizePolicy1);
        btnMo.setMinimumSize(new QSize(35, 35));
        btnMo.setMaximumSize(new QSize(35, 35));

        gridLayout.addWidget(btnMo, 1, 7, 1, 1);

        btnMi = new QPushButton(Form);
        btnMi.setObjectName("btnMi");
        QSizePolicy sizePolicy2 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy2.setHorizontalStretch((byte)0);
        sizePolicy2.setVerticalStretch((byte)0);
        sizePolicy2.setHeightForWidth(btnMi.sizePolicy().hasHeightForWidth());
        btnMi.setSizePolicy(sizePolicy2);
        btnMi.setMinimumSize(new QSize(35, 35));
        btnMi.setMaximumSize(new QSize(35, 35));

        gridLayout.addWidget(btnMi, 1, 9, 1, 2);

        btnFri = new QPushButton(Form);
        btnFri.setObjectName("btnFri");
        QSizePolicy sizePolicy3 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy3.setHorizontalStretch((byte)0);
        sizePolicy3.setVerticalStretch((byte)0);
        sizePolicy3.setHeightForWidth(btnFri.sizePolicy().hasHeightForWidth());
        btnFri.setSizePolicy(sizePolicy3);
        btnFri.setMinimumSize(new QSize(35, 35));
        btnFri.setMaximumSize(new QSize(35, 35));

        gridLayout.addWidget(btnFri, 1, 12, 1, 1);

        btnSo = new QPushButton(Form);
        btnSo.setObjectName("btnSo");
        QSizePolicy sizePolicy4 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy4.setHorizontalStretch((byte)0);
        sizePolicy4.setVerticalStretch((byte)0);
        sizePolicy4.setHeightForWidth(btnSo.sizePolicy().hasHeightForWidth());
        btnSo.setSizePolicy(sizePolicy4);
        btnSo.setMinimumSize(new QSize(35, 35));
        btnSo.setMaximumSize(new QSize(35, 35));

        gridLayout.addWidget(btnSo, 1, 14, 1, 1);

        btnSa = new QPushButton(Form);
        btnSa.setObjectName("btnSa");
        QSizePolicy sizePolicy5 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy5.setHorizontalStretch((byte)0);
        sizePolicy5.setVerticalStretch((byte)0);
        sizePolicy5.setHeightForWidth(btnSa.sizePolicy().hasHeightForWidth());
        btnSa.setSizePolicy(sizePolicy5);
        btnSa.setMinimumSize(new QSize(35, 35));
        btnSa.setMaximumSize(new QSize(35, 35));

        gridLayout.addWidget(btnSa, 1, 13, 1, 1);

        btnDo = new QPushButton(Form);
        btnDo.setObjectName("btnDo");
        QSizePolicy sizePolicy6 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy6.setHorizontalStretch((byte)0);
        sizePolicy6.setVerticalStretch((byte)0);
        sizePolicy6.setHeightForWidth(btnDo.sizePolicy().hasHeightForWidth());
        btnDo.setSizePolicy(sizePolicy6);
        btnDo.setMinimumSize(new QSize(35, 35));
        btnDo.setMaximumSize(new QSize(35, 35));

        gridLayout.addWidget(btnDo, 1, 11, 1, 1);

        btnDi = new QPushButton(Form);
        btnDi.setObjectName("btnDi");
        QSizePolicy sizePolicy7 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy7.setHorizontalStretch((byte)0);
        sizePolicy7.setVerticalStretch((byte)0);
        sizePolicy7.setHeightForWidth(btnDi.sizePolicy().hasHeightForWidth());
        btnDi.setSizePolicy(sizePolicy7);
        btnDi.setMinimumSize(new QSize(35, 35));
        btnDi.setMaximumSize(new QSize(35, 35));

        gridLayout.addWidget(btnDi, 1, 8, 1, 1);

        lblBis = new QLabel(Form);
        lblBis.setObjectName("lblBis");
        lblBis.setAlignment(com.trolltech.qt.core.Qt.AlignmentFlag.createQFlags(com.trolltech.qt.core.Qt.AlignmentFlag.AlignCenter));

        gridLayout.addWidget(lblBis, 0, 11, 1, 1);

        btnRight = new QPushButton(Form);
        btnRight.setObjectName("btnRight");
        QSizePolicy sizePolicy8 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy8.setHorizontalStretch((byte)0);
        sizePolicy8.setVerticalStretch((byte)0);
        sizePolicy8.setHeightForWidth(btnRight.sizePolicy().hasHeightForWidth());
        btnRight.setSizePolicy(sizePolicy8);
        btnRight.setMinimumSize(new QSize(35, 35));
        btnRight.setMaximumSize(new QSize(35, 35));

        gridLayout.addWidget(btnRight, 0, 14, 1, 1);

        btnLeft = new QPushButton(Form);
        btnLeft.setObjectName("btnLeft");
        QSizePolicy sizePolicy9 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy9.setHorizontalStretch((byte)0);
        sizePolicy9.setVerticalStretch((byte)0);
        sizePolicy9.setHeightForWidth(btnLeft.sizePolicy().hasHeightForWidth());
        btnLeft.setSizePolicy(sizePolicy9);
        btnLeft.setMinimumSize(new QSize(35, 35));
        btnLeft.setMaximumSize(new QSize(35, 35));

        gridLayout.addWidget(btnLeft, 0, 7, 1, 1);

        lblEnd = new QLabel(Form);
        lblEnd.setObjectName("lblEnd");

        gridLayout.addWidget(lblEnd, 0, 12, 1, 2);

        lblStart = new QLabel(Form);
        lblStart.setObjectName("lblStart");
        lblStart.setAlignment(com.trolltech.qt.core.Qt.AlignmentFlag.createQFlags(com.trolltech.qt.core.Qt.AlignmentFlag.AlignRight,com.trolltech.qt.core.Qt.AlignmentFlag.AlignVCenter));

        gridLayout.addWidget(lblStart, 0, 8, 1, 3);

        horizontalSpacer = new QSpacerItem(40, 20, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);

        gridLayout.addItem(horizontalSpacer, 1, 2, 1, 1);

        lblCurrDate = new QLabel(Form);
        lblCurrDate.setObjectName("lblCurrDate");

        gridLayout.addWidget(lblCurrDate, 1, 3, 1, 1);

        retranslateUi(Form);

        Form.connectSlotsByName();
    } // setupUi

    void retranslateUi(QWidget Form)
    {
        Form.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("Form", "Form", null));
        btnMo.setText(com.trolltech.qt.core.QCoreApplication.translate("Form", "Mon", null));
        btnMi.setText(com.trolltech.qt.core.QCoreApplication.translate("Form", "Wed", null));
        btnFri.setText(com.trolltech.qt.core.QCoreApplication.translate("Form", "Fri", null));
        btnSo.setText(com.trolltech.qt.core.QCoreApplication.translate("Form", "Sun", null));
        btnSa.setText(com.trolltech.qt.core.QCoreApplication.translate("Form", "Sat", null));
        btnDo.setText(com.trolltech.qt.core.QCoreApplication.translate("Form", "Thu", null));
        btnDi.setText(com.trolltech.qt.core.QCoreApplication.translate("Form", "Tue", null));
        lblBis.setText(com.trolltech.qt.core.QCoreApplication.translate("Form", "-", null));
        btnRight.setText(com.trolltech.qt.core.QCoreApplication.translate("Form", ">", null));
        btnLeft.setText(com.trolltech.qt.core.QCoreApplication.translate("Form", "<", null));
        lblEnd.setText("");
        lblStart.setText("");
        lblCurrDate.setText("");
    } // retranslateUi

}

