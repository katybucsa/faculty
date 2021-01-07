#include "UI.h"
#include <iostream>

void UI::printOffers(const std::vector<Oferta>& offers) {
	std::cout << std::endl;
	for (const auto& offer : offers)
		std::cout << offer;
	std::cout << std::endl << std::endl;
}


template <typename T>
T read(const std::string& message, const std::string& err)
{
	T cmd{};
	while (true) {
		std::cout << message;
		std::cin >> cmd;
		const bool fail = std::cin.fail();
		std::cin.clear();
		const auto& aux = std::cin.ignore(1000, '\n');
		if (!fail && aux.gcount() <= 1)
			break;
		std::cout << err;
	}
	return cmd;
}

void UI::uiAddOffer() {
	float price = -1;
	std::string name, destination, type;
	std::cout << "Introduceti denumire oferta: ";
	std::getline(std::cin, name);
	std::cout << "Introduceti destinatie: ";
	std::getline(std::cin, destination);
	std::cout << "Introduceti tip: ";
	std::getline(std::cin, type);
	price = read<float>("Introduceti pret: ", "Pret invalid!\n\n");
	serv.addOffer(name, destination, type, price);
	std::cout << std::endl << "Oferta adaugata cu succes!\n" << std::endl << std::endl;
}

void UI::uiModifyOffer() {
	std::string name, destination, type;
	float price = -1;
	std::cout << "Introduceti denumirea: ";
	std::getline(std::cin, name);
	std::cout << "Introduceti destinatie: ";
	std::getline(std::cin, destination);
	std::cout << "Introduceti tip: ";
	std::getline(std::cin, type);
	price = read<float>("Introduceti noul pret: ", "Pret invalid!\n\n");
	serv.modifyOffer(name, destination, type, price);
	std::cout << std::endl << "Oferta modificata cu succes!" << std::endl;
}

void UI::uiRemoveOffer() {
	std::string name;
	std::cout << "Introduceti denumirea ofertei pe care doriti sa o stergeti:";
	std::getline(std::cin, name);
	serv.removeOffer(name);
	std::cout << std::endl << "Oferta stearsa  cu succes!" << std::endl << std::endl;
}

void UI::uiGetOffer() {
	std::string name;
	std::getline(std::cin, name);
	const Oferta o = serv.getOffer(name);
	std::cout << std::endl<< o << std::endl;
}

void UI::uiFilterByDestination() {
	std::string destination;
	std::cout << "Introduceti destinatie: ";
	std::getline(std::cin, destination);
	const std::vector<Oferta> v = serv.filterByDestination(destination);
	if (v.size() == 0)
		std::cout << "Nu exista oferte destinatia " << destination << std::endl << std::endl;
	else
		printOffers(v);
}



void UI::uiFilterByPrice() {
	float price;
	price = read<float>("Introduceti pret: ", "Pret invalid!\n\n");
	const std::vector<Oferta> v = serv.filterByPrice(price);
	if (v.size() == 0)
		std::cout << "Nu exista oferte cu pretul mai mic decat " << price << std::endl << std::endl;
	else
		printOffers(v);
}


void UI::uiSortByName() {
	const std::vector<Oferta> v = serv.sortByName();
	printOffers(v);
}

void UI::uiSortByDestination() {
	const std::vector<Oferta> v = serv.sortByDestination();
	printOffers(v);
}


void UI::uiSortByTypePrice() {
	const std::vector<Oferta> v = serv.sortByTypePrice();
	printOffers(v);
}


void UI::uiAddToWishlist()
{
	std::string name;
	std::cout << "Introduceti denumirea ofertei pe care doriti sa o adaugati in wishlist: ";
	std::getline(std::cin, name);
	serv.addToWishlist(name);
	std::cout << std::endl << "Oferta adaugata cu succes in wishlist!\n\n";
}


void UI::uiEmptyWishlist() {
	serv.emptyWishlist();
	std::cout << std::endl << "Golire wishlist s-a realizat cu succes!\n\n";
}

void UI::uiAddRandomToWishlist() {
	unsigned int nr = read<unsigned int>("Introduceti numarul total de oferte care doriti sa fie in wishlist", "Introduceti un intreg");
	serv.addRandom(nr);
	std::cout << std::endl << "Au fost adaugate oferte aleator in wishlist\n\n";
}

void UI::printWishlistSize() {
	const std::vector<Oferta> wlist = serv.allFromWishlist();
	std::cout << std::endl << "Numarul total de oferte din wishlist: " << wlist.size() << "\n\n";;
}


void UI::uiExportWishlistHTML() {
	std::string file = read<std::string>("Introduceti fisierul HTML: ", "Eroare fisier!\n");
	serv.exportWishlistHTML(file);
	std::cout << "Export wishlist HTML s-a realizat cu succes!\n\n";
}

void UI::executeCmd(int nr) {
	const std::vector<Oferta> v = serv.getAl();
	const std::vector<Oferta>& wlist = serv.allFromWishlist();
	switch (nr)
	{
	case 1:
		uiAddOffer();
		break;
	case 2:
		printOffers(v);
		break;
	case 3:
		uiModifyOffer();
		break;
	case 4:
		uiRemoveOffer();
		break;
	case 5:
		uiGetOffer();
		break;
	case 6:
		uiFilterByDestination();
		break;
	case 7:
		uiFilterByPrice();
		break;
	case 8:
		uiSortByName();
		break;
	case 9:
		uiSortByDestination();
		break;
	case 10:
		uiSortByTypePrice();
		break;
	case 11:
		serv.undo();
		break;
	case 12:
		uiAddToWishlist();
		break;
	case 13:
		uiEmptyWishlist();
		break;
	case 14:
		uiAddRandomToWishlist();
		break;
	case 15:
		printOffers(wlist);
		break;
	case 16:
		uiExportWishlistHTML();
		break;
	}
	printWishlistSize();
}


void printMenu() {
	std::cout << "1.Adauga oferta" << std::endl;
	std::cout << "2.Afisare oferte" << std::endl;
	std::cout << "3.Modifica oferta" << std::endl;
	std::cout << "4.Sterge oferta" << std::endl;
	std::cout << "5.Cauta oferta" << std::endl;
	std::cout << "6.Filtreaza dupa destinatie" << std::endl;
	std::cout << "7.Filtreaza dupa pret" << std::endl;
	std::cout << "8.Sorteaza dupa denumire" << std::endl;
	std::cout << "9.Sorteaza dupa destinatie" << std::endl;
	std::cout << "10.Sorteaza dupa tip+pret" << std::endl;
	std::cout << "11.Undo" << std::endl;
	std::cout << "12.Adauga in wishlist" << std::endl;
	std::cout << "13.Goleste wishlist" << std::endl;
	std::cout << "14.Genereaza wishlist cu un de oferte precizat" << std::endl;
	std::cout << "15.Afiseaza wishlist" << std::endl;
	std::cout << "16.Exporta wishlist pe HTML" << std::endl;
	std::cout << "0.Iesire" << std::endl;
}



void UI::run()
{
	const int nrcmds = 17;
	while (true)
		try {
		printMenu();
		int cmd = read<int>("Introduceti comanda: ", "Introduceti un intreg!\n\n");
		if (cmd >= 0 && cmd < nrcmds)
		{
			if (cmd == 0)
				return;
			executeCmd(cmd);
		}
		else
			std::cout << "Comanda invalida!\n\n";
	}
	catch (FileException e) { std::cout << std::endl << e.getMessage() << std::endl << std::endl; }
	catch (UndoException e) { std::cout << std::endl << e.getMessage() << std::endl << std::endl; }
	catch (ValidatorException e) { std::cout << std::endl << e.getMessage() << std::endl << std::endl; }
	catch (RepositoryException e) { std::cout << std::endl << e.getMessage() << std::endl << std::endl; }
}