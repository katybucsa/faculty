#include <vector>
#include <string>

class Elem;

static std::vector<Elem> Elems;


class Elem {
public:
	std::vector<int> N1;
	std::vector<int> N2;
	std::vector<char> C;
	std::vector<int> CARRIES;
	std::vector<int> RESULT;

	Elem() {}

	Elem(std::vector<int> N1, std::vector<int> N2) {
		if (N1.size() < N2.size()) {
			std::swap(N1, N2);
		}
		this->N1 = N1;
		this->N2 = N2;
		this->C.resize(N1.size());
		this->CARRIES.resize(N1.size());
		this->RESULT.resize(N1.size());
	}
};