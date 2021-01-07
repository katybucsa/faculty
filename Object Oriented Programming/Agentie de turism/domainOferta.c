#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "domainOferta.h"

Oferta* create(int id, char *type, char *destination, int date, float price) {
	Oferta* off = (Oferta*)malloc(sizeof(Oferta));
	off->id = id;
	off->type = malloc(sizeof(char)*strlen(type) + 1);
	strcpy(off->type, type);
	off->destination = malloc(sizeof(char)*strlen(destination) + 1);
	strcpy(off->destination, destination);
	off->date = date;
	off->price = price;
	
}


void destruct(Oferta* off) {
	free(off->type);
	free(off->destination);
	free(off);
}

void distruge(Oferta off) {
	free(off.type);
	free(off.destination);
}


void testDomainOferte() {
	Oferta* offer = create(12, "mare", "Euforie Sud", 18, 1000);
	assert(offer->id == 12);
	assert(strcmp(offer->type, "mare") == 0);
	assert(strcmp(offer->destination, "Euforie Sud") == 0);
	assert(offer->date == 18);
	assert(offer->price == 1000);
	destruct(offer);
}


