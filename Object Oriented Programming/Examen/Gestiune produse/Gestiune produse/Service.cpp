#include "Service.h"
#include <algorithm>

void Service::add(int id, std::string nume, std::string tip, double pret){
	Produs p{ id,nume,tip,pret };
	valid.validate(p);
	repo.add(p);
}

std::vector<Produs> Service::getAll(){
	return repo.getAll();
}

bool cmpPrice(const Produs& p1, const Produs& p2) {
	return p1.getPret() < p2.getPret();
}

std::vector<Produs> Service::sortByPrice(){
	std::vector<Produs> v = repo.getAll();
	std::sort(v.begin(), v.end(), cmpPrice);
	return  v;
}


