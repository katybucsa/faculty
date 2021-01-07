#include <string>
#include <vector>
#include <thread>
#include <chrono>
#include <algorithm>
#include "BigIntegerGenerator.cpp"
#include "MyThread.cpp"

class BigIntegerAddition {
public:
	static std::string readNumber(std::string fileName) {
		std::string number = "";
		std::ifstream in(fileName);
		if (in.is_open()) {
			std::string line;
			while (std::getline(in, line)) {
				number += line;
			}
		}
		else {
			std::cout << "File could not be opened!";
		}
		return number;
	}

	static std::vector<long> parseString(std::string operand) {
		std::vector<std::string> list;
		for (int i = 0; operand.length() > 10; i++) {
			list.push_back(operand.substr(operand.length() - 18));
			operand = operand.substr(0, operand.length() - 18);
		}
		list.push_back(operand);
		std::reverse(list.begin(), list.end());
		std::vector<long> tokens(list.size());
		for (int j = 0; j < tokens.size(); j++) {
			tokens.at(j) = std::stol(list.at(j));
		}
		return tokens;
	}

	static std::string convertToString(std::vector<long> arr) {
		std::string res = "";
		for (auto e : arr) {
			res += std::to_string(e);
		}
		return res;
	}

	static std::string sequentialAddition(std::string number1, std::string number2) {
		std::vector<long> tokens_N1 = parseString(number1);
		std::vector<long> tokens_N2 = parseString(number2);
		if (tokens_N1.size() < tokens_N2.size()) {
			tokens_N1.swap(tokens_N2);
		}
		int length_N1 = tokens_N1.size();
		int length_N2 = tokens_N2.size();
		std::vector<long> result(tokens_N1.size(), 0);

		for (int i = length_N1 - 1, j = length_N2 - 1; i != -1; i--, j--) {
			if (j < 0) {
				result.at(i) = result.at(i) + tokens_N1.at(i);
			}
			else {
				result.at(i) = result.at(i) + tokens_N1.at(i) + tokens_N2.at(j);
			}
			if (i != 0 && std::to_string(result.at(i)).size() > 18) {
				result.at(i - 1) = 1L;
				result.at(i) = result.at(i) % 1000000000000000000L;
			}
		}
		return convertToString(result);
	}

	static void sequential(std::string number1, std::string number2) {
		std::cout << "Secvential" << std::endl;
		std::reverse(number1.begin(), number1.end());
		std::reverse(number2.begin(), number2.end());
		auto begin = std::chrono::system_clock::now();
		std::cout << sequentialAddition(number1, number2) << std::endl;
		auto end = std::chrono::system_clock::now();
		std::time_t begin_time = std::chrono::system_clock::to_time_t(begin);
		std::time_t end_time = std::chrono::system_clock::to_time_t(end);
		std::cout << "Inceput " << begin_time << " Sfarsit " << end_time << std::endl;
		std::cout << "Durata " << std::chrono::duration<double>(end - begin).count() << std::endl;

	}

	static std::vector<int> getNumberByDigits(std::string number) {
		std::vector<int> result;
		for (char c : number) {
			result.push_back((int)(c - '0'));
		}
		return result;
	}

	static void initialize() {
		if (N1.size() < N2.size()) {
			N1.swap(N2);
		}
		RESULT.resize(N1.size());
		CARRIES.resize(N1.size());
		C.resize(N1.size());
		PREFIXES.resize(N1.size());
	}

	static std::thread* createThread(std::string type, int begin, int offset) {
		if (std::string("C").compare(type) == 0) {
			return std::move(new std::thread(&MyThread::myThreadC, begin, offset));
		}
		else if (std::string("Carry").compare(type) == 0) {
			return std::move(new std::thread(&MyThread::myThreadCarry, begin, offset));
		}
		else if (std::string("Carry2").compare(type) == 0) {
			return std::move(new std::thread(&MyThread::myThreadCarry2, begin, offset));
		}
		else if (std::string("Prefix").compare(type) == 0) {
			return std::move(new std::thread(&MyThread::myThreadPrefix, begin, offset));
		}
		return std::move(new std::thread(&MyThread::myThreadFinal, begin, offset));
	}

	static void startThreads(std::vector<std::thread*> & threads, std::string threadType, int nrThreads, int nrC, int r) {
		int count = 0;

		//create and start threads
		for (size_t i = 0; i < nrThreads; i++) {
			std::thread* t;
			if (r > 0) {
				t = createThread(threadType, count, nrC + 1);
				count += nrC + 1;
			}
			else {
				t = createThread(threadType, count, nrC);
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

	static void sequentiallyAddCarries() {
		for (size_t i = 1; i < N1.size(); i++) {
			int s = RESULT.at(i) + CARRIES.at(i - 1);
			if (s == 10) {
				RESULT.at(i) = 0;
				CARRIES.at(i) = 1;
			}
			else {
				RESULT.at(i) = s;
			}
		}
		if (CARRIES.at(N1.size() - 1) == 1) {
			RESULT.insert(RESULT.end(), 1);
		}
	}


	static void parallelAdditionV1(int nrTh) {
		initialize();

		if (N1.size() < nrTh) {
			nrTh = N1.size();
		}

		int nrC = N1.size() / nrTh;
		int r = N1.size() % nrTh;
		std::string threadType = "Carry";
		std::vector<std::thread*> threads;

		startThreads(threads, threadType, nrTh, nrC, r);
		joinThreads(std::move(threads));
		sequentiallyAddCarries();
	}

	static void parallelV1() {
		std::cout << "Paralel varianta 1\n";
		auto beginV1 = std::chrono::system_clock::now();
		parallelAdditionV1(5);
		auto endV1 = std::chrono::system_clock::now();
		std::time_t begin_time = std::chrono::system_clock::to_time_t(beginV1);
		std::time_t end_time = std::chrono::system_clock::to_time_t(endV1);
		std::cout << "Inceput " << begin_time << " Sfarsit " << end_time << std::endl;
		std::cout << "Durata " << std::chrono::duration<double>(endV1 - beginV1).count() << std::endl;
		std::reverse(RESULT.begin(), RESULT.end());
		for (int i : RESULT) {
			std::cout << i;
		}
		std::cout << std::endl;
	}


	static void parallelAdditionV2(int nrThreads) {
		initialize();
		if (N1.size() < nrThreads) {
			nrThreads = N1.size();
		}

		int nrC = N1.size() / nrThreads;
		int r = N1.size() % nrThreads;
		std::vector<std::thread*> threads;

		startThreads(threads, "C", nrThreads, nrC, r);
		joinThreads(threads);

		threads.clear();

		startThreads(threads, "Prefix", nrThreads, nrC, r);
		joinThreads(threads);

		threads.clear();

		startThreads(threads, "Carry2", nrThreads, nrC, r);
		joinThreads(threads);

		threads.clear();

		startThreads(threads, "Final", nrThreads, nrC, r);
		joinThreads(threads);
	}


	static void parallelV2() {
		std::cout << "Paralel varianta 2\n";
		auto beginV2 = std::chrono::high_resolution_clock::now();
		parallelAdditionV2(5);
		auto endV2 = std::chrono::high_resolution_clock::now();
		//std::time_t begin_time = std::chrono::steady_clock::to_time_t(beginV2);
		//std::time_t end_time = std::chrono::steady_clock::to_time_t(endV2);
		auto duration = std::chrono::duration_cast<std::chrono::milliseconds>(endV2 - beginV2);
		//std::cout << "Inceput " << beginV2.cou << " Sfarsit " << endV2 << std::endl;
		std::cout << "Durata " << std::chrono::duration<double>(endV2 - beginV2).count() << std::endl;
		std::reverse(RESULT.begin(), RESULT.end());

		std::cout << std::endl;
		for (int i : RESULT) {
			std::cout << i;
		}
		std::cout << std::endl;
	}
};


void main() {
	std::string file1 = "a.txt", file2 = "b.txt";
	BigIntegerGenertor::generateBigInt(100, file1);
	BigIntegerGenertor::generateBigInt(100, file2);
	std::string number1 = BigIntegerAddition::readNumber(file1);
	std::string number2 = BigIntegerAddition::readNumber(file2);
	//std::string number1 = "97531";
	//std::string number2 = "32186";
	//number1 = "9998";
	//number2 = "1112";
	N1 = BigIntegerAddition::getNumberByDigits(number1);
	N2 = BigIntegerAddition::getNumberByDigits(number2);


	//sequentially
	if (VARIANT.compare("S") == 0) {
		BigIntegerAddition::sequential(number1, number2);
	}

	//parallel V1
	if (VARIANT.compare("V1") == 0) {
		BigIntegerAddition::parallelV1();
	}

	//parallel V1
	if (VARIANT.compare("V2") == 0) {
		BigIntegerAddition::parallelV2();
	}
}