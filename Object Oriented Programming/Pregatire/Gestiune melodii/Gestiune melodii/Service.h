#pragma once
#include "Repository.h"
#include "Validator.h"

class Service {
private:
	FileRepo & repo;
	Validator& valid;
public:
	Service(FileRepo& r, Validator& v) :repo{ r }, valid{ v } {}

	void add(const int&, const std::string&, const std::string&, const std::string&);
	
	void modify(int, std::string, std::string, std::string);

	void remove(Music);

	int getNrCuArtistul(const std::string&);

	int getNrCuGenul(const std::string&);

	std::vector<Music> sortByArtist() const;

	const std::vector<Music>& getAll();

};