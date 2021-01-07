#include <vector>
#include <algorithm>
#include <mutex>
#include "Elem.cpp"
#include "DigitMultiplication.cpp"

static std::vector<int> NR1;//nr 1 la inmultire
static std::vector<int> NR2;//nr la inmultire
static std::vector<int> result;//rezultat inmultire
static std::mutex mtx;

class MyThread {
public:

	static void myThreadMult(int begin, int offset, int pos) {
		std::vector<int> first;
		for (int i = begin; i < begin + offset; i++) {
			std::vector<int> r = DigitMultiplication::multiplyVector(NR1, NR2.at(i));
			std::fill_n(std::back_inserter(r), NR2.size() - i - 1, 0);
			std::reverse(r.begin(), r.end());
			if (offset == 1) {
				Elem e = Elem{ r,r };
				e.RESULT = r;
				Elems[pos] = e;
				continue;
			}
			if (i == begin) {
				first = r;
			}
			else {
				Elem e;
				if (i == begin + 1) {
					e = Elem{ first,r };
					Elems[pos] = e;
				}
				else {
					e = Elems[pos];
					e.N1 = e.RESULT;
					e.C.clear();
					e.CARRIES.clear();
					e.RESULT.clear();
					e.N2 = r;
					if (e.N1.size() < e.N2.size()) {
						std::swap(e.N1, e.N2);
					}
					e.C.resize(e.N1.size());
					e.CARRIES.resize(e.N1.size());
					e.RESULT.resize(e.N1.size());
				}
				myThreadC(0, e.N1.size(), pos);
				myThreadCarry(0, e.N1.size(), pos);
				myThreadFinal(0, e.N1.size(), pos);
			}
		}
	}

	static void myThreadC(int begin, int offset, int pos) {
		std::vector<int> N1 = Elems.at(pos).N1;
		std::vector<int> N2 = Elems.at(pos).N2;
		std::vector<char> C = Elems.at(pos).C;

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
		//mtx.lock();
		std::copy(C.begin(), C.end(), Elems[pos].C.begin() + begin);
		//std::copy_n(C.begin() + begin, offset, Elems[pos].C.begin() + begin);
		//Elems[pos].C = C;
		//mtx.unlock();
	}

	static void myThreadCarry(int begin, int offset, int pos) {
		std::vector<char> C = Elems[pos].C;
		std::vector<int> CARRIES = Elems.at(pos).CARRIES;

		for (size_t i = begin; i < begin + offset; i++) {
			if (i == 0) {
				CARRIES.at(0) = 0;
				if (C.at(C.size() - 1) == 'C') {
					CARRIES.resize(CARRIES.size() + 1);
					CARRIES.at(CARRIES.size() - 1) = 1;
				}
				else if (C.at(C.size() - 1) == 'M') {
					int x = C.at(C.size() - 1);
					while (C.at(x) == 'M') {
						x--;
					}
					if (C.at(x) == 'C') {
						CARRIES.resize(CARRIES.size() + 1);
						CARRIES.at(CARRIES.size() - 1) = 1;
					}
				}
			}
			else {
				if (C.at(i - 1) == 'C') {
					CARRIES.at(i) = 1;
				}
				else if (C.at(i - 1) == 'N') {
					CARRIES.at(i) = 0;
				}
				else {
					int x = i - 1;
					while (C.at(x) == 'M') {
						x--;
					}
					if (C.at(x) == 'C') {
						CARRIES.at(i) = 1;
					}
					else {
						CARRIES.at(i) = 0;
					}
				}
			}
		}
		std::copy(CARRIES.begin(), CARRIES.end(), Elems[pos].CARRIES.begin() + begin);
		//std::copy_n(CARRIES.begin() + begin, offset, Elems[pos].CARRIES.begin() + begin);
		//mtx.lock();
		//Elems[pos].CARRIES = CARRIES;
		//mtx.unlock();
	}


	static void myThreadFinal(int begin, int offset, int pos) {

		std::vector<int> N1 = Elems.at(pos).N1;
		std::vector<int> N2 = Elems.at(pos).N2;
		std::vector<int> CARRIES = Elems.at(pos).CARRIES;
		std::vector<int> result = Elems.at(pos).RESULT;
		for (size_t i = begin; i < begin + offset; i++) {
			int s;
			if (i == 0 && CARRIES.size() > N1.size()) {
				result.resize(result.size() + 1);
				result.at(result.size() - 1) = CARRIES.at(CARRIES.size() - 1);
			}
			if (i < N2.size()) {
				s = (N1.at(i) + N2.at(i) + CARRIES.at(i)) % 10;
			}
			else {
				s = (N1.at(i) + CARRIES.at(i)) % 10;
			}
			result.at(i) = s;
		}
		std::copy(result.begin(), result.end(), Elems[pos].RESULT.begin() + begin);
		//std::copy_n(result.begin() + begin, offset, Elems[pos].RESULT.begin() + begin);
		//mtx.lock();
		//Elems[pos].RESULT = result;
		//mtx.unlock();
	}
};