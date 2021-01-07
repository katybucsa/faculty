#include <vector>
#include <string>
#include <map>
#include <fstream>
#include <iostream>
#include "DictionarOrdonat.cpp"

class FipGenerator {
public:
	/*
	genereaza si afiseaza in fisier forma interna a programului
	all - vectorul care contine  liniile fisierului de intrare salvate in cate un vector
	cuvinte_rezervate - reprezinta tabela cuvintelor rezervate
	separatori - reprezinta tabela separatorilor
	operatori - reprezinta tabela operatorilor
	simboluri - dictionarul ordonat al simbolurilor
	outputFile - fisierul in care se afiseaza FIP
	*/
	static void createFIP(std::vector<std::vector<std::string>> all, std::string outputFile, std::map<std::string, int> cuvinte_rezervate, std::map<std::string, int> operatori,
		std::map<std::string, int> separatori, DictionarOrdonat simboluri) {
		std::ofstream out;
		out.open(outputFile);
		for (auto l : all) {
			for (auto elem : l) {
				if (cuvinte_rezervate.find(elem) != cuvinte_rezervate.end()) {
					out << cuvinte_rezervate[elem] << " ";
					std::cout << cuvinte_rezervate[elem] << " ";
				}
				else if (operatori.find(elem) != operatori.end()) {
					out << operatori[elem] << " ";
					std::cout << operatori[elem] << " ";
				}
				else if (separatori.find(elem) != separatori.end()) {
					out << separatori[elem] << " ";
					std::cout << separatori[elem] << " ";
				}
				else if (simboluri.cauta(elem) != NULL) {
					out << simboluri.cauta(elem) << " ";
					std::cout << simboluri.cauta(elem) << " ";
				}
				else {
					std::cout << "Something went wrong!" << std::endl;
					std::cout << "Something went wrong!" << std::endl;
				}
			}
			out<<std::endl;
			std::cout << std::endl;
		}
		out.close();
	}
};