#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_Gestiunetaskuri.h"

class Gestiunetaskuri : public QMainWindow
{
	Q_OBJECT

public:
	Gestiunetaskuri(QWidget *parent = Q_NULLPTR);

private:
	Ui::GestiunetaskuriClass ui;
};
