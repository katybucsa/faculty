#pragma once
#include "Domain.h"
#include <algorithm>

template <class TElem> class Iterator;
template <class TElem> class Nod;
template <class TElem> class Lista;

template <class TElem> class Nod
{
public:
	friend class Lista<TElem>;
	Nod(TElem, Nod*);
	TElem element() noexcept;
	Nod* urmator() noexcept;

private:
	TElem e;
	Nod* urm;
};


template <class TElem> class Lista
{
public:
	friend class Iterator<TElem>;
	Lista() noexcept;
	Lista(const Lista&l);
	void add(const TElem&);
	void modify(const TElem&);
	void remove(const TElem&) noexcept;
	bool vida() const noexcept;
	size_t size() const noexcept;
	TElem elem(size_t) const noexcept;
	void sort(bool(*cmp)(const TElem&, const TElem&)) noexcept;
	Iterator<TElem> iterator() const noexcept;

	~Lista();
private:
	Nod<TElem>* prim;
};


template <class TElem> class Iterator
{
public:
	friend class Lista<TElem>;

	void prim() noexcept;

	void urmator() noexcept;

	bool valid() noexcept;

	TElem element() const noexcept;

private:
	Iterator(const Lista<TElem>& list) noexcept;
	const Lista<TElem>& lista;
	Nod<TElem>* curent;
};




template <class TElem> Nod<TElem>::Nod(TElem e, Nod* urm) {
	this->e = e;
	this->urm = urm;
}

template <class TElem>  TElem Nod<TElem>::element() noexcept{
	return e;
}

template <class TElem> Nod<TElem>* Nod<TElem>::urmator() noexcept {
	return urm;
}


template <class TElem> Lista<TElem>::Lista() noexcept {
	prim = NULL;
}

template <class TElem> void Lista<TElem>::add(const TElem& e) {
	Nod<TElem>* q = new Nod<TElem>(e, NULL);
	if (prim == NULL)
		prim = q;
	else {
		Nod<TElem>* p = prim;
		while (p->urm != NULL) {
			p = p->urm;
		}
		p->urm = q;
	}
}

template<class TElem>
void Lista<TElem>::modify(const TElem& e)
{
	Nod<TElem>* p = prim;
	while (p->e != e)
		p = p->urm;
	p->e = e;
}

template<class TElem>
void Lista<TElem>::remove(const TElem& e) noexcept {
	Nod<TElem> *p=NULL, *v=NULL, *ant=NULL;
	if (prim->e == e) {
		v = prim;
		prim = prim->urm;
		delete v;
	}
	else {
		bool gasit = false;
		p = prim;
		while (!gasit) {
			if (p->e == e)
				gasit = true;
			else {
				ant = p;
				p = p->urm;
			}
		}
		if(ant!=NULL)
			ant->urm = p->urm;
		delete p;
	}
}

template<class TElem>
inline bool Lista<TElem>::vida() const noexcept
{
	return prim == NULL;
}

template<class TElem>
inline size_t Lista<TElem>::size() const noexcept
{
	size_t i = 0;
	if (!vida()) {
		
		Nod<TElem>* p = prim;
		i++;
		while (p->urm != NULL) {
			i++;
			p = p->urm;
		}
	}
	return i;
}

template<class TElem>
inline TElem Lista<TElem>::elem(size_t i) const noexcept
{
	size_t j = 0;
	Nod<TElem>* p = prim;
	while (p->urm != NULL && j < i) {
		j++;
		p = p->urm;
	}
	if (i == j && p!=NULL)
		return p->e;
}

template<class TElem>
inline void Lista<TElem>::sort(bool(*cmp)(const TElem&, const TElem&)) noexcept
{
	Nod<TElem>* p = prim;
	while (p->urm != NULL) {
		Nod<TElem>* u = p->urm;
		while (u != NULL) {
			if ( cmp!=NULL && !cmp(p->e, u->e))
				std::swap(p->e, u->e);
			u = u->urm;
		}
		p = p->urm;
	}
}

template<class TElem>
Lista<TElem>::Lista(const Lista & l)
{
	this->prim = NULL;
	Nod<TElem>* n = l.prim;
	if (n != NULL) {
		this->add(n->e);
		while (n->urm != NULL) {
			n = n->urm;
			this->add(n->e);
		}
	}
}

template <class TElem> Iterator<TElem> Lista<TElem>::iterator() const noexcept{
	return Iterator<TElem>(*this);
}

template <class TElem> Lista<TElem>::~Lista() {
	while (prim != NULL) {
		Nod<TElem>* p = prim;
		prim = prim->urm;
		delete p;
	}
}

template <class TElem> Iterator<TElem>::Iterator(const Lista<TElem>& list)noexcept :lista{ list } {
	curent = list.prim;
}

template <class TElem> void Iterator<TElem>::prim() noexcept{
	curent = lista.prim;
}

template <class TElem> void Iterator<TElem>::urmator() noexcept{
	curent = curent->urmator();
}

template <class TElem> bool Iterator<TElem>::valid() noexcept{
	return curent != NULL;
}


template <class TElem> TElem Iterator<TElem>::element() const noexcept{
	return curent->element();
}