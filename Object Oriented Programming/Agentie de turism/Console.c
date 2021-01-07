#ifdef _MSC_VER
#define _CRT_SECURE_NO_WARNINGS
#endif
#define _CRTDBG_MAP_ALLOC  
#include <stdlib.h>  
#include <crtdbg.h>  
#include <stdio.h>
#include <errno.h>
#include <string.h>
#include "domainOferta.h"
#include "repositoryOferte.h"
#include "serviceOferte.h"
#include "validator.h"



void afisare_oferte(ListaOferte*oferte) {
	int n = oferte->dim;
	for (int i = 0; i < n; i++)
	{
		Oferta oferta = pozOffer(oferte, i);
		printf("Id:%d\tTip:%s\tDestinatie:%s\tData de plecare:%d\tPret:%f\n", oferta.id, oferta.type, oferta.destination, oferta.date, oferta.price);
	}

}

void ui_addOffer(ListaOferte*oferte) {
	int id, date, ok=0;
	float price;
	char *type, *destination, erori[200] = "";
	type = (char*)malloc(20 * sizeof(char));
	destination = (char*)malloc(20*sizeof(char));
	printf("Introduceti oferta id:");
	scanf("%d", &id);
	printf("Introduceti tipul:");
	scanf("%s", type);
	printf("Introduceti destinatia:");
	scanf("%s", destination);
	printf("Introduceti data de plecare:");
	scanf("%d", &date);
	printf("Introduceti pretul:");
	scanf("%f", &price);
	ok=addOffer(oferte, id, type, destination, date, price, erori);
	if (ok == 0)
		printf("Oferta adaugata cu succes!\n");
	else if (strlen(erori) == 0)
		printf("Exista deja o oferta cu acest id!\n");
	else if (strlen(erori))
		printf("%s", erori);
	afisare_oferte(oferte);
	free(type);
	free(destination);
}

void ui_modifyOffer(ListaOferte*oferte) {
	int id,date,ok=0;
	float price;
	char *type, *destination, erori[200] = "";
	type = (char*)malloc(20 * sizeof(char));
	destination = (char*)malloc(20 * sizeof(char));
	printf("Introduceti id-ul ofertei pe care o modificati:");
	scanf("%d", &id);
	printf("Introduceti noul tipul:");
	scanf("%s", type);
	printf("Introduceti noua destinatia:");
	scanf("%s", destination);
	printf("Introduceti noua data de plecare:");
	scanf("%d", &date);
	printf("Introduceti noul pretul:");
	scanf("%f", &price);
	ok = modifyOffer(oferte, id, type, destination, date, price, erori);
	if (ok == 0)
		printf("Oferta modificata cu succes!\n");
	else if (strlen(erori) == 0)
		printf("Nu exista nicio oferta cu id-ul introdus!\n");
	else if (strlen(erori))
		printf("%s", erori);
	afisare_oferte(oferte);
	free(type);
	free(destination);
} 



void ui__deleteOffer(ListaOferte *oferte) {
	int id=-1, ok;
	char erori[200] = "";
	printf("Introduceti id-ul ofertei pe care doriti sa o stergeti:");
	scanf("%d", &id);
	ok = deleteOffer(oferte, id,erori);
	if (ok == 0)
		printf("Oferta a fost stearsa cu succes!\n");
	else if (strlen(erori) == 0)
		printf("Nu exista nicio oferta cu id-ul introdus!\n");
	else if (strlen(erori))
		printf("%s", erori);
	afisare_oferte(oferte);
}

void dealocare(ListaOferte *oferte) {
	for (int i = 0; i < oferte->dim; i++)
	{
		distruge(oferte->elems[i]);
	}

}



void print_menu() {
	printf("0.Iesire\n");
	printf("1.Adaugare de noi oferte\n");
	printf("2.Actualizare oferte\n");
	printf("3.Stergere oferta\n");
}

void testAll() {
	testDomainOferte();
	testServiceOferte();
	testValidate();
	testRepositoryOferte();
}

void ui_run() {
	ListaOferte* oferte = createEmpty();
	while (1)
	{
		int comanda = 0;
		print_menu();
		printf("Introduceti comanda:\n");
		scanf("%d", &comanda);
		switch (comanda)
		{
		case 0:
			dealocare(oferte);
			return;

		case 1:
			ui_addOffer(oferte);
			break;
		case 2:
			ui_modifyOffer(oferte);
			break;
		case 3:
			ui__deleteOffer(oferte);
			break;

		default:
			printf("Comanda invalida!\n");
			break;
		}
		printf("\n");
	}
}

int main()
{
	testAll();
	//ui_run();
	_CrtDumpMemoryLeaks();
	return 0;
}