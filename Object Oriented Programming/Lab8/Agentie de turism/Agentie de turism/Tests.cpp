#include <assert.h>
#include <iostream>
#include <vector>
#include "Tests.h"



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
	Repository repo;
	Oferta o = Oferta(12, "excursie", "Rarau", "munte", 120);
	repo.add(o);
	assert(repo.sizeElems() == 1);
	Oferta o1 = Oferta(22, "dfd", "dss", "dsc", 12);

	try {
		const int poz = repo.find(o1); assert(false);
	}
	catch (RepositoryException re) { assert(re.getMessage() == "Oferta inexistenta!"); }
	
	try {
		repo.modify(o1); assert(false);
	}
	catch (RepositoryException re) { assert(re.getMessage() == "Oferta inexistenta!"); }

	try {
		repo.remove(o1); assert(false);
	}
	catch (RepositoryException re) { assert(re.getMessage() == "Oferta inexistenta!"); }

	Oferta o2 = Oferta(12, "fgdfg", "mare", "Euforie Sud", 556);

	try {
		repo.add(o2); assert(false);
	}
	catch (RepositoryException re) { assert(re.getMessage() == "Oferta existenta!"); }

	repo.modify(o2);
	repo.add(o1);
	assert(repo.find(o1) == 1);
	assert(repo.sizeElems() == 2);
	std::vector<Oferta> v = repo.getAll();
	assert(v[0] == o2);
	assert(v[1] == o1);
	repo.remove(o2);
	assert(repo.sizeElems() == 1);
	try {
		const int poz = repo.find(o2); assert(false);
	}
	catch (RepositoryException re) { assert(re.getMessage() == "Oferta inexistenta!"); }
}


void testsService() {
	Repository repo;
	OfferValidator valid;
	Service serv{ repo, valid };
	serv.addOffer(12, "excursie", "Rarau", "munte", 120);
	assert(serv.len() == 1);
	try {
		serv.addOffer(-2, "fd", "fr", "rf", 123); assert(false);
	}
	catch (ValidatorException ve) { assert(ve.getMessage() == "Id invalid!"); }
	try {
		serv.modifyOffer(-4, "gd", "tr", "tr", -43); assert(false);
	}
	catch (ValidatorException ve) { assert(ve.getMessage() == "Id invalid!Pret invalid!"); }

	serv.modifyOffer(12, "excursie", "constanta", "mare", 4566);
	serv.addOffer(22, "dfd", "dss", "dsc", 12323);
	Oferta o = serv.getOffer(12);
	assert(o == serv.getAl()[0]);
	serv.addOffer(56, "assd", "dss", "tyrg", 453);
	serv.addOffer(87, "gjgd", "afg", "dsc", 6453);
	serv.addOffer(45, "cmas", "mghf", "asd", 132);
	assert(serv.len() == 5);
	std::vector<Oferta> v = serv.sortByDestination();
	assert(v[0] == serv.getAl()[3]);
	assert(v[1] == serv.getAl()[0]);
	assert(v[2] == serv.getAl()[1]);
	assert(v[3] == serv.getAl()[2]);
	assert(v[4] == serv.getAl()[4]);
	std::vector<Oferta> v1 = serv.sortByName();
	assert(v1[0] == serv.getAl()[2]);
	assert(v1[1] == serv.getAl()[4]);
	assert(v1[2] == serv.getAl()[1]);
	assert(v1[3] == serv.getAl()[0]);
	assert(v1[4] == serv.getAl()[3]);
	std::vector<Oferta> v2 = serv.sortByTypePrice();
	assert(v2[0] == serv.getAl()[4]);
	assert(v2[1] == serv.getAl()[3]);
	assert(v2[2] == serv.getAl()[1]);
	assert(v2[3] == serv.getAl()[0]);
	assert(v2[4] == serv.getAl()[2]);
	std::vector<Oferta> v3 = serv.filterByPrice(5234);
	assert(v3[0] == serv.getAl()[0]);
	assert(v3[1] == serv.getAl()[2]);
	assert(v3[2] == serv.getAl()[4]);
	std::vector<Oferta> v4 = serv.filterByDestination("dss");
	assert(v4[0] == serv.getAl()[1]);
	assert(v4[1] == serv.getAl()[2]);
	serv.removeOffer(87);
	assert(serv.len() == 4);
}

void testValidator() {
	Oferta o = Oferta(-1, "dsd", "das", "dasf", -23);
	OfferValidator valid;
	try {
		valid.validate(o); assert(false);
	}
	catch (ValidatorException) { assert(true); }
	Oferta o1 = Oferta(-1, "dsd", "das", "dasf", 1223);
	try {
		valid.validate(o1); assert(false);
	}
	catch (ValidatorException ve) { assert(ve.getMessage() == "Id invalid!"); }

	Oferta o2 = Oferta(42, "dsd", "das", "dasf", -1223);
	try {
		valid.validate(o2); assert(false);
	}
	catch (ValidatorException ve) { assert(ve.getMessage() == "Pret invalid!"); }
}


void runAllTests() {
	testsDomain();
	testsRepository();
	testsService();
	testValidator();
}