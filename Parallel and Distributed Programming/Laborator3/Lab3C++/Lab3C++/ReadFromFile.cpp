#include <vector>
#include <fstream>
#include <string>
#include <iostream>

class ReadFromFile {

public:
	static std::vector<int> readNumber(std::string fileName) {
		std::vector<int> number;
		std::ifstream in(fileName);
		if (in.is_open()) {
			std::string line;
			while (std::getline(in, line)) {
				for (char c : line) {
					number.push_back((int)(c - '0'));
				}
			}
		}
		else {
			std::cout << "File could not be opened!";
		}
		return number;
	}

	static std::vector<int> readSequenceFromFile(std::string fileName, int start, int end) {
		std::vector<int> number;

		std::ifstream file(fileName, std::ios::binary | std::ios::ate);
		if (file.is_open())
		{
			if (file.tellg() >= end) {
				file.seekg(start);
				std::string s;
				s.resize(end - start);
				file.read(&s[0], end - start);
				for (char c : s) {
					number.push_back((int)(c - '0'));
				}
			}
			else if (file.tellg() > start&& file.tellg() < end) {
				end = file.tellg();
				file.seekg(start);
				std::string s;
				s.resize(end - start);
				file.read(&s[0], end - start);
				for (char c : s) {
					number.push_back((int)(c - '0'));
				}
			}
		}
		return number;
	}
};