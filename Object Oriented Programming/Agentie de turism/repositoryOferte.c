#include "repositoryOferte.h"
#include "domainOferta.h"
#include <assert.h>
#include <malloc.h>
#include <string.h>


ListaOferte* createEmpty() {
	ListaOferte* lista=(ListaOferte*)malloc(sizeof(ListaOferte));
	lista->capac = 1;
	lista->elems = (Oferta*)malloc(sizeof(Oferta)*lista->capac);
	lista->dim = 0;
	return lista;
}

void resize(ListaOferte* lista) {
	int newdim = lista->capac * 2;
	Oferta* nelems = (Oferta*)malloc(sizeof(Oferta)*newdim);
	for (int i = 0; i < lista->dim; i++) {
		nelems[i] = lista->elems[i];
	}
	free(lista->elems);
	lista->elems = nelems;
	lista->capac *= 2;
}

int find(ListaOferte* lista, Oferta* off) {
	for (int i = 0; i < lista->dim; i++)
		if (lista->elems[i].id==off->id)
			return i;
	return -1;
}



int add(ListaOferte *l, Oferta* of) {
	if (l->dim == l->capac)
		resize(l);
	if (find(l, of) == -1) {
		l->elems[l->dim] = *of;
		l->dim++;
		return 0;
	}
	destruct(of);
	return -1;
}

int modify(ListaOferte*l, Oferta* off) {
	int pos = find(l, off);
	if (pos < 0) {
		destruct(off);
		return -1;
	}
	l->elems[pos] = *off;
	return 0;
}


int del(ListaOferte*oferte, Oferta *off) {
	int pos = find(oferte, off);
	if (pos < 0) {
		destruct(off);
		return -1;
	}
	int i = pos;
	Oferta o = oferte->elems[pos];
	distruge(o);
	for (; i < oferte->dim - 1; i++)
		oferte->elems[i] = oferte->elems[i + 1];
	oferte->dim--;
	return 0;
}


Oferta getOferta(ListaOferte * l, int poz)
{
	return l->elems[poz];
}


void testRepositoryOferte() {
	ListaOferte* oferte = createEmpty();
	Oferta* offer = create(12, "munte", "Everest", 18, 1000);
	assert(add(oferte, offer)==0);
	assert(oferte->dim == 1);
	Oferta* offer1 = create(12, "mare", "gfd", 24, 800);
	assert(find(oferte, offer) == 0);
	assert(add(oferte, offer1)==-1);
	assert(oferte->dim == 1);
	Oferta* offer2 = create(14, "mare", "ads", 24, 800);
	assert(modify(oferte, offer2) == -1);
	Oferta* offer3 = create(14, "mare", "Euforie sud", 24, 800);
	add(oferte, offer3);
	assert(oferte->dim == 2);
	Oferta* offer4 = create(12, "mare", "Euforie sud", 24, 800);
	assert(modify(oferte, offer4) == 0);
	assert(find(oferte, offer3) == 1);
	destruct(offer);
	destruct(offer3);
	destruct(offer4);
	free(oferte->elems);
	free(oferte);
}