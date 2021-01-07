#pragma once
#include "domainOferta.h"

typedef struct {
	int dim;
	int capac;
	Oferta* elems;
}ListaOferte;
/*
creates a new list for elements of type Oferta
lista list for offers
post:the list is empty
*/
ListaOferte* createEmpty();


void resize(ListaOferte* lista);


int find(ListaOferte* lista, Oferta* of);

/*
adds a new offer in the offer's list
l - vector of offers
offer - offer to be added
*/
int add(ListaOferte *l, Oferta *offer);



/*
modify an existing offer
l - vector of offers
offer - the offer which will be in the place of the old offer
*/
int modify(ListaOferte *l, Oferta *offer);


int del(ListaOferte *oferte, Oferta *offer);

/*
l - vector of offers
poz- position in vector
post:the offer that is on position poz in vector
*/
Oferta getOferta(ListaOferte*l, int poz);




/*
test function for functions above
*/
void testRepositoryOferte();