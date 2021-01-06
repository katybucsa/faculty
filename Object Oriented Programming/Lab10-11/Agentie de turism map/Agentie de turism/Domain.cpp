#include "Domain.h"

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



const bool Oferta::operator==(const Oferta& o) const noexcept
{
	return o.getName() == this->getName();
}


const bool Oferta::operator!=(const Oferta& o) const noexcept
{
	return o.getName() != this->getName();
}


std::ostream& operator<<(std::ostream& stream, const Oferta & o)
{
	stream << "Denumire: " << o.name << "\t" << "Destinatie: " << o.destination << "\t" << "Tip: " << o.type << "\t" << "Pret: " << o.price << std::endl;
	return stream;
}
