#include "Service.h"


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
	return repo.find(o);
}


Lista<Oferta> Service::filterByDestination(const std::string destination) const
{
	Lista<Oferta> cop;
	Lista<Oferta> l= getAl();
	Iterator<Oferta> it = l.iterator();
	while(it.valid()){
		if (it.element().getDestination() == destination){
			cop.add(it.element());
		}
		it.urmator();
	}
	return cop;
}




Lista<Oferta> Service::filterByPrice(float price) const
{
	Lista<Oferta> cop;
	Lista<Oferta> l = getAl();
	Iterator<Oferta> it = l.iterator();
	while (it.valid()) {
		if (it.element().getPrice() <= price) {
			cop.add(it.element());
		}
		it.urmator();
	}
	return cop;
}

bool cmpName(const Oferta& o1, const Oferta& o2) noexcept {
	return o1.getName() < o2.getName();
}


Lista<Oferta> Service::sortByName() const {
	Lista<Oferta> cop= getAl();
	cop.sort(cmpName);
	return cop;
}

bool cmpDestination(const Oferta& o1, const Oferta& o2) noexcept {
	return o1.getDestination() < o2.getDestination();
}

Lista<Oferta> Service::sortByDestination() const {
	Lista<Oferta> cop = getAl();
	cop.sort(cmpDestination);
	return cop;
}

bool cmpTypePrice(const Oferta& o1, const Oferta& o2) noexcept {
	if (o1.getType() != o2.getType())
		return o1.getType() < o2.getType();
	return o1.getPrice() < o2.getPrice();
}

Lista<Oferta> Service::sortByTypePrice() const {
	Lista<Oferta> cop = getAl();
	cop.sort(cmpTypePrice);
	return cop;
}