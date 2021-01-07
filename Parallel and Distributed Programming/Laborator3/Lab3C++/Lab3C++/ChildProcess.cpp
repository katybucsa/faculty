#include <vector>
#include <mpi.h>
#include <string>
#include <fstream>
#include "ReadFromFile.cpp"


class ChildProcess {
public:

	static void createCVector(std::vector<int> N1, std::vector<int> N2, std::vector<char>& c) {
		for (int i = 0; i < N1.size(); i++) {
			if (i < N2.size()) {
				if (N1[i] + N2[i] > 9) {
					c[i] = 'C';
				}
				else if (N1[i] + N2[i] == 9) {
					c[i] = 'M';
				}
				else {
					c[i] = 'N';
				}
			}
			else {
				if (N1[i] == 9) {
					c[i] = 'M';
				}
				else {
					c[i] = 'N';
				}
			}
		}
	}

	static void createResultVector(int C_begin, int C_end, std::vector<char> C, std::vector<int> N1, std::vector<int> N2, std::vector<int>& result) {
		int m, j = 0, s;

		for (size_t i = C_begin; i < C_end; i++) {
			if (C[i - 1] == 'C') {
				m = 1;
			}
			else if (C[i - 1] == 'N') {
				m = 0;
			}
			else {
				int x = i - 1;
				while (x >= 0 && C[x] == 'M') {
					x--;
				}
				if (C[x] == 'C') {
					m = 1;
				}
				else {
					m = 0;
				}
			}
			if (j < N2.size()) {
				s = (N1[j] + N2[j] + m) % 10;
			}
			else {
				s = (N1[j] + m) % 10;
			}
			result.push_back(s);
			if (i == C.size() - 1) {
				if (C[C.size() - 1] == 'C') {
					result.push_back(1);
				}
				else if (C[C.size() - 1] == 'M') {
					int x = C[C.size() - 1];
					while (x >= 0 && C[x] == 'M') {
						x--;
					}
					if (C[x] == 'C') {
						result.push_back(1);
					}
				}
			}
			j++;
		}
	}

	static void childProcess() {
		//create c vector-------------------------------------
		MPI_Status status;
		int nr1, nr2;
		MPI_Recv(&nr1, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
		std::vector<int> N1(nr1);

		MPI_Recv(N1.data(), nr1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);

		MPI_Recv(&nr2, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
		std::vector<int> N2(nr2);

		MPI_Recv(N2.data(), nr2, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);

		std::vector<char> c(nr1);
		createCVector(N1, N2, c);

		// sends the partial sum to the root process 
		MPI_Send(&nr1, 1, MPI_INT,
			0, 0, MPI_COMM_WORLD);
		MPI_Send(c.data(), nr1, MPI_CHAR,
			0, 0, MPI_COMM_WORLD);


		// create result vector from c vector--------------------------------
		int C_size, C_begin, C_end;
		MPI_Recv(&C_size, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
		MPI_Recv(&C_begin, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
		MPI_Recv(&C_end, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
		std::vector<char> C(C_size);
		MPI_Recv(C.data(), C_size, MPI_CHAR, 0, 0, MPI_COMM_WORLD, &status);

		std::vector<int> result;
		createResultVector(C_begin, C_end, C, N1, N2, result);
		nr1 = result.size();
		MPI_Send(&nr1, 1, MPI_INT,
			0, 0, MPI_COMM_WORLD);
		MPI_Send(result.data(), nr1, MPI_INT,
			0, 0, MPI_COMM_WORLD);
	}

	static void childProcessFile(std::string file1, std::string file2) {
		MPI_Status status;
		int start, end, nr1;
		MPI_Recv(&start, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
		MPI_Recv(&end, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);

		std::vector<int> N1 = ReadFromFile::readSequenceFromFile(file1, start, end);
		std::vector<int> N2 = ReadFromFile::readSequenceFromFile(file2, start, end);

		nr1 = N1.size();
		std::vector<char> c(nr1);
		createCVector(N1, N2, c);

		// sends the partial sum to the root process 
		MPI_Send(&nr1, 1, MPI_INT,
			0, 0, MPI_COMM_WORLD);
		MPI_Send(c.data(), nr1, MPI_CHAR,
			0, 0, MPI_COMM_WORLD);


		// create result vector from c vector--------------------------------
		int C_size, C_begin, C_end;
		MPI_Recv(&C_size, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
		MPI_Recv(&C_begin, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
		MPI_Recv(&C_end, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
		std::vector<char> C(C_size);
		MPI_Recv(C.data(), C_size, MPI_CHAR, 0, 0, MPI_COMM_WORLD, &status);

		std::vector<int> result;
		createResultVector(C_begin, C_end, C, N1, N2, result);
		nr1 = result.size();
		MPI_Send(&nr1, 1, MPI_INT,
			0, 0, MPI_COMM_WORLD);
		MPI_Send(result.data(), nr1, MPI_INT,
			0, 0, MPI_COMM_WORLD);
	}
};