#include <stdio.h>
#include <stdlib.h>

main(int argc, char** argv)
{
	int cmd;
	int go = 1;
	while (go == 1) {
		printf("\n0. Exit\n");
		printf("1. Pentru a incarca fisierul a.txt\n");
		printf("2. Pentru a incarca fisierul b.txt\n");
		printf("3. Pentru a incarca fisierul c.txt\n\n");
		printf("Introduceti comada: ");
		scanf("%d", &cmd);
		int correct = 1;
		if (cmd == 0) {
			return 0;
		}
		else {
			if (cmd == 1) {
				system("file1.bat");
			}
			else if (cmd == 2) {
				system("file2.bat");
			}
			else if (cmd == 3) {
				system("file3.bat");
			}
			else {
				printf("Comanda gresita\n");
				correct = 0;
			}
		}
	}
}