#pragma once
#include <vector>
#include <iostream>
#include <string>

class Participant {
public:
	virtual void tipareste() = 0;
	virtual bool eVoluntar() { return true; }
};

class Personal :public Participant {
private:
	std::string nume;
public:
	Personal(const std::string n) :nume{ n } {}
	
	virtual void tipareste() override {
		std::cout << nume;
	}
};

class Administrator :public Personal {
public:
	Administrator(const std::string nume) :Personal{ nume } {}
	
	virtual void tipareste() override {
		std::cout << "Administrator: ";
		Personal::tipareste();
	}
};

class Director : public Personal {
public: 
	Director(const std::string n) :Personal{ n } {}

	virtual void tipareste() override {
		std::cout << "Director: ";
		Personal::tipareste();
	}
};

class Angajat :public Participant {
private:
	Participant* p;
public:
	Angajat(Participant* p) :p{ p } {}
	void tipareste() override {
		std::cout << "angajat ";
		p->tipareste();
	}
	bool eVoluntar() override {
		return false;
	}
};

class ONG {
private:
	std::vector<Participant*> participanti;
public:
	ONG()  = default;
	void add(Participant* p) {
		participanti.push_back(p);
	}
	std::vector<Participant*> getAll(bool voluntari) {
		std::vector<Participant*> v;
		for (auto e : participanti)
			if (e->eVoluntar()==voluntari)
				v.push_back(e);
		return v;
	}

};

ONG f() {
	ONG ong;
	Administrator* a1 = new Administrator{ "Andrei" };
	Administrator* a2 = new Administrator{ "Alin" };
	Angajat* ang1 = new Angajat{ a2 };
	Director* d1 = new Director{ "Andreea" };
	Director* d2 = new Director{ "Alina" };
	Angajat* ang2 = new Angajat{ d2 };
	ong.add(a1);
	ong.add(ang1);
	ong.add(d1);
	ong.add(ang2);
	return ong;
}