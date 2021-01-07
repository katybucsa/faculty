#include "Service.h"
#include <algorithm>

bool cmpArtist(const Music& m1, const Music& m2) {
	return m1.getArtist() < m2.getArtist();
}

void Service::add(const int &id, const std::string & t, const std::string &a, const std::string &g){
	Music m{ id,t,a,g };
	valid.validate(m);
	repo.add(m);
}

void Service::modify(int id, std::string t, std::string a, std::string g){
	Music m{ id,t,a,g };
	valid.validate(m);
	repo.modify(m);
}

void Service::remove(Music m){
	repo.remove(m);
}

int Service::getNrCuArtistul(const std::string & artist){
	std::vector<Music> all = repo.getAll();
	return std::count_if(all.begin(), all.end(), [&artist](const Music& m) {return m.getArtist() == artist; });
}

int Service::getNrCuGenul(const std::string & gen){
	std::vector<Music> all = repo.getAll();
	return std::count_if(all.begin(), all.end(), [&gen](const Music& m) {return m.getArtist() == gen; });
}

std::vector<Music> Service::sortByArtist() const{
	std::vector<Music> v = repo.getAll();
	std::sort(v.begin(), v.end(), cmpArtist);
	return v;
}

const std::vector<Music>& Service::getAll() {
	return repo.getAll();
}
