#pragma once
#include <string>
using namespace std;

class  Music {
private:
	string artist;
	string titlu;
	int likes;
public:
	Music() = default;
	Music(const string& artist, const string& titlu, int likes) :artist{ artist }, titlu{ titlu }, likes{ likes } {};


	const string& getArtist() const noexcept {
		return artist;
	}

	const string& getTitlu() const noexcept {
		return titlu;
	}

	const int& getLikes() const noexcept {
		return likes;
	}

	void setLike(int like) {
		likes = like;
	}

	bool operator==(const Music&);
};