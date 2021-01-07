#include <assert.h>
#include <iostream>
#include <vector>
#include "Tests.h"



bool cmpName1(const Oferta& o1, const Oferta& o2) noexcept {
	return o1.getName() < o2.getName();
}

void testsDomain() {
	Oferta o = Oferta(12, "excursie", "Rarau", "munte", 120);
	assert(o.getId() == 12);
	assert(o.getName() == "excursie");
	assert(o.getDestination() == "Rarau");
	assert(o.getType() == "munte");
	assert(o.getPrice() == 120);
	Oferta o1 = Oferta(12, "hjg", "fd", "gfg", 342);
	assert(o == o1);
	Oferta o2 = Oferta(22, "dfd", "dss", "dsc", 12);
	assert(!(o == o2));
}


void testsRepository() {
	Repository<Oferta> repo;
	Oferta o = Oferta(12, "excursie", "Rarau", "munte", 120);
	repo.add(o);
	assert(repo.size() == 1);
	Oferta o1 = Oferta(22, "dfd", "dss", "dsc", 12);

	try {
		const Oferta e = repo.find(o1); assert(false);
	}catch (RepositoryException re) { assert(re.getMessage() == "Oferta inexistenta!"); }
	
	try {
		repo.modify(o1); assert(false);
	}catch (RepositoryException re) { assert(re.getMessage() == "Oferta inexistenta!"); }

	try {
		repo.remove(o1); assert(false);
	}catch (RepositoryException re) { assert(re.getMessage() == "Oferta inexistenta!"); }

	Oferta o2 = Oferta(12, "fgdfg", "mare", "Euforie Sud", 556);

	try {
		repo.add(o2); assert(false);
	}catch (RepositoryException re) { assert(re.getMessage() == "Oferta existenta!"); }

	repo.modify(o2);
	repo.add(o1);
	assert(repo.size() == 2);
	const Lista<Oferta>& v = repo.getAll();
	assert(v.elem(1) == repo.find(o1));
	assert(v.elem(0) == o2);
	assert(v.elem(1) == o1);
	Oferta o3 = Oferta(22, "ndfg", "sga", "wrqe", 12);
	repo.modify(o3);
	repo.remove(o2);
	assert(repo.size() == 1);
	try {
		const Oferta e = repo.find(o2); assert(false);
	}catch (RepositoryException re) { assert(re.getMessage() == "Oferta inexistenta!"); }
}


void testsService() {
	Repository<Oferta> repo;
	OfferValidator<Oferta> valid;
	Service serv{ repo, valid };
	serv.addOffer(12, "excursie", "Rarau", "munte", 120);
	assert(serv.len() == 1);
	try {
		serv.addOffer(-2, "fd", "fr", "rf", 123); assert(false);
	}catch (ValidatorException ve) { assert(ve.getMessage() == "Id invalid!"); }
	try {
		serv.modifyOffer(-4, "gd", "tr", "tr", -43); assert(false);
	}catch (ValidatorException ve) { assert(ve.getMessage() == "Id invalid!Pret invalid!"); }

	serv.modifyOffer(12, "excursie", "constanta", "mare", 4566);
	serv.addOffer(22, "dfd", "dss", "dsc", 12323);
	Oferta o = serv.getOffer(12);
	assert(o == serv.getAl().elem(0));
	serv.addOffer(56, "assd", "dss", "tyrg", 453);
	serv.addOffer(87, "gjgd", "afg", "dsc", 6453);
	serv.addOffer(45, "cmas", "mghf", "asd", 132);
	assert(serv.len() == 5);
	Lista<Oferta> v = serv.sortByDestination();
	assert(v.elem(0) == serv.getAl().elem(3));
	assert(v.elem(1) == serv.getAl().elem(0));
	assert(v.elem(2) == serv.getAl().elem(2));
	assert(v.elem(3) == serv.getAl().elem(1));
	assert(v.elem(4) == serv.getAl().elem(4));
	Lista<Oferta> v1 = serv.sortByName();
	assert(v1.elem(0) == serv.getAl().elem(2));
	assert(v1.elem(1) == serv.getAl().elem(4));
	assert(v1.elem(2) == serv.getAl().elem(1));
	assert(v1.elem(3) == serv.getAl().elem(0));
	assert(v1.elem(4) == serv.getAl().elem(3));
	Lista<Oferta> v2 = serv.sortByTypePrice();
	assert(v2.elem(0) == serv.getAl().elem(4));
	assert(v2.elem(1) == serv.getAl().elem(3));
	assert(v2.elem(2) == serv.getAl().elem(1));
	assert(v2.elem(3) == serv.getAl().elem(0));
	assert(v2.elem(4) == serv.getAl().elem(2));
	Lista<Oferta> v3 = serv.filterByPrice(5234);
	assert(v3.elem(0) == serv.getAl().elem(0));
	assert(v3.elem(1) == serv.getAl().elem(2));
	assert(v3.elem(2) == serv.getAl().elem(4));
	Lista<Oferta> v4 = serv.filterByDestination("dss");
	assert(v4.elem(0) == serv.getAl().elem(1));
	assert(v4.elem(1) == serv.getAl().elem(2));
	serv.removeOffer(87);
	assert(serv.len() == 4);
}

void testValidator() {
	Oferta o = Oferta(-1, "dsd", "das", "dasf", -23);
	OfferValidator<Oferta> valid;
	try {
		valid.validate(o); assert(false);
	}catch (ValidatorException) { assert(true); }
	Oferta o1 = Oferta(-1, "dsd", "das", "dasf", 1223);
	try {
		valid.validate(o1); assert(false);
	}catch (ValidatorException ve) { assert(ve.getMessage() == "Id invalid!"); }

	Oferta o2 = Oferta(42, "dsd", "das", "dasf", -1223);
	try {
		valid.validate(o2); assert(false);
	}catch (ValidatorException ve) { assert(ve.getMessage() == "Pret invalid!"); }
}

void testLista() {
	Lista<Oferta> l;
	assert(l.vida() == true);
	Oferta o1 = Oferta(1, "das", "dsf", "fsdf", 2424);
	l.add(o1);
	assert(l.size() == 1);
	Oferta o2 = Oferta(1, "vdf", "fdsf", "fds", 432);
	l.modify(o2);
	Oferta o3 = Oferta(2, "dsf", "dsf", "fteg", 5645);
	l.add(o3);
	assert(l.size() == 2);
	Oferta o4 = Oferta(2, "fre", "hyujt", "reg", 3422);
	l.modify(o4);
	l.remove(o2);
	assert(l.size() == 1);
	Oferta o5 = Oferta(3, "are", "fsd", "oj", 452);
	l.add(o5);
	assert(l.vida() == false);
	assert(l.elem(1) == o5);
	Oferta o6 = Oferta(4, "mbfd", "gfg", "fgd", 5423);
	l.add(o6);
	Lista<Oferta> l1 = l;
	l1.sort(cmpName1);
	assert(l1.elem(0) == o5);
	assert(l1.elem(1) == o4);
	assert(l1.elem(2) == o6);
	l1.remove(o6);
	assert(l1.size() == 2);
	Iterator<Oferta> it = l.iterator();
	int k = 0;
	while (it.valid()) {
		assert(it.element() == l.elem(k));
		k++;
		it.urmator();
	}
}

void runAllTests() {
	testsDomain();
	testsRepository();
	testsService();
	testValidator();
	testLista();
}