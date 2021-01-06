#include "Colectie.h"
#include <iostream>

using namespace std;

Colectie* creeazaColectie() {
	Colectie* c = new Colectie();
	c->adauga(2);
	c->adauga(4);
	c->adauga(2);
	c->adauga(5);
	c->adauga(4);
	return c;
}

void tiparesteColectie(Colectie *c) {
	Iterator it = c->iterator();
	it.prim();
	while (it.valid()) {
		cout << it.element() << ' ';
		it.urmator();
	}
}

int main() {

	Colectie* c = creeazaColectie();
	c->sterge(2);
	tiparesteColectie(c);
	delete c;
	return 0;
}
