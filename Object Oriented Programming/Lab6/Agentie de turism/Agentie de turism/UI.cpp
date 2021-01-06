#include "UI.h"
#include <iostream>

void UI::printOffers(const std::vector<Oferta>& offers) {
	for (const auto& offer : offers) {
		std::cout << "Id: " << offer.getId() << "\t" << "Denumire: " << offer.getName() << "\t" << "Destinatie: " << offer.getDestination() << "\t" << "Tip: " << offer.getType() << "\t" << "Pret: " << offer.getPrice() << std::endl;
	}
	std::cout << std::endl << std::endl;
}


void UI::uiAddOffer() {
	int id=-1;
	std::string name, destination, type,idb,priceb;
	float price=-1;
	std::cout << "Introduceti id: ";
	std::cin >> idb;
	std::cout << "Introduceti denumire oferta: ";
	std::cin >> name;
	std::cout << "Introduceti destinatie: ";
	std::cin >> destination;
	std::cout << "Introduceti tip: ";
	std::cin >> type;
	std::cout << "Introduceti pret: ";
	std::cin >> priceb;
	try {
		id = stoi(idb);
		price = stof(priceb);
	}catch (...) { std::cout << "Tip de data invalid!\n"; }
	serv.addOffer(id, name, destination, type, price);
	std::cout << "Oferta adaugata cu succes!\n" << std::endl << std::endl;
}

void UI::uiModifyOffer() {
	int id=-1;
	std::string name, destination, type,idb,priceb;
	float price=-1;
	std::cout << "Introduceti id: ";
	std::cin >> idb;
	std::cout << "Introduceti noua denumire: ";
	std::cin >> name;
	std::cout << "Introduceti noua destinatie: ";
	std::cin >> destination;
	std::cout << "Introduceti noul tip: ";
	std::cin >> type;
	std::cout << "Introduceti noul pret: ";
	std::cin >> priceb;
	try {
		id = stoi(idb);
		price = stof(priceb);
	}catch (...) { std::cout << "Tip de data invalid!\n"; }
	serv.modifyOffer(id, name, destination, type, price);
	std::cout << "Oferta modificata cu succes!" << std::endl;
}

void UI::uiRemoveOffer() {
	int id;
	std::string idb;
	std::cout << "Introduceti id: ";
	std::cin >> idb;
	try {
		id = stoi(idb);
	}catch (...) { std::cout << "Tip de data invalid!\n"; }
	serv.removeOffer(id);
	std::cout << "Oferta stearsa  cu succes!" << std::endl << std::endl;
}

void UI::uiGetOffer() {
	int id;
	std::cout << "Introduceti oferta id: ";
	std::cin >> id;
	const Oferta o = serv.getOffer(id);
	std::cout << std::endl << "Id: " << o.getId() << "\t" << "Denumire: " << o.getName() << "\t" << "Destinatie: " << o.getDestination() << "\t" << "Tip: " << o.getType() << "\t" << "Pret: " << o.getPrice() << std::endl << std::endl;
}


void UI::uiFilterByDestination() {
	std::string destination;
	std::cout << "Introduceti destinatie: ";
	std::cin >> destination;
	const std::vector<Oferta> v = serv.filterByDestination(destination);
	if (v.size() == 0)
		std::cout << "Nu exista oferte destinatia " << destination << std::endl << std::endl;
	else
		printOffers(v);
}



void UI::uiFilterByPrice() {
	float price;
	std::cout << "Introduceti pretul: ";
	std::cin >> price;
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


void UI::executeCmd(int nr) {
	const std::vector<Oferta> v = serv.getAl();
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
	}
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
	std::cout << "0.Iesire" << std::endl;
}

void UI::run()
{
	std::string cmd;
	int nr=-1;
	const int nrcmds = 11;
	while (true) {
		printMenu();
		std::cout << "Introduceti comanda: ";
		try {
			std::cin >> cmd;
			nr = stoi(cmd);
			try {
				if (nr >= 0 && nr<nrcmds)
				{
					if (nr == 0)
						return;
					executeCmd(nr);
				}
				else
					std::cout << "Comanda invalida" << std::endl;
			}
			catch (ValidatorException e) { std::cout << std::endl << e.getMessage() << std::endl << std::endl; }
			catch (RepositoryException e) { std::cout << std::endl << e.getMessage() << std::endl << std::endl; }
		}catch (...) { std::cout <<"Numar intreg invalid!"<< std::endl; }
	}
}