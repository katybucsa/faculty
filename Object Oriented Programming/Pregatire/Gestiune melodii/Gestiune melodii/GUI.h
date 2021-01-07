#pragma once
#include "Service.h"
#include "MyTableModel.h"
#include <qtablewidget.h>
#include <qlineedit.h>
#include <qlabel.h>
#include <qlineedit.h>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QVBoxLayout>
#include <qtableview.h>
#include <qformlayout.h>
#include <qpushbutton.h>
#include <qpainter.h>

class MusicGUI:public QWidget {
private:
	Service & serv;
	//QTableWidget* table;
	QTableView* table;
	MyTableModel* model;
	QLabel* label;
	QLineEdit* txtId;
	QLineEdit* txtTitlu;
	QLineEdit* txtArtist;
	QLineEdit* txtGen;
	QPushButton* btnAddMusic;
	QPushButton* btnRemMusic;

	QWidget* widStangaSus = new QWidget;
	QWidget* widStangaJos = new QWidget;
	QWidget* widDreaptaSus = new QWidget;
	QWidget* widDreaptaJos = new QWidget;


	void initCmp();
	void connectSignalsSlots();
	void reload(const std::vector<Music>&);

	void paintEvent(QPaintEvent*) override {
		srand(time(NULL));
		QPainter p1{ widStangaSus };
		QPainter p2{ widStangaJos };
		QPainter p3{ widDreaptaSus };
		QPainter p4{ widDreaptaJos };
		std::vector<Music> v = serv.getAll();
		for (const auto& m : v) {
			if (m.getGen() == "pop") {
				p1.drawRect(5, 5, 20,20);
			}
			if (m.getGen() == "rock") {
				p2.setRenderHints(QPainter::Antialiasing);
				p2.translate(width() / 2, height() / 2);
			}
			if (m.getGen() == "folk") {
				p3.setRenderHints(QPainter::Antialiasing);
				p3.translate(width() / 2, height() / 2);
			}
			if (m.getGen() == "disco") {
				p4.setRenderHints(QPainter::Antialiasing);
				p4.translate(width() / 2, height() / 2);
			}
		}
	}

	void onSelectionChanged();
	void guiAdd();
	void guiRem();
public:
	MusicGUI(Service&s) :serv{ s } {
		paintEvent(NULL);
		initCmp();
		connectSignalsSlots();
		reload(serv.sortByArtist());
		
	}
};