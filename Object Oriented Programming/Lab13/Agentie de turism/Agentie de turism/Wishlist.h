#pragma once
#include "Domain.h"
#include <vector>
#include <algorithm>
#include <random>
#include <chrono>
#include <vector>

class Observer {
public:
	virtual void update() = 0;
};


class Observable {
protected:
	std::vector<Observer*> obs;
	void notify() {
		for (auto o : obs) {
			o->update();
		}
	}
public:
	void addObserver(Observer* o) {
		obs.push_back(o);
	}
};


class Wishlist :public Observable {
private:
	std::vector<Oferta> inWishlist;
public:
	Wishlist() = default;

	void add(const Oferta& o) {
		inWishlist.push_back(o);
		notify();
	}

	void modify(const Oferta& o) {
		for (size_t i = 0; i < inWishlist.size(); i++)
			if (inWishlist[i] == o) {
				inWishlist[i] = o;
				notify();
				break;
			}
	}

	void remove(const Oferta& o) {
		for (size_t i = 0; i < inWishlist.size(); i++)
			if (inWishlist[i] == o) {
				inWishlist.erase(inWishlist.begin() + i);
				notify();
				break;
			}
	}

	void empty() {
		inWishlist.clear();
		notify();
	}

	void fillRandom(size_t nr, std::vector<Oferta> v) {
		std::shuffle(v.begin(), v.end(), std::default_random_engine(std::random_device{}()));
		while (inWishlist.size() < nr && v.size() > 0) {
			inWishlist.push_back(v.back());
			v.pop_back();
		}
		notify();
	}

	const std::vector<Oferta>& Wlist() const {
		return inWishlist;
	}
};
