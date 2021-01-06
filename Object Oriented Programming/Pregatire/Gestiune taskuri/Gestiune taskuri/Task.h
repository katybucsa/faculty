#pragma once
#include <string>
#include <list>

class Task {
private:
	int id;
	std::string descriere;
	std::list<std::string> programatori;
	std::string stare;
public:
	Task(int& id, const std::string& d, const std::list<std::string>& p, const std::string& s) :id{ id }, descriere{ d }, programatori{ p }, stare{ s } {};
	const int& getId() const noexcept {
		return id;
	}

	const std::string& getDescriere() const noexcept {
		return descriere;
	}

	const std::list<std::string>& getProgramatori() const noexcept {
		return programatori;
	}
	const std::string& getStare() const noexcept {
		return stare;
	}

	bool operator==(const Task&);
};


class TaskException {
private:
	std::string mesaj;
public:
	TaskException(const std::string& m) :mesaj{ m } {}
	const std::string& getMsg() const noexcept {
		return mesaj;
	}
};