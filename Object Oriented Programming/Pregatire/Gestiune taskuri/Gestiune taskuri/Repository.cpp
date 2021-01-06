#include "Repository.h"
#include <fstream>
#include <algorithm>


void FileRepo::add(const Task &task){
	//loadFromFile();
	if (std::find(taskuri.begin(), taskuri.end(), task) != taskuri.end())
		throw TaskException("Task existent!");
	taskuri.push_back(task);
	//writeToFile();
}

const std::vector<Task>& FileRepo::getAll() {
	loadFromFile();
	return taskuri;
}


void FileRepo::loadFromFile()
{
	int id;
	std::string descriere, stare, programator;
	std::list<std::string> programatori;
	std::ifstream in(file);
	if (!in.is_open())
		throw TaskException("Nu s-a putut deschide fisierul!");
	taskuri.clear();
	while (!in.eof()) {
		programatori.clear();
		in >> id;
		if (in.eof())
			break;
		in >> descriere;
		in >> programator;
		while (programator != ";") {
			programatori.push_back(programator);
			in >> programator;
		}
		in >> stare;
		Task t{ id,descriere.c_str(),programatori,stare.c_str() };
		add(t);
	}
	in.close();
}

void FileRepo::writeToFile()
{
	std::ofstream out(file);
	for (const auto& elem : taskuri) {
		out << elem.getId() << ' ' << elem.getDescriere() << ' ';
		for (const auto& p : elem.getProgramatori())
			out << p << ' ';
		out << '; ' << elem.getStare() << std::endl;
	}
	out.close();
}
