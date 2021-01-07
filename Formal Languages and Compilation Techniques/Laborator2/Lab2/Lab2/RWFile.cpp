#include <string>
#include <vector>
#include <fstream>

class RWFile {
private:
	std::string IN_FILE;
	std::string OUT_FILE;

public:
	RWFile(std::string fin) {
		this->IN_FILE = fin;
	}
	std::vector<std::string> read() {
		std::ifstream fin;
		std::string line;
		fin.open(IN_FILE, std::ios::in);
		std::vector<std::string> lines;
		while (!fin.eof()) {
			std::getline(fin, line);
			lines.push_back(line);
		}
		return lines;
	}
};