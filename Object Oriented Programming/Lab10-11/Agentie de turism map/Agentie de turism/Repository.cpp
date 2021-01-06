#include "Repository.h"
#include <iostream>


const Oferta& Repository::find(const std::string &name)
{
	std::map<std::string, Oferta>::iterator it = offers.find(name);
	if (it != offers.end())
		return it->second;
	throw RepositoryException("Oferta inexistenta!");
}


void Repository::add(const Oferta& o) {
	for (const auto& of : offers)
		if (of.second == o)
			throw RepositoryException("Oferta existenta!");
	offers.insert(std::pair<std::string, Oferta>(o.getName(), o));
}


void Repository::modify(const Oferta& o) {
	std::map<std::string, Oferta>::iterator it = offers.find(o.getName());
	if(it==offers.end())
		throw RepositoryException("Oferta inexistenta!");
	it->second = o;
}


void Repository::remove(const Oferta&o) {
	std::map<std::string, Oferta>::iterator it = offers.find(o.getName());
	if (it == offers.end())
		throw RepositoryException("Oferta inexistenta!");
	offers.erase(o.getName());
}


const std::map<std::string,Oferta>& Repository::getAll() noexcept {
	return offers;
}

size_t Repository::sizeElems() noexcept {
	return offers.size();
}


void FileRepository::loadFromFile() {
	std::ifstream in(fName);
	if (!in.is_open())
		throw FileException("Eroare deschidere " + fName);
	offers.clear();
	while (!in.eof()) {
		int k = 0;
		float price;
		std::string buf,name, destination, type;
		in >> name;
		if (in.eof())
			break;
		while (k != 3) {
			in >> buf;
			if (buf == ";")
				k++;
			else {
				if (k == 0)
					name.append(" "+buf);
				if (k == 1)
					if (destination == "")
						destination.append(buf);
					else
						destination.append(" " + buf);
				if (k == 2)
					if (type == "")
						type.append(buf);
					else
						type.append(" " + buf);
			}
		}
		in >> price;
		Oferta o{name.c_str(),destination.c_str(),type.c_str(),price };
		Repository::add(o);
	}
	in.close();
}


void FileRepository::writeToFile() {
	std::ofstream out(fName);
	for (const auto& o: offers)
		out << o.second.getName() << " ; " << o.second.getDestination() << " ; " << o.second.getType() << " ; " << o.second.getPrice() << std::endl;
	out.close();
}