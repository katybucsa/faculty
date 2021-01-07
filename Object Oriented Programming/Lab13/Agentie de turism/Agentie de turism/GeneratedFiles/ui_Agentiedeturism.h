/********************************************************************************
** Form generated from reading UI file 'Agentiedeturism.ui'
**
** Created by: Qt User Interface Compiler version 5.10.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_AGENTIEDETURISM_H
#define UI_AGENTIEDETURISM_H

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

class Ui_AgentiedeturismClass
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *AgentiedeturismClass)
    {
        if (AgentiedeturismClass->objectName().isEmpty())
            AgentiedeturismClass->setObjectName(QStringLiteral("AgentiedeturismClass"));
        AgentiedeturismClass->resize(600, 400);
        menuBar = new QMenuBar(AgentiedeturismClass);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        AgentiedeturismClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(AgentiedeturismClass);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        AgentiedeturismClass->addToolBar(mainToolBar);
        centralWidget = new QWidget(AgentiedeturismClass);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        AgentiedeturismClass->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(AgentiedeturismClass);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        AgentiedeturismClass->setStatusBar(statusBar);

        retranslateUi(AgentiedeturismClass);

        QMetaObject::connectSlotsByName(AgentiedeturismClass);
    } // setupUi

    void retranslateUi(QMainWindow *AgentiedeturismClass)
    {
        AgentiedeturismClass->setWindowTitle(QApplication::translate("AgentiedeturismClass", "Agentiedeturism", nullptr));
    } // retranslateUi

};

namespace Ui {
    class AgentiedeturismClass: public Ui_AgentiedeturismClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_AGENTIEDETURISM_H
