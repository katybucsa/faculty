#pragma once
#include <string>


class Produs {
private:
	int id;
	std::string nume;
	std::string tip;
	double pret;
public:
	Produs(int id, std::string n, std::string t, double p) : id{ id }, nume{ n }, tip{ t }, pret{ p } {}

	int getId() const {
		return id;
	}

	const std::string getNume() const {
		return nume;
	}

	const std::string getTip() const {
		return tip;
	}

	double getPret() const {
		return pret;
	}

	bool operator==(const Produs& p) {
		return this->id == p.id;
	}
};


class ProdusException {
private:
	std::string mesaj;
public:
	ProdusException(const std::string& m) :mesaj{ m } {}
	const std::string& getMsg() const {
		return mesaj;
	}
};