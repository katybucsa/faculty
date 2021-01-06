/********************************************************************************
** Form generated from reading UI file 'Gestiuneproduse.ui'
**
** Created by: Qt User Interface Compiler version 5.10.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_GESTIUNEPRODUSE_H
#define UI_GESTIUNEPRODUSE_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_GestiuneproduseClass
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *GestiuneproduseClass)
    {
        if (GestiuneproduseClass->objectName().isEmpty())
            GestiuneproduseClass->setObjectName(QStringLiteral("GestiuneproduseClass"));
        GestiuneproduseClass->resize(600, 400);
        menuBar = new QMenuBar(GestiuneproduseClass);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        GestiuneproduseClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(GestiuneproduseClass);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        GestiuneproduseClass->addToolBar(mainToolBar);
        centralWidget = new QWidget(GestiuneproduseClass);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        GestiuneproduseClass->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(GestiuneproduseClass);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        GestiuneproduseClass->setStatusBar(statusBar);

        retranslateUi(GestiuneproduseClass);

        QMetaObject::connectSlotsByName(GestiuneproduseClass);
    } // setupUi

    void retranslateUi(QMainWindow *GestiuneproduseClass)
    {
        GestiuneproduseClass->setWindowTitle(QApplication::translate("GestiuneproduseClass", "Gestiuneproduse", nullptr));
    } // retranslateUi

};

namespace Ui {
    class GestiuneproduseClass: public Ui_GestiuneproduseClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_GESTIUNEPRODUSE_H
