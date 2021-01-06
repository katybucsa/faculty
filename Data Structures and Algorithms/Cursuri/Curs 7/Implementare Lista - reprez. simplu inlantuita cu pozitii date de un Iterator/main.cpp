//============================================================================
// Name        : SDA_collectie.cpp
// Author      :
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include "lista.h"
#include <iostream>

using namespace std;

void creeazaLista(Lista& l) {
	l.adaugaSfarsit(2);
	l.adaugaSfarsit(4);
	l.adaugaSfarsit(5);
	l.adaugaSfarsit(2);
	l.adaugaSfarsit(3);
	l.adaugaSfarsit(2);
}

void tiparesteLista(const Lista& l) {
	Iterator it = l.prim();
	while (it.valid()) {
		cout << it.element() << ' ';
		it.urmator();
	}
}

//dupa prima aparitie a unui element e1, se adauga elementul e2
void prelucrare(Lista& l, TElem e1, TElem e2){
	Iterator it = l.prim();
	while (it.valid()) {
		if (it.element()==e1)
            break;
 		it.urmator();
	}
	//daca a fost gasit elementul e1 in lista
	if (it.valid())
        l.adaugaDupa(it, e2);
}


int main() {
	Lista l;
    creeazaLista(l);
    //dupa prima valoare egala cu 2 din lista, se adauga valoarea 0
	prelucrare(l,2,0);
	tiparesteLista(l);
	return 0;
}
