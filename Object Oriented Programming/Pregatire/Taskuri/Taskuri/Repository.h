#pragma once
#include "Task.h"
#include <string>
#include <vector>


class Observer {
public:
	virtual void update() = 0;
};

class Observable {
protected:
	std::vector<Observer*> obs;
	void notify(){
		for (const auto& o : obs)
		o->update();
	}
public:
	void addObserver(Observer* o) {
		obs.push_back(o);
	}
};

class FileRepo:public Observable {
private:
	std::vector<Task> taskuri;
	std::string file;
	void loadFromFile();
	void writeToFile();
public:
	FileRepo(const std::string& f) : file{ f } {
		loadFromFile();
	}

	void add(const Task&);

	void modify(Task&,const std::string&);

	const std::vector<Task>& getAll();
};