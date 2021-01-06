#pragma once
#include <vector>
#include "Domain.h"

class Repository {
private:
	std::vector<Oferta> offers;
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
	void add(const Oferta&);

	/*
	modify an offer
	throw an exception if the offer does not exist
	*/
	void modify(const Oferta&);

	/*
	verify if an offer already exists returning it's position in the list or throwing an exception otherwise
	*/
	int find(const Oferta&);

	/*
	removes an offer
	throw an exception if the offer does not exist
	*/
	void remove(const Oferta&);

	/*
	return all the offers which were saved
	*/
	const std::vector<Oferta>& getAll() const noexcept;

	const Oferta& getOferta(unsigned int);

	/*
	return the number of offers
	*/
	int sizeElems() const noexcept;
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
