#pragma once

#include "Repository.h"
#include "Validator.h"
#include "Undo.h"
#include "Wishlist.h"
#include<memory>
using std::unique_ptr;


class Service {
private:
	Repository & repo;
	OfferValidator& valid;
	std::vector<unique_ptr<UndoAction>> undoActions;
	Wishlist wlist;
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
	void addOffer(const std::string, const std::string, const std::string, float);

	/*
	modify an existing offer
	throws an exception if offer already exists or is not valid 
	*/
	void modifyOffer(const std::string, const std::string, const std::string, float);

	/*
	removes the offer with the id given
	throws an exception if the offer does not exist or the if is not valid
	*/
	void removeOffer(const std::string&);

	/*
	returns the offer with the given id 
	throw an exception if the offer does not exist or the id is not valid
	*/
	const Oferta getOffer(const std::string&);

	/*
	returns the list of the offers which has the destination given
	*/
	std::map<std::string,Oferta> filterByDestination(std::string) const;

	/*
	returns the list of the offers which has the max price, the price given
	*/
	std::map<std::string,Oferta> filterByPrice(float) const;

	/*
	returns the list of offers sorted ascending by name 
	*/
	std::map<std::string,Oferta> sortByName() const;
	
	/*
	returns the list of offers sorted ascending by destination
	*/
	std::map<std::string,Oferta> sortByDestination() const;

	/*
	returns the list of offers sorted ascending by price
	*/
	std::map<std::string,Oferta> sortByTypePrice() const;

	/*
	returns all the offers saved
	*/
	const std::map<std::string,Oferta>& getAl() const noexcept {
		return repo.getAll();
	}

	/*
	returns the number of offers saved
	*/
	size_t len() const noexcept;

	void undo();

	void addToWishlist(const std::string&);

	void addRandom(unsigned int nr);

	void emptyWishlist();

	const std::map<std::string,Oferta>& allFromWishlist();

	void exportWishlistHTML(std::string fName) const;

};