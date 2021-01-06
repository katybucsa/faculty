#include "Repository.h"

void FileRepo::loadFromFile(){
	std::ifstream in(file);
	int id;
	std::string titlu, artist, gen;
	if (!in.is_open())
		throw MusicException("Nu s-a putut deschide fisierul!\n");
	all.clear();
	while (!in.eof()) {
		in >> id;
		if (in.eof())
			break;
		in >> titlu;
		in >> artist;
		in >> gen;
		Music m{ id,titlu,artist,gen };
		all.push_back(m);
	}
	in.close();
}

void FileRepo::writeToFile(){
	std::ofstream out(file);
	for (auto m : all) {
		out << m.getId() << " " << m.getTitlu() << " " << m.getArtist() << " " << m.getGen() << std::endl;
	}
	out.close();
}

void FileRepo::add(const Music &m){
	loadFromFile();
	if (std::find(all.begin(), all.end(), m) != all.end())
		throw MusicException("Melodie existenta!\n");
	all.push_back(m);
	writeToFile();
}

void FileRepo::modify(const Music &m){
	loadFromFile();
	auto it = std::find(all.begin(), all.end(), m);
	*it = m;
	writeToFile();
}

void FileRepo::remove(const Music &m){
	loadFromFile();
	auto it = std::find(all.begin(), all.end(), m);
	all.erase(it);
	writeToFile();
}

const std::vector<Music>& FileRepo::getAll(){
	return all;
}
