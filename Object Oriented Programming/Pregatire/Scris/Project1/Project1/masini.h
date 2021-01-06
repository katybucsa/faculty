#pragma once
//#include <iostream>
#include <vector>
#include <string>
//#include <algorithm>

/*class Vehicul {
private:
	double pret;
public:
	Vehicul(double p) :pret{ p } {

	}
	virtual std::string descriere() = 0;
	double getPret() {
		return pret;
	}
	virtual ~Vehicul(){}
};

class Masina :public Vehicul {
private:
	std::string model;
public:
	Masina(std::string m, double pret) :Vehicul(pret), model{ m } {
	}
	std::string descriere() override {
		return model;
	}
};

class SchimbatorAutomat :public Vehicul {
private:
	Vehicul * vehicul;
public:
	SchimbatorAutomat(Vehicul * v) :Vehicul(v->getPret()+4000), vehicul{ v } {
	}
	std::string descriere() override {
		return vehicul->descriere() + " cu schimbator automat";
	}
	~SchimbatorAutomat() {
		delete vehicul;
	}
};

class SenzorParcare :public Vehicul {
private:
	Vehicul * vehicul;
public:
	SenzorParcare(Vehicul*v) :Vehicul(v->getPret() + 2500), vehicul{ v } {}
	std::string descriere() override {
		return vehicul->descriere() + " cu senzor de parcare";
	}
	~SenzorParcare() {
	delete vehicul;
	}
};

std::vector<Vehicul*> f() {
	std::vector<Vehicul*> masini;
	Masina * Audi = new Masina("Audi", 4000);
	SchimbatorAutomat * AudiSch = new SchimbatorAutomat(Audi);
	SenzorParcare * AudiFinal = new SenzorParcare(AudiSch);

	Masina * Merc = new Masina("Mercedes", 6000);
	SchimbatorAutomat * MS = new SchimbatorAutomat(Merc);

	Masina * Opel = new Masina("Opel", 10000);
	SenzorParcare * OS = new SenzorParcare(Opel);

	masini.push_back(Audi);
	masini.push_back(AudiFinal);
	masini.push_back(MS);
	masini.push_back(OS);
	return masini;
}

//int main() {
//	std::vector<Vehicul*> mas = f();
//	std::sort(mas.begin(), mas.end(), [](Vehicul* a, Vehicul* b) {
//		return a->getPret() < b->getPret();
//	});
//	for (auto & m : mas) {
//		std::cout << m->descriere() << " pret: " << m->getPret() << "\n";
//	}
//	
//}

/*
#include <iostream>
#include <string>
#include <vector>

template<typename T>
class Cos {
private:
	std::vector<T> lista;
	std::string nume;
public:
	Cos(T nume) :nume{ nume } {
	}
	Cos& operator+(T prod) {
		lista.push_back(prod);
		return *this;
	}
	Cos& operator=(const Cos& ot) {
		lista = ot.lista;
		nume = ot.nume;
		return *this;
	}
	void operator-(T prod) {
		int poz = 0;
		bool gasit = false;
		for (auto& p : lista) {
			if (p == prod) {
				gasit = true;
				break;
			}
			poz++;
		}
		if (gasit == true) {
			lista.erase(lista.begin() + poz);
		}
	}
	auto begin() {
		return lista.begin();
	}
	auto end() {
		return lista.end();
	}
};

int main() {
	Cos<std::string> cos{ "Ion" };
	cos = cos + "Mere";
	cos = cos + "Paine" + "Lapte";
	cos - "Paine";
	for (auto i : cos) {
		std::cout << i << "\n";
	}
}*/

class Vehicul {
private:
	double pret;
public:
	Vehicul(double p) :pret{ p } {}
	virtual std::string descriere() = 0;
	double getPret() const {
		return pret;
	}
};


class Masina : public Vehicul {
private:
	std::string model;
public:
	Masina(std::string m, double p) :model{ m }, Vehicul{ p } {}
	std::string descriere() override {
		return model;
	}
};

class SchimbatorAutomat : public Vehicul {
private: 
	Vehicul* masina;
public:
	SchimbatorAutomat(Vehicul* m) :masina{ m }, Vehicul{ m->getPret() + 4000 } {}
	std::string descriere() override {
		return masina->descriere() + " cu schimbator automat";
	}
};


class SenzorDeParcare :public Vehicul {
private:
	Vehicul* masina;
public: 
	SenzorDeParcare(Vehicul* m) :masina{ m }, Vehicul{ m->getPret() + 2000 } {}
	std::string descriere() override {
		return masina->descriere() + " cu senzor de parcare";
	}
};


