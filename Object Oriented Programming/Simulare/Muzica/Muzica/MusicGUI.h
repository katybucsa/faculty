#pragma once
#include "Service.h"
#include <qwidget.h>
#include <qlistwidget.h>
#include <qpushbutton.h>
#include <qformlayout.h>
#include <qobject.h>
#include <qlineedit.h>
#include <qlabel.h>
#include  <qlistwidget.h>
#include <qmessagebox.h>

class MusicGUI :public QWidget {
private:
	Service & serv;
	QListWidget* lst;
	QPushButton* btnAdd;
	QPushButton* btnLike;
	QPushButton* btnDislike;
	QLineEdit* artistTxt;
	QLineEdit* titluTxt;
	void initComponents();
	void connectSignalsSlots();
	void reloadList(const vector<Music>&);
	void guiAdd();
public:
	MusicGUI(Service& serv) :serv{ serv } {
		initComponents();
		connectSignalsSlots();
		reloadList(serv.sortLikes());
	}
};