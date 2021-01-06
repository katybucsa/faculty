#pragma once
#include "FileRepo.h"
#include "Validator.h"

class Service {
private:
	FileRepo & repo;
	Validator& valid;
public:
	Service(FileRepo& r, Validator& v) :repo{ r }, valid{ v } {}

	void add(int, std::string, std::string, double);

	std::vector<Produs> getAll();

	std::vector<Produs> sortByPrice();
};