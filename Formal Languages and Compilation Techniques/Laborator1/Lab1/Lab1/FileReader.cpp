#include <string>
#include <iostream>
#include <fstream>
#include <map>

class FileReader {
public:
	/*
	citeste diecare linie din fisier si le insereaza in map-ul dat ca parametru
	filename - numele fisierului 
	separator - elementul dupa care se separa elementele din fisier
	hash_map - map-ul in care se insereaza elementele din fisier
	*/
	static void readFromFile(std::string filename, std::string separator, std::map<std::string, int>& hash_map) {
		std::string line;
		std::ifstream infile;
		infile.open(filename);

		size_t separator_position = 0;
		std::string key;
		int value;

		while (!infile.eof()) // To get you all the lines.
		{
			getline(infile, line); // Saves the line in line string.
			separator_position = line.find(separator);
			key = line.substr(0, separator_position);
			value = stoi(line.substr(separator_position + 1, line.size()));
			hash_map.insert(std::pair<std::string, int>(key, value));
		}
		infile.close();
	}
};