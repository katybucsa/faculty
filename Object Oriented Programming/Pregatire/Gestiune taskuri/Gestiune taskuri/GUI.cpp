#include "GUI.h"



void TaskGUI::initComponents(){
	QHBoxLayout* mainLayout = new QHBoxLayout;
	setLayout(mainLayout);
	label = new QLabel("\t       Id\t\t         Descriere\t\t      Stare\t     Nr. programatori");

	QWidget* widStanga = new QWidget;
	QVBoxLayout* lyStanga = new QVBoxLayout;
	widStanga->setLayout(lyStanga);

	table = new QTableWidget(1, 4);
	lyStanga->addWidget(label);
	lyStanga->addWidget(table);

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
	layoutDreapta->addWidget(btnAddTask);

	mainLayout->addWidget(widStanga);
	mainLayout->addWidget(widDreapta);
}

void TaskGUI::connectSignalsSlots(){
	QObject::connect(btnAddTask, &QPushButton::clicked, this, &TaskGUI::guiAddTask);
}

void TaskGUI::upload(const std::vector<Task>& all){
	table->clear();
	table->setRowCount(all.size());
	int currentRow = 0;
	for (const auto& t : all) {
		QTableWidgetItem* item1 = new QTableWidgetItem(QString::number(t.getId()));
		QTableWidgetItem* item2 = new QTableWidgetItem(QString::fromStdString(t.getDescriere()));
		QTableWidgetItem* item3 = new QTableWidgetItem(QString::fromStdString(t.getStare()));
		QTableWidgetItem* item4 = new QTableWidgetItem(QString::number(t.getProgramatori().size()));
		table->setItem(currentRow, 0, item1);
		table->setItem(currentRow, 1, item2);
		table->setItem(currentRow, 2, item3);
		table->setItem(currentRow, 3, item4);
		currentRow++;
	}
}

void TaskGUI::guiAddTask(){
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
		//upload(serv.sortByState());
	}catch (const TaskException& e) { QMessageBox::warning(this, "Warning", QString::fromStdString(e.getMsg())); }

}
