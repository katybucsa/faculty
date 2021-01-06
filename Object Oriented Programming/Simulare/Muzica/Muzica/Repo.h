#pragma once
#include <vector>
#include <fstream>
#include "Music.h"

class Repo {
private:
	vector<Music> elems;
public:
	Repo() = default;
	Repo(const Repo& r) = delete;

	void add(const Music&);

	const vector<Music>& getAll() noexcept; 

	void top5();

	void like(Music m);

	void dislike(Music m);
};


class RepoException {
private:
	string message;
public:
	RepoException(const string& msg) :message{ msg } {};
	const string& getMessage() const noexcept{
		return message;
	}
};