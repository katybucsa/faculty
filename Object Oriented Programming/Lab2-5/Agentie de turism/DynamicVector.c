#include "DynamicVector.h"
#include "Domain.h"
#include <malloc.h>


DynamicVector* createDynamicVector(int capacity, int(*cmp)(TElem*, TElem*), void(*destroy)(TElem*)) {
	DynamicVector* vector = (DynamicVector*)malloc(sizeof(DynamicVector));
	vector->capacity = capacity;
	vector->dim = 0;
	vector->cmp = cmp;
	vector->destroy = destroy;
	vector->elems = (TElem*)malloc(sizeof(TElem));
	return vector;
}



void resize(DynamicVector* vector) {
	int newdim = vector->dim * 2;
	TElem* nelems = malloc(sizeof(TElem)*newdim);
	for (int i = 0; i < vector->dim; i++)
		nelems[i] = vector->elems[i];
	TElem* elems = vector->elems;
	vector->elems = nelems;
	vector->capacity = newdim;
	free(elems);
}


int find(DynamicVector* vector, TElem* elem) {
	for (int i = 0; i < vector->dim; i++)
		if (ofertaCmp(vector->elems[i], elem) == 1)
			return i;
	return -1;
}



void add(DynamicVector* vector, TElem* elem) {
	if (vector->dim == vector->capacity)
		resize(vector);
	vector->elems[vector->dim] = elem;
	vector->dim++;
}


void modify(DynamicVector* vector, TElem* elem) {
	int pos = find(vector, elem);
	TElem* e = vector->elems[pos];
	vector->elems[pos] = elem;
	destr(e);
}

void del(DynamicVector* vector, TElem* elem) {
	int pos = find(vector, elem);
	TElem* e = vector->elems[pos];
	for (int i = pos; i < vector->dim - 1; i++)
		vector->elems[i] = vector->elems[i + 1];
	vector->dim--;
	destr(e);
	destr(elem);
}

TElem* get(DynamicVector* v, int poz) {
	return v->elems[poz];
}

int size(DynamicVector* vector) {
	return vector->dim;
}

DynamicVector* getAl(DynamicVector* vector) {
	return vector;
}


DynamicVector* copyVector(DynamicVector* vector) {
	DynamicVector* svector = createDynamicVector(1, ofertaCmp, destr);
	for (int i = 0; i < vector->dim; i++) {
		TElem elem = get(vector, i);
		add(svector, copyOferta(elem));
	}
	return svector;
}


TElem* removeLast(DynamicVector* vector) {
	TElem rez = vector->elems[vector->dim - 1];
	vector->dim -= 1;
	return rez;
}


void destroyDynamicVector(DynamicVector* vector) {
	for (int i = 0; i < vector->dim; i++)
		destr(vector->elems[i]);
	free(vector->elems);
	free(vector);
}

void destroyListaUndo(TElem* elem) {
	DynamicVector* v = elem;
	int n = v->dim;
	for (int i = 0; i < n; i++) {
		DynamicVector* ve = removeLast(v);
		destroyDynamicVector(ve);
	}
	free(v->elems);
	free(v);
}