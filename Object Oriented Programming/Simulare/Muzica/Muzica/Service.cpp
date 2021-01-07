#include "Service.h"
#include <algorithm>

void Service::add(const string & artist, const string & titlu, int likes){
	Music m = Music{ artist,titlu,likes };
	repo.add(m);
}

bool cmpLikes(const Music& m1, const Music& m2) {
	return m1.getLikes() > m2.getLikes();
}

vector<Music> Service::sortLikes() const {
	vector<Music> all = repo.getAll();
	sort(all.begin(), all.end(), cmpLikes);
	return all;
}

void Service::like(const Music& m)
{
	repo.like(m);
}

void Service::dislike(const Music& m)
{
	repo.dislike(m);
}

void Service::top5()
{
	repo.top5();
}

