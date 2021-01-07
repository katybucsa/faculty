#include "Service.h"
#include "Domain.h"
#include "Validator.h"
#include <malloc.h>


Service* createService(Repository* repo) {
	Service* serv = (Service*)malloc(sizeof(Service));
	serv->repo = repo;
	return serv;
}

int addOffer(Service* serv, int id, char *type, char *destination, int date, float price) {
	Oferta* o = createOferta(id, type, destination, date, price);
	DynamicVector*undo = getCopyVector(serv->repo);
	int val = validateOferta(o);
	if (val != 1) {
		destroyDynamicVector(undo);
		return val;
	}
	val = addElem(serv->repo, o);
	if (val== 1) {
		addEl(serv->repo, undo);
		return 1;
	}
	destroyDynamicVector(undo);
	return -1;
}


int modifyOffer(Service* serv, int id, char*type, char* destination, int date, float price) {
	Oferta* o = createOferta(id, type, destination, date, price);
	DynamicVector*undo = getCopyVector(serv->repo);
	int val = validateOferta(o);
	if (val != 1) {
		destroyDynamicVector(undo);
		return val;
	}
	val = modifyElem(serv->repo, o);
	if (val == 1) {
		addEl(serv->repo, undo);
		return 1;
	}
	destroyDynamicVector(undo);
	return -1;
}


int deleteOffer(Service* serv, int id) {
	Oferta* o = createOferta(id, "munte", "dfs", 1, 12);
	DynamicVector*undo = getCopyVector(serv->repo);
	int val = validateOferta(o);
	if (val != 1) {
		destroyDynamicVector(undo);
		return val;
	}
	val = deleteElem(serv->repo, o);
	if (val == 1) {
		addEl(serv->repo, undo);
		return 1;
	}
	destroyDynamicVector(undo);
	return -1;
}



int cmpAscPrice(Oferta* o1, Oferta* o2) {
	return o1->price > o2->price;
}



int cmpDescPrice(Oferta* o1, Oferta* o2) {
	return o1->price < o2->price;
}


int cmpAscDest(Oferta* o1, Oferta* o2) {
	return strcmp(o1->destination,o2->destination);
}



int cmpDescDest(Oferta* o1, Oferta* o2) {
	return strcmp(o2->destination, o1->destination);
}


DynamicVector* sortAscByPrice(Service*serv) {
	DynamicVector* elems = getCopyVector(serv->repo);
	sort(elems, cmpAscPrice, sizeElems(serv->repo));
	return elems;
}


DynamicVector* sortDescByPrice(Service*serv) {
	TElem* elems = getCopyVector(serv->repo);
	sort(elems, cmpDescPrice, sizeElems(serv->repo));
	return elems;
}


DynamicVector* sortAscByDest(Service*serv) {
	TElem* elems = getCopyVector(serv->repo);
	sort(elems, cmpAscDest, sizeElems(serv->repo));
	return elems;
}

DynamicVector* sortDescByDest(Service*serv) {
	DynamicVector* elems = getCopyVector(serv->repo);
	sort(elems, cmpDescDest, sizeElems(serv->repo));
	return elems;
}



DynamicVector* filterType(Service*serv, char*type) {
	DynamicVector* vector = getCopyVector(serv->repo);
	for (int i = 0; i < vector->dim; i++) {
		Oferta* o = vector->elems[i];
		if (strcmp(type, o->type) != 0) {
			Oferta*copy = copyOferta(o);
			del(vector, copy);
			i--;
		}
	}
	return vector;
}


DynamicVector* filterDest(Service*serv,char* dest) {
	DynamicVector* vector = getCopyVector(serv->repo);
	for (int i = 0; i < vector->dim; i++) {
		Oferta* o = vector->elems[i];
		if (strcmp(dest, o->destination) != 0) {
			Oferta*copy = copyOferta(o);
			del(vector, copy);
			i--;
		}
	}
	return vector;
}


DynamicVector* filterPrice(Service*serv, float price) {
	DynamicVector* vector = getCopyVector(serv->repo);
	for (int i = 0; i < vector->dim; i++) {
		Oferta* o = vector->elems[i];
		if (o->price>price) {
			Oferta*copy = copyOferta(o);
			del(vector, copy);
			i--;
		}
	}
	return vector;
}



int len(Service* serv) {
	return sizeElems(serv->repo);
}

DynamicVector* getAllOffers(Service* serv) {
	return getAll(serv->repo);
}


int undo(Service*serv) {
	if(size(serv->repo->undovect)== 0)
		return 0;
	DynamicVector* vector = removeLst(serv->repo);
	DynamicVector* oldvect = getAll(serv->repo);
	setVector(serv->repo, vector);
	destroyDynamicVector(oldvect);
	return 1;
}


void destroyService(Service* serv) {
	destroyRepository(serv->repo);
	free(serv);
}
