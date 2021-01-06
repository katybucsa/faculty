#include <QtWidgets/QApplication>
#include "Repo.h"
#include "Service.h"
#include "MusicGUI.h"
#include "tests.h"

int main(int argc, char *argv[])
{
	test();
	QApplication a(argc, argv);
	Repo repo;
	Service serv{ repo };
	MusicGUI music{ serv };
	music.show();
	return a.exec();
}
