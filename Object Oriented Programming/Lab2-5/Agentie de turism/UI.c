#define _CRTDBG_MAP_ALLOC
#include <stdlib.h>
#include <crtdbg.h> 
#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
#include "UI.h"

UI* createUI(Service* serv) {
	UI* ui = (UI*)malloc(sizeof(UI));
	ui->serv = serv;
	return ui;
}

void uiAfisareOferte(UI* ui, DynamicVector* sorted) {
	printf("\n     ================================================Oferte================================================\n\n");
	for (int i = 0; i < sorted->dim; i++) {
		Oferta* o = sorted->elems[i];
		printf_s("\tId:%d\t\tTip:%s\t\tDestinatie:%s\t\tData:%d\t\tPret:%f\n", o->id, o->type, o->destination, o->date, o->price, "\n");
	}
	printf("\n\n");
}
void uiAddOffer(UI* ui) {
	int date;
	int id;
	double price;
	char type[10], destination[50], idbuff[50], valbuff[50];
	char *fin;
	printf_s("Introduceti oferta id:");
	scanf_s("%s", idbuff, 50);
	id = strtol(idbuff, &fin, 10);
	if (*fin==NULL)
	{
		printf_s("Introduceti tipul:");
		scanf_s("%s", type, 10);
		printf_s("Introduceti destinatia:");
		scanf_s("%s", destination, 50);
		printf_s("Introduceti data de plecare:");
		scanf_s("%s", valbuff, 50);
		date = strtol(valbuff,&fin,10);
		if (*fin==NULL) {
			printf("Introduceti pretul:");
			scanf_s("%s", valbuff, 50);
			price = strtod(valbuff,&fin,10);
			if (*fin==NULL) {
				int val = addOffer(ui->serv, id, type, destination, date, price);
				if (val == 1)
					printf_s("Oferta adaugata cu succes!\n");
				else {
					if (val % 2 == 0)
						printf_s("Id invalid!\n");
					if (val % 3 == 0)
						printf_s("Tip oferta invalid!\n");
					if (val % 5 == 0)
						printf_s("Data invalida!\n");
					if (val % 7 == 0)
						printf_s("Pret invalid!\n");
					if (val < 0)
						printf_s("Oferta deja existenta!\n");
				}
			}
			else {
				printf_s("Pret invalid!\n");
			}
		}
		else {
			printf_s("Data invalida!\n");
		}
	}
	else {
		printf_s("Id invalid!\n");
	}
}

void uiModifyOffer(UI* ui) {
	int date;
	int id;
	double price;
	char type[10], destination[50], idbuff[50], valbuff[50];
	char *fin;
	printf_s("Introduceti id-ul ofertei pe care doriti sa o modificati:");
	scanf_s("%s", idbuff, 50);
	id = strtol(idbuff, &fin, 10);
	if (*fin == NULL)
	{
		printf_s("Introduceti noul tipul:");
		scanf_s("%s", type, 10);
		printf_s("Introduceti noua destinatia:");
		scanf_s("%s", destination, 50);
		printf_s("Introduceti noua data de plecare:");
		scanf_s("%s", valbuff, 50);
		date = strtol(valbuff, &fin, 10);
		if (*fin == NULL) {
			printf("Introduceti noul pretul:");
			scanf_s("%s", valbuff, 50);
			price = strtod(valbuff, &fin, 10);
			if (*fin == NULL) {
				int val = modifyOffer(ui->serv, id, type, destination, date, price);
				if (val == 1)
					printf_s("Oferta modificata cu succes!\n");
				else {
					if (val % 2 == 0)
						printf_s("Id invalid!\n");
					if (val % 3 == 0)
						printf_s("Tip oferta invalid!\n");
					if (val % 5 == 0)
						printf_s("Data invalida!\n");
					if (val % 7 == 0)
						printf_s("Pret invalid!\n");
					if (val < 0)
						printf_s("Nu exista aceasta oferta!\n");
				}
			}
			else {
				printf_s("Pret invalid!\n");
			}
		}
		else {
			printf_s("Data invalida!\n");
		}
	}
	else {
		printf_s("Id invalid!\n");
	}
}


void uiDeleteOffer(UI*ui) {
	int id;
	char idbuff[50];
	char *fin;
	printf_s("Introduceti id-ul ofertei pe care doriti sa o stergeti: ");
	scanf_s("%s", idbuff, 50);
	id = strtol(idbuff, &fin, 10);
	if (*fin == NULL)
	{
		if (id < 0)
			printf_s("Id invalid!\n");
		else
		{
			int val = deleteOffer(ui->serv, id);
			if (val == 1)
				printf_s("Oferta stearsa cu succes!\n");
			else
				printf_s("Nu exista aceasta oferta!\n");
		}
	}
	else
		printf("Id invalid!\n");
}


void uiSortAscByPrice(UI* ui) {
	DynamicVector* sorted = sortAscByPrice(ui->serv);
	uiAfisareOferte(ui, sorted);
	destroyDynamicVector(sorted);
}


void uiSortDescByPrice(UI* ui) {
	DynamicVector* sorted = sortDescByPrice(ui->serv);
	uiAfisareOferte(ui, sorted);
	destroyDynamicVector(sorted);
}


void uiSortAscByDest(UI* ui) {
	DynamicVector* sorted = sortAscByDest(ui->serv);
	uiAfisareOferte(ui, sorted);
	destroyDynamicVector(sorted);
}

void uiSortDescByDest(UI* ui) {
	DynamicVector* sorted = sortDescByDest(ui->serv);
	uiAfisareOferte(ui, sorted);
	destroyDynamicVector(sorted);
}


void uiFilterType(UI* ui) {
	char type[10];
	printf_s("Introduceti tipul dorit: ");
	scanf_s("%s", type, 10);
	DynamicVector* filtered = filterType(ui->serv, type);
	if (filtered->dim)
		uiAfisareOferte(ui, filtered);
	else
		printf("Nu exista oferte cu tipul introdus!\n");
	destroyDynamicVector(filtered);
}




void uiFilterDest(UI* ui) {
	char destination[50];
	printf_s("Introduceti destinatia dorita: ");
	scanf_s("%s", destination, 50);
	DynamicVector* filtered = filterDest(ui->serv,destination);
	if (filtered->dim)
		uiAfisareOferte(ui, filtered);
	else
		printf("Nu exista oferte cu destinatia introdusa!\n");
	destroyDynamicVector(filtered);
}



void uiFilterPrice(UI* ui) {
	float price;
	char *fin, pricebuff[50];
	printf("Introduceti pretul:");
	scanf_s("%s", pricebuff, 50);
	price = strtod(pricebuff, &fin, 10);
	if (*fin == NULL)
	{
		DynamicVector* filtered = filterPrice(ui->serv, price);
		if (filtered->dim)
			uiAfisareOferte(ui, filtered);
		else
			printf("Nu exista oferte care sa nu depaseasca pretul specificat!\n");
		destroyDynamicVector(filtered);
	}
	else
		printf("Pret invalid!\n");
}

void uiUndo(UI*ui) {
	int rez=undo(ui->serv);
	if (rez == 0)
		printf_s("Lista goala -> Nu se poate face undo!\n");
	else
		uiAfisareOferte(ui, getAllOffers(ui->serv));
}




void executeCmd(UI* ui, int nr) {
	switch (nr)
	{
	case 1:
		uiAddOffer(ui);
		break;
	case 2:
		uiAfisareOferte(ui,getAllOffers(ui->serv));
		break;
	case 3:
		uiModifyOffer(ui);
		break;
	case 4:
		uiDeleteOffer(ui);
		break;
	case 5:
		uiSortAscByPrice(ui);
		break;
	case 6:
		uiSortDescByPrice(ui);
		break;
	case 7:
		uiSortAscByDest(ui);
		break;
	case 8:
		uiSortDescByDest(ui);
		break;
	case 9:
		uiFilterType(ui);
		break;
	case 10:
		uiFilterDest(ui);
		break;
	case 11:
		uiFilterPrice(ui);
		break;
	case 12:
		uiUndo(ui);
		break;
	}
}

int validateCmd(int *cmds, int nrcmds, long nr) {
	for (int i = 0; i < nrcmds; i++)
		if (nr == cmds[i])
			return 1;
	return 0;
}


void printMenu() {
	printf_s("\n======Menu=====\n");
	printf_s("1.Adauga Oferta\n");
	printf_s("2.Afisare lista Oferte\n");
	printf_s("3.Modificare Oferta\n");
	printf_s("4.Sterge Oferta\n");
	printf_s("5.Vizualizare oferte crescator dupa pret\n");
	printf_s("6.Vizualizare oferte descrescator dupa pret\n");
	printf_s("7.Vizualizare oferte crescator dupa destinatie\n");
	printf_s("8.Vizualizare oferte descrescator dupa destinatie\n");
	printf_s("9.Filtrare oferte dupa tip\n");
	printf_s("10.Filtrare oferte dupa destinatie\n");
	printf_s("11.Filtrare oferte dupa pret\n");
	printf_s("12.Undo\n");
	printf_s("0.Iesire\n");
}

void run(UI* ui) {
	char cmd[20];
	int cmds[] = { 0,1,2,3,4,5,6,7,8,9,10,11,12 };
	int nrcmds = 13;
	long nr;
	while (1) {
		printMenu();
		printf_s("Introduceti comanda: ");
		scanf_s("%s", cmd, 20);
		char *fin;
		nr = strtol(cmd, &fin, 10);
		if (*fin == NULL) {
			if (validateCmd(cmds, nrcmds, nr)) {
				if (nr == 0)
					return;
				executeCmd(ui, nr);
			}
			else {
				printf_s("Comanda neacceptata!\n");
			}
		}
		else {
			printf_s("Comanda invalida!\n");
		}
	}
}




void destroyUI(UI* ui) {
	destroyService(ui->serv);
	free(ui);
}

