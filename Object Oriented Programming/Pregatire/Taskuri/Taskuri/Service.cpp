#include "Service.h"
#include <algorithm>

void Service::add(int  id, const std::string & descriere, const std::list<std::string>& programatori, const std::string & stare) {
	Task t{ id,descriere,programatori,stare };
	valid.validate(t);
	repo.add(t);
}

void Service::modify(Task & t, const std::string &s){
	repo.modify(t, s);
}

bool cmpState(const Task& t1, const Task& t2) {
	return t1.getStare() < t2.getStare();
}

std::vector<Task> Service::sortByState() const {
	std::vector<Task> v = repo.getAll();
	std::sort(v.begin(), v.end(), cmpState);
	return v;
}

std::vector<Task> Service::filterByProg(const std::string & prog) const{
	std::vector<Task> cop;
	for (auto e : repo.getAll())
		if (std::find(e.getProgramatori().begin(), e.getProgramatori().end(), prog) != e.getProgramatori().end())
			cop.push_back(e);
	return cop;
}

std::vector<Task> Service::filterOpen() const
{
	std::vector<Task> v= repo.getAll();
	std::vector<Task> cop;
	std::copy_if(v.begin(), v.end(), std::back_inserter(cop), [](const Task& t) {return t.getStare() == "open"; });
	return cop;
}


std::vector<Task> Service::filterClosed() const
{
	std::vector<Task> v = repo.getAll();
	std::vector<Task> cop;
	std::copy_if(v.begin(), v.end(), std::back_inserter(cop), [](const Task& t) {return t.getStare() == "closed"; });
	return cop;
}


std::vector<Task> Service::filterInProgress() const
{
	std::vector<Task> v = repo.getAll();
	std::vector<Task> cop;
	std::copy_if(v.begin(), v.end(), std::back_inserter(cop), [](const Task& t) {return t.getStare() == "inprogress"; });
	return cop;
}

const std::vector<Task>& Service::getAll() {
	return repo.getAll();
}

void Service::addO(Observer * o){
	repo.addObserver(o);
}
