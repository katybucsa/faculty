#include <string>
#include <map>
#include <iostream>
#include <vector>

std::map<int, std::vector<std::string>> FIP;

int main() {
	FIP[0].push_back("24");
	for (auto e : FIP) {
		for (std::string x : e.second) {
			std::cout << x;
		}
	}
	for (auto e : FIP) {
		std::cout << e.first << " " << e.second[0];
	}
	std::string s = "Eroare la linia" + std::to_string(3)+ "! Identificator";
	char* c;
	c = new char[10] {"afgsd.txt"};
	std::cout << c;
	free(c);
}