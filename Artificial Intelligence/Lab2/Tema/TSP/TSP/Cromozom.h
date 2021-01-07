#pragma once
#include <vector>
#include "Oras.h"
class Cromozom
{
private:
	std::vector<int> genotip;
	int fitness;
public:
	Cromozom(std::vector<int> gen) :genotip{ gen }, fitness{ 0 } {}

	std::vector<int> getGenotip() {
		return genotip;
	}

	int getFitness() {
		return fitness;
	}

	void evaluare(std::vector<Oras> orase) {
		int suma = 0;
		for (auto i = 0; i < orase.size - 1; i++)
			suma += orase[genotip[i] - 1].getVecini()[genotip[i + 1] - 1];
		suma += orase[genotip[0] - 1].getVecini()[genotip[orase.size - 1] - 1];
		this->fitness = suma;
	}
	
	~Cromozom();
};

