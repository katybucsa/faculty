#include "Gestiunetaskuri.h"
#include <QtWidgets/QApplication>
#include "GUI.h"

int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	FileRepo repo{ "taskuri.txt" };
	Validator valid;
	Service serv{ repo,valid };
	TaskGUI gui{ serv };
	gui.show();
	return a.exec();
}
