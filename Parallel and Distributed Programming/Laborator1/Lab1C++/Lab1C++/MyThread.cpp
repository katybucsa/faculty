#include <vector>
#include <string>
#include <iostream>

static std::vector<int> N1;
static std::vector<int> N2;
static std::vector<char> C;
static std::vector<char> PREFIXES;
static std::vector<int> CARRIES;
static std::vector<int> RESULT;
static const std::string VARIANT = "S";


class MyThread {
public:
	static void myThreadC(int begin, int offset) {
		for (size_t i = begin; i < begin + offset; i++) {
			if (i < N2.size()) {
				if (N1.at(i) + N2.at(i) > 9) {
					C.at(i) = 'C';
				}
				else if (N1.at(i) + N2.at(i) == 9) {
					C.at(i) = 'M';
				}
				else {
					C.at(i) = 'N';
				}
			}
			else {
				if (N1.at(i) == 9) {
					C.at(i) = 'M';
				}
				else {
					C.at(i) = 'N';
				}
			}
		}
	}


	static void myThreadPrefix(int begin, int offset) {
		PREFIXES.at(0) = C.at(0);
		for (size_t i = begin; i < begin + offset; i++) {
			if (i == 0) {
				continue;
			}
			if (C.at(i) == 'C' || C.at(i) == 'N') {
				PREFIXES.at(i) = C.at(i);
			}
			else {
				int x = i;
				while (C.at(x) == 'M') {
					x--;
				}
				PREFIXES.at(i) = C.at(x);
			}
		}
	}

	static void myThreadCarry(int begin, int offset) {
		int s;
		for (size_t i = begin; i < begin + offset; i++) {
			if (i < N2.size()) {
				s = N1.at(i) + N2.at(i);
				RESULT.at(i) = s % 10;
				CARRIES.at(i) = s / 10;
			}
			else {
				RESULT.at(i) = N1.at(i);
			}
		}
	}


	static void myThreadCarry2(int begin, int offset) {
		for (size_t i = begin; i < begin + offset; i++) {
			if (i == 0) {
				CARRIES.at(0) = 0;
				if (PREFIXES.at(PREFIXES.size() - 1) == 'C') {
					CARRIES.resize(CARRIES.size() + 1);
					CARRIES.at(CARRIES.size() - 1) = 1;
				}
			}
			else {
				if (PREFIXES.at(i - 1) == 'C') {
					CARRIES.at(i) = 1;
				}
				else {
					CARRIES.at(i) = 0;
				}
			}
		}
	}


	static void myThreadFinal(int begin, int offset) {
		for (size_t i = begin; i < begin + offset; i++) {
			int s;
			if (i == 0 && CARRIES.size() > N1.size()) {
				RESULT.resize(RESULT.size() + 1);
				RESULT.at(RESULT.size() - 1) = CARRIES.at(CARRIES.size() - 1);
			}
			if (i < N2.size()) {
				s = (N1.at(i) + N2.at(i) + CARRIES.at(i)) % 10;
			}
			else {
				s = (N1.at(i) + CARRIES.at(i)) % 10;
			}
			RESULT.at(i) = s;
		}
	}
};