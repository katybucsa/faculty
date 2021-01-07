#pragma once
#include <algorithm>
#include <iostream>
#include <vector>
class Channel {
public:
	Channel(){};
	virtual void send(std::string) = 0;
};

class Telefon :public Channel {
private:
	int nrTel;
public:
	Telefon(int nr) :nrTel{ nr } {}
	
	void send(std::string s) override {
		if (rand() % 10 == 7)
			throw std::exception("Linie ocupata!\n");
		std::cout << "dail: ";
	}
};

class Fax :public Telefon {
public:
	Fax(int nr) : Telefon { nr } {}
	void send(std::string m) override {
		try {
			Telefon::send(m);
			std::cout << "sending fax\n";
		}catch (std::exception) {}
	}
};

class SMS :public Telefon {
public:
	SMS(int nr) :  Telefon{ nr } {}

	void send(std::string m) override {
		try {
			Telefon::send(m);
			std::cout << "sending sms\n";
		}catch (std::exception) {}
	}
};


class Failover :public Channel {
private:
	Channel* channel1;
	Channel* channel2;
	std::string msg;
public:
	Failover(Channel* c1, Channel* c2, std::string s) :channel1{ c1 }, channel2{ c2 }, msg{ s } {
	}

	void send(std::string s) override {
		try {
			channel1->send(msg);
		}
		catch (std::exception) {
			try {
				channel2->send(msg);
			}
			catch (std::exception) {}
		}
	}
};

class Contact {
private:
	std::vector<Channel*> cnls;
	std::string msg;
public:
	Contact(Channel* c1, Channel* c2, Channel* c3, std::string m) :msg{ m } {
		cnls.push_back(c1);
		cnls.push_back(c2);
		cnls.push_back(c3);
		for (int i = 0; i<3; i++)
			try {
			cnls[i]->send(msg);
			break;
		}
		catch (std::exception) {}
	}
};

void f() {
	Telefon* t=new Telefon{ 440742486929 };
	Fax* fax = new Fax{ 440742486929 };
	SMS* sms = new SMS{ 440742486929 };
	Failover* fover = new Failover{ fax,sms,"daca e ocupat incearca" };
	Contact* contact = new Contact{ t,fax,sms,"daca e ocupat incearca" };

}