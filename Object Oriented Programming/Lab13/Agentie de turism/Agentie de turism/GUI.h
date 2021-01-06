#pragma once
#include "Service.h"
#include <qwidget.h>
#include <qwindow.h>
#include <qmainwindow.h>
#include <qaction.h>
#include <qmenubar.h>
#include <qtablewidget.h>
#include <qmdiarea.h>
#include <qlistwidget.h>
#include <qpushbutton.h>
#include <qlineedit.h>
#include <qboxlayout.h>
#include <qlabel.h>
#include <vector>
#include <qtableview.h>
#include <qheaderview.h>
#include <qmessagebox.h>
#include <qpainter.h>
#include <qevent.h>
#include <qdebug.h>



class WishlistCRUDGUI :public QWidget, public Observer {
private:
	Service & serv;
	QListWidget* wlist;
	QLineEdit* txtName;
	QPushButton* btnAddWishlist;
	QPushButton* btnEmptyWishlist;
	QPushButton* btnAddRandom;
	QPushButton* btnExportHTML;
	void guiAddToWishlist();
	void reloadWishlist(const std::vector<Oferta>&);
	void update() override {
		reloadWishlist(serv.allFromWishlist());
	}
	void initGUIComponents();
	void connectSignalsSlots();

public:
	WishlistCRUDGUI(Service& serv) :serv{ serv } {
		initGUIComponents();
		connectSignalsSlots();
		reloadWishlist(serv.allFromWishlist());
		serv.addO(this);
	}
};


class WishlistRdOnlyGUI :public QWidget, public Observer {
private:
	Service & serv;
	void paintEvent(QPaintEvent* ev) override {
		srand(time(NULL));
		QPainter p{ this };
		std::vector<Oferta> v = serv.allFromWishlist();
		for (const auto& o : v) {
			p.drawRoundRect(rand() % 100, rand() % 300, rand() % 150, rand() % 250);
		}
	}

	void update() override {
		paintEvent(NULL);
	}
	

public:
	WishlistRdOnlyGUI(Service& serv) :serv{ serv } {
		serv.addO(this);
		paintEvent(NULL);
	}
};



class OfertaGUI :public QWidget, public Observer {
private:
	Service & serv;
	QListWidget* listOfAll;
	QListWidget* newList;
	QListWidget* wlist;
	QPushButton* btnSortByName;
	QPushButton* btnSortByDest;
	QPushButton* btnSortByPrice;
	QPushButton* btnAdd;
	QPushButton* btnModify;
	QPushButton* btnRemove;
	QPushButton* btnGetOffer;
	QPushButton* btnShowWishlist;
	QPushButton* btnWishlistRdOnly;
	QPushButton* btnAddWishlist;
	QPushButton* btnEmptyWishlist;
	QPushButton* btnAddRandom;
	QPushButton* btnExportHTML;
	QPushButton* btnUndo;
	QLineEdit* txtName;
	QLineEdit* txtDest;
	QLineEdit* txtTip;
	QLineEdit* txtPrice;
	QPushButton* btnFilterByDest;
	QLineEdit* filtDest;
	QPushButton* btnFilterByPrice;
	QLineEdit* filtPrice;
	void uploadList(const std::vector<Oferta>&);
	void reloadList(const std::vector<Oferta>&);
	void uploadWishlist(const std::vector<Oferta>&);
	void update() override {
		uploadWishlist(serv.allFromWishlist());
	}
	void guiAddOffer();
	void guiModifyOffer();
	void guiRemoveOffer();
	void guiGetOffer();
	void guiAddToWishlist();
	void initGUIComponents();
	void connectSignalsSlots();
public:
	OfertaGUI(Service& serv) :serv{ serv } {
		initGUIComponents();
		connectSignalsSlots();
		uploadList(serv.getAl());
		uploadWishlist(serv.allFromWishlist());
		serv.addO(this);
	}

};