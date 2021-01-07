#include <string>
#include <map>
#include <vector>

class State {
private:
	bool isAcceptState;
	std::string description;
	std::map<std::string, std::vector<std::string>> transitions;

public:
	State() {
		this->isAcceptState = false;
		this->description = "";
		this->transitions = std::map<std::string, std::vector<std::string>>();
	}
	State(std::string description) {
		this->isAcceptState = false;
		this->description = description;
		this->transitions = std::map<std::string, std::vector<std::string>>();
	}

	void addTransition(std::string alphabetItem, std::string state) {
		if (this->transitions.find(alphabetItem) == this->transitions.end()) {
			this->transitions.insert(std::pair<std::string, std::vector<std::string>>(alphabetItem, std::vector<std::string>{state}));
		}
		else {
			this->transitions[alphabetItem].push_back(state);
		}
	}

	bool gIsAcceptState() {
		return this->isAcceptState;
	}

	void setIsAcceptState(bool isAcceptState) {
		this->isAcceptState = isAcceptState;
	}

	std::string getDescription() {
		return this->description;
	}

	void setDescription(std::string description) {
		this->description = description;
	}

	std::map < std::string, std::vector<std::string>> getTransitions() {
		return this->transitions;
	}

	void setTransitions(std::map < std::string, std::vector<std::string>> transitions) {
		this->transitions = transitions;
	}

	std::vector<std::string> getKeys() {
		std::vector<std::string> keys;
		for (const auto& it : this->transitions) {
			keys.emplace_back(it.first);
		}
		return keys;
	}

	std::vector<std::string> getDestinations() {
		std::vector<std::string> rez;
		for (auto e : this->transitions) {
			for (auto i : e.second) {
				if (std::find(rez.begin(), rez.end(), i) == rez.end()) {
					rez.push_back(i);
				}
			}
		}
		return rez;
	}
};