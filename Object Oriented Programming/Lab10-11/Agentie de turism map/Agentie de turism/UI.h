#pragma once
#include "Service.h"

class UI {
private:

	Service & serv;
	/*
	reads data form the  keyboard and adds the offer
	throws an exception if it can't be saved or is not valid
	*/
	void uiAddOffer();

	/*
	prints a list of all saved offers
	*/
	void printOffers(const std::map<std::string,Oferta>&);

	/*
	reads data from keyboard and modify the offer with the id read
	throws an exception if offer already exists or is not valid 
	*/
	void uiModifyOffer();

	/*
	reads data from keyboard and remove the offer with the given id
	throws an exception if offer does not exist or id is not valid

	*/
	void uiRemoveOffer();

	/*
	reads form keyboard data and prints the offer with the id given
	throws an exception if offer does not exist
	*/
	void uiGetOffer();

	/*
	prints a list with the offers filtered by destination
	*/
	void uiFilterByDestination();

	/*
	prints a list with the offers filtered by price
	*/
	void uiFilterByPrice();

	/*
	prints a list with the offers sorted ascending by name
	*/
	void uiSortByName();

	/*
	prints a list with the offers sorted ascending by destination
	*/
	void uiSortByDestination();

	/*
	prints a list with the offers sorted ascending by the type and price criteria
	*/
	void uiSortByTypePrice();

	void executeCmd(int);

	void uiAddToWishlist();

	void uiEmptyWishlist();

	void uiAddRandomToWishlist();

	void printWishlistSize();

	void uiExportWishlistHTML();
public:
	UI(Service& serv) noexcept : serv{ serv } {
	}

	//do not allow to copy this object
	UI(const UI& otui) = delete;

	void run();

};