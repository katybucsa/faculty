#include <QtWidgets/QApplication>
#define _CRTDBG_MAP_ALLOC
#include <stdlib.h>
#include <crtdbg.h> 
#include "Repository.h"
#include "Service.h"
#include "GUI.h"
#include "Tests.h"

int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	{
		runAllTests();
		FileRepository repo{ "oferte.txt" };
		OfferValidator valid;
		Service serv{ repo,valid };
		OfertaGUI gui{ serv };
		WishlistCRUDGUI anw(serv);
		WishlistRdOnlyGUI paint(serv);
		gui.show();
		anw.show();
		paint.show();
		return a.exec();
	}
	//_CrtDumpMemoryLeaks();
}
