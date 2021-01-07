#include "Service.h"
#include "Export.h"
#include <algorithm>
#include <iterator>
#include <set>


void Service::addOffer(const std::string name, const std::string destination, const std::string type, float price) {
	Oferta o = Oferta{name, destination, type, price };
	valid.validate(o);
	repo.add(o);
	undoActions.push_back(std::make_unique<AddUndo>(repo, o));
}

void Service::modifyOffer(const std::string name, const std::string destination, const std::string type, float price)
{
	Oferta o = Oferta{name, destination, type, price };
	valid.validate(o);
	Oferta of = repo.find(name);
	repo.modify(o);
	wlist.modify(o);
	undoActions.push_back(std::make_unique<ModifyUndo>(repo, of));
}

void Service::removeOffer(const std::string& name) {
	Oferta o = Oferta{name, "def", "def", 234 };
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

const Oferta Service::getOffer(const std::string& name){
	return repo.find(name);
}


std::map<std::string,Oferta> Service::filterByDestination(std::string destination) const
{
	std::map<std::string,Oferta> cop = repo.getAll();
	std::map<std::string,Oferta> m;
	std::copy_if(cop.begin(), cop.end(), std::inserter(m,m.end()), [&destination](const std::pair<std::string,Oferta>& pair) noexcept {return pair.second.getDestination() == destination; });
	return m;
}




std::map<std::string,Oferta> Service::filterByPrice(float price) const
{
	std::map<std::string,Oferta> cop = repo.getAll();
	std::map<std::string,Oferta> m;
	std::copy_if(cop.begin(), cop.end(), std::inserter(m, m.end()), [&price](const std::pair<std::string, Oferta>& pair) noexcept {return pair.second.getPrice() <= price; });
	return m;
}


bool cmpName(const std::pair<std::string, Oferta>& pair1, const std::pair<std::string, Oferta>& pair2) noexcept {
	return pair1.second.getName() < pair2.second.getName();
}

std::map<std::string,Oferta> Service::sortByName() const {
	std::vector < std::pair<std::string, Oferta>> cop = repo.getAll();
	std::map<std::string, Oferta> m;
	//std::transform(cop.begin(), cop.end(), std::inserter(m, m.end()), cmpName);
	//std::set<std::pair<std::string, Oferta>, bool>set(cop.begin(), cop.end(), cmpName);
	std::sort(cop.begin(), cop.end(), cmpName);
	return cop;
}

/*bool cmpDestination(const Oferta& o1, const Oferta& o2) noexcept {
	return o1.getDestination() < o2.getDestination();
}

std::map<std::string,Oferta> Service::sortByDestination() const {
	std::map<std::string,Oferta> cop = repo.getAll();
	std::sort(cop.begin()->second, cop.end()->second, cmpDestination);
	return cop;
}

bool cmpTypePrice(const Oferta& o1, const Oferta& o2) noexcept {
	if (o1.getType() != o2.getType())
		return o1.getType() < o2.getType();
	return o1.getPrice() < o2.getPrice();
}

std::map<std::string,Oferta> Service::sortByTypePrice() const {
	std::map<std::string,Oferta> cop = repo.getAll();
	std::sort(cop.begin()->second, cop.end()->second, cmpTypePrice);
	return cop;
}
*/
size_t Service::len() const noexcept {
	return repo.sizeElems();
}


void Service::addToWishlist(const std::string& name) {
	const Oferta& o = repo.find(name);
	wlist.add(o);
}

void Service::addRandom(unsigned int nr) {
	wlist.fillRandom(nr, repo.getAll());
}


void Service::emptyWishlist() {
	wlist.empty();
}

const std::map<std::string,Oferta>& Service::allFromWishlist() {
	return wlist.Wlist();
}

void Service::exportWishlistHTML(std::string fName) const {
	exportToHTML(fName, wlist.Wlist());
}
