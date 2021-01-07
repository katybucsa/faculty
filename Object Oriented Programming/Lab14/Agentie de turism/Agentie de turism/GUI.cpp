#include "GUI.h"
#include <string>
#include <algorithm>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QFormLayout>


void WishlistCRUDGUI::initGUIComponents() {
	QHBoxLayout* wMainLayout = new QHBoxLayout;
	setLayout(wMainLayout);



	QWidget* wWidStanga = new QWidget;
	QVBoxLayout* layoutStanga = new QVBoxLayout;
	wWidStanga->setLayout(layoutStanga);
	wlist = new QListWidget;
	layoutStanga->addWidget(wlist);

	wMainLayout->addWidget(wWidStanga);

	QWidget* wWidDreapta = new QWidget;
	QFormLayout* vl = new QFormLayout;
	wWidDreapta->setLayout(vl);
	txtName = new QLineEdit;
	vl->addRow(new QLabel("Name"), txtName);
	btnAddWishlist = new QPushButton("Add to wishlist");
	btnEmptyWishlist = new QPushButton("Empty wishlist");
	btnAddRandom = new QPushButton("Add random wishlist");
	btnExportHTML = new QPushButton("Export wishlist to HTML");
	vl->addWidget(btnAddWishlist);
	vl->addWidget(btnEmptyWishlist);
	vl->addWidget(btnAddRandom);
	vl->addWidget(btnExportHTML);

	wMainLayout->addWidget(wWidDreapta);
}

void WishlistCRUDGUI::connectSignalsSlots() {
	QObject::connect(btnAddWishlist, &QPushButton::clicked, this, &WishlistCRUDGUI::guiAddToWishlist);
	QObject::connect(btnEmptyWishlist, &QPushButton::clicked, [&]() {
		serv.emptyWishlist();
	});
	QObject::connect(btnAddRandom, &QPushButton::clicked, [&]() {
		serv.addRandom(5);
	});
	QObject::connect(btnExportHTML, &QPushButton::clicked, [&]() {
		serv.exportWishlistHTML("oferte.html");
	});
}



void OfertaGUI::initGUIComponents() {


	QHBoxLayout* mainLayout = new QHBoxLayout;
	setLayout(mainLayout);

	QWidget* stangaVertical = new QWidget;
	QVBoxLayout* lyStangaVertical = new QVBoxLayout;
	stangaVertical->setLayout(lyStangaVertical);
	labelAll = new QLabel;
	labelAll->setText(QString{ "   Name \tDestination\t   Type\tPrice" });
	lyStangaVertical->addWidget(labelAll);

	listModel = new MyListModel;
	QWidget* widStanga = new QWidget;
	QVBoxLayout* vl = new QVBoxLayout;
	widStanga->setLayout(vl);
	listOfAll = new QListView;
	listOfAll->setUniformItemSizes(true);
	vl->addWidget(listOfAll);
	listOfAll->setModel(listModel);



	QWidget* widBtnStanga = new QWidget;
	QHBoxLayout* lyBtnsStanga = new QHBoxLayout;
	widBtnStanga->setLayout(lyBtnsStanga);

	btnSortByName = new QPushButton("Sort by name");
	lyBtnsStanga->addWidget(btnSortByName);
	lyBtnsStanga->addStretch();



	btnSortByDest = new QPushButton("Sort by destination");
	lyBtnsStanga->addWidget(btnSortByDest);
	lyBtnsStanga->addStretch();

	btnSortByPrice = new QPushButton("Sort by price");
	lyBtnsStanga->addWidget(btnSortByPrice);
	lyBtnsStanga->addStretch();


	vl->addWidget(widBtnStanga);
	lyStangaVertical->addWidget(widStanga);

	QWidget* wWishlist = new QWidget;
	QVBoxLayout* wLayout = new QVBoxLayout;
	wWishlist->setLayout(wLayout);
	wlist = new QListWidget;
	wLayout->addWidget(wlist);
	lyStangaVertical->addWidget(wWishlist);

	mainLayout->addWidget(stangaVertical);

	QWidget* widDreapta = new QWidget;
	QFormLayout* layoutDreapta = new QFormLayout;
	widDreapta->setLayout(layoutDreapta);
	txtName = new QLineEdit;
	layoutDreapta->addRow(new QLabel("Name:"), txtName);
	txtDest = new QLineEdit;
	layoutDreapta->addRow(new QLabel("Destination:"), txtDest);
	txtTip = new QLineEdit;
	layoutDreapta->addRow(new QLabel("Type:"), txtTip);
	txtPrice = new QLineEdit;
	layoutDreapta->addRow(new QLabel("Price:"), txtPrice);


	btnAdd = new QPushButton("Add offer");
	btnModify = new QPushButton("Modify offer");
	btnRemove = new QPushButton("Remove offer");
	btnGetOffer = new QPushButton("Get offer");
	btnUndo = new QPushButton("Undo");
	btnShowWishlist = new QPushButton("Show wishlist");
	btnAddWishlist = new QPushButton("Add to wishlist");
	btnAddRandom = new QPushButton("Add random to wishlist");
	btnEmptyWishlist = new QPushButton("Empty wishlist");
	btnExportHTML = new QPushButton("Export wishlist to HTML");
	btnWishlistRdOnly = new QPushButton("Show RdOnly Wishlist");

	QWidget* widMijloc = new QWidget;
	QFormLayout* layoutMijloc = new QFormLayout;
	widMijloc->setLayout(layoutMijloc);
	filtDest = new QLineEdit;
	layoutMijloc->addRow(new QLabel("Destination"), filtDest);
	mainLayout->addWidget(widMijloc);
	btnFilterByDest = new QPushButton("Filter by destination");
	layoutMijloc->addWidget(btnFilterByDest);


	filtPrice = new QLineEdit;
	layoutMijloc->addRow(new QLabel("Price"), filtPrice);
	btnFilterByPrice = new QPushButton("Filter by price");
	layoutMijloc->addWidget(btnFilterByPrice);


	layoutDreapta->addWidget(btnAdd);
	layoutDreapta->addWidget(btnModify);
	layoutDreapta->addWidget(btnRemove);
	layoutDreapta->addWidget(btnGetOffer);
	layoutDreapta->addWidget(btnUndo);
	layoutDreapta->addWidget(btnShowWishlist);
	layoutDreapta->addWidget(btnAddWishlist);
	layoutDreapta->addWidget(btnAddRandom);
	layoutDreapta->addWidget(btnEmptyWishlist);
	layoutDreapta->addWidget(btnExportHTML);
	layoutDreapta->addWidget(btnWishlistRdOnly);



	mainLayout->addWidget(widDreapta);
}




void OfertaGUI::connectSignalsSlots() {

	QObject::connect(btnSortByName, &QPushButton::clicked, [&]() {
		reloadList(serv.sortByName());
	});
	QObject::connect(btnSortByDest, &QPushButton::clicked, [&]() {
		reloadList(serv.sortByDestination());
	});
	QObject::connect(btnSortByPrice, &QPushButton::clicked, [&]() {
		reloadList(serv.sortByTypePrice());
	});
	QObject::connect(btnFilterByDest, &QPushButton::clicked, [&]() {

		std::vector<Oferta> v = serv.filterByDestination(filtDest->text().toStdString());
		if (v.size())
			reloadList(v);
		else
			QMessageBox::warning(this, "Warning", "Nu exista oferte cu aceasta destinatie!");
	});
	QObject::connect(btnFilterByPrice, &QPushButton::clicked, [&]() {
		std::vector<Oferta> v = serv.filterByPrice(filtPrice->text().toFloat());
		if (v.size())
			reloadList(v);
		else
			QMessageBox::warning(this, "Warning", "Nu exista oferte cu pretul mai mic sau egal cu " + filtPrice->text() + "!");
	});
	QObject::connect(btnAdd, &QPushButton::clicked, this, &OfertaGUI::guiAddOffer);
	QObject::connect(btnModify, &QPushButton::clicked, this, &OfertaGUI::guiModifyOffer);
	QObject::connect(btnRemove, &QPushButton::clicked, this, &OfertaGUI::guiRemoveOffer);
	QObject::connect(btnGetOffer, &QPushButton::clicked, this, &OfertaGUI::guiGetOffer);
	QObject::connect(btnUndo, &QPushButton::clicked, [&]() {
		try {
			serv.undo();
			uploadList(serv.getAl());
		}
		catch (const UndoException& e) { QMessageBox::warning(this, "Warning", QString::fromStdString(e.getMessage())); }
	});
	QObject::connect(btnShowWishlist, &QPushButton::clicked, [&]() {
		WishlistCRUDGUI* w = new WishlistCRUDGUI{ serv };
		w->show();
	});
	QObject::connect(btnAddWishlist, &QPushButton::clicked, this, &OfertaGUI::guiAddToWishlist);
	QObject::connect(btnEmptyWishlist, &QPushButton::clicked, [&]() {
		serv.emptyWishlist();
	});
	QObject::connect(btnAddRandom, &QPushButton::clicked, [&]() {
		serv.addRandom(5);
	});
	QObject::connect(btnExportHTML, &QPushButton::clicked, [&]() {
		serv.exportWishlistHTML("oferte.html");
	});
	QObject::connect(btnWishlistRdOnly, &QPushButton::clicked, [&]() {
		WishlistRdOnlyGUI* wl = new WishlistRdOnlyGUI(serv);
		wl->show();
	});
	QObject::connect(listOfAll->selectionModel(), &QItemSelectionModel::selectionChanged, this, &OfertaGUI::onSelectionChanged);

}

void OfertaGUI::onSelectionChanged() {
	auto sel = listOfAll->selectionModel()->selectedIndexes();
	if (sel.isEmpty()) {
		txtName->setText("");
		txtDest->setText("");
		txtTip->setText("");
		txtPrice->setText("");
		return;
	}
	auto firstSel = sel.at(0);
	Oferta o = listModel->getOferta(firstSel);
	txtName->setText(QString::fromStdString(o.getName()));
	txtDest->setText(QString::fromStdString(o.getDestination()));
	txtTip->setText(QString::fromStdString(o.getType()));
	txtPrice->setText(QString::number(o.getPrice()));
}


void OfertaGUI::uploadList(const std::vector<Oferta>& v) {
	listModel->setOffers(v);
}

void OfertaGUI::reloadList(const std::vector<Oferta>& v) {
	QWidget* ot = new QWidget;
	QVBoxLayout* vl = new QVBoxLayout;
	newList = new QListWidget;
	vl->addWidget(newList);
	ot->setLayout(vl);
	QListWidgetItem* item = new QListWidgetItem("Name\tDestination\t     Type\tPrice\n", newList);
	for (const auto& o : v) {
		QListWidgetItem* item = new QListWidgetItem(QString::fromStdString(o.getName() + "\t    " + o.getDestination() + "\t     " + o.getType()) + "\t" + QString::number(o.getPrice()), newList);
	}
	ot->show();
}

void OfertaGUI::uploadWishlist(const std::vector<Oferta>& v) {
	wlist->clear();
	QListWidgetItem* item = new QListWidgetItem("\t         WISHLIST", wlist);
	QListWidgetItem* item1 = new QListWidgetItem("Name\tDestination\t     Type\tPrice\n", wlist);
	for (const auto& o : v) {
		QListWidgetItem* item = new QListWidgetItem(QString::fromStdString(o.getName() + "\t    " + o.getDestination() + "\t     " + o.getType()) + "\t" + QString::number(o.getPrice()), wlist);
	}

}


void OfertaGUI::guiAddOffer() {
	try {
		serv.addOffer(txtName->text().toStdString(), txtDest->text().toStdString(), txtTip->text().toStdString(), txtPrice->text().toFloat());
		uploadList(serv.getAl());
	}
	catch (const FileException& e) { QMessageBox::warning(this, "Warning", QString::fromStdString(e.getMessage())); }
	catch (const ValidatorException& e) { QMessageBox::warning(this, "Warning", QString::fromStdString(e.getMessage())); }
	catch (const RepositoryException& e) { QMessageBox::warning(this, "Warning", QString::fromStdString(e.getMessage())); }
}

void OfertaGUI::guiModifyOffer() {
	try {
		serv.modifyOffer(txtName->text().toStdString(), txtDest->text().toStdString(), txtTip->text().toStdString(), txtPrice->text().toFloat());
		uploadList(serv.getAl());
	}
	catch (const FileException& e) { QMessageBox::warning(this, "Warning", QString::fromStdString(e.getMessage())); }
	catch (const ValidatorException& e) { QMessageBox::warning(this, "Warning", QString::fromStdString(e.getMessage())); }
	catch (const RepositoryException& e) { QMessageBox::warning(this, "Warning", QString::fromStdString(e.getMessage())); }
}



void OfertaGUI::guiRemoveOffer() {
	try {
		serv.removeOffer(txtName->text().toStdString());
		uploadList(serv.getAl());
	}
	catch (const FileException& e) { QMessageBox::warning(this, "Warning", QString::fromStdString(e.getMessage())); }
	catch (const ValidatorException& e) { QMessageBox::warning(this, "Warning", QString::fromStdString(e.getMessage())); }
	catch (const RepositoryException& e) { QMessageBox::warning(this, "Warning", QString::fromStdString(e.getMessage())); }
}

void OfertaGUI::guiGetOffer() {
	Oferta o;
	try {
		o = serv.getOffer(txtName->text().toStdString());
		QWidget* ot = new QWidget;
		QVBoxLayout* vl = new QVBoxLayout;
		newList = new QListWidget;
		vl->addWidget(newList);
		ot->setLayout(vl);
		QListWidgetItem* item = new QListWidgetItem("Name\tDestination\tType\tPrice\n", newList);
		QListWidgetItem* item1 = new QListWidgetItem(QString::fromStdString(o.getName() + "\t" + o.getDestination() + "\t" + o.getType()) + "\t" + QString::number(o.getPrice()), newList);
		ot->show();
	}
	catch (const RepositoryException& e) { QMessageBox::warning(this, "Warning", QString::fromStdString(e.getMessage())); }
}

void OfertaGUI::guiAddToWishlist() {
	try {
		serv.addToWishlist(txtName->text().toStdString());
		uploadWishlist(serv.allFromWishlist());
	}
	catch (const FileException& e) { QMessageBox::warning(this, "Warning", QString::fromStdString(e.getMessage())); }
	catch (const RepositoryException& e) { QMessageBox::warning(this, "Warning", QString::fromStdString(e.getMessage())); }

}




void WishlistCRUDGUI::guiAddToWishlist() {
	try {
		serv.addToWishlist(txtName->text().toStdString());
		reloadWishlist(serv.allFromWishlist());
	}
	catch (const FileException& e) { QMessageBox::warning(this, "Warning", QString::fromStdString(e.getMessage())); }
	catch (const RepositoryException& e) { QMessageBox::warning(this, "Warning", QString::fromStdString(e.getMessage())); }
}

void WishlistCRUDGUI::reloadWishlist(const std::vector<Oferta>& v) {
	wlist->clear();
	QListWidgetItem* item = new QListWidgetItem("Name\tDestination\t     Type\tPrice\n", wlist);
	for (const auto& o : v) {
		QListWidgetItem* item = new QListWidgetItem(QString::fromStdString(o.getName() + "\t    " + o.getDestination() + "\t     " + o.getType()) + "\t" + QString::number(o.getPrice()), wlist);
	}
}


