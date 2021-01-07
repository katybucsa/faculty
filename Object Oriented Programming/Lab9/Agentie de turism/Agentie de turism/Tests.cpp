#include <assert.h>
#include <fstream>
#include <vector>
#include "Tests.h"



void testsDomain() {
	Oferta o = Oferta("excursie", "Rarau", "munte", 120);
	assert(o.getName() == "excursie");
	assert(o.getDestination() == "Rarau");
	assert(o.getType() == "munte");
	assert(o.getPrice() == 120);
	Oferta o1 = Oferta("excursie", "fd", "gfg", 342);
	assert(o == o1);
	Oferta o2 = Oferta("dfd", "dss", "dsc", 12);
	assert(!(o == o2));
	std::ofstream out("teste.txt", std::ios::trunc);
	out << o;
	out.close();
}


void testsRepository() {
	std::ofstream out("teste.txt", std::ios::trunc);
	out.close();
	FileRepository repo{ "teste.txt" };
	Oferta o = Oferta("excursie", "Rarau", "munte", 120);
	repo.add(o);
	assert(repo.sizeElems() == 1);
	Oferta o1 = Oferta("dfd", "dss", "dsc", 12);
	try {
		const size_t poz = repo.getOPoz(o1); assert(false);
	}catch (RepositoryException re) { assert(re.getMessage() == "Oferta inexistenta!"); }
	
	try {
		repo.modify(o1); assert(false);
	}catch (RepositoryException re) { assert(re.getMessage() == "Oferta inexistenta!"); }

	try {
		repo.remove(o1); assert(false);
	}catch (RepositoryException re) { assert(re.getMessage() == "Oferta inexistenta!"); }

	Oferta o2 = Oferta("excursie", "mare", "Euforie Sud", 556);

	try {
		repo.add(o2); assert(false);
	}catch (RepositoryException re) { assert(re.getMessage() == "Oferta existenta!"); }
	
	repo.modify(o2);
	repo.add(o1);
	assert(repo.getOPoz(o1) == 1);
	assert(repo.sizeElems() == 2);
	std::vector<Oferta> v = repo.getAll();
	assert(v[0] == o2);
	assert(v[1] == o1);
	repo.remove(o2);
	assert(repo.sizeElems() == 1);
	try {
		const size_t poz = repo.getOPoz(o2); assert(false);
	}catch (RepositoryException re) { assert(re.getMessage() == "Oferta inexistenta!"); }
}


void testsService() {
	std::ofstream out("teste.txt", std::ios::trunc);
	out.close();
	FileRepository repo{ "teste.txt" };
	OfferValidator valid;
	Service serv{ repo, valid };
	serv.addOffer("excursie", "Rarau", "munte", 120);
	assert(serv.len() == 1);
	try {
		serv.modifyOffer("gd", "tr", "tr", -43); assert(false);
	}catch (ValidatorException ve) { assert(ve.getMessage() == "Pret invalid!"); }

	serv.modifyOffer("excursie", "constanta", "mare", 4566);
	serv.addOffer("dfd", "dss", "dsc", 12323);
	Oferta o = serv.getOffer("excursie");
	assert(o == serv.getAl()[0]);
	serv.addOffer("assd", "dss", "tyrg", 453);
	serv.addOffer("gjgd", "afg", "dsc", 6453);
	serv.addOffer("cmas", "mghf", "asd", 132);
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


	//Wishlist
	serv.addToWishlist("assd");
	std::vector<Oferta> wl = serv.allFromWishlist();
	assert(wl.size() == 1);
	serv.addRandom(4);
	assert(serv.allFromWishlist().size() == 4);
	serv.emptyWishlist();
	assert(serv.allFromWishlist().size() == 0);
	serv.addRandom(10);
	assert(serv.allFromWishlist().size() == 5);


	serv.removeOffer("gjgd");
	assert(serv.len() == 4);
	serv.modifyOffer("cmas", "sdf", "dfs", 4232);
}

void testValidator() {
	OfferValidator valid;
	Oferta o = Oferta("dsd", "das", "dasf", -1223);
	try {
		valid.validate(o); assert(false);
	}catch (ValidatorException ve) { assert(ve.getMessage() == "Pret invalid!"); }
}


void testsFileRepository() {
	try {
		FileRepository frepo{ "testopen.txt" }; assert(false);
	}catch (const FileException& e) { assert(e.getMessage() == "Eroare deschidere testopen.txt"); };
	
	std::ofstream out("teste.txt", std::ios::trunc);
	out.close();
	FileRepository frepo{ "teste.txt" };
	frepo.add(Oferta{ "as","fdads sfd","sds",432 });
	auto o = frepo.getOferta("as");
	assert(o.getPrice() == 432);
	assert(frepo.sizeElems() == 1);
	try {
		auto i = frepo.getOPoz(Oferta("ds", "fwe", "fds", 54)); assert(false);
	}catch (const RepositoryException&) { assert(true); };

	try {
		auto o = frepo.getOferta("gf");
	}catch (const RepositoryException&) { assert(true); };
	
	frepo.modify(Oferta("as", "da", "fe", 634));
	std::vector<Oferta> v = frepo.getAll();
	assert(v.size() == 1);
	frepo.remove(Oferta("as", "dsf", "def", 42));
	assert(frepo.sizeElems() == 0);
}



void testsUndo() {
	FileRepository frepo{ "teste.txt" };
	OfferValidator valid;
	Service serv{ frepo,valid };
	try {
		serv.undo(); assert(false);
	}catch (const UndoException& e) { assert(e.getMessage()== "Nu se poate face undo!"); };
	
	serv.addOffer("ds", "fe", "fsdf", 345);
	serv.addOffer("dsfd", "frte", "df", 978);
	serv.modifyOffer("ds", "few", "fer", 4383);
	assert(serv.getOffer("ds").getPrice() == 4383);
	serv.undo();
	assert(serv.getOffer("ds").getPrice() == 345);
	assert(serv.len() == 2);
	serv.removeOffer("dsfd");
	assert(serv.len() == 1);
	serv.undo();
	assert(serv.len() == 2);
	serv.undo();
	assert(serv.len() == 1);
}


void testExportHTML() {
	Repository repo;
	OfferValidator valid;
	Service serv{ repo,valid };
	serv.addOffer("a", "a", "a", 123);
	serv.addOffer("b", "a", "a", 765);
	serv.addOffer("c", "a", "a", 3425);
	serv.addToWishlist("a");
	serv.addToWishlist("b");
	serv.addToWishlist("c");
	serv.exportWishlistHTML("testExport.html");
	std::ifstream in("testExportHTML");
	in.open("testExportHTML");

	try {
		serv.exportWishlistHTML("m/n/export.html"); assert(false);
	}catch (const FileException& e) { assert(e.getMessage() == "Nu se poate deschide m/n/export.html"); };	
}


void runAllTests() {
	testsDomain();
	testsRepository();
	testsService();
	testValidator();
	testsFileRepository();
	testsUndo();
	testExportHTML();
}