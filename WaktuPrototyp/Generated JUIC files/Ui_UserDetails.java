/********************************************************************************
** Form generated from reading ui file 'UserDetails.jui'
**
** Created: Mo. Mrz 28 14:36:46 2011
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_UserDetails implements com.trolltech.qt.QUiForm<QWidget>
{
    public QGridLayout gridLayout;
    public QTreeView treeView;
    public QWidget widget;

    public Ui_UserDetails() { super(); }

    public void setupUi(QWidget UserDetails)
    {
        UserDetails.setObjectName("UserDetails");
        UserDetails.resize(new QSize(490, 375).expandedTo(UserDetails.minimumSizeHint()));
        gridLayout = new QGridLayout(UserDetails);
        gridLayout.setObjectName("gridLayout");
        treeView = new QTreeView(UserDetails);
        treeView.setObjectName("treeView");
        QSizePolicy sizePolicy = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);
        sizePolicy.setHorizontalStretch((byte)0);
        sizePolicy.setVerticalStretch((byte)0);
        sizePolicy.setHeightForWidth(treeView.sizePolicy().hasHeightForWidth());
        treeView.setSizePolicy(sizePolicy);

        gridLayout.addWidget(treeView, 0, 0, 1, 1);

        widget = new QWidget(UserDetails);
        widget.setObjectName("widget");
        widget.setMinimumSize(new QSize(200, 150));

        gridLayout.addWidget(widget, 0, 1, 1, 1);

        retranslateUi(UserDetails);

        UserDetails.connectSlotsByName();
    } // setupUi

    void retranslateUi(QWidget UserDetails)
    {
        UserDetails.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("UserDetails", "Form", null));
    } // retranslateUi

}

