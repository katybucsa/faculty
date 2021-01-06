#pragma once

#include "Repository.h"
#include "Validator.h"
#include <vector>

class Service {
private:
	Repository & repo;
	OfferValidator& valid;
public:
	/*
	implicit constructor
	*/
	Service(Repository& repo, OfferValidator& valid) noexcept : repo{ repo }, valid{ valid } {
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
	std::vector<Oferta> filterByDestination(std::string) const;

	/*
	returns the list of the offers which has the max price, the price given
	*/
	std::vector<Oferta> filterByPrice(float) const;

	/*
	returns the list of offers sorted ascending by name 
	*/
	std::vector<Oferta> sortByName() const;
	
	/*
	returns the list of offers sorted ascending by destination
	*/
	std::vector<Oferta> sortByDestination() const;

	/*
	returns the list of offers sorted ascending by price
	*/
	std::vector<Oferta> sortByTypePrice() const;

	/*
	returns all the offers saved
	*/
	const std::vector<Oferta>& getAl() const noexcept {
		return repo.getAll();
	}

	/*
	returns the number of offers saved
	*/
	int len() const noexcept;
};