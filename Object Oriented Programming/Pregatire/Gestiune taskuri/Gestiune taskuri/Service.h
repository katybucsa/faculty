#pragma once
#include "Repository.h"
#include "Validator.h"

class Service {
private:
	FileRepo & repo;
	Validator & valid;
public:
	Service(FileRepo& r, Validator& v) :repo{ r }, valid{ v } {}

	void add(int id, const std::string& descriere, const std::list<std::string>& programatori, const std::string& stare);
	std::vector<Task> sortByState() const;
	const std::vector<Task>& getAll();
};