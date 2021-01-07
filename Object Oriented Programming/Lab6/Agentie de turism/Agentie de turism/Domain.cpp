#include "Domain.h"

const int& Oferta::getId() const noexcept {
	return id;
}


const std::string& Oferta::getName() const noexcept {
	return name;
}

const std::string& Oferta::getDestination() const noexcept {
	return destination;
}

const std::string& Oferta::getType() const noexcept {
	return type;
}

const float& Oferta::getPrice() const noexcept {
	return price;
}



bool Oferta::operator==(const Oferta& o) noexcept
{
	if (o.getId() == this->getId())
		return true;
	return false;
}
