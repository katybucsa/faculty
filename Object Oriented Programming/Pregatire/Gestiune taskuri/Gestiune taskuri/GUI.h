#pragma once
#include <Service.h>
#include <qtablewidget.h>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QVBoxLayout>
#include <qlabel.h>
#include <qlineedit.h>
#include <qformlayout.h>
#include <qpushbutton.h>
#include <qmessagebox.h>
#include <vector>

class TaskGUI:public QWidget {
private:
	Service & serv;
	QTableWidget* table;
	QLabel* label;

	QPushButton* btnAddTask;
	QLineEdit* txtId;
	QLineEdit* txtDesc;
	QLineEdit* txtP1;
	QLineEdit* txtP2;
	QLineEdit* txtP3;
	QLineEdit* txtP4;
	QLineEdit* txtStare;

	void initComponents();
	void connectSignalsSlots();
	void upload(const std::vector<Task>&);
	void guiAddTask();
public:
	TaskGUI(Service&s): serv{ s } {
		initComponents();
		connectSignalsSlots();
		upload(serv.sortByState());
	}
};