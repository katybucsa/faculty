#include "Service.h"
#include <algorithm>



void Service::addOffer(int id, const std::string name, const std::string destination, const std::string type, float price) {
	Oferta o = Oferta{ id, name, destination, type, price };
	valid.validate(o);
	repo.add(o);
}

void Service::modifyOffer(int id, const std::string name, const std::string destination, const std::string type, float price)
{
	Oferta o = Oferta{ id, name, destination, type, price };
	valid.validate(o);
	repo.modify(o);
}

void Service::removeOffer(int id) {
	Oferta o = Oferta{ id, "def", "def", "def", 234 };
	valid.validate(o);
	repo.remove(o);
}


const Oferta Service::getOffer(int id)
{
	Oferta o = Oferta{ id, "def", "def", "def", 234 };
	valid.validate(o);
	const int poz = repo.find(o);
	return repo.getOferta(poz);
}


std::vector<Oferta> Service::filterByDestination(std::string destination) const
{
	std::vector<Oferta> cop = repo.getAll();
	std::vector<Oferta> v;
	std::copy_if(cop.begin(), cop.end(), std::back_inserter(v), [&destination](const Oferta& o) noexcept {return o.getDestination() == destination; });
	return v;
}




std::vector<Oferta> Service::filterByPrice(float price) const
{
	std::vector<Oferta> cop = repo.getAll();
	std::vector<Oferta> v;
	std::copy_if(cop.begin(), cop.end(), std::back_inserter(v), [&price](const Oferta& o) noexcept {return o.getPrice() <= price; });
	return v;
}


bool cmpName(const Oferta& o1, const Oferta& o2) noexcept {
	return o1.getName() < o2.getName();
}

std::vector<Oferta> Service::sortByName() const {
	std::vector<Oferta> cop = repo.getAll();
	std::sort(cop.begin(), cop.end(), cmpName);
	return cop;
}

bool cmpDestination(const Oferta& o1, const Oferta& o2) noexcept {
	return o1.getDestination() < o2.getDestination();
}

std::vector<Oferta> Service::sortByDestination() const {
	std::vector<Oferta> cop = repo.getAll();
	std::sort(cop.begin(), cop.end(), cmpDestination);
	return cop;
}

bool cmpTypePrice(const Oferta& o1, const Oferta& o2) noexcept {
	if (o1.getType() != o2.getType())
		return o1.getType() < o2.getType();
	return o1.getPrice() < o2.getPrice();
}

std::vector<Oferta> Service::sortByTypePrice() const {
	std::vector<Oferta> cop = repo.getAll();
	std::sort(cop.begin(), cop.end(), cmpTypePrice);
	return cop;
}


int Service::len() const noexcept {
	return repo.sizeElems();
}
