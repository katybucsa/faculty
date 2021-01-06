#pragma once
#include "Domain.h"

/*
used to signal the errors occured in validator
*/

class ValidatorException {
private:
	std::string message;
public:
	ValidatorException(std::string msg) : message{ msg } {
	}
	const std::string& getMessage() const noexcept {
		return message;
	}
};

template<class TElem>
class OfferValidator {
public:
	/*
	validate an offer
	*/
	void validate(const Oferta&);
};