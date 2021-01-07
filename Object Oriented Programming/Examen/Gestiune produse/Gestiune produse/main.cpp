#include "Gestiuneproduse.h"
#include <QtWidgets/QApplication>
#include "ProdusGUI.h"
#include "Teste.h"

int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	//testAll();
	FileRepo repo( "produse1.txt" );
	Validator valid;
	Service serv{ repo,valid };
	ProdusGUI gui{ serv };
	gui.show();
	return a.exec();
}
