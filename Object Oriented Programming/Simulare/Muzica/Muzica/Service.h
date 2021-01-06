#pragma once
#include "Repo.h"

class Service {
private:
	Repo& repo;
public:
	Service(Repo& repo) :repo{ repo } {};

	void add(const string& artist, const string& titlu, int likes);

	vector<Music> sortLikes() const ;

	const vector<Music>& getAll() const noexcept {
		return repo.getAll();
	}

	void like(const Music& m);

	void dislike(const Music& m);

	void top5();
};