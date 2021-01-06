#include "Validator.h"


void OfferValidator::validate(const Oferta& offer) {
	std::string error = "";
	if (offer.getName() == "")
		error += "Denumire invalida!\n";
	if (offer.getDestination() == "")
		error += "Destinatie invalida!\n";
	if (offer.getType() == "")
		error += "Tip invalid!\n";
	if (offer.getPrice() <= 0)
		error += "Pret invalid!\n";
	if (error != "")
		throw ValidatorException(error);
}

