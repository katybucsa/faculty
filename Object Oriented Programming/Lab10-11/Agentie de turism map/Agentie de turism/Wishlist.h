#pragma once
#include "Domain.h"
#include <map>
#include <algorithm>
#include <random>
#include <chrono>

class Wishlist {
private:
	std::map<std::string,Oferta> inWishlist;
public:
	Wishlist() = default;
	
	void add(const Oferta& o) {
		inWishlist.insert(std::pair<std::string, Oferta>(o.getName(), o));
	}

	void modify(const Oferta& o) {
		std::map<std::string, Oferta>::iterator it = inWishlist.find(o.getName());
		if (it != inWishlist.end())
			it->second = o;
	}

	void remove(const Oferta& o) {
		std::map<std::string, Oferta>::iterator it = inWishlist.find(o.getName());
		if (it != inWishlist.end())
			inWishlist.erase(o.getName());
	}

	void empty() {
		inWishlist.clear();
	}

	void fillRandom(size_t nr, std::map<std::string,Oferta> m) {
		std::vector<Oferta> v;
		for (auto o : m)
			v.push_back(o.second);
		std::shuffle(v.begin(), v.end(), std::default_random_engine(std::random_device{}()));
		while (inWishlist.size() < nr && v.size() > 0) {
			inWishlist.insert(std::pair<std::string,Oferta>(v.back().getName(),v.back()));
			v.pop_back();
		}
	}

	const std::map<std::string,Oferta>& Wlist() const {
		return inWishlist;
	}
};
