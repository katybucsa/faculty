#pragma once
#include "Task.h"
#include <string>
#include <vector>

class FileRepo {
private:
	std::vector<Task> taskuri;
	std::string file;
	void loadFromFile();
	void writeToFile();
public:
	FileRepo(const std::string& f ) : file{ f }{
		loadFromFile();
	}

	void add(const Task&);

	const std::vector<Task>& getAll();
};