/********************************************************************************
** Form generated from reading UI file 'Gestiunemelodii.ui'
**
** Created by: Qt User Interface Compiler version 5.10.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_GESTIUNEMELODII_H
#define UI_GESTIUNEMELODII_H

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

class Ui_GestiunemelodiiClass
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *GestiunemelodiiClass)
    {
        if (GestiunemelodiiClass->objectName().isEmpty())
            GestiunemelodiiClass->setObjectName(QStringLiteral("GestiunemelodiiClass"));
        GestiunemelodiiClass->resize(600, 400);
        menuBar = new QMenuBar(GestiunemelodiiClass);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        GestiunemelodiiClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(GestiunemelodiiClass);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        GestiunemelodiiClass->addToolBar(mainToolBar);
        centralWidget = new QWidget(GestiunemelodiiClass);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        GestiunemelodiiClass->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(GestiunemelodiiClass);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        GestiunemelodiiClass->setStatusBar(statusBar);

        retranslateUi(GestiunemelodiiClass);

        QMetaObject::connectSlotsByName(GestiunemelodiiClass);
    } // setupUi

    void retranslateUi(QMainWindow *GestiunemelodiiClass)
    {
        GestiunemelodiiClass->setWindowTitle(QApplication::translate("GestiunemelodiiClass", "Gestiunemelodii", nullptr));
    } // retranslateUi

};

namespace Ui {
    class GestiunemelodiiClass: public Ui_GestiunemelodiiClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_GESTIUNEMELODII_H
