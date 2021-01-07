#pragma once

typedef void* TElem;

typedef struct {
	int dim;
	int capacity;
	TElem* elems;
	int(*cmp)(TElem, TElem);
	void(*destroy)(TElem);
}DynamicVector;


DynamicVector* createDynamicVector(int, int(*cmp)(TElem*, TElem*), void(*destruct)(TElem*));


TElem* get(DynamicVector*, int);


void add(DynamicVector*, TElem*);


void modify(DynamicVector*, TElem*);


void del(DynamicVector*, TElem*);


int find(DynamicVector*, TElem*);


int size(DynamicVector*);


DynamicVector* getAl(DynamicVector*);

DynamicVector* copyVector(DynamicVector*);

TElem* removeLast(DynamicVector*);

void destroyListaUndo(TElem*);

void destroyDynamicVector(DynamicVector*);