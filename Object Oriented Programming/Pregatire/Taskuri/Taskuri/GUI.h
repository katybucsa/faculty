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
#include <qlistwidget.h>
#include <vector>

class Open:public QWidget,public Observer {
private:
	Service & serv;
	QListWidget* list;
	QPushButton* btnOpen;
	QPushButton* btnInProgress;
	QPushButton* btnClose;
	
	void initCmp();
	void connectSignalsSlots();
	void reload(const std::vector<Task>&);
	void update() override{
		reload(serv.filterOpen());
	}

public:
	Open(Service& s) :serv{ s } {
		initCmp();
		connectSignalsSlots();
		reload(serv.filterOpen());
		serv.addO(this);
	}
};

class Close :public QWidget, public Observer {
private:
	Service & serv;
	QListWidget* list;
	QPushButton* btnOpen;
	QPushButton* btnInProgress;
	QPushButton* btnClose;

	void initCmp();
	void connectSignalsSlots();
	void reload(const std::vector<Task>&);
	void update() override {
		reload(serv.filterClosed());
	}

public:
	Close(Service& s) :serv{ s } {
		initCmp();
		connectSignalsSlots();
		reload(serv.filterClosed());
		serv.addO(this);
	}
};

class InProgress :public QWidget, public Observer {
private:
	Service & serv;
	QListWidget* list;
	QPushButton* btnOpen;
	QPushButton* btnInProgress;
	QPushButton* btnClose;

	void initCmp();
	void connectSignalsSlots();
	void reload(const std::vector<Task>&);
	void update() override {
		reload(serv.filterInProgress());
	}

public:
	InProgress(Service& s) :serv{ s } {
		initCmp();
		connectSignalsSlots();
		reload(serv.filterInProgress());
		serv.addO(this);
	}
};


class TaskGUI :public QWidget,public Observer {
private:
	Service & serv;
	QTableWidget* table;
	QLabel* label;

	QPushButton* btnAddTask;
	QPushButton* btnOpen;
	QPushButton* btnInProgress;
	QPushButton* btnClose;
	QLineEdit* txtProg;
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
	void update() override {
		upload(serv.sortByState());
	}
public:
	TaskGUI(Service&s) : serv{ s } {
		initComponents();
		connectSignalsSlots();
		upload(serv.sortByState());
		serv.addO(this);
	}
};