#include "Repo.h"

void Repo::add(const Music & m){
	auto it = find(elems.begin(), elems.end(), m);
	if (it != elems.end())
		throw RepoException("Piesa existenta!\n");
	elems.push_back(m);
}

const vector<Music>& Repo::getAll() noexcept{
	return elems;
}

void Repo::top5()
{
	ofstream out("top5.txt");
	for (int i = 0; i < 5, i<elems.size(); i++) {
		out << elems[i].getArtist() << " " << elems[i].getTitlu() << " " << elems[i].getLikes() << endl;
	}
}

void Repo::like(Music m)
{
	auto it = find(elems.begin(), elems.end(), m);
	if (it == elems.end())
		throw RepoException("Piesa inexistenta!\n");
	elems[it - elems.begin()].setLike(elems[it - elems.begin()].getLikes() + 1);
}

void Repo::dislike(Music m)
{
	auto it = find(elems.begin(), elems.end(), m);
	if (it == elems.end())
		throw RepoException("Piesa inexistenta!\n");
	if (elems[it - elems.begin()].getLikes() == 0)
		throw RepoException("Numarul de like-uri este deja 0!\n");
	elems[it - elems.begin()].setLike(elems[it - elems.begin()].getLikes() - 1);
}





