#include "colectie.h"
#include <iostream>

Colectie::Colectie() {
	len = 0;
}

void Colectie::adauga(TElem elem) {
	elems[len++] = elem;
}

void Colectie::sterge(TElem elem) {
	for (int i = 0; i < len; i++) {
		if (elem == elems[i]) {
			for (int j = i; j < len; j++) {
				elems[j] = elems[j + 1];
			}
			len--;
			return;
		}
	}
}

bool Colectie::cauta(TElem elem) {
	for (int i = 0; i < len; i++) {
		if (elem == elems[i]) {
			return true;
		}
	}
	return false;
}

int Colectie::dim() const {
	return len;
}
Iterator Colectie::iterator() const {
	return Iterator(this);
}

Iterator::Iterator(const Colectie* col) {
	this->colectie = col;
	curent = 0;
}

void Iterator::prim() {
	curent = 0;
}

void Iterator::urmator() {
	curent++;
}

bool Iterator::valid() const {
	return curent < colectie->dim();
}

TElem Iterator::element() const {
	return colectie->elems[curent];
}
