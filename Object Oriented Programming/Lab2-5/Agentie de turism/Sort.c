#include "sort.h"
#include "Repository.h"



void sort(DynamicVector* elems, CmpFunction cmpF,int n) {
	for (int i = 0; i <n-1; i++) {
		for (int j = i + 1; j <n; j++) {
			Oferta* o1 = elems->elems[i];
			Oferta* o2 = elems->elems[j];
			if (cmpF(o1, o2) > 0) {
				elems->elems[i] = o2;
				elems->elems[j] = o1;
			}
		}
	}
}