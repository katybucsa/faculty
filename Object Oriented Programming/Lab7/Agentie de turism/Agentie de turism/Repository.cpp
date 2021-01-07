#include "Repository.h"

template<class TElem>
const TElem Repository<TElem>::find(const TElem& o) {
	Iterator<TElem> it= elems.iterator();
	while (it.valid())
	{
		if (it.element() == o)
			return it.element();
		it.urmator();
	}
	throw RepositoryException("Oferta inexistenta!");
}

template<class TElem>
void Repository<TElem>::add(const TElem& o) {
	Iterator<TElem> it = elems.iterator();
	while (it.valid())
	{
		if (it.element() == o)
			throw RepositoryException("Oferta existenta!");
		it.urmator();
	}
	elems.add(o);
}

template<class TElem>
void Repository<TElem>::modify(const TElem& o) {
	Iterator<TElem> it = elems.iterator();
	while (it.valid())
	{
		if (it.element() == o)
		{
			elems.modify(o);
			return;
		}
		it.urmator();
	}
	throw RepositoryException("Oferta inexistenta!");
}

template<class TElem>
void Repository<TElem>::remove(const TElem&o) {
	Iterator<TElem> it = elems.iterator();
	while (it.valid())
	{
		if (it.element() == o)
		{
			elems.remove(o);
			return;
		}
		it.urmator();
	}
	throw RepositoryException("Oferta inexistenta!");
}




template<class TElem>
const Lista<TElem>& Repository<TElem>::getAll() const noexcept {
	return elems;
}
