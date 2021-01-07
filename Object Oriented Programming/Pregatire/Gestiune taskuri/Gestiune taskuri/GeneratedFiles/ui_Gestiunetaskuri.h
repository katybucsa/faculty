/********************************************************************************
** Form generated from reading UI file 'Gestiunetaskuri.ui'
**
** Created by: Qt User Interface Compiler version 5.10.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_GESTIUNETASKURI_H
#define UI_GESTIUNETASKURI_H

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

class Ui_GestiunetaskuriClass
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *GestiunetaskuriClass)
    {
        if (GestiunetaskuriClass->objectName().isEmpty())
            GestiunetaskuriClass->setObjectName(QStringLiteral("GestiunetaskuriClass"));
        GestiunetaskuriClass->resize(600, 400);
        menuBar = new QMenuBar(GestiunetaskuriClass);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        GestiunetaskuriClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(GestiunetaskuriClass);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        GestiunetaskuriClass->addToolBar(mainToolBar);
        centralWidget = new QWidget(GestiunetaskuriClass);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        GestiunetaskuriClass->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(GestiunetaskuriClass);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        GestiunetaskuriClass->setStatusBar(statusBar);

        retranslateUi(GestiunetaskuriClass);

        QMetaObject::connectSlotsByName(GestiunetaskuriClass);
    } // setupUi

    void retranslateUi(QMainWindow *GestiunetaskuriClass)
    {
        GestiunetaskuriClass->setWindowTitle(QApplication::translate("GestiunetaskuriClass", "Gestiunetaskuri", nullptr));
    } // retranslateUi

};

namespace Ui {
    class GestiunetaskuriClass: public Ui_GestiunetaskuriClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_GESTIUNETASKURI_H
