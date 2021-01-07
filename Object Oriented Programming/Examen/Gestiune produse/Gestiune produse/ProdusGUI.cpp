#include "ProdusGUI.h"

void ProdusGUI::initCmp(){
	QHBoxLayout* mainLy = new QHBoxLayout;
	setLayout(mainLy);

	QWidget* widStanga = new QWidget;
	QVBoxLayout* lyStanga = new QVBoxLayout;
	widStanga->setLayout(lyStanga);

	table = new QTableWidget(1,5);
	lyStanga->addWidget(table);

	mainLy->addWidget(widStanga);
}

void ProdusGUI::connectSignalsSlots()
{
}

void ProdusGUI::reload(std::vector<Produs> v){
	table->clear();
	int linii = 0;
	table->setRowCount(v.size());
	std::string vocale = "aeiouAEIOU";
	for (auto e : v) {
		int count = 0;
		QTableWidgetItem* item1 = new QTableWidgetItem(QString::number(e.getId()));
		QTableWidgetItem* item2 = new QTableWidgetItem(QString::fromStdString(e.getNume()));
		QTableWidgetItem* item3 = new QTableWidgetItem(QString::fromStdString(e.getTip()));
		QTableWidgetItem* item4 = new QTableWidgetItem(QString::number(e.getPret()));
		for (auto litera : e.getNume())
			if (std::find(vocale.begin(), vocale.end(), litera) != vocale.end())
				count++;
		QTableWidgetItem* item5 = new QTableWidgetItem(QString::number(count));
		table->setItem(linii, 0, item1);
		table->setItem(linii, 1, item2);
		table->setItem(linii, 2, item3);
		table->setItem(linii, 3, item4);
		table->setItem(linii, 4, item5);
		item1->setData(Qt::UserRole, QString::number(e.getId()));
		item2->setData(Qt::UserRole, QString::fromStdString(e.getNume()));
		item3->setData(Qt::UserRole, QString::fromStdString(e.getTip()));
		item4->setData(Qt::UserRole, QString::number(e.getPret()));
		linii++;
	}
}
