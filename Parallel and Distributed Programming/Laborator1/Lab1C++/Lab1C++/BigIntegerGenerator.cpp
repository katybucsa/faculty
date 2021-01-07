#include <string>
#include <ctime> 
#include <fstream>
#include <iostream>
#include <random>

class BigIntegerGenertor {
public:
	static void generateBigInt(int digitNr, std::string fileName) {
		std::random_device rd;
		std::mt19937 gen(rd());
		std::uniform_int_distribution<> dis(0, 9);
		std::ofstream outfile(fileName, std::ios::trunc | std::ios::out);

		if (outfile.is_open()) {
			int c;
			while (digitNr > 1) {
				c = dis(gen);//rand();
				outfile << c;
				digitNr--;
			}
			while ((c = dis(gen)) == 0) {
				continue;
			}
			outfile << c;
			outfile.close();
		}
		else {
			std::cout << "The file could not be created!";
		}
	}
};