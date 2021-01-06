
#include "dictionar.h"
#include <iostream>

using namespace std;

void creeazaDictionar(Dictionar& d) {
	d.adauga(2, 21);
	d.adauga(4, 41);
	d.adauga(5, 51);
	d.adauga(14,141);
}

void tiparesteDictionar(const Dictionar& d) {
	Iterator it = d.iterator();
	while (it.valid()) {
        TElem e = it.element();
		cout << '('<<e.c<<','<<e.v<<')'<<endl;
		it.urmator();
	}
}

int main() {
	Dictionar d;
    creeazaDictionar(d);
	tiparesteDictionar(d);
	return 0;
}
