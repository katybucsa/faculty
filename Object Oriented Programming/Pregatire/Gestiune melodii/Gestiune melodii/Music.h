#pragma once
#include <string>

class Music {
private:
	int id;
	std::string titlu;
	std::string artist;
	std::string gen;
public:
	Music(const int& id, const std::string& t, const std::string& a, const std::string& g) :id{ id }, titlu{ t }, artist{ a }, gen{ g } {}

	const int& getId() const noexcept {
		return id;
	}

	const std::string& getTitlu() const noexcept {
		return titlu;
	}

	const std::string& getArtist() const noexcept {
		return artist;
	}

	const std::string& getGen() const noexcept {
		return gen;
	}

	bool operator==(const Music& m) {
		return this->getId() == m.getId();
	}
};


class MusicException {
private:
	std::string mesaj;
public:
	MusicException(const std::string& m) :mesaj{ m } {}

	const std::string& getMsg() const noexcept {
		return mesaj;
	}
};