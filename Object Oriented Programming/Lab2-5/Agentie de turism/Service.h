#pragma once
#include "Repository.h"

typedef struct {
	Repository* repo;
	void(*destr)(TElem);
}Service;


Service* createService(Repository*);

int addOffer(Service*,int, char*, char*, int, float);

int modifyOffer(Service*, int, char*, char*, int, float);

int deleteOffer(Service*, int);

int len(Service*);

DynamicVector* getAllOffers(Service*);

int cmpAscPrice(Oferta*, Oferta*);

int cmpDescPrice(Oferta*, Oferta*);

int cmpAscDest(Oferta*, Oferta*);

int cmpDescDest(Oferta*, Oferta*);

DynamicVector* sortAscByPrice(Service*);

DynamicVector* sortDescByPrice(Service*);

DynamicVector* sortAscByDest(Service*);

DynamicVector* sortDescByDest(Service*);

DynamicVector* filterType(Service*, char*);

DynamicVector* filterDest(Service*, char*);

DynamicVector* filterPrice(Service*, float);

int undo(Service*);

void destroyService(Service*);
