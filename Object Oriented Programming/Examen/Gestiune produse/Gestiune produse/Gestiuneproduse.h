#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_Gestiuneproduse.h"

class Gestiuneproduse : public QMainWindow
{
	Q_OBJECT

public:
	Gestiuneproduse(QWidget *parent = Q_NULLPTR);

private:
	Ui::GestiuneproduseClass ui;
};
