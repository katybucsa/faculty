#pragma once
#include "Music.h"
#include <vector>
#include <fstream>

class FileRepo {
private:
	std::vector<Music> all;
	std::string file;
	void loadFromFile();
	void writeToFile();
public:
	FileRepo(const std::string& f) :file{ f } {
		loadFromFile();
	}

	void add(const Music&);

	void modify(const Music&);

	void remove(const Music&);

	const std::vector<Music>& getAll();
};