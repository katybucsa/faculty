#include <stdio.h>
#include <malloc.h>
#include "DynamicVector.h"
#include "Repository.h"
#include "Validator.h"
#include "Service.h"
#include "Tests.h"
#include "UI.h"




int main() {
	Repository* repo = createRepository(ofertaCmp, destr);
	Service* serv = createService(repo);
	UI* ui = createUI(serv);
	runAll();
	run(ui);
	destroyUI(ui);
	_CrtDumpMemoryLeaks();
	return 0;
}