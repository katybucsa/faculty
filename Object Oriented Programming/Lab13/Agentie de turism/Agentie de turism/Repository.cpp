#include "Repository.h"


size_t Repository::getOPoz(const Oferta& o) {
	for (size_t i = 0; i < offers.size(); i++)
		if (offers[i] == o)
			return i;
	throw RepositoryException("Oferta inexistenta!");
}


void Repository::add(const Oferta& o) {
	auto it = std::find(offers.begin(), offers.end(), o);
	if (it != offers.end())
		throw RepositoryException("Oferta existenta!");
	offers.push_back(o);
}

void Repository::modify(const Oferta& o) {
	const size_t poz = getOPoz(o);
	offers[poz] = o;
}


void Repository::remove(const Oferta&o) {
	const size_t poz = getOPoz(o);
	offers.erase(offers.begin() + poz);
}

const Oferta& Repository::getOferta(const std::string& name) {
	auto found = std::find_if(offers.begin(), offers.end(), [&](const Oferta& o) {return o.getName() == name; });
	if (found == offers.end())
		throw RepositoryException("Oferta inexistenta!");
	return *found;
}


const std::vector<Oferta>& Repository::getAll() noexcept {
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
		std::string buf, name, destination, type;
		in >> name;
		if (in.eof())
			break;
		while (k != 3) {
			in >> buf;
			if (buf == ";")
				k++;
			else {
				if (k == 0)
					name.append(" " + buf);
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
		Oferta o{ name.c_str(),destination.c_str(),type.c_str(),price };
		Repository::add(o);
	}
	in.close();
}

void FileRepository::writeToFile() {
	std::ofstream out(fName);
	for (auto& o : offers)
		out << o.getName() << " ; " << o.getDestination() << " ; " << o.getType() << " ; " << o.getPrice() << std::endl;
	out.close();
}