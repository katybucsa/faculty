#include <string>
#include <map>
#include "FileReader.cpp"
#include "Analizator.cpp"
#include "Parser.cpp"

class Run {
public:
	static void run(std::string cuvinte_rezervate, std::string operatori, std::string separatori, std::string fileCerc, std::string fileCmmdc, std::string fileSuma, std::string outFile) {
		std::map<std::string, int> map_cuvinte;
		std::map<std::string, int> map_operatori;
		std::map<std::string, int> map_separatori;
		FileReader::readFromFile(cuvinte_rezervate, ",", map_cuvinte);
		FileReader::readFromFile(operatori, ",", map_operatori);
		FileReader::readFromFile(separatori, " ", map_separatori);
		std::string file;
		int cmd;
		while (true) {
			std::cout << "0. Exit" << std::endl;
			std::cout << "1. Pentru a incarca fisierul cerc.txt" << std::endl;
			std::cout << "2. Pentru a incarca fisierul cmmdc.txt" << std::endl;
			std::cout << "3. Pentru a incarca fisierul suma.txt" << std::endl << std::endl;
			std::cout << "Introduceti comada: ";
			std::cin >> cmd;
			if (cmd == 0) {
				return;
			}
			if (cmd == 1) {
				file = fileCerc;
			}
			else if (cmd == 2) {
				file = fileCmmdc;
			}
			else if (cmd == 3) {
				file = fileSuma;
			}
			else {
				std::cout << "Comanda gresita\n";
				continue;
			}
			try {
				std::vector<std::vector<std::string>> all = Parser::programElements(file, ' ');
				std::string ts = outFile + "ts" + std::to_string(cmd)+".txt";
				std::string fip = outFile + "fip" + std::to_string(cmd)+".txt";
				DictionarOrdonat simboluri = Analizator::analize(all, map_cuvinte, map_operatori, map_separatori, 300, ts);
				std::cout << std::endl;
				FipGenerator::createFIP(all, fip, map_cuvinte, map_operatori, map_separatori, simboluri);
			}
			catch (MyException e) {
				std::cout << e.getMessage();
			}
		}
	}
};