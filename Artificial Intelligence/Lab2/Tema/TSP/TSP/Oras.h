#pragma once
#include <vector>

class Oras
{
private:
	int info;
	std::vector<int> vecini;
public:
	Oras(int info, std::vector<int> vecini) :info{ info }, vecini{ vecini } {}

	int getInfo() {
		return info;
	}


	std::vector<int> getVecini() {
		return vecini;
	}

	~Oras();
};

