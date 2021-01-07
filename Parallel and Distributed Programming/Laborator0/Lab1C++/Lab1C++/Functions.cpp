#include <vector>
#include <sstream>
#include <stdlib.h>
#include <iostream>
#include <string>
#include <fstream>  
#include <map> 



class Functions
{
public:
	static void createFile(std::string fileName, int size, int min, int max) {
		std::ofstream outfile(fileName);
		if (outfile.is_open()) {
			for (int i = 0; i < size; i++) {
				outfile << min + (std::rand() % (max - min + 1));
				if (i < size - 1) {
					outfile << " ";
				}
			}
		}
		else {
			std::cout << "Fisierul nu s-a putut deschide!";
		}
	}


	static std::map<int, int> readFromFile(std::string file) {
		std::map<int, int> map;
		std::ifstream f(file);
		std::vector<std::string> nums;
		if (f.is_open()) {
			std::string line;
			std::string elem;
			while (std::getline(f, line)) {
				std::stringstream lineStream(line);
				while (std::getline(lineStream, elem, ' '))
				{
					int number = stoi(elem);
					if (map.find(number) == map.end()) {
						map.insert(std::pair<int, int>(stoi(elem), 1));
					}
					else
						map[number] += 1;
				}
			}
		}
		return map;

	}

	static bool filesEquality(std::string file1, std::string file2) {
		std::map<int, int> mapFile1 = readFromFile(file1);
		std::map<int, int> mapFile2 = readFromFile(file2);
		for (std::pair<int, int> elem : mapFile1) {
			if (mapFile2.find(elem.first) == mapFile2.end() || mapFile2[elem.first] != elem.second)
				return false;
		}
		return true;
	}

	static void writeToCsvFile(std::string csvFile, std::vector<std::string> content) {
		std::fstream out;
		out.open(csvFile, std::ios::out | std::ios::app);
		for (int i = 0; i < content.size() - 1; i++) {
			out << content[i] << ",";
		}
		out << content[content.size() - 1] << "\n";
		out.close();
	}
};


int main() {
	Functions::createFile("numbers.txt", 4, 1, 13);
	std::cout << Functions::filesEquality("a.txt", "b.txt");
	std::vector<std::string> student = { "Ana","21","Suceava" };
	Functions::writeToCsvFile("c.csv", student);
	int x;
	std::cin >> x;
	return 0;
}
