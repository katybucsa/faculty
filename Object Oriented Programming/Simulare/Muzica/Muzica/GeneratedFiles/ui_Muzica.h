/********************************************************************************
** Form generated from reading UI file 'Muzica.ui'
**
** Created by: Qt User Interface Compiler version 5.10.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MUZICA_H
#define UI_MUZICA_H

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

class Ui_MuzicaClass
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *MuzicaClass)
    {
        if (MuzicaClass->objectName().isEmpty())
            MuzicaClass->setObjectName(QStringLiteral("MuzicaClass"));
        MuzicaClass->resize(600, 400);
        menuBar = new QMenuBar(MuzicaClass);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        MuzicaClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(MuzicaClass);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        MuzicaClass->addToolBar(mainToolBar);
        centralWidget = new QWidget(MuzicaClass);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        MuzicaClass->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(MuzicaClass);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        MuzicaClass->setStatusBar(statusBar);

        retranslateUi(MuzicaClass);

        QMetaObject::connectSlotsByName(MuzicaClass);
    } // setupUi

    void retranslateUi(QMainWindow *MuzicaClass)
    {
        MuzicaClass->setWindowTitle(QApplication::translate("MuzicaClass", "Muzica", nullptr));
    } // retranslateUi

};

namespace Ui {
    class MuzicaClass: public Ui_MuzicaClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MUZICA_H
