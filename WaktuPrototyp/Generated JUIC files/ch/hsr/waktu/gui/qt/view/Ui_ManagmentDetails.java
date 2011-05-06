/********************************************************************************
** Form generated from reading ui file 'ManagmentDetails.jui'
**
** Created: Fr. Mai 6 15:00:18 2011
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package ch.hsr.waktu.gui.qt.view;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_ManagmentDetails implements com.trolltech.qt.QUiForm<QWidget>
{
    public QGridLayout gridLayout;
    public QWidget widget;
    public QGridLayout gridLayout_2;
    public QTreeView treeView;
    public QLineEdit lineEdit;

    public Ui_ManagmentDetails() { super(); }

    public void setupUi(QWidget ManagmentDetails)
    {
        ManagmentDetails.setObjectName("ManagmentDetails");
        ManagmentDetails.resize(new QSize(200, 449).expandedTo(ManagmentDetails.minimumSizeHint()));
        QSizePolicy sizePolicy = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);
        sizePolicy.setHorizontalStretch((byte)0);
        sizePolicy.setVerticalStretch((byte)0);
        sizePolicy.setHeightForWidth(ManagmentDetails.sizePolicy().hasHeightForWidth());
        ManagmentDetails.setSizePolicy(sizePolicy);
        ManagmentDetails.setMinimumSize(new QSize(200, 0));
        gridLayout = new QGridLayout(ManagmentDetails);
        gridLayout.setMargin(12);
        gridLayout.setObjectName("gridLayout");
        widget = new QWidget(ManagmentDetails);
        widget.setObjectName("widget");
        gridLayout_2 = new QGridLayout(widget);
        gridLayout_2.setMargin(0);
        gridLayout_2.setObjectName("gridLayout_2");
        treeView = new QTreeView(widget);
        treeView.setObjectName("treeView");
        QSizePolicy sizePolicy1 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);
        sizePolicy1.setHorizontalStretch((byte)0);
        sizePolicy1.setVerticalStretch((byte)0);
        sizePolicy1.setHeightForWidth(treeView.sizePolicy().hasHeightForWidth());
        treeView.setSizePolicy(sizePolicy1);
        treeView.setMinimumSize(new QSize(200, 0));

        gridLayout_2.addWidget(treeView, 1, 0, 1, 1);

        lineEdit = new QLineEdit(widget);
        lineEdit.setObjectName("lineEdit");

        gridLayout_2.addWidget(lineEdit, 0, 0, 1, 1);


        gridLayout.addWidget(widget, 2, 1, 1, 1);

        retranslateUi(ManagmentDetails);

        ManagmentDetails.connectSlotsByName();
    } // setupUi

    void retranslateUi(QWidget ManagmentDetails)
    {
        ManagmentDetails.setWindowTitle("");
    } // retranslateUi

}

