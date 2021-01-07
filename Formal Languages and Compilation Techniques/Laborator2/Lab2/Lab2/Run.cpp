#include <iostream>
#include "Automat.cpp"

class Run {
private:
	Automat automat = Automat{};
public:

	void checkIfSequenceIsAccepted() {
		std::string sequence;
		std::cout << "Introduceti secventa:\n";
		std::cin >> sequence;
		if (automat.checkSequence(sequence)) {
			std::cout << "Secventa " << sequence << " este acceptata de automat!\n\n";
		}
		else {
			std::cout << "Secventa " << sequence << " nu este acceptata de automat!\n\n";
		}
	}


	void determineTheLongestSeqAccepted() {
		std::string sequence;
		std::cout << "Introduceti secventa:\n";
		std::cin >> sequence;
		std::string rez = automat.determineTheLongestAcceptedSeq(sequence);
		if (rez.compare("") == 0) {
			std::cout << "Nu exista prefix acceptat pentru aceasta secventa!\n\n";
		}
		else {
			std::cout << "Cel mai lung prefix acceptat este: " << rez << std::endl << std::endl;
		}
	}

	void readMachineFromKeyboard() {
		std::vector<std::string> machine;
		std::string stari, alfabet, tranzitii, stareI, stareF;
		std::cout << "Introduceti multimea starilor:\n";
		std::cin >> stari;
		automat.createStates("Q=" + stari);
		std::cout << "Introduceti alfabetul:\n";
		std::cin >> alfabet;
		automat.createAlphabet("A=" + alfabet);
		std::cout << "Introduceti tranzitiile:\n";
		std::cin >> tranzitii;
		automat.setTransitions("T=" + tranzitii);
		std::cout << "Introduceti starea initiala:\n";
		std::cin >> stareI;
		automat.setInitialState("I=" + stareI);
		std::cout << "Introduceti starea finala:\n";
		std::cin >> stareF;
		automat.setisAcceptState("F=" + stareF);
	}

	void run(std::string file) {
		int cmd;

		automat.check(file);
		while (true) {
			std::cout << "    ----- Meniu ------" << std::endl << std::endl;
			std::cout << "  0. Exit" << std::endl;
			std::cout << "  1. Pentru a afisa multimea starilor" << std::endl;
			std::cout << "  2. Pentru a afisa alfabetul" << std::endl;
			std::cout << "  3. Pentru a afisa tranzitiile" << std::endl;
			std::cout << "  4. Pentru a afisa multimea starilor finale" << std::endl;
			std::cout << "  5. Pentru a verifica daca o secventa este acceptata de automat" << std::endl;
			std::cout << "  6. Pentru a determina cea mai lung prefix acceptat de automat" << std::endl;
			//std::cout << "7. Citeste automatul de la tastatura" << std::endl << std::endl;
			std::cout << "  Introduceti comada: ";
			std::cin >> cmd;
			if (cmd == 0) {
				return;
			}
			if (cmd == 1) {
				automat.printStates();
			}
			else if (cmd == 2) {
				automat.printAlphabet();
			}
			else if (cmd == 3) {
				automat.printTransitions();
			}
			else if (cmd == 4) {
				automat.printFinalStates();
			}
			else if (cmd == 5) {
				checkIfSequenceIsAccepted();
			}
			else if (cmd == 6) {
				determineTheLongestSeqAccepted();
			}
			else if (cmd == 7) {
				readMachineFromKeyboard();
			}
			else {
				std::cout << "  Comanda gresita\n";
				continue;
			}
		}
	}
};