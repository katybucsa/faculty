#include <string>
#include <ctime> 
#include <fstream>
#include <iostream>
#include <random>

static class BigIntegerGenerator {
public:
	static void generateBigInt(int digitNr, std::string fileName) {
		std::random_device rd;
		std::mt19937 gen(rd());
		std::uniform_int_distribution<> dis(0, 9);
		std::ofstream outfile(fileName, std::ios::trunc | std::ios::out);

		if (outfile.is_open()) {
			int c;
			while ((c = dis(gen)) == 0) {
				continue;
			}
			outfile << c;
			digitNr--;
			while (digitNr > 0) {
				c = dis(gen);//rand();
				outfile << c;
				digitNr--;
			}

			outfile.close();
		}
		else {
			std::cout << "The file could not be created!";
		}
	}
};