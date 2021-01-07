#include <vector>
#include <string>
#include <sstream>
#include <fstream>
#include <algorithm>
class Parser {
public:
	/*
	face split unei linii din fisierul de intrare la fiecare aparitie a delimitatorului
	delimiter - delimitatorul dpa care se face split
	*/
	static std::vector<std::string> split(const std::string& s, char delimiter)
	{
		std::vector<std::string> tokens;
		std::string token;
		std::istringstream tokenStream(s);
		while (std::getline(tokenStream, token, delimiter))
		{
			token.erase(remove(token.begin(), token.end(), '\t'), token.end());
			tokens.push_back(token);
		}
		return tokens;
	}

	/*
	citeste cate o linie din fisierul de intrare si salveaza ca un vector elementele fiecareia
	*/
	static std::vector<std::vector<std::string>> programElements(std::string inputFile, char sep) {
		std::vector<std::vector<std::string>> all;
		std::string line;
		std::ifstream in(inputFile);
		std::vector<std::string> elems;
		if (in.is_open()) {
			while (getline(in, line)) {
				elems = split(line, sep);
				all.push_back(elems);
			}
		}
		return all;
	}
};