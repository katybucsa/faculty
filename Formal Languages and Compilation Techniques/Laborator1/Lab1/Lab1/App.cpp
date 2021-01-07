#include <string>
#include "Run.cpp"

int main() {
	std::string cuvinte_rezervate = "C:\\Users\\Katy\\Documents\\A3S1\\LFTC\\Laborator\\Laborator1\\cuvinte_rezervate.txt";
	std::string operatori = "C:\\Users\\Katy\\Documents\\A3S1\\LFTC\\Laborator\\Laborator1\\operatori.txt";
	std::string separatori = "C:\\Users\\Katy\\Documents\\A3S1\\LFTC\\Laborator\\Laborator1\\separatori.txt";
	std::string out = "C:\\Users\\Katy\\Documents\\A3S1\\LFTC\\Laborator\\Laborator1\\";
	std::string fileCerc = "C:\\Users\\Katy\\Documents\\A3S1\\LFTC\\Laborator\\Laborator1\\cerc1.txt";
	std::string fileCmmdc = "C:\\Users\\Katy\\Documents\\A3S1\\LFTC\\Laborator\\Laborator1\\cmmdc1.txt";
	std::string fileSuma = "C:\\Users\\Katy\\Documents\\A3S1\\LFTC\\Laborator\\Laborator1\\suma1.txt";
    Run::run(cuvinte_rezervate, operatori, separatori, fileCerc, fileCmmdc, fileSuma, out);
	return 0;
}