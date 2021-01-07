#include <iostream>
#define _CRTDBG_MAP_ALLOC
#include <stdlib.h>
#include <crtdbg.h> 
#include "Repository.h"
#include "Service.h"
#include "UI.h"
#include "Tests.h"




int main() {
	{
	runAllTests();
	Repository repo;
	OfferValidator valid;
	Service serv{ repo,valid };
	UI ui{ serv };
	ui.run();
	}
	_CrtDumpMemoryLeaks();
	return 0;
}