#include "GUI.h"


void Open::initCmp() {
	QHBoxLayout* mainLy = new QHBoxLayout;
	setLayout(mainLy);

	list = new QListWidget;
	mainLy->addWidget(list);

	QWidget* dreapta = new QWidget;
	QVBoxLayout* vly = new QVBoxLayout;
	dreapta->setLayout(vly);
	btnOpen = new QPushButton("Open");
	btnInProgress = new QPushButton("Inprogress");
	btnClose = new QPushButton("Close");
	vly->addWidget(btnOpen);
	vly->addWidget(btnInProgress);
	vly->addWidget(btnClose);
	btnOpen->setEnabled(false);
	btnInProgress->setEnabled(false);
	btnClose->setEnabled(false);

	mainLy->addWidget(dreapta);
}

void Open::connectSignalsSlots(){
	QObject::connect(list, &QListWidget::itemSelectionChanged, [&]() {
		if (list->selectedItems().isEmpty()) {
			btnOpen->setEnabled(false);
			btnInProgress->setEnabled(false);
			btnClose->setEnabled(false);
			}
		else {
			btnOpen->setEnabled(true);
			btnInProgress->setEnabled(true);
			btnClose->setEnabled(true);
		}
	});
	QObject::connect(btnOpen, &QPushButton::clicked, [&]() {
		auto selItem = list->selectedItems().at(0)->data(Qt::UserRole).toInt();
		std::list<std::string> l;
		Task t{ selItem,"def",l,"def" };
		serv.modify(t, "open");
	});
	QObject::connect(btnClose, &QPushButton::clicked, [&]() {
		auto selItem = list->selectedItems().at(0)->data(Qt::UserRole).toInt();
		std::list<std::string> l;
		Task t{ selItem,"def",l,"def" };
		serv.modify(t, "closed");
	});
	QObject::connect(btnInProgress, &QPushButton::clicked, [&]() {
		auto selItem = list->selectedItems().at(0)->data(Qt::UserRole).toInt();
		std::list<std::string> l;
		Task t{ selItem,"def",l,"def" };
		serv.modify(t, "inprogress");
	});
}

void Open::reload(const std::vector<Task>&all){
	list->clear();

	for (const auto& t : all) {
		QListWidgetItem* item = new QListWidgetItem(QString::number(t.getId()) + "\t" + QString::fromStdString(t.getDescriere() + "\t" + t.getStare() + "\t") + QString::number(t.getProgramatori().size()),list);	
		item->setData(Qt::UserRole, QString::number(t.getId()));
	}
}

void Close::initCmp() {
	QHBoxLayout* mainLy = new QHBoxLayout;
	setLayout(mainLy);

	list = new QListWidget;
	mainLy->addWidget(list);

	QWidget* dreapta = new QWidget;
	QVBoxLayout* vly = new QVBoxLayout;
	dreapta->setLayout(vly);
	btnOpen = new QPushButton("Open");
	btnInProgress = new QPushButton("Inprogress");
	btnClose = new QPushButton("Close");
	vly->addWidget(btnOpen);
	vly->addWidget(btnInProgress);
	vly->addWidget(btnClose);
	btnOpen->setEnabled(false);
	btnInProgress->setEnabled(false);
	btnClose->setEnabled(false);

	mainLy->addWidget(dreapta);
}

void Close::connectSignalsSlots() {
	QObject::connect(list, &QListWidget::itemSelectionChanged, [&]() {
		if (list->selectedItems().isEmpty()) {
			btnOpen->setEnabled(false);
			btnInProgress->setEnabled(false);
			btnClose->setEnabled(false);
		}
		else {
			btnOpen->setEnabled(true);
			btnInProgress->setEnabled(true);
			btnClose->setEnabled(true);
		}
	});
	QObject::connect(btnOpen, &QPushButton::clicked, [&]() {
		auto selItem = list->selectedItems().at(0)->data(Qt::UserRole).toInt();
		std::list<std::string> l;
		Task t{ selItem,"def",l,"def" };
		serv.modify(t, "open");
	});
	QObject::connect(btnClose, &QPushButton::clicked, [&]() {
		auto selItem = list->selectedItems().at(0)->data(Qt::UserRole).toInt();
		std::list<std::string> l;
		Task t{ selItem,"def",l,"def" };
		serv.modify(t, "closed");
	});
	QObject::connect(btnInProgress, &QPushButton::clicked, [&]() {
		auto selItem = list->selectedItems().at(0)->data(Qt::UserRole).toInt();
		std::list<std::string> l;
		Task t{ selItem,"def",l,"def" };
		serv.modify(t, "inprogress");
	});
}

void Close::reload(const std::vector<Task>&all) {
	list->clear();

	for (const auto& t : all) {
		QListWidgetItem* item = new QListWidgetItem(QString::number(t.getId()) + "\t" + QString::fromStdString(t.getDescriere() + "\t" + t.getStare() + "\t") + QString::number(t.getProgramatori().size()), list);
		item->setData(Qt::UserRole, QString::number(t.getId()));
	}
}

void InProgress::initCmp() {
	QHBoxLayout* mainLy = new QHBoxLayout;
	setLayout(mainLy);

	list = new QListWidget;
	mainLy->addWidget(list);

	QWidget* dreapta = new QWidget;
	QVBoxLayout* vly = new QVBoxLayout;
	dreapta->setLayout(vly);
	btnOpen = new QPushButton("Open");
	btnInProgress = new QPushButton("Inprogress");
	btnClose = new QPushButton("Close");
	vly->addWidget(btnOpen);
	vly->addWidget(btnInProgress);
	vly->addWidget(btnClose);
	btnOpen->setEnabled(false);
	btnInProgress->setEnabled(false);
	btnClose->setEnabled(false);

	mainLy->addWidget(dreapta);
}

void InProgress::connectSignalsSlots() {
	QObject::connect(list, &QListWidget::itemSelectionChanged, [&]() {
		if (list->selectedItems().isEmpty()) {
			btnOpen->setEnabled(false);
			btnInProgress->setEnabled(false);
			btnClose->setEnabled(false);
		}
		else {
			btnOpen->setEnabled(true);
			btnInProgress->setEnabled(true);
			btnClose->setEnabled(true);
		}
	});
	QObject::connect(btnOpen, &QPushButton::clicked, [&]() {
		auto selItem = list->selectedItems().at(0)->data(Qt::UserRole).toInt();
		std::list<std::string> l;
		Task t{ selItem,"def",l,"def" };
		serv.modify(t, "open");
	});
	QObject::connect(btnClose, &QPushButton::clicked, [&]() {
		auto selItem = list->selectedItems().at(0)->data(Qt::UserRole).toInt();
		std::list<std::string> l;
		Task t{ selItem,"def",l,"def" };
		serv.modify(t, "closed");
	});
	QObject::connect(btnInProgress, &QPushButton::clicked, [&]() {
		auto selItem = list->selectedItems().at(0)->data(Qt::UserRole).toInt();
		std::list<std::string> l;
		Task t{ selItem,"def",l,"def" };
		serv.modify(t, "inprogress");
	});
}

void InProgress::reload(const std::vector<Task>&all) {
	list->clear();

	for (const auto& t : all) {
		QListWidgetItem* item = new QListWidgetItem(QString::number(t.getId()) + "\t" + QString::fromStdString(t.getDescriere() + "\t" + t.getStare() + "\t") + QString::number(t.getProgramatori().size()), list);
		item->setData(Qt::UserRole, QString::number(t.getId()));
	}
}


void TaskGUI::initComponents() {
	QHBoxLayout* mainLayout = new QHBoxLayout;
	setLayout(mainLayout);
	label = new QLabel("\t       Id\t\t         Descriere\t\t      Stare\t     Nr. programatori");

	QWidget* widStanga = new QWidget;
	QVBoxLayout* lyStanga = new QVBoxLayout;
	widStanga->setLayout(lyStanga);

	table = new QTableWidget(1, 4);
	lyStanga->addWidget(label);
	lyStanga->addWidget(table);
	table->setSelectionBehavior(QAbstractItemView::SelectRows);
	table->setFixedSize(522, 522);

	QWidget* widMijloc = new QWidget;
	QFormLayout* layoutMijloc = new QFormLayout;
	widMijloc->setLayout(layoutMijloc);
	txtProg = new QLineEdit;
	layoutMijloc->addRow(new QLabel("Programator:"), txtProg);

	QWidget* widDreapta = new QWidget;
	QFormLayout* layoutDreapta = new QFormLayout;
	widDreapta->setLayout(layoutDreapta);
	txtId = new QLineEdit;
	txtDesc = new QLineEdit;
	txtP1 = new QLineEdit;
	txtP2 = new QLineEdit;
	txtP3 = new QLineEdit;
	txtP4 = new QLineEdit;
	txtStare = new QLineEdit;
	layoutDreapta->addRow(new QLabel("Id:"), txtId);
	layoutDreapta->addRow(new QLabel("Descriere:"), txtDesc);
	layoutDreapta->addRow(new QLabel("Programator 1:"), txtP1);
	layoutDreapta->addRow(new QLabel("Programator 2:"), txtP2);
	layoutDreapta->addRow(new QLabel("Programator 3:"), txtP3);
	layoutDreapta->addRow(new QLabel("Programator 4:"), txtP4);
	layoutDreapta->addRow(new QLabel("Stare:"), txtStare);

	btnAddTask = new QPushButton("Add task");
	btnOpen = new QPushButton("Open");
	btnInProgress = new QPushButton("Inprogress");
	btnClose = new QPushButton("Close");
	layoutDreapta->addWidget(btnAddTask);
	layoutDreapta->addWidget(btnOpen);
	layoutDreapta->addWidget(btnInProgress);
	layoutDreapta->addWidget(btnClose);
	btnOpen->setEnabled(false);
	btnInProgress->setEnabled(false);
	btnClose->setEnabled(false);

	mainLayout->addWidget(widStanga);
	mainLayout->addWidget(widMijloc);
	mainLayout->addWidget(widDreapta);
}

void TaskGUI::connectSignalsSlots() {
	QObject::connect(btnAddTask, &QPushButton::clicked, this, &TaskGUI::guiAddTask);
	QObject::connect(txtProg, &QLineEdit::textChanged , [&]() {
		if (txtProg->text().isEmpty())
			upload(serv.sortByState());
		else
			upload(serv.filterByProg(txtProg->text().toStdString()));
	});
	QObject::connect(table, &QTableWidget::itemSelectionChanged, [&]() {
		if (table->selectedItems().isEmpty()) {
			txtId->setText("");
			txtDesc->setText("");
			txtP1->setText("");
			txtP2->setText("");
			txtP3->setText("");
			txtP4->setText("");
			txtStare->setText("");
			btnOpen->setEnabled(false);
			btnInProgress->setEnabled(false);
			btnClose->setEnabled(false);
			return;
		}
		QTableWidgetItem* selItem1 = table->selectedItems().at(0);
		QTableWidgetItem* selItem2 = table->selectedItems().at(1);
		QTableWidgetItem* selItem3 = table->selectedItems().at(2);
		txtId->setText(selItem1->text());
		txtDesc->setText(selItem2->text());
		txtStare->setText(selItem3->text());
		btnOpen->setEnabled(true);
		btnInProgress->setEnabled(true);
		btnClose->setEnabled(true);
	});

	QObject::connect(btnOpen, &QPushButton::clicked, [&]() {
		auto selItem = table->selectedItems().at(0)->text().toInt();
		std::list<std::string> l;
		Task t{ selItem,"def",l,"def" };
		serv.modify(t, "open");
	});
	QObject::connect(btnClose, &QPushButton::clicked, [&]() {
		auto selItem = table->selectedItems().at(0)->text().toInt();
		std::list<std::string> l;
		Task t{ selItem,"def",l,"def" };
		serv.modify(t, "closed");
	});
	QObject::connect(btnInProgress, &QPushButton::clicked, [&]() {
		auto selItem = table->selectedItems().at(0)->text().toInt();
		std::list<std::string> l;
		Task t{ selItem,"def",l,"def" };
		serv.modify(t, "inprogress");
	});
}

void TaskGUI::upload(const std::vector<Task>& all) {
	table->clear();
	table->setRowCount(all.size());
	int currentRow = 0;
	for (const auto& t : all) {
		QTableWidgetItem* item1 = new QTableWidgetItem(QString::number(t.getId()));
		QTableWidgetItem* item2 = new QTableWidgetItem(QString::fromStdString(t.getDescriere()));
		QTableWidgetItem* item3 = new QTableWidgetItem(QString::fromStdString(t.getStare()));
		QTableWidgetItem* item4 = new QTableWidgetItem(QString::number(t.getProgramatori().size()));
		item1->setData(Qt::UserRole, QString::number(t.getId()));
		item2->setData(Qt::UserRole, QString::fromStdString(t.getDescriere()));
		item3->setData(Qt::UserRole, QString::fromStdString(t.getStare()));
		table->setItem(currentRow, 0, item1);
		table->setItem(currentRow, 1, item2);
		table->setItem(currentRow, 2, item3);
		table->setItem(currentRow, 3, item4);
		currentRow++;
	}
}

void TaskGUI::guiAddTask() {
	try {
		std::list<std::string> prog;
		if (txtP1->text().toStdString() != "")
			prog.push_back(txtP1->text().toStdString());
		if (txtP2->text().toStdString() != "")
			prog.push_back(txtP2->text().toStdString());
		if (txtP3->text().toStdString() != "")
			prog.push_back(txtP3->text().toStdString());
		if (txtP4->text().toStdString() != "")
			prog.push_back(txtP4->text().toStdString());
		serv.add(txtId->text().toInt(), txtDesc->text().toStdString(), prog, txtStare->text().toStdString());
		upload(serv.sortByState());
	}
	catch (const TaskException& e) { QMessageBox::warning(this, "Warning", QString::fromStdString(e.getMsg())); }
}