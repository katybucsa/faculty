#include "Validator.h"


void OfferValidator::validate(const Oferta& offer) {
	std::string error = "";
	if (offer.getId() < 0)
		error += "Id invalid!";
	if (offer.getPrice() < 0)
		error += "Pret invalid!";
	if (error != "")
		throw ValidatorException(error);
}

