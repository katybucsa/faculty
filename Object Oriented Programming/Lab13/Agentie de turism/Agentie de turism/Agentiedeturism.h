#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_Agentiedeturism.h"

class Agentiedeturism : public QMainWindow
{
	Q_OBJECT

public:
	Agentiedeturism(QWidget *parent = Q_NULLPTR);

private:
	Ui::AgentiedeturismClass ui;
};
