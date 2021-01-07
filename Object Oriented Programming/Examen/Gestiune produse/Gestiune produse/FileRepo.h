#pragma once
#include "Produs.h"
#include <vector>
#include <fstream>

class FileRepo {
private:
	std::vector<Produs> produse;
	std::string f;
	void loadFromFile();
	void writeToFile();
public:
	FileRepo(const std::string& f) :f{ f } {
		loadFromFile();
	}
	void add(const Produs&);

	std::vector<Produs> getAll();
};