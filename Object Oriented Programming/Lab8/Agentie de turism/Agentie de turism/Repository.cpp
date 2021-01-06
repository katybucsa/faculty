#include "Repository.h"



int Repository::find(const Oferta& o) {
	for (size_t i = 0; i < offers.size(); i++)
		if (offers[i] == o)
			return i;
	throw RepositoryException("Oferta inexistenta!");
}


void Repository::add(const Oferta& o) {
	for (auto of : offers)
		if (of == o)
			throw RepositoryException("Oferta existenta!");
	offers.push_back(o);
}

void Repository::modify(const Oferta& o) {
	const int poz = find(o);
	offers[poz] = o;
}


void Repository::remove(const Oferta&o) {
	const int poz = find(o);
	offers.erase(offers.begin() + poz);
}

const Oferta& Repository::getOferta(unsigned int i) {
	return offers[i];
}


const std::vector<Oferta>& Repository::getAll() const noexcept {
	return offers;
}

int Repository::sizeElems() const noexcept {
	return offers.size();
}