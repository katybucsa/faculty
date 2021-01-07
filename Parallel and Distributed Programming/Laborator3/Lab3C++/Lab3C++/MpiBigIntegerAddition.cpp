#include <mpi.h>
#include <string>
#include <fstream>
#include <iostream>
#include <vector>
#include <chrono>
#include "BigIntegerGenerator.cpp"
#include "ParentProcess.cpp"



int main(int argc, char* argv[])
{
	std::string file1 = "C:\\Users\\Katy\\Documents\\A3S1\\PPD\\Laborator\\Laborator3\\Lab3C++\\Lab3C++\\a.txt"
		, file2 = "C:\\Users\\Katy\\Documents\\A3S1\\PPD\\Laborator\\Laborator3\\Lab3C++\\Lab3C++\\b.txt";
	int n1 = 6, n2 = 6, pid, np;
	//BigIntegerGenerator::generateBigInt(n1, file1);
	//BigIntegerGenerator::generateBigInt(n2, file2);

	std::chrono::nanoseconds duration;
	std::chrono::steady_clock::time_point begin;
	begin = std::chrono::high_resolution_clock::now();
	std::vector<int> RESULT;

	// Creation of parallel processes 
	MPI_Init(&argc, &argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &pid);
	MPI_Comm_size(MPI_COMM_WORLD, &np);

	// master process 
	if (pid == 0) {

		RESULT = ParentProcess::parentProcess(file1, file2, np);
		//RESULT = ParentProcess::optimalParentProcess(file1, file2, np);
	}
	// slave processes 
	else {
		ChildProcess::childProcess();
		//ChildProcess::childProcessFile(file1, file2);
	}

	// cleans up all MPI state before exit of process 
	MPI_Finalize();
	if (pid == 0) {
		auto end = std::chrono::high_resolution_clock::now();
		duration = std::chrono::duration_cast<std::chrono::nanoseconds>(end - begin);
		std::cout << std::endl << "Durata " << (double)duration.count() / std::pow(10, 9) << std::endl;
	}
	for (int i : RESULT) {
		std::cout << i << " ";
	}
	return 0;
}