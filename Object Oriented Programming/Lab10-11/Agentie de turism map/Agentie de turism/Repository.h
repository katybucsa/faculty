#pragma once
#include <map>
#include <fstream>
#include <algorithm>
#include "Domain.h"

class Repository {
protected:
	std::map<std::string,Oferta> offers;
public:
	/*
	default constructor for Repository class
	*/
	Repository() = default;

	/*
	do not allow to make copy for an object of type Repository
	*/
	Repository(const Repository& otrep) = delete;

	/*
	store an offer
	throw an exception if the offer already exists
	*/
	virtual void add(const Oferta&);

	/*
	modify an offer
	throw an exception if the offer does not exist
	*/
	virtual void modify(const Oferta&);

	/*
	verify if an offer already exists returning it's position in the list or throwing an exception otherwise
	*/
	virtual const Oferta& find(const std::string&);

	/*
	removes an offer
	throw an exception if the offer does not exist
	*/
	virtual void remove(const Oferta&);

	/*
	return all the offers which were saved
	*/
	virtual const std::map<std::string,Oferta>& getAll() noexcept;

	/*
	return the number of offers
	*/
	virtual size_t sizeElems() noexcept;
};

class FileRepository :public Repository {
private:
	std::string fName;
	void loadFromFile();
	void writeToFile();
public:
	FileRepository(std::string fName) :Repository{}, fName{ fName } {
		loadFromFile();
	}
	void add(const Oferta& o) override {
		loadFromFile();
		Repository::add(o);
		writeToFile();
	}

	void modify(const Oferta& o) override {
		loadFromFile();
		Repository::modify(o);
		writeToFile();
	}
	
	void remove(const Oferta& o) override {
		loadFromFile();
		Repository::remove(o);
		writeToFile();
	}

	const Oferta& find(const std::string&name) override {
		loadFromFile();
		return Repository::find(name);
	}


	const std::map<std::string,Oferta>& getAll() noexcept override{
		loadFromFile();
		return Repository::getAll();
	}


	size_t sizeElems() noexcept override{
		loadFromFile();
		return Repository::sizeElems();
	}
};


/*
used to report the errors occured in repo
*/
class RepositoryException {
private:
	std::string message;
public:
	RepositoryException(std::string msg) :message{ msg } {}
	const std::string& getMessage() const noexcept {
		return message;
	}
};
