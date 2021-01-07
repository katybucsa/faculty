#pragma once
#include "Service.h"
#include <assert.h>

void testDomain() {
	Produs p{ 12,"apa","lichid",2 };
	assert(p.getId() == 12);
	assert(p.getNume() == "apa");
	assert(p.getTip() == "lichid");
	assert(p.getPret() == 2);
	Produs p1{ 12,"suc","lichid",2 };
	assert(p == p1);
}


void testRepoService() {
	/*std::ofstream ("teste.txt");
	FileRepo repo{ "teste.txt" };
	Validator valid;
	Service serv{ repo,valid };
	Produs p{ 12,"apa","lichid",2 };
	serv.add(12, "apa", "lichid", 2);
	assert(serv.getAll().size() == 1);
	try {
		repo.add(p); assert(false);
	}catch (ProdusException) { assert(true); }
	Produs p1{ 13,"suc","lichid",1 };
	Produs p2{ 12,"ciocolata","asd",5 };
	std::vector<Produs> v = serv.sortByPrice();
	assert(v[0].getId() == 13);
	assert(v[1].getId() == 12);
	assert(v[2].getId() == 15);
	Produs p4{ 12,"","lichid",222 };
	try {
		valid.validate(p4); assert(false);
	}catch( ProdusException) { assert(true); }

	try {
		FileRepo{ "dsf" }; assert(false);
	}catch (ProdusException) { assert(true); }*/
}


void testAll() {
	testDomain();
	//testRepoService();
}
