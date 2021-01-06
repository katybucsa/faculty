#include "lista.h"
#include <iostream>


Nod::Nod(TElem e, PNod urm) {
	this->e = e;
	this->urm = urm;
}

TElem Nod::element() {
	return e;
}

PNod Nod::urmator() {
	return urm;
}

Lista::Lista() {
	_prim = NULL;
}

//adaugare la sfarsitul listei
void Lista::adaugaSfarsit(TElem e) {
	PNod q = new Nod(e, NULL);
    if (_prim==NULL)
        _prim = q;
    else{
        PNod p=_prim;
        // se parcurge lista pana la ultimul nod
        for(;p->urm!=NULL;p=p->urm);
        //se adauga q dupa p
        p->urm = q;
    }
}

//adaugare dupa o pozitie data de un iterator
//daca iteratorul nu e valid, se adauga la sfarsitul listei
void Lista::adaugaDupa(Iterator poz, TElem e){
	PNod q = new Nod(e, NULL);
    if (!poz.valid())
        //iteratorul nu e valid, se adauga la sfarsitul listei
        adaugaSfarsit(e);
    else{
        //pozitia curenta a iteratorului
        PNod p=poz.curent;
        //se adauga dupa p
        q->urm=p->urm;
        p->urm=q;
    }
}


Iterator Lista::prim() const{
	return Iterator(*this);
}

Lista::~Lista() {
	//se elibereaza memoria alocata nodurilor listei
	while (_prim != NULL) {
		PNod p = _prim;
		_prim = _prim->urm;
		delete p;
	}
}

//constructor
Iterator::Iterator(const Lista& lst) :
		lista(lst) {
	curent = lst._prim;
}

void Iterator::prim() {
	curent = lista._prim;
}

void Iterator::urmator() {
	curent = curent->urmator();
}

bool Iterator::valid() const {
	return curent != NULL;
}

TElem Iterator::element() const {
	return curent->element();
}
