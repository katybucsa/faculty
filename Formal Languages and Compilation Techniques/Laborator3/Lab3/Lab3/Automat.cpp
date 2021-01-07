#include <iostream>
#include <sstream>
#include "State.cpp"
#include "RWFile.cpp"
#define UNDERLINE "\033[4m"
#define CLOSEUNDERLINE "\033[0m"

class Automat {
private:
	std::string file;
	const std::string equal = "=";
	const std::string comma = ",";
	const std::string sequences = "";
	std::vector<std::string> alphabet;
	std::map<std::string, State> state;
	State initialState = State();

public:
	Automat(std::string file) {
		this->file = file;
		try {
			read(this->file);
		}
		catch (std::exception & e) {
			e.what();
		}
	}
	/*
		verifica daca o secventa este acceptata de automatul finit determinist
		pre:automatul este finit determinist
		post: true - daca secventa este acceptata
			  false - altfel
	*/
	bool checkSequence(std::string sequence) {
		State current_state = this->initialState;
		int currentSeq_pos = 0;
		while (true) {
			if (currentSeq_pos == sequence.size()) {
				std::vector<std::string> fStates = getFinalStates();
				std::string fstr(1, sequence[currentSeq_pos]);
				if (std::find(fStates.begin(), fStates.end(), current_state.getDescription()) != fStates.end()) {
					return true;
				}
				return false;
			}
			std::map<std::string, std::vector<std::string>> transitions = state[current_state.getDescription()].getTransitions();
			std::string  str(1, sequence[currentSeq_pos]);
			std::vector<std::string> next_possible_states = transitions[str];
			if (next_possible_states.size() == 0)
				return false;
			std::string desc = next_possible_states[0];
			current_state = state[desc];
			currentSeq_pos++;
		}
	}

	/*bool recursCheckSequence(std::string sequence, int currentSeq_pos, State current_state) {
		if (currentSeq_pos == sequence.size()) {
			std::vector<std::string> fStates = getFinalStates();
			std::string fstr(1, sequence[currentSeq_pos]);
			if (std::find(fStates.begin(), fStates.end(), current_state.getDescription()) != fStates.end()) {
				return true;
			}
			return false;
		}
		std::map<std::string, std::vector<State>> transitions = current_state.getTransitions();
		std::string  str(1, sequence[currentSeq_pos]);
		std::vector<State> next_possible_states = transitions[str];
		for (State s : next_possible_states) {
			if (recursCheckSequence(sequence, currentSeq_pos + 1, s)) {
				return true;
			}
		}
		return false;

	}*/

	/*
		- pentru secventa data, sequence, determina cea mai lunga subsecventa acceptata de automatul finit
		  determinist
		- se folosesc apelurile metodei care determina daca o secventa e acceptata de automatul finit determinist
	*/
	std::string determineTheLongestAcceptedSeq(std::string sequence) {
		while (sequence.compare("") != 0) {
			if (checkSequence(sequence)) {
				return sequence;
			}
			sequence.pop_back();
		}
		return sequence;
	}

	/*
		- determina daca dintr-o stare precizata se poate ajunge intr-o stare folosind drumul precizat
	*/
	bool existsRoad(State state, std::string letter) {
		if (state.getTransitions().find(letter) != state.getTransitions().end()) {
			return true;
		}
		return false;
	}

	/*
		- returneaza multimea elementelor alfabetului pentru map-ul dat ca parametru
	*/
	std::vector<std::string> getKeys(std::map<std::string, State> map) {
		std::vector<std::string> keys;
		for (const auto& it : map) {
			keys.emplace_back(it.first);
		}
		return keys;
	}

	/*
		- returneaza multimea starilor starilor finale
	*/
	std::vector<std::string> getFinalStates() {
		std::vector<std::string> finalStates;
		for (std::string s : getKeys(state)) {
			if (state.at(s).gIsAcceptState()) {
				finalStates.emplace_back(s);
			}
		}
		return finalStates;
	}

	/*
		- afiseaza multimea tuturor starilor
	*/
	void printStates() {
		std::cout << "Multimea starilor: ";
		for (std::string s : getKeys(state)) {
			std::cout << s + " ";
		}
		std::cout << std::endl << std::endl;
	}

	/*
		- afiseaza alfabetul automatului
	*/
	void printAlphabet() {
		std::cout << "Alfabet: ";
		for (std::string s : this->alphabet) {
			std::cout << s << " ";
		}
		std::cout << std::endl << std::endl;
	}

	/*
		- afiseaza tabelul tranzitiilor
	*/
	void printTransitions() {
		std::cout << "Multimea tranzitiilor:\n" << UNDERLINE << "     ";
		for (auto letter : this->alphabet) {
			std::cout << letter << "  ";
		}
		std::cout << CLOSEUNDERLINE;
		std::cout << std::endl;
		for (std::string key : getKeys(state)) {
			std::cout << key << "|   ";
			for (auto letter : this->alphabet) {
				auto v = state[key].getKeys();
				if (std::find(v.begin(), v.end(), letter) != v.end()) {
					auto x = state[key].getTransitions()[letter];
					for (auto e : x) {
						std::cout << e;
					}
				}
				else
				{
					std::cout << "  ";
				}
				std::cout << "   ";
			}
			std::cout << std::endl;
		}
		std::cout << std::endl << std::endl;
	}

	/*
		- afiseaza multimea starilor finale
	*/
	void printFinalStates() {
		std::cout << "Stari finale: ";
		for (std::string key : getKeys(state)) {
			if (state.at(key).gIsAcceptState()) {
				std::cout << key << " ";
			}
		}
		std::cout << std::endl << std::endl;
	}

	/*
	citeste datele din fisier
	*/
	void read(std::string file) {
		RWFile rw = RWFile(file);
		try {
			std::vector<std::string> lines = rw.read();
			for (std::string line : lines) {
				switch ((split(line, '=')[0][0])) {
				case 'I': {
					setInitialState(line);
					break;
				}
				case 'Q': {
					createStates(line);
					break;
				}
				case 'F': {
					setisAcceptState(line);
					break;
				}
				case 'A': {
					createAlphabet(line);
					break;
				}
				case 'T': {
					setTransitions(line);
					break;
				}
				default: {
					std::cout << "Invalid command!";
				}

				}
			}
		}
		catch (std::exception & e) {
			e.what();
		}
	}


	void setTransitions(std::string line)
	{
		std::vector<std::string> lines = split(split(line, '=')[1], ';');
		for (std::string transition : lines) {
			std::vector<std::string> states = split(transition, '>'); // q-p,a;p-q,b;p-q,a;p-p,b
			std::vector<std::string> states_to = split(states[1], ':');
			std::vector<std::string> states_with = split(states_to[1], ',');
			if ((state.find(states[0]) != state.end())
				&& (state.find(states_to[0]) != state.end())) {
				for (auto e : states_with) {
					state.at(states[0])
						.addTransition(e, states_to[0]);
				}
			}
			else {
				std::cout << "State name invalide at tranzitions!";
			}
		}
	}

	void createStates(std::string line)
	{
		this->state.clear();
		std::vector<std::string> states = split(split(line, '=')[1], ',');
		for (std::string stateName : states) {
			if (state.find(stateName) == state.end()) {
				state[stateName] = State(stateName);
			}
		}
	}

	void createAlphabet(std::string line)
	{
		this->alphabet.clear();
		std::vector<std::string> alphs = split(split(line, '=')[1], ',');
		for (std::string letter : alphs) {
			if (std::find(alphabet.begin(), alphabet.end(), letter) == alphabet.end()) { //daca nu exista
				alphabet.push_back(letter);
			}
		}
	}

	void setInitialState(std::string line)
	{
		std::string in_state = split(line, '=')[1];
		if (state.find(in_state) != state.end()) {
			initialState = state[in_state];//State(in_state);
		}
		else {
			std::cout << "Starea initiala nu este valida!";
		}
	}

	void setisAcceptState(std::string line)
	{
		std::vector<std::string> final_states = split(split(line, '=')[1], ',');
		for (std::string stateName : final_states) {
			bool found = false;
			for (std::string s : getKeys(state)) {
				if (s.compare(stateName)) {
					state.at(stateName).setIsAcceptState(true);
					found = true;
					break;
				}
			}
			if (!found) {
				std::cout << "Starea " + stateName + " nu este valida!";
			}
		}
	}

	std::vector<std::string> split(const std::string& s, char delim)
	{
		//split string s based on delim
		std::vector<std::string> result;
		std::stringstream ss(s);
		std::string item;

		while (getline(ss, item, delim)) {
			result.push_back(item);
		}

		return result;
	}
};