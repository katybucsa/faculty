#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_Taskuri.h"

class Taskuri : public QMainWindow
{
	Q_OBJECT

public:
	Taskuri(QWidget *parent = Q_NULLPTR);

private:
	Ui::TaskuriClass ui;
};
