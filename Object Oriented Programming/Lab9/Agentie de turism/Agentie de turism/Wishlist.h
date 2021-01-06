#pragma once
#include "Domain.h"
#include <vector>
#include <algorithm>
#include <random>
#include <chrono>

class Wishlist {
private:
	std::vector<Oferta> inWishlist;
public:
	Wishlist() = default;
	
	void add(const Oferta& o) {
		inWishlist.push_back(o);
	}

	void modify(const Oferta& o) {
		for (size_t i = 0; i < inWishlist.size(); i++)
			if (inWishlist[i] == o) {
				inWishlist[i] = o;
				break;
			}
	}

	void remove(const Oferta& o) {
		for (size_t i = 0; i < inWishlist.size(); i++)
			if (inWishlist[i] == o) {
				inWishlist.erase(inWishlist.begin() + i);
				break;
			}
	}

	void empty() {
		inWishlist.clear();
	}

	void fillRandom(size_t nr, std::vector<Oferta> v) {
		std::shuffle(v.begin(), v.end(), std::default_random_engine(std::random_device{}()));
		while (inWishlist.size() < nr && v.size() > 0) {
			inWishlist.push_back(v.back());
			v.pop_back();
		}
	}

	const std::vector<Oferta>& Wlist() const {
		return inWishlist;
	}
};
