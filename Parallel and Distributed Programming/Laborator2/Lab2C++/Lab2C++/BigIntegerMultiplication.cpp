#include <string>
#include <vector>
#include <fstream>
#include <iostream>
#include <algorithm>
#include <iterator>
#include <thread>
#include <chrono>
#include <cmath>
#include "SequentialA.cpp"
#include "SequentialD.cpp"
#include "BigIntegerGenerator.cpp"
#include "MyThread.cpp"

class BigIntegerMultiplication {
public:
	static std::vector<int> readNumber(std::string fileName) {
		std::vector<int> number;
		std::ifstream in(fileName);
		if (in.is_open()) {
			std::string line;
			while (std::getline(in, line)) {
				for (char c : line) {
					number.push_back((int)(c - '0'));
				}
			}
		}
		else {
			std::cout << "File could not be opened!";
		}
		return number;
	}

	static std::vector<int> karatsuba(std::vector<int> num1, std::vector<int> num2) {
		if (num1.size() == 1) {
			return DigitMultiplication::multiplyVector(std::ref(num2), std::ref(num1.at(0)));
		}
		if (num2.size() == 1) {
			return DigitMultiplication::multiplyVector(std::ref(num1), std::ref(num2.at(0)));
		}
		while (num1.size() < num2.size()) {
			num1.insert(num1.begin(), 0);
		}
		while (num2.size() < num1.size()) {
			num2.insert(num2.begin(), 0);
		}
		int m = num1.size();
		int mij;
		if (m % 2 == 0) {
			mij = m / 2;
		}
		else {
			mij = m / 2 + 1;
		}

		std::vector<int> z0, z1, z2;
		std::vector<int> high1(num1.begin(), num1.begin() + mij);
		std::vector<int> low1(num1.begin() + mij, num1.end());
		std::vector<int> high2(num2.begin(), num2.begin() + mij);
		std::vector<int> low2(num2.begin() + mij, num2.end());

		while (high1.at(0) == 0 && high1.size() > 1) {
			high1.erase(high1.begin());
		}
		while (high2.at(0) == 0 && high2.size() > 1) {
			high2.erase(high2.begin());
		}

		std::vector<int> low1high1 = SequentialA::sequentialAdd(std::ref(high1), std::ref(low1));
		std::vector<int> low2high2 = SequentialA::sequentialAdd(std::ref(high2), std::ref(low2));


		z0 = karatsuba(low1, low2);
		z1 = karatsuba(low1high1, low2high2);
		z2 = karatsuba(high1, high2);

		//((z1 - z2 - z0) * 10 ^ m2)
		std::vector<int> r = SequentialD::sequentialDiff(std::ref(z1), std::ref(z2));
		std::vector<int> z1_z2_z0 = SequentialD::sequentialDiff(std::ref(r), std::ref(z0));
		if (m % 2 != 0) {
			z1_z2_z0.insert(z1_z2_z0.end(), mij - 1, 0);
		}
		else {
			z1_z2_z0.insert(z1_z2_z0.end(), mij, 0);
		}
		//(z2 * 10 ^ (m2 * 2))
		if (z2.at(0) != 0) {
			if (m % 2 != 0) {
				std::fill_n(std::back_inserter(z2), 2 * (mij - 1), 0);
			}
			else {
				std::fill_n(std::back_inserter(z2), m, 0);
			}
		}
		std::vector<int> a = SequentialA::sequentialAdd(ref(z2), ref(z1_z2_z0));
		return SequentialA::sequentialAdd(std::ref(a), ref(z0));
		//return (z2 * 10 ^ (m2 * 2)) + ((z1 - z2 - z0) * 10 ^ m2) + z0
	}

	static std::thread* createThread(std::string type, int begin, int offset, int pos) {
		if (std::string("M").compare(type) == 0) {
			return std::move(new std::thread(&MyThread::myThreadMult, begin, offset, pos));
		}
		if (std::string("C").compare(type) == 0) {
			return std::move(new std::thread(&MyThread::myThreadC, begin, offset, pos));
		}
		else if (std::string("Carry").compare(type) == 0) {
			return std::move(new std::thread(&MyThread::myThreadCarry, begin, offset, pos));
		}
		return std::move(new std::thread(&MyThread::myThreadFinal, begin, offset, pos));
	}

	static void startThreads(std::vector<std::thread*> & threads, std::string threadType, int nrThreads, int nrC, int r) {
		int count = 0;

		//create and start threads
		for (size_t i = 0; i < nrThreads; i++) {
			std::thread* t;
			if (r > 0) {
				if (std::string("M").compare(threadType) != 0) {
					t = createThread(threadType, count, nrC + 1, nrThreads);
				}
				else {
					t = createThread(threadType, count, nrC + 1, i);
				}
				count += nrC + 1;
			}
			else {
				if (std::string("M").compare(threadType) != 0) {
					t = createThread(threadType, count, nrC, nrThreads);
				}
				else {
					t = createThread(threadType, count, nrC, i);
				}
				count += nrC;
			}
			threads.push_back(std::move(t));
			r--;
		}
	}

	static void joinThreads(std::vector<std::thread*> threads) {
		for (auto th : threads) {
			th->join();
		}
	}

	static void initialize(int pos) {
		Elems[pos].RESULT.resize(Elems[pos].N1.size());
		Elems[pos].C.resize(Elems[pos].N1.size());
		Elems[pos].CARRIES.resize(Elems[pos].N1.size());

	}

	static void parallelAddition(int nrThreads) {
		initialize(nrThreads);
		if (Elems[nrThreads].N1.size() < nrThreads) {
			nrThreads = Elems[nrThreads].N1.size();
		}

		int nrC = Elems[nrThreads].N1.size() / nrThreads;
		int r = Elems[nrThreads].N1.size() % nrThreads;
		std::vector<std::thread*> threads;

		startThreads(threads, "C", nrThreads, nrC, r);
		joinThreads(threads);

		threads.clear();

		startThreads(threads, "Carry", nrThreads, nrC, r);
		joinThreads(threads);

		threads.clear();

		startThreads(threads, "Final", nrThreads, nrC, r);
		joinThreads(threads);

		for (std::thread* t : threads) {
			free(t);
		}
	}


	//inmulteste primul numar pe rand cu fiecare cifra din cel de-al doilea numar
	static void bigIntegerParallelMultiplication(int nrThreads) {
		if (NR1.size() < NR2.size()) {
			NR1.swap(NR2);
		}

		if (NR2.size() < nrThreads) {
			nrThreads = NR2.size();
		}

		Elems.resize(nrThreads);

		int nrC = NR2.size() / nrThreads;
		int r = NR2.size() % nrThreads;
		std::vector<std::thread*> threads;

		startThreads(threads, "M", nrThreads, nrC, r);
		joinThreads(threads);

		for (std::thread* t : threads) {
			free(t);
		}
		/*for (Elem e : Elems) {
			for (int i : e.RESULT) {
				std::cout << i << " ";
			}
			std::cout << std::endl;
		}*/
		if (Elems.size() < 2) {
			result = Elems[0].RESULT;
		}
		else {
			Elems.resize(Elems.size() + 1);
			Elem e1 = Elem{ Elems[0].RESULT, Elems[1].RESULT };
			Elems[nrThreads] = e1;
			parallelAddition(nrThreads);
			for (int i = 2; i < Elems.size() - 1; i++) {
				Elems[nrThreads].N1 = Elems[nrThreads].RESULT;
				Elems[nrThreads].N2 = Elems[i].RESULT;
				parallelAddition(nrThreads);
			}
		}
		result = Elems[nrThreads].RESULT;
	}
};

int main() {
	std::string file1 = "a.txt", file2 = "b.txt";
	//BigIntegerGenerator::generateBigInt(1000, file1);
	//BigIntegerGenerator::generateBigInt(500, file2);
	std::vector<int> number1 = BigIntegerMultiplication::readNumber("C:\\Users\\Katy\\Documents\\A3S1\\PPD\\Laborator\\Laborator2\\Lab2C++\\Lab2C++\\a.txt");
	std::vector<int> number2 = BigIntegerMultiplication::readNumber("C:\\Users\\Katy\\Documents\\A3S1\\PPD\\Laborator\\Laborator2\\Lab2C++\\Lab2C++\\b.txt");
	NR1 = number1; NR2 = number2;
	auto begin = std::chrono::high_resolution_clock::now();

	//std::vector<int> result = BigIntegerMultiplication::karatsuba(number1, number2);
	BigIntegerMultiplication::bigIntegerParallelMultiplication(2);
	std::reverse(result.begin(), result.end());
	auto end = std::chrono::high_resolution_clock::now();
	auto duration = std::chrono::duration_cast<std::chrono::nanoseconds>(end - begin);
	for (int i : result) {
		std::cout << i;
	}
	std::cout << std::endl << "Durata " << (double)duration.count() / std::pow(10, 9) << std::endl;
}