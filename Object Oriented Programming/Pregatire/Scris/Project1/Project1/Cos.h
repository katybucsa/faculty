#pragma once
#include <string>
#include <vector>
#include <iostream>

template<typename T> class Cos {
private:
	std::string cumparator;
	std::vector<T> produse;
	std::vector<Cos<T>> und;
public:
	Cos<T>(const std::string& nume) :cumparator{ nume } {}
	
	Cos<T>& undo() {
		*this = und.back();
		return *this;
	}

	Cos<T>& operator+(const T& produs) {
		und.push_back(*this);
		produse.push_back(produs);
		return *this;
	}

	void operator-(const T&produs) {
		auto it = std::find(produse.begin(), produse.end(), produs);
		if (it != produse.end()) {
			und.push_back(*this);
			produse.erase(it);
		}
	}

	auto begin() {
		return produse.begin();
	}

	auto end() {
		return produse.end();
	}
	
	void  tipareste(std::ostream& stream) {
		for (auto& e : produse) {
			stream << e << std::endl;
		}
	}
};