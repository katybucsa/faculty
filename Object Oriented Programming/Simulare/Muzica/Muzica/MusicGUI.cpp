#include "MusicGUI.h"
#include <QtWidgets/QHBoxLayout>

void MusicGUI::initComponents(){
	QHBoxLayout* ly = new QHBoxLayout;
	setLayout(ly);

	QWidget* wdr = new QWidget;
	QVBoxLayout* vl = new QVBoxLayout;
	wdr->setLayout(vl);
	lst = new QListWidget;
	vl->addWidget(lst);


	ly->addWidget(wdr);

	QWidget* wbutoane = new QWidget;
	QFormLayout* form = new QFormLayout;
	wbutoane->setLayout(form);

	artistTxt = new QLineEdit;
	form->addRow(new QLabel("Artist"), artistTxt);
	titluTxt = new QLineEdit;
	form->addRow(new QLabel("Titlu"), titluTxt);

	btnAdd = new QPushButton("Add");
	btnLike = new QPushButton("Like");
	btnDislike = new QPushButton("Dislike");

	form->addWidget(btnAdd);
	form->addWidget(btnLike);
	form->addWidget(btnDislike);

	ly->addWidget(wbutoane);
}

void MusicGUI::connectSignalsSlots(){
	QObject::connect(btnAdd, &QPushButton::clicked, this, &MusicGUI::guiAdd);
	QObject::connect(btnLike, &QPushButton::clicked, [&]() {
		Music m = Music(artistTxt->text().toStdString(), titluTxt->text().toStdString(), 0);
		try {
			serv.like(m);
			reloadList(serv.sortLikes());
		}catch (const RepoException& e) { QMessageBox::warning(this, "Warning", QString::fromStdString(e.getMessage())); }
	});
	QObject::connect(btnDislike, &QPushButton::clicked, [&]() {
		Music m = Music(artistTxt->text().toStdString(), titluTxt->text().toStdString(), 0);
		try {
			serv.dislike(m);
			reloadList(serv.sortLikes());
		}catch (const RepoException& e) { QMessageBox::warning(this, "Warning", QString::fromStdString(e.getMessage())); }
	});
}

void MusicGUI::reloadList(const vector<Music>& all){
	lst->clear();
	QListWidgetItem* item = new QListWidgetItem("Artist\tTitlu\tLikes",lst);
	for (const auto& m : all) 
		QListWidgetItem* it = new QListWidgetItem(QString::fromStdString(m.getArtist() + "\t" + m.getTitlu()) + "\t" + QString::number(m.getLikes()),lst);
}

void MusicGUI::guiAdd(){
	try {
		serv.add(artistTxt->text().toStdString(), titluTxt->text().toStdString(), 0);
		serv.top5();
		reloadList(serv.sortLikes());
	}catch (RepoException re) { QMessageBox::warning(this, "Warning", QString::fromStdString(re.getMessage())); }
}


