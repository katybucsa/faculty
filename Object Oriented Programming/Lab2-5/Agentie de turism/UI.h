#pragma once

#include "Service.h"


typedef struct {
	Service* serv;
}UI;

UI* createUI(Service* serv);
void run(UI*);
void destroyUI(UI*);
