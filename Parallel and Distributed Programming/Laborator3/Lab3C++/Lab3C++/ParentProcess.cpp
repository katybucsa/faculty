#include<vector>
#include<string>
#include<fstream>
#include <mpi.h>
#include "ChildProcess.cpp"

class ParentProcess {
public:
	static void firstParentSend(std::vector<int> number1, std::vector<int> number2, int np, int r, int elements_per_process, int index) {
		int n2 = number2.size();
		for (int i = 1; i < np; i++) {
			if (r > 0) {
				int nr = elements_per_process + 1;
				MPI_Send(&nr, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
				MPI_Send(&number1[index], nr, MPI_INT, i, 0, MPI_COMM_WORLD);
				if (index >= n2) {
					MPI_Send(0, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
					MPI_Send(NULL, 0, MPI_INT, i, 0, MPI_COMM_WORLD);
				}
				else if (index + nr >= n2) {
					int j = n2 - index;
					MPI_Send(&j, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
					MPI_Send(&number2[index], j, MPI_INT, i, 0, MPI_COMM_WORLD);
				}
				else {
					MPI_Send(&nr, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
					MPI_Send(&number2[index], nr, MPI_INT, i, 0, MPI_COMM_WORLD);
				}
				index += nr;
			}
			else {
				MPI_Send(&elements_per_process, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
				MPI_Send(&number1[index], elements_per_process, MPI_INT, i, 0, MPI_COMM_WORLD);
				if (index >= n2) {
					MPI_Send(0, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
					MPI_Send(NULL, 0, MPI_INT, i, 0, MPI_COMM_WORLD);
				}
				else if (index + elements_per_process >= n2) {
					int j = n2 - index;
					MPI_Send(&j, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
					MPI_Send(&number2[index], j, MPI_INT, i, 0, MPI_COMM_WORLD);
				}
				else {
					MPI_Send(&elements_per_process, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
					MPI_Send(&number2[index], elements_per_process, MPI_INT, i, 0, MPI_COMM_WORLD);
				}
				index += elements_per_process;
			}
			r--;
		}
	}

	static void collectCVector(int rest, int elements_per_process, int np, std::vector<char>& C) {
		MPI_Status status;
		int nr_elems;
		for (int i = 1; i < np; i++) {
			MPI_Recv(&nr_elems, 1, MPI_INT,
				MPI_ANY_SOURCE, 0,
				MPI_COMM_WORLD,
				&status);
			int sender = status.MPI_SOURCE;
			int idx;
			if (sender < rest) {
				idx = sender * (elements_per_process + 1);
			}
			else {
				idx = rest + sender * elements_per_process;
			}
			MPI_Recv(&C[idx], nr_elems, MPI_CHAR,
				sender, 0,
				MPI_COMM_WORLD,
				&status);
		}
	}

	static int sendCVector(int rest, int index, int elements_per_process, int n1, int np, std::vector<char> C) {
		int r = rest - 1;
		int ult;
		for (int i = 1; i < np; i++) {
			if (i == np - 1) {
				ult = n1 - index;
			}
			MPI_Send(&n1, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
			if (r > 0) {
				int nr = elements_per_process + 1;
				MPI_Send(&index, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
				index += nr;
				MPI_Send(&index, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
			}
			else {
				MPI_Send(&index, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
				index += elements_per_process;
				MPI_Send(&index, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
			}
			MPI_Send(C.data(), n1, MPI_CHAR, i, 0, MPI_COMM_WORLD);
			r--;
		}
		return ult;
	}

	static void collectResultVector(int np, int elements_per_process, int ult, int rest, std::vector<int>& RESULT) {
		int nr_elems;
		MPI_Status status;
		for (int i = 1; i < np; i++) {
			MPI_Recv(&nr_elems, 1, MPI_INT,
				MPI_ANY_SOURCE, 0,
				MPI_COMM_WORLD,
				&status);
			int sender = status.MPI_SOURCE;
			int idx;
			if (sender < rest) {
				idx = sender * (elements_per_process + 1);
			}
			else {
				idx = rest + sender * elements_per_process;
			}
			if (sender == np - 1 && nr_elems > ult) {
				RESULT.resize(RESULT.size() + 1);
			}
			MPI_Recv(&RESULT[idx], nr_elems, MPI_INT,
				sender, 0,
				MPI_COMM_WORLD,
				&status);
		}
	}

	static std::vector<int> parentProcess(std::string file1, std::string file2, int np) {
		std::vector<int> number1 = ReadFromFile::readNumber(file1);
		std::vector<int> number2 = ReadFromFile::readNumber(file2);
		if (number1.size() < number2.size()) {
			std::swap(number1, number2);
		}
		int n1 = number1.size();
		int n2 = number2.size();
		int index = 0, rest, r, begin, elements_per_process;
		rest = r = n1 % np;
		begin = index = elements_per_process = n1 / np;
		MPI_Status status;

		// check if more than 1 processes are run 
		if (np > 1) {
			if (r > 0) {
				index += 1;
				begin += 1;
				r--;
			}
			firstParentSend(number1, number2, np, r, elements_per_process, index);
		}

		// master process add its own sub array 
		std::vector<char> C(n1);
		for (int i = 0; i < begin; i++) {
			if (i < n2) {
				if (number1[i] + number2[i] > 9) {
					C[i] = 'C';
				}
				else if (number1[i] + number2[i] == 9) {
					C[i] = 'M';
				}
				else {
					C[i] = 'N';
				}
			}
			else {
				if (number1[i] == 9) {
					C[i] = 'M';
				}
				else {
					C[i] = 'N';
				}
			}
		}

		// collects C vector
		collectCVector(rest, elements_per_process, np, C);

		int ult = sendCVector(rest, begin, elements_per_process, n1, np, C);

		std::vector<int> RESULT(n1);
		int m, k = 1, s;
		RESULT[0] = (number1[0] + number2[0]) % 10;
		for (size_t i = 1; i < begin; i++) {
			if (C[i - 1] == 'C') {
				m = 1;
			}
			else if (C[i - 1] == 'N') {
				m = 0;
			}
			else {
				int x = i - 1;
				while (C[x] == 'M' && x >= 0) {
					x--;
					if (i < 0) {
						break;
					}
				}
				if (C[x] == 'C') {
					m = 1;
				}
				else {
					m = 0;
				}
			}
			if (k < n2) {
				s = (number1[k] + number2[k] + m) % 10;
			}
			else {
				s = (number1[k] + m) % 10;
			}
			RESULT[i] = s;
			if (i == n1 - 1 && C[i] == 'C') {
				RESULT.push_back(1);
			}
			k++;
		}

		collectResultVector(np, elements_per_process, ult, rest, RESULT);
		std::reverse(RESULT.begin(), RESULT.end());
		return RESULT;
	}

	static std::vector<int> optimalParentProcess(std::string file1, std::string file2, int np) {
		std::ifstream f1(file1, std::ios::binary | std::ios::ate);
		std::ifstream f2(file2, std::ios::binary | std::ios::ate);
		int n1 = f1.tellg();
		int n2 = f2.tellg();
		int index = 0, rest, r, begin, elements_per_process;
		rest = r = n1 % np;
		begin = index = elements_per_process = n1 / np;
		MPI_Status status;

		if (r > 0) {
			index += 1;
			begin += 1;
			r--;
		}

		std::vector<int> number1 = ReadFromFile::readSequenceFromFile(file1, 0, index);
		std::vector<int> number2 = ReadFromFile::readSequenceFromFile(file2, 0, index);

		for (int i = 1; i < np; i++) {
			if (r > 0) {
				int nr = elements_per_process + 1;
				MPI_Send(&index, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
				index += nr;
				MPI_Send(&index, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
			}
			else {
				MPI_Send(&index, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
				index += elements_per_process;
				MPI_Send(&index, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
			}
			r--;
		}


		std::vector<char> C(n1);
		for (int i = 0; i < begin; i++) {
			if (i < n2) {
				if (number1[i] + number2[i] > 9) {
					C[i] = 'C';
				}
				else if (number1[i] + number2[i] == 9) {
					C[i] = 'M';
				}
				else {
					C[i] = 'N';
				}
			}
			else {
				if (number1[i] == 9) {
					C[i] = 'M';
				}
				else {
					C[i] = 'N';
				}
			}
		}

		// collects C vector
		collectCVector(rest, elements_per_process, np, C);

		int ult = sendCVector(rest, begin, elements_per_process, n1, np, C);

		std::vector<int> RESULT(n1);
		int m, k = 1, s;
		RESULT[0] = (number1[0] + number2[0]) % 10;
		for (size_t i = 1; i < begin; i++) {
			if (C[i - 1] == 'C') {
				m = 1;
			}
			else if (C[i - 1] == 'N') {
				m = 0;
			}
			else {
				int x = i - 1;
				while (C[x] == 'M') {
					x--;
				}
				if (C[x] == 'C') {
					m = 1;
				}
				else {
					m = 0;
				}
			}
			if (k < n2) {
				s = (number1[k] + number2[k] + m) % 10;
			}
			else {
				s = (number1[k] + m) % 10;
			}
			RESULT[i] = s;
			if (i == n1 - 1 && C[i] == 'C') {
				RESULT.push_back(1);
			}
			k++;
		}

		collectResultVector(np, elements_per_process, ult, rest, RESULT);
		std::reverse(RESULT.begin(), RESULT.end());
		return RESULT;
	}
};