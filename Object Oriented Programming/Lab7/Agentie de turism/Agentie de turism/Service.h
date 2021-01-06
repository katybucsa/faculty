#pragma once

#include "Repository.h"
#include "Repository.cpp"
#include "Validator.h"
#include <vector>


class Service {
private:
	Repository<Oferta>& repo;
	OfferValidator<Oferta>& valid;
public:
	/*
	implicit constructor
	*/
	Service(Repository<Oferta>& repo, OfferValidator<Oferta>& valid) noexcept : repo{ repo }, valid{ valid } {
	}
	/*
	do not allow to copy an object of this type
	*/
	Service(const Service& otserv) = delete;

	/*
	adds a new offer
	throws an exception if it can't be saved or is not valid
	*/
	void addOffer(int, const std::string, const std::string, const std::string, float);

	/*
	modify an existing offer
	throws an exception if offer already exists or is not valid 
	*/
	void modifyOffer(int, const std::string, const std::string, const std::string, float);

	/*
	removes the offer with the id given
	throws an exception if the offer does not exist or the if is not valid
	*/
	void removeOffer(int);

	/*
	returns the offer with the given id 
	throw an exception if the offer does not exist or the id is not valid
	*/
	const Oferta getOffer(int);

	/*
	returns the list of the offers which has the destination given
	*/
	Lista<Oferta> filterByDestination(std::string) const;

	/*
	returns the list of the offers which has the max price, the price given
	*/
	Lista<Oferta> filterByPrice(float) const;

	/*
	returns the list of offers sorted ascending by name 
	*/
	Lista<Oferta> sortByName() const;
	
	/*
	returns the list of offers sorted ascending by destination
	*/
	Lista<Oferta> sortByDestination() const;

	/*
	returns the list of offers sorted ascending by price
	*/
	Lista<Oferta> sortByTypePrice() const;

	/*
	returns all the offers saved
	*/
	const Lista<Oferta>& getAl() const noexcept {
		return repo.getAll();
	}

	size_t len() noexcept{
		return repo.size();
	}
};