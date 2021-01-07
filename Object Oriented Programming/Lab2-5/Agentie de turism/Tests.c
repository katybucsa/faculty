#include "Domain.h"
#include "DynamicVector.h"
#include "Repository.h"
#include "Service.h"
#include "Tests.h"
#include "Validator.h"
#include <assert.h>





void testCreateOferta() {
	Oferta* o = createOferta(12, "munte", "Rarau", 14, 120);
	char type[10], dest[50];
	assert(getId(o)==12);
	getType(o, type);
	assert(strcmp(type, "munte") == 0);
	getDestination(o, dest);
	assert(strcmp(dest, "Rarau") == 0);
	assert(getDate(o) == 14);
	assert(getPrice(o) == 120);
	Oferta* o1 = createOferta(12, "mare", "Mamaia", 21, 430);
	assert(ofertaCmp(o, o1) == 1);
	Oferta* o2 = copyOferta(o1);
	assert(getId(o2) == 12);
	destr(o);
	destr(o1);
	destr(o2);
}



void testDynVector() {
	DynamicVector* vector = createDynamicVector(1, ofertaCmp, destr);
	Oferta* o = createOferta(12, "munte", "Rarau", 14, 120);
	add(vector, o);
	assert(size(vector) == 1);
	assert(ofertaCmp(get(vector, 0), o) == 1);
	Oferta* o1 = createOferta(13, "mare", "werwe", 21, 430);
	assert(find(vector, o1) == -1);
	add(vector, o1);
	assert(size(vector) == 2);
	assert(find(vector, o1) == 1);
	Oferta* o2 = createOferta(14, "mare", "Constanta", 25, 5000);
	add(vector, o2);
	assert(size(vector) == 3);
	Oferta* o3 = createOferta(13, "mare", "Mamaia", 28, 1232);
	modify(vector, o3);
	assert(getDate(vector->elems[1]) == 28);
	assert(getPrice(vector->elems[1]) == 1232);
	DynamicVector* v = getAl(vector);
	assert(size(v) == 3);
	assert(ofertaCmp(v->elems[0], o) == 1);
	assert(ofertaCmp(v->elems[1], o3) == 1);
	assert(ofertaCmp(v->elems[2], o2) == 1);
	DynamicVector* vect = copyVector(vector);
	assert(size(vect) == 3);
	Oferta* o4 = createOferta(13, "mare", "werwe", 21, 430);
	del(vector, o4);
	assert(size(vector) == 2);
	assert(find(vector, o3) == -1);
	Oferta* o5=removeLast(vect);
	assert(size(vect) == 2);
	assert(find(vect, o2) == -1);
	destr(o5);
	destroyDynamicVector(vector);
	destroyDynamicVector(vect);
}


void testRepo() {
	Repository* repo = createRepository(ofertaCmp, destr);
	Oferta* o = createOferta(12, "munte", "Rarau", 14, 120);
	Oferta*co= createOferta(12, "munte", "Rarau", 14, 120);
	assert(addElem(repo, o) == 1);
	assert(sizeElems(repo) == 1);
	assert(addElem(repo, co) == -1);
	assert(sizeElems(repo) == 1);
	Oferta* o1 = createOferta(13, "mare", "werwe", 21, 430);
	assert(addElem(repo, o1) == 1);
	Oferta* o2 = createOferta(13, "mare", "Mamaia", 28, 1232);
	assert(modifyElem(repo, o2) == 1);
	Oferta* o3 = createOferta(14, "mare", "Constanta", 25, 5000);
	assert(modifyElem(repo, o3) == -1);
	Oferta* o4 = createOferta(14, "mare", "Constanta", 25, 5000);
	assert(deleteElem(repo, o4) == -1);
	Oferta* o5 = createOferta(13, "mare", "werwe", 21, 430);
	assert(deleteElem(repo, o5) == 1);
	destroyRepository(repo);
}



void testService() {
	Repository*repo = createRepository(1, ofertaCmp, destr);
	Service* serv = createService(repo);
	assert(len(serv) == 0);
	assert(undo(serv) == 0);
	assert(addOffer(serv, 12, "munte", "Rarau", 14, 120)==1);
	assert(addOffer(serv, 12, "munte", "Rarau", 14, 120) == -1);
	assert(len(serv) == 1);
	DynamicVector* v4 = filterDest(serv, "Rarau");
	assert(modifyOffer(serv, 13, "mare", "Mamaia", 28, 1232)==-1);
	assert(deleteOffer(serv, 13, "mare", "Mamaia", 28, 1232) == -1);
	assert(addOffer(serv, 13, "mare", "Mamaia", 28, 1232) == 1);
	DynamicVector* v = sortAscByPrice(serv);
	assert(modifyOffer(serv, 13, "mare", "Constanta", 22,234) == 1);
	assert(addOffer(serv, -12, "munte", "Rarau", 14, 120) == 2);
	DynamicVector* v1 = sortDescByPrice(serv);
	DynamicVector* v2 = sortAscByDest(serv);
	assert(modifyOffer(serv, -12, "munte", "Rarau", 14, 120) == 2);
	assert(deleteOffer(serv, 13, "munte", "Rarau", 14, 120) == 1);
	assert(deleteOffer(serv, -15, "munte", "Rarau", 14, 120) !=-1);
	assert(len(serv) == 1);
	DynamicVector* v3 = sortDescByDest(serv);
	addOffer(serv, 15, "mare", "Mamaia", 14, 12);
	DynamicVector* v5 = filterPrice(serv, 50);
	DynamicVector* v6 = filterType(serv,"mare");
	DynamicVector* v7 = getAllOffers(serv);
	assert(len(serv) == 2);
	assert(undo(serv) == 1);
	assert(len(serv) == 1);
    destroyService(serv);
	destroyDynamicVector(v);
	destroyDynamicVector(v1);
	destroyDynamicVector(v2);
	destroyDynamicVector(v3);
	destroyDynamicVector(v4);
	destroyDynamicVector(v5);
	destroyDynamicVector(v6);
}


void testValidator() {
	Oferta* o = createOferta(12, "dfs", "sdsfd", -1, -23);
	assert(validateOferta(o) != 1);

}


void runAll() {
	testCreateOferta();
	testDynVector();
	testRepo();
	testService();
	testValidator();
}