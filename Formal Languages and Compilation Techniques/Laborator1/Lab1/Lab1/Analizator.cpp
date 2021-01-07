#include <map>
#include <string>
#include <vector>
#include <regex>
#include "FipGenerator.cpp"

/*
clasa proprie de tratare a exceptiilor 
*/
class MyException {
private:
	std::string message;
public:
	MyException(std::string msg) : message{ msg } {
	}
	const std::string& getMessage() const noexcept {
		return message;
	}
};

class Analizator {
private:


	/*
	creeaza tabelul de simboluri si verifica corectitudinea lexicala
	all - vectorul care contine  liniile fisierului de intrare salvate in cate un vector
	cuvinte_rezervate - reprezinta tabela cuvintelor rezervate
	separatori - reprezinta tabela separatorilor
	operatori - reprezinta tabela operatorilor
	start - codul de la care incepe criptarea simbolurilor
	*/
	static DictionarOrdonat getSimbols(std::vector<std::vector<std::string>> all, std::map<std::string, int> cuvinte_rezervate, std::map<std::string, int> operatori,
		std::map<std::string, int> separatori, int start) {
		DictionarOrdonat simboluri{ cmp };
		static const std::regex re{ "^[\\+-]?([0-9]+\\.?[0-9]*|\\.?[0-9]+)$" };
		for (int j = 0; j < all.size(); j++) {
			int l = j + 1;
			for (auto i : all.at(j)) {
				///daca atomul nu exista in niciunul dintre tabelele cuvintelor rezervate, operatori
				///si separatori si nu a mai fost adaugat in tabela simbolurilor atunci se adauga
				if (cuvinte_rezervate.find(i) == cuvinte_rezervate.end()
					&& operatori.find(i) == operatori.end()
					&& separatori.find(i) == separatori.end()
					&& simboluri.cauta(i) == NULL) {
					if (!std::regex_match(i, re) && i.at(0)>='0' && i.at(0)<='9') {
						throw MyException("Eroare la linia " + std::to_string(l) + ". Identificatorul nu poate incepe cu cifra!\n");
					}
					//un identificator nu poate contine mai mult de 256 caractere
					if (!std::all_of(i.begin(), i.end(), ::isdigit) && i.length() > 256) {
						throw MyException("Eroare la linia" + std::to_string(l) + "\nIdentificatorul nu poate contine mai mult de 256 caractere!\n");
					}
					simboluri.adauga(i, start);
					start++;
				}
			}
		}
		return simboluri;
	}
public:
	/*
	verifica corectitudinea lexicala si apoi afiseaza in fisier tabela de simboluri
	*/
	static DictionarOrdonat analize(std::vector<std::vector<std::string>> all, std::map<std::string, int> cuvinte_rezervate, std::map<std::string, int> operatori,
		std::map<std::string, int> separatori, int start, std::string file) {
		DictionarOrdonat d = getSimbols(all, cuvinte_rezervate, operatori, separatori, start);
		std::ofstream out;
		out.open(file);
		///itereaza dictionarul ordonat si afiseaza simbolurile in fisier 
		IteratorDo it = d.iterator();
		std::queue<std::string> q = it.inordine();
		while (!q.empty()) {
			std::string s = q.front();
			out <<s<<std::endl;
			std::cout <<s<<std::endl;
			q.pop();
		}
		out.close();
		return d;
	}
};