#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_Gestiunemelodii.h"

class Gestiunemelodii : public QMainWindow
{
	Q_OBJECT

public:
	Gestiunemelodii(QWidget *parent = Q_NULLPTR);

private:
	Ui::GestiunemelodiiClass ui;
};
