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
	void modify(Task&, const std::string&);
	std::vector<Task> sortByState() const;
	std::vector<Task> filterByProg(const std::string&) const;
	std::vector<Task> filterOpen() const;
	std::vector<Task> filterClosed() const;
	std::vector<Task> filterInProgress() const;
	const std::vector<Task>& getAll();

	void addO(Observer* o);

};