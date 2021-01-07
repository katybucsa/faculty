#include "UI.h"
#include <iostream>
#include <ios>


void UI::printOffers(const Lista<Oferta>& offers) {
	if (offers.vida())
		std::cout << std::endl << "Nu exista oferte!" << std::endl;
	else {
		Iterator<Oferta> it = offers.iterator();
		while (it.valid())
		{
			std::cout << it.element();
			it.urmator();
		}
		std::cout << std::endl << std::endl;
	}
}


void UI::uiAddOffer() {
	int id=-1;
	float price = -1;
	std::string name, destination, type;
	id = readInt("Introduceti id: ", "Id invalid!\n\n");
	std::cout << "Introduceti denumire oferta: ";
	std::cin >> name;
	std::cout << "Introduceti destinatie: ";
	std::cin >> destination;
	std::cout << "Introduceti tip: ";
	std::cin >> type;
	price = readFloat("Introduceti pret: ", "Pret invalid!\n\n");
	serv.addOffer(id, name, destination, type, price);
	std::cout << "Oferta adaugata cu succes!\n" << std::endl << std::endl;
}

void UI::uiModifyOffer() {
	int id=-1;
	std::string name, destination, type;
	float price=-1;
	id = readInt("Introduceti id: ", "Id invalid!\n\n");
	std::cout << "Introduceti noua denumire: ";
	std::cin >> name;
	std::cout << "Introduceti noua destinatie: ";
	std::cin >> destination;
	std::cout << "Introduceti noul tip: ";
	std::cin >> type;
	price = readFloat("Introduceti noul pret: ", "Pret invalid!\n\n");
	serv.modifyOffer(id, name, destination, type, price);
	std::cout << "Oferta modificata cu succes!" << std::endl;
}

void UI::uiRemoveOffer() {
	int id= readInt("Introduceti id: ", "Id invalid!\n\n");
	serv.removeOffer(id);
	std::cout << "Oferta stearsa  cu succes!" << std::endl << std::endl;
}

void UI::uiGetOffer() {
	int id = readInt("Introduceti oferta id: ", "Id invalid!\n\n");
	const Oferta o = serv.getOffer(id);
	std::cout << o<<std::endl;
}



void UI::uiFilterByDestination() {
	std::string destination;
	std::cout << "Introduceti destinatie: ";
	std::cin >> destination;
	const Lista<Oferta> l = serv.filterByDestination(destination);
	if (l.vida())
		std::cout << std::endl << "Nu exista oferte destinatia " << destination << std::endl << std::endl;
	else
		printOffers(l);
}



void UI::uiFilterByPrice() {
	float price = readFloat("Introduceti pretul: ", "Pret invalid!\n\n");
	const Lista<Oferta> l = serv.filterByPrice(price);
	if (l.vida())
		std::cout << "Nu exista oferte cu pretul mai mic decat " << price << std::endl << std::endl;
	else
		printOffers(l);
}


void UI::uiSortByName() {
	const Lista<Oferta> l = serv.sortByName();
	printOffers(l);
}

void UI::uiSortByDestination() {
	const Lista<Oferta> l = serv.sortByDestination();
	printOffers(l);
}


void UI::uiSortByTypePrice() {
	const Lista<Oferta> l = serv.sortByTypePrice();
	printOffers(l);
}


void UI::executeCmd(int nr) {
	const Lista<Oferta>& v = serv.getAl();
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


int UI::readInt(const std::string& message,const std::string& err)
{
	int cmd=-1;
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

float UI::readFloat(const std::string & message, const std::string & err)
{
	float n = 0;
	while (true) {
		std::cout << message;
		std::cin >> n;
		const bool fail = std::cin.fail();
		std::cin.clear();
		const auto& aux = std::cin.ignore(1000, '\n');
		if (!fail && aux.gcount() <= 1)
			break;
		std::cout << err;
	}
	return n;
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
	int cmd=-1;
	const int nrcmds = 11;
	while (true)
		try {
			printMenu();
			cmd = readInt("Introduceti comanda: ", "Introduceti un intreg!\n\n");
			if (cmd >= 0 && cmd < nrcmds)
			{
				if (cmd == 0)
					return;
				executeCmd(cmd);
			}
			else
				std::cout << "Comanda invalida!\n\n";
		}
		catch (ValidatorException e) { std::cout << std::endl << e.getMessage() << std::endl << std::endl; }
		catch (RepositoryException e) { std::cout << std::endl << e.getMessage() << std::endl << std::endl; }
		catch (ConsoleException e) { std::cout << std::endl << e.getMessage() << std::endl << std::endl; }
}