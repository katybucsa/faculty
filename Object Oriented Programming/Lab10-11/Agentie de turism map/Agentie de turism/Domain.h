#pragma once
#include <string>

class Oferta {
private:
	std::string name;
	std::string destination;
	std::string type;
	float price;
public:
	Oferta() = default;


	/*
	create a new object of type oferta
	*/
	Oferta(const std::string name, const std::string destination, const std::string type, float price) :name{ name }, destination{ destination }, type{ type }, price{ price } {
	}


	/*
	copy constructor
	*/
	Oferta(const Oferta &o) :name{ o.name }, destination{ o.destination }, type{ o.type }, price{ o.price } {};
	
	
	virtual ~Oferta() = default;

	Oferta& operator=(const Oferta&) = default;
	
	Oferta(Oferta&&) = default;
	
	Oferta& operator=(Oferta&&) = default;


	/*
	Getter method for name
	*/
	const std::string& getName() const noexcept;


	/*
	Getter method for destination
	*/
	const std::string& getDestination() const noexcept;


	/*
	Getter method for type
	*/
	const std::string& getType() const noexcept;


	/*
	Getter method for price
	*/
	const float& getPrice() const noexcept;


	/*
	Initialize the == operator for Oferta entities
	*/
	const bool operator==(const Oferta&) const noexcept;

	const bool operator!=(const Oferta& o) const noexcept;


	friend std::ostream& operator<<(std::ostream&, const Oferta&);

};


std::ostream& operator<< (std::ostream&, const Oferta&);


class FileException {
private:
	std::string message;
public:
	FileException(std::string msg) :message{ msg } {}
	const std::string& getMessage() const noexcept {
		return message;
	}
};
