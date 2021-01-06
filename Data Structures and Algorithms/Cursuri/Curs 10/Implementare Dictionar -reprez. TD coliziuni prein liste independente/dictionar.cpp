#include "dictionar.h"
#include <iostream>

int hashCode(TCheie c){
   return c;
}

Nod::Nod(TCheie c, TValoare v, PNod urm) {
	this->c = c;
	this->v = v;
	this->urm = urm;
}

int Dictionar::d(TCheie c) {
	//dispersia prin diviziune
	return hashCode(c)%m;
}


Dictionar::Dictionar() {
    m=MAX; //initializam m cu o valoare predefinita
	//se initilaizeaza listele inlantuite ca fiind vide
	for (int i=0;i<m;i++)
        l[i] = NULL;
}

//adaugare in dictionar
void Dictionar::adauga(TCheie c, TValoare v) {
	//locatia de dispersie a cheii
	int i=d(c);
	//se creeaza un nod
	PNod p = new Nod(c, v, NULL);
	//se adauga in capul listei inlantuite de la locatia i
    p->urm = l[i];
    l[i]=p;
}

Iterator Dictionar::iterator() const{
	return Iterator(*this);
}

Dictionar::~Dictionar() {
	//se elibereaza memoria alocata listelor
	for (int i=0;i<m;i++){
    //se elibereaza memoria pentru lista i
      while (l[i] != NULL) {
		PNod p = l[i];
		l[i] = l[i]->urm;
		delete p;
	  }
    }
}

void Iterator::deplasare(){
//gaseste prima lista nevida incepand cu locatia poz din tabela
      while (poz<dct.m && dct.l[poz]==NULL) poz++;
      if (poz<dct.m)
	    curent=dct.l[poz];
}

//constructor iterator
Iterator::Iterator(const Dictionar& d) :
		dct(d) {
	poz=0;
    deplasare();
}

void Iterator::prim() {
	//se determina prima lista nevida
   poz=0;
   deplasare();
}

void Iterator::urmator() {
   curent=curent->urm;
   if (curent==NULL){
      poz=poz+1;
      deplasare();
   }
}

bool Iterator::valid() const {
	return (poz<dct.m) && (curent != NULL);
}

TElem Iterator::element() const {
	TElem e;
	e.c=curent->c;
	e.v=curent->v;
	return e;
}
