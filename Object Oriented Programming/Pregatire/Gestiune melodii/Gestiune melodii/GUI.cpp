#include "GUI.h"
#include <qmessagebox.h>

void MusicGUI::initCmp(){
	QHBoxLayout* mainLayout = new QHBoxLayout;
	setLayout(mainLayout);



	QWidget* widStanga = new QWidget;
	QVBoxLayout*layoutStanga = new QVBoxLayout;
	widStanga->setLayout(layoutStanga);
	layoutStanga->addWidget(widStangaSus);
	label = new QLabel("\t   Id\t\t        Titlu\t\t   Artist\t\t\tGen");
	layoutStanga->addWidget(label);

	//table = new QTableWidget(2, 4);
	table = new QTableView;
	model = new MyTableModel;
	table->setModel(model);
	table->setSelectionBehavior(QAbstractItemView::SelectRows);
	layoutStanga->addWidget(table);
	layoutStanga->addWidget(widStangaJos);

	QWidget* widDreapta = new QWidget;
	QFormLayout* lyDreapta = new QFormLayout;
	widDreapta->setLayout(lyDreapta);
	lyDreapta->addWidget(widDreaptaSus);

	txtId = new QLineEdit;
	txtTitlu = new QLineEdit;
	txtArtist = new QLineEdit;
	txtGen = new QLineEdit;
	lyDreapta->addRow(new QLabel("Id:"), txtId);
	lyDreapta->addRow(new QLabel("Titlu:"), txtTitlu);
	lyDreapta->addRow(new QLabel("Artist:"), txtArtist);
	lyDreapta->addRow(new QLabel("Gen:"), txtGen);
	btnAddMusic = new QPushButton("Add music");
	btnRemMusic = new QPushButton("Remove music");
	btnRemMusic->setEnabled(false);
	lyDreapta->addWidget(btnAddMusic);
	lyDreapta->addWidget(btnRemMusic);

	lyDreapta->addWidget(widDreaptaJos);

	mainLayout->addWidget(widStanga);
	mainLayout->addWidget(widDreapta);
}

void MusicGUI::connectSignalsSlots(){
	/*QObject::connect(table->selectionModel(), &QItemSelectionModel::currentRowChanged, [&]() {
		if (table->selectionModel()->selectedIndexes().isEmpty())
			return;
		auto selItem1 = table->selectionModel()->selectedIndexes().at(0).data(Qt::UserRole);
		auto selItem2 = table->selectionModel()->selectedIndexes().at(1).data(Qt::UserRole);
		auto selItem3 = table->selectionModel()->selectedIndexes().at(2).data(Qt::UserRole);
		auto selItem4 = table->selectionModel()->selectedIndexes().at(3).data(Qt::UserRole);
		//serv.modify(selItem1, selItem2, selItem3, selItem4);
		//reload(serv.sortByArtist());
	});*/
	QObject::connect(table->selectionModel(), &QItemSelectionModel::selectionChanged, this, &MusicGUI::onSelectionChanged);
	QObject::connect(btnAddMusic, &QPushButton::clicked, this, &MusicGUI::guiAdd);
	QObject::connect(btnRemMusic, &QPushButton::clicked, this, &MusicGUI::guiRem);
}

void MusicGUI::reload(const std::vector<Music>& all){
	/*table->clear();
	int currentRow = 0;
	table->setRowCount(all.size());
	for (const auto& m : all) {
		QTableWidgetItem* item1 = new QTableWidgetItem(QString::number(m.getId()));
		QTableWidgetItem* item2 = new QTableWidgetItem(QString::fromStdString(m.getTitlu()));
		QTableWidgetItem* item3 = new QTableWidgetItem(QString::fromStdString(m.getArtist()));
		QTableWidgetItem* item4 = new QTableWidgetItem(QString::fromStdString(m.getGen()));
		item1->setData(Qt::UserRole, QString::number(m.getId()));
		item2->setData(Qt::UserRole, QString::fromStdString(m.getTitlu()));
		item3->setData(Qt::UserRole, QString::fromStdString(m.getArtist()));
		item3->setData(Qt::UserRole, QString::fromStdString(m.getGen()));
		table->setItem(currentRow, 0, item1);
		table->setItem(currentRow, 1, item2);
		table->setItem(currentRow, 2, item3);
		table->setItem(currentRow, 3, item4);
		currentRow++;
	}*/
	model->setMusics(all,serv);
}

void MusicGUI::onSelectionChanged(){
	auto sel = table->selectionModel()->selectedIndexes();
	if (sel.isEmpty()) {
		txtId->setText("");
		txtTitlu->setText("");
		txtArtist->setText("");
		txtGen->setText("");
		btnRemMusic->setEnabled(false);
		return;
	}
	btnRemMusic->setEnabled(true);
	auto firstSel = sel.at(0);
	Music m = model->getMusic(firstSel);
	txtId->setText(QString::number(m.getId()));
	txtTitlu->setText(QString::fromStdString(m.getTitlu()));
	txtArtist->setText(QString::fromStdString(m.getArtist()));
	txtGen->setText(QString::fromStdString(m.getGen()));
	/*int nr1 = serv.getNrCuArtistul(m.getArtist());
	int nr2 = serv.getNrCuGenul(m.getGen());
	/*model->setData(firstSel,4, nr1);
	model->setData(firstSel,5, nr2);
	model->setData(firstSel, Qt::UserRole, nr1);
	model->setData(firstSel, Qt::UserRole, nr2);*/
}

void MusicGUI::guiAdd(){
	try {
		serv.add(txtId->text().toInt(), txtTitlu->text().toStdString(), txtArtist->text().toStdString(), txtGen->text().toStdString());
		reload(serv.sortByArtist());
	}catch (const MusicException& e) { QMessageBox::warning(this, "Warning", QString::fromStdString(e.getMsg())); }
}

void MusicGUI::guiRem(){
	auto sel = table->selectionModel()->selectedIndexes().at(0);
	Music m = model->getMusic(sel);
	serv.remove(m);
	reload(serv.sortByArtist());
}
