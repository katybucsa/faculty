#pragma once
#include "Domain.h"
#include "Lista.h"

template <class TElem>
class Repository {
private:
	Lista<TElem> elems;

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
	void add(const TElem&);


	/*
	modify an offer
	throw an exception if the offer does not exist
	*/
	void modify(const TElem&);

	/*
	verify if an offer already exists returning it's position in the list or throwing an exception otherwise
	*/
	const TElem find(const TElem&);

	/*
	removes an offer
	throw an exception if the offer does not exist
	*/
	void remove(const TElem&);

	/*
	return all the offers which were saved
	*/
	const Lista<TElem>& getAll() const noexcept;

	size_t size() const noexcept {
		return elems.size();
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
