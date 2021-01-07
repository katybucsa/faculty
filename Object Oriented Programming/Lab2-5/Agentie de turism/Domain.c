#include "Domain.h"
#include <string.h>
#include<malloc.h>
#include <stdlib.h>



Oferta* createOferta(int id, char*type, char*destination, int date, float price) {
	Oferta* o = (Oferta*)malloc(sizeof(Oferta));
	o->id = id;
	o->type = (char*)malloc(sizeof(char)*strlen(type) + 1);
	strcpy(o->type, type);
	o->destination = (char*)malloc(sizeof(char)*strlen(destination) + 1);
	strcpy(o->destination, destination);
	o->date = date;
	o->price = price;
	return o;
}

int getId(Oferta* o) {
	return o->id;
}

void getType(Oferta* o, char *type) {
	strcpy(type, o->type);
}

void getDestination(Oferta* o, char *destination) {
	strcpy(destination, o->destination);
}

int getDate(Oferta* o) {
	return o->date;
}


int getPrice(Oferta* o) {
	return o->price;
}


int ofertaCmp(Oferta *o1, Oferta*o2) {
	return getId(o1) == getId(o2);
}

Oferta* copyOferta(Oferta* o) {
	return createOferta(o->id, o->type, o->destination, o->date, o->price);
}

void destr(Oferta *o) {
	free(o->type);
	free(o->destination);
	free(o);
}



