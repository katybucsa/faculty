#include "Service.h"
#include <algorithm>

void Service::add(int  id, const std::string & descriere, const std::list<std::string>& programatori, const std::string & stare){
	Task t{ id,descriere,programatori,stare };
	valid.validate(t);
	repo.add(t);
}

bool cmpState(const Task& t1, const Task& t2) {
	return t1.getStare() < t2.getStare();
}

std::vector<Task> Service::sortByState() const {
	std::vector<Task> v = repo.getAll();
	std::sort(v.begin(), v.end(), cmpState);
	return v;
}

const std::vector<Task>& Service::getAll(){
	return repo.getAll();
}
