#pragma once
#include "Service.h"
#include <assert.h>
#include <fstream>

void testDomain() {
	std::list<std::string> progs;
	progs.push_back("Andreea");
	progs.push_back("Gabriela");
	progs.push_back("Alex");
	Task t{ 12,"abc",progs,"open" };
	assert(t.getId() == 12);
	assert(t.getDescriere() == "abc");
	assert(t.getStare() == "open");
	assert(t.getProgramatori().size()==3);
	Task t1{ 12,"sff",progs,"closed" };
	assert(t == t1);
}

void testRepoService() {
	std::ofstream out("teste.txt", std::ios::trunc);
	out.close();
	FileRepo repo{ "teste.txt" };
	Validator valid;
	Service serv{ repo,valid };
	std::list<std::string> progs;
	progs.push_back("Andreea");
	progs.push_back("Gabriela");
	progs.push_back("Alex");
	serv.add(12, "abc", progs, "open");
	Task t{ 12,"abc",progs,"open" };
	assert(serv.getAll().size() == 1);
	try {
		serv.add(12, "abc", progs, "open");
	}catch (const TaskException& e) { assert(e.getMsg() == "Task existent!"); }
	serv.modify(t, "inprogress");
	assert(repo.getAll()[0].getStare() == "inprogress");
	serv.add(13, "abc", progs, "open");
	serv.add(14, "abc", progs, "closed");
	std::vector<Task> v = serv.sortByState();
	assert(v[0].getId() == 14);
	assert(v[1].getId() == 12);
	assert(v[2].getId() == 13);
	progs.push_back("Ionut");
	serv.add(15, "sd", progs, "open");
	std::vector<Task> v1 = serv.filterByProg("Ionut");
	assert(v1.size() == 1);
	std::vector<Task> v2 = serv.filterOpen();
	assert(v2.size() == 2);
	std::vector<Task> v3 = serv.filterClosed();
	assert(v3.size() == 1);
	std::vector<Task> v4 = serv.filterInProgress();
	assert(v4.size() == 1);
	try {
		FileRepo r{ "ds" };
	}catch (const TaskException& e) { assert(e.getMsg() == "Nu s-a putut deschide fisierul!"); }
}

void testValidator() {
	Validator valid;
	std::list<std::string> progs;
	progs.push_back("Andreea");
	progs.push_back("Gabriela");
	progs.push_back("Alex");
	Task t{ -12,"abc",progs,"open" };
	try {
		valid.validate(t); assert(false);
	}catch (const TaskException& e) { assert(e.getMsg() == "Id invalid!\n"); }

	progs.clear();
	Task t1{ 12,"",progs,"ggrer" };
	try {
		valid.validate(t1); assert(false);
	}catch (const TaskException& e) { assert(true); }
}

void testAll() {
	testDomain();
	testRepoService();
	testValidator();
}