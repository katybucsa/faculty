#pragma once
#include "DynamicVector.h"
#include "Domain.h"

typedef Oferta* Telem;



typedef struct {
	DynamicVector* vector;
	DynamicVector* undovect;
	void(*destr)(TElem);
}Repository;

/*
creates a new list for elements of type Oferta
lista list for offers
post:the list is empty
*/
Repository* createRepository(int(*cmp)(TElem*, TElem*), void(*destr)(TElem*));

/*

*/
void addEl(Repository*, TElem*);
/*
verify if TElem already exists and if not, adds it in the list and return 1; return -1 otherwise
*/
int addElem(Repository*, TElem*);
/*
verify if the TElem exists in the list and replace it with the new TElem returning 1 and -1 otherwise 
*/
int modifyOffer(Repository*, TElem*);

int deleteElem(Repository*, TElem*);

int sizeElems(Repository*);

void setVector(Repository*, TElem*);

DynamicVector* getAll(Repository*);

DynamicVector* getCopyVector(Repository*);

DynamicVector* removeLst(Repository*);

void destroyRepository(Repository*);