#pragma once
#include <Service.h>
#include <qwidget.h>
#include <QHBoxLayout> 
#include <QVBoxLayout> 
#include <QFormLayout> 
#include <qtablewidget.h>

class ProdusGUI :public QWidget {
private:
	Service & serv;
	QTableWidget* table;


	void initCmp();
	void connectSignalsSlots();
	void reload(std::vector<Produs>);
public:
	ProdusGUI(Service s) :serv{ s } {
		initCmp();
		connectSignalsSlots();
		reload(serv.sortByPrice());
	}
};