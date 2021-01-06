#include <QtWidgets/QApplication>
#include "GUI.h"
#include "Tests.h"
int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	testAll();
	FileRepo repo{ "taskuri.txt" };
	Validator valid;
	Service serv{ repo,valid };
	TaskGUI gui{ serv };
	Open o{ serv };
	Close c{ serv };
	InProgress ip{ serv };
	gui.show();
	o.show();
	c.show();
	ip.show();
	return a.exec();
}
