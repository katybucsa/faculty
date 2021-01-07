#include <stdio.h>
#include <errno.h>
#include <string.h>
#include <assert.h>
#include <errno.h>
#include "validator.h"
#include "repositoryOferte.h"

int addOffer(ListaOferte*oferte, int id, char *type, char *destination, int date, float price, char erori[]) {
	Oferta* oferta = create(id, type, destination, date, price);
	validate(oferta, erori);
	if (errno) {
		destruct(oferta);
		return -1;
	}	
	return add(oferte, oferta);
}
int modifyOffer(ListaOferte*oferte, int id, char *type, char *destination, int date, float price, char erori[]) {
	Oferta* oferta = create(id, type, destination, date, price);
	validate(oferta,erori);
	if (errno) {
		destruct(oferta);
		return -1;
	}
	return modify(oferte, oferta);
}

int deleteOffer(ListaOferte *oferte, int id,char erori[]) {
	Oferta* oferta = create(id, "munte", "sdf", 13, 132);
	validate(oferta, erori);
	if (errno)
		return -1;
	return del(oferte, oferta);
}

Oferta pozOffer(ListaOferte*oferte, int poz) {
	return getOferta(oferte, poz);
}


void testServiceOferte(){
	ListaOferte* oferte = createEmpty();
	int id = 12, date = 18, id2 = 14, id3=-14, date2 = 25, date1 = 34,n,i;
	char type[6] = "munte", destination[8] = "Everest", erori[200] = "", type1[4] = "abc";
	float price = 1000, price2 = 897,price3=-57;
	assert(oferte->dim == 0);
	assert(addOffer(oferte, id, type, destination, date, price, erori) == 0);
	assert(oferte->dim == 1);
	assert(addOffer(oferte, id3, type1, destination, date, price, erori) == -1);
	assert(strcmp(erori, "Id invalid!\nTip invalid!\n") == 0);
	strcpy(erori, "");
	assert(modifyOffer(oferte, id2, type, destination, date1, price3, erori) == -1);
	assert(strcmp(erori, "Pret invalid!\nData invalida!\n") == 0);
	strcpy(erori, "");
	assert(modifyOffer(oferte, id, type, destination, date2, price2, erori) == 0);
	n = oferte->dim;
	for (i = 0; i < n; i++) {
		distruge(oferte->elems[i]);
		free(&(oferte->elems[i]));
	}
	free(oferte->elems);
	free(oferte);
}