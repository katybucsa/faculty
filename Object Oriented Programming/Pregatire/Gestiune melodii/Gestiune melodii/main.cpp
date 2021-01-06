#include "Gestiunemelodii.h"
#include <QtWidgets/QApplication>
#include "GUI.h"

int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	FileRepo repo{ "music.txt" };
	Validator valid;
	Service serv{ repo,valid };
	MusicGUI gui{ serv };
	gui.show();
	return a.exec();
}
