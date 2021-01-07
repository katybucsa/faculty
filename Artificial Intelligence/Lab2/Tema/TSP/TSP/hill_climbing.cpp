#include <fstream>
#include <string>
#include <vector>

#include "Oras.h"

void read_from_file(std::string file) {
	std::ifstream in(file);
	int nrorase;
	int i = 0;
	std::vector<Oras> orase;
	in >> nrorase;
	if (in.is_open()) {
		while (!in.eof()) {
			std::string line;
			std::getline(in, line);
			std::vector<int> 

		}
	}
}