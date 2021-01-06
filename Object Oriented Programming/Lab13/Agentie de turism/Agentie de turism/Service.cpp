#include "Service.h"
#include "Export.h"
#include <algorithm>



void Service::addOffer(const std::string name, const std::string destination, const std::string type, float price) {
	Oferta o = Oferta{ name, destination, type, price };
	valid.validate(o);
	repo.add(o);
	undoActions.push_back(std::make_unique<AddUndo>(repo, o));
}

void Service::modifyOffer(const std::string name, const std::string destination, const std::string type, float price)
{
	Oferta o = Oferta{ name, destination, type, price };
	valid.validate(o);
	Oferta of = repo.getOferta(name);
	repo.modify(o);
	wlist.modify(o);
	undoActions.push_back(std::make_unique<ModifyUndo>(repo, of));
}

void Service::removeOffer(const std::string& name) {
	Oferta o = Oferta{ name, "def", "def", 234 };
	valid.validate(o);
	repo.remove(o);
	wlist.remove(o);
	undoActions.push_back(std::make_unique<RemoveUndo>(repo, o));
}

void Service::undo() {
	if (undoActions.empty())
		throw UndoException("Nu se poate face undo!");
	undoActions.back()->doUndo();
	undoActions.pop_back();
}

const Oferta Service::getOffer(const std::string& name) {
	return repo.getOferta(name);
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

size_t Service::len() const noexcept {
	return repo.sizeElems();
}


void Service::addToWishlist(const std::string& name) {
	const Oferta& o = repo.getOferta(name);
	wlist.add(o);
}

void Service::addRandom(unsigned int nr) {
	wlist.fillRandom(nr, repo.getAll());
}


void Service::emptyWishlist() {
	wlist.empty();
}

const std::vector<Oferta>& Service::allFromWishlist() {
	return wlist.Wlist();
}

void Service::exportWishlistHTML(std::string fName) const {
	exportToHTML(fName, wlist.Wlist());
}
