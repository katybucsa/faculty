#include "Repository.h"
#include "DynamicVector.h"
#include <stdlib.h>

Repository* createRepository(int(*cmp)(TElem*, TElem*), void(*destr)(TElem*)) {
	Repository* repo = (Repository*)malloc(sizeof(Repository));
	repo->vector = createDynamicVector(1, cmp, destr);
	repo->undovect = createDynamicVector(1, cmp, destroyListaUndo);
	repo->destr = destr;
	return repo;
}




int addElem(Repository* repo, TElem* elem) {
	if (find(repo->vector, elem) != -1) {
		destr(elem);
		return -1;
	}
	add(repo->vector, elem);
	return 1;
}

int modifyElem(Repository* repo, TElem* elem) {
	if (find(repo->vector, elem) == -1) {
		destr(elem);
		return -1;
	}
	modify(repo->vector, elem);
	return 1;
}

int deleteElem(Repository* repo, TElem* elem) {
	if (find(repo->vector, elem) == -1) {
		destr(elem);
		return -1;
	}
	del(repo->vector, elem);
	return 1;
}

void setVector(Repository* repo, TElem* elem) {
	repo->vector = elem;
}

int sizeElems(Repository* repo) {
	return size(repo->vector);
}

void addEl(Repository* repo, TElem* elem) {
	add(repo->undovect, elem);
}

DynamicVector* getAll(Repository* repo) {
	return getAl(repo->vector);
}

DynamicVector* getCopyVector(Repository* repo) {
	return copyVector(repo->vector);
}

DynamicVector*removeLst(Repository* repo) {
	return removeLast(repo->undovect);
}


void destroyRepository(Repository*repo) {
	destroyDynamicVector(repo->vector);
	destroyListaUndo(repo->undovect);
	free(repo);
}
