#include "FileRepo.h"



void FileRepo::add(const Produs & p) {
	loadFromFile();
	auto it = std::find(produse.begin(), produse.end(), p);
	if (it != produse.end())
		throw ProdusException("Produs existent!\n");
	produse.push_back(p);
	writeToFile();
}
std::vector<Produs> FileRepo::getAll() {
	loadFromFile();
	return produse;
}
void FileRepo::loadFromFile() {
	std::ifstream in(f);
	produse.clear();
	int id;
	std::string nume, tip;
	double pret;
	if (!in.is_open())
		throw ProdusException("Nu se poate deschide fisierul!\n");
	while (!in.eof()) {
		in >> id;
		if (in.eof())
			break;
		in >> nume;
		in >> tip;
		in >> pret;
		Produs p{ id,nume,tip,pret };
		produse.push_back(p);
	}
	in.close();
}

void FileRepo::writeToFile() {
	std::ofstream out(f);
	for (const auto& e : produse)
		out << e.getId() << " " << e.getNume() << " " << e.getTip() << " " << e.getPret() << std::endl;
	out.close();
}


