#include <iostream>
#include <vector>
#include <deque>
#include <algorithm>
#include <map>
#include <string>
#include <assert.h>
#include <queue>
#include <list>
#include <memory>
#include "ste.h"
#include "Cos.h"
#include "masini.h"
#include "participant.h"

/*class A {
public:
	virtual void print() {
		std::cout << "printA";
	}
	virtual ~A() {
		std::cout << "~A";
	}
};

class B :public A {
public:
	virtual void print() {
		std::cout << "printB";
	}
	~B() {
		std::cout << "~B";
	}
};*/

void f(std::vector<int>& l) {
	if (l.size() < 1)
		throw  std::exception("Illegal argument");
	std::map<int, int> m;
	for (auto e : l)
		m[e]++;
	std::sort(l.begin(), l.end(), [&m](int a, int b) {return m[a] < m[b]; });
}

void cumparaturi(){
	Cos<std::string> cos{ "Ion" };
	cos = cos + "Mere";
	cos = cos + "Paine" + "Lapte";
	cos - "Paine";
	cos.undo().undo();
	/*for (auto i : cos) {
		std::cout << i << "\n";
	}*/
	cos.tipareste(std::cout);
}

void sampleVector() {
	std::vector<int> v;
	v.push_back(4);
	v.push_back(8);
	v.push_back(12);
	v[2] = v[0] + 2;
	int lg = v.size();
	for (int i = 0; i<lg; i++)
	{
		std::cout << v.at(i) << " ";
	}
}

void sampleDeque() {
	std::deque<double> dq;
	dq.push_back(4);
	dq.push_back(8);
	dq.push_back(12);
	dq[2] = dq[0] + 2;
	int lg = dq.size();
	for (int i = 0; i<lg; i++)
	{
		std::cout << dq.at(i) << " ";
	}
}

std::vector<Vehicul*> makeList() {
	std::vector<Vehicul*> masini;
	/*std::list<std::unique_ptr<Vehicul>> l;
	l.push_back(std::make_unique<Masina>("Audi", 3000));
	SchimbatorAutomat sa{ Masina{"Audi",3000} };
	SenzorDeParcare sp{ Masina{sa.descriere(),sa.getPret() } };
	l.push_back(std::make_unique<SenzorDeParcare>(sp));
	l.push_back(std::make_unique<SchimbatorAutomat>(Masina{ "Mercedes",5000 }));
	l.push_back(std::make_unique<SenzorDeParcare>(Masina{ "Opel",4500 }));
	return l;*/

	Masina * Audi = new Masina("Audi", 4000);
	SchimbatorAutomat * AudiSch = new SchimbatorAutomat(Audi);
	SenzorDeParcare * AudiFinal = new SenzorDeParcare(AudiSch);

	Masina * Merc = new Masina("Mercedes", 6000);
	SchimbatorAutomat * MS = new SchimbatorAutomat(Merc);

	Masina * Opel = new Masina("Opel", 10000);
	SenzorDeParcare * OS = new SenzorDeParcare(Opel);

	masini.push_back(Audi);
	masini.push_back(AudiFinal);
	masini.push_back(MS);
	masini.push_back(OS);
	return masini;
}


void samplePriorQueue() {

	std::priority_queue<int, std::vector<int> > s;
	s.push(3);
	s.push(4);
	s.push(1);
	s.push(2);
	while (!s.empty()) {
		std::cout << s.top() << " ";
		s.pop();
	}
}


class B {
public:
	B() { std::cout << "B()"; }
	virtual void print() { std::cout << "B"; }
	virtual ~B() { std::cout << "~B()"; }
};

class D:public B {
public:
	D() { std::cout << "D()"; }
	virtual void print() { std::cout << "D"; }
	virtual ~D() { std::cout << "~D()"; }
};

class E {
public:
	E() { std::cout << "E()"; }
	E(const E& e) { std::cout << "E(const E&)"; }
	~E() { std::cout << "~E()"; }
};

int fun(int a[], int n) {
	if (n < 0)
		throw E();
	else if (n == 0) throw new E();
	return 0;
}

int main() {
	/*std::vector<A> v;//eroare - nu se poate face pointer dintr-o referinta
	A a;
	B b;
	v.push_back(a);
	v.push_back(b);
	for (auto e : v) {
		e.print();
	}*/
	/*std::deque<std::string> s;
	s.push_back("A");
	s.push_back("B");
	s.push_front("C");
	s.push_front("D");
	auto it = s.begin();
	auto end = s.end();
	end--; end--;
	while (it != end) {
		std::cout << *it;
		it++;
	}*/

	/*Derived d;
	Base& b = d;

	b.multiply(10);  // these two calls will call the same function but
	d.multiply(10);  // with different arguments and so different results*/
	/*int y = 7;
	int &x = y;
	y = 3;
	x = 5;*/
	/*std::vector<int> v = { 1,3,6,2 };
	f(v);
	v.clear();
	try {
		f(v); assert(false);
	}catch (std::exception) { assert(true); }*/

	/*int j = 100;
	int i = 7;
	const int* p2 = &j;
	std::cout << *p2 << "\n";
	p2 = &i;
	std::cout << *p2 << "\n";
	//*p2 = 7;*/

	//cumparaturi();
	//sampleVector();
	//sampleDeque();

	/*std::vector <Vehicul*> l = makeList();
	std::sort(l.begin(), l.end(), [](Vehicul* a, Vehicul*b) {return a->getPret() > b->getPret(); });
	for (auto& e : l) {
		std::cout << e->descriere() << "  " << e->getPret() << std::endl;
	}*/

	/*ONG ong = f();
	std::vector<Participant*> v = ong.getAll(false);
	for (auto e : v) {
		e->tipareste();
		std::cout<<"\n";
	}

	std::vector<Participant*> v1 = ong.getAll(true);
	for (auto e : v1) {
		e->tipareste();
		std::cout << "\n";
	}*/

	//samplePriorQueue();

	/*B* b []= {new B(), new D()};
	b[0]->print();
	b[1]->print();
	delete b[0];
	delete b[1];*/

	/*int v[] = {};
	try {
		std::cout << fun(v, sizeof(v) / sizeof(int));
	}
	catch (E& e) {}
	catch (E*e) {}*/
	return 0;
}