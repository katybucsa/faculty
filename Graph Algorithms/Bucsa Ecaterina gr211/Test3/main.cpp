/*Kruskal- gasirea arborelui minim de acoperire pentru un graf 
	Algoritmul sorteaza crescator muchiile dupa pondere dupa care parcurge muchiile sortate si daca varfurile acesteia nu fac parte din acelasi set il adauga in arborele minim de acoperire
	Generelizare: declararea unei matrici de dimensiuni mari 
	Complexitate: O(E^2)
	Aplicabilitate: arborele minim de acoperire construit pentru un curier care trebuie sa livreze pachete in diferite puncte ale orasului
*/


#include <iostream>
#include <fstream>
using namespace std;

ifstream in("kruskal.txt");

int k = 0;
struct {
	int stare;
	int parinte;
	int dist;
}nod[1000];

struct {
	int stanga;
	int dreapta;
	int pondere;
}muchii[10000];

struct {
	int stanga;
	int dreapta;
}A[10000];


void citire(int& n, int&m) {
	in >> n;
	int x, y, z;
	m = 0;
	while (in >> x >> y >> z) {
		muchii[m].stanga = x;
		muchii[m].dreapta = y;
		muchii[m].pondere = z;
		m++;
	}
}


void afisare(int&m) {
	for (int i = 0; i < m; i++)
		cout << muchii[i].stanga << " " << muchii[i].dreapta << " " << muchii[i].pondere << endl;
	cout << endl;
}


void make_set(int i) {
	nod[i].parinte = i;
	nod[i].dist = 1;
	nod[i].stare = 0;
}


int find_set(int i) {
	while (i != nod[i].parinte)
		i = nod[i].parinte;
	return i;
}


void sorteaza(int m) {
	for (int i = 0; i < m - 1; i++)
		for (int j = i + 1; j < m; j++)
			if (muchii[i].pondere > muchii[j].pondere) {
				swap(muchii[i].dreapta, muchii[j].dreapta);
				swap(muchii[i].stanga, muchii[j].stanga);
				swap(muchii[i].pondere, muchii[j].pondere);
			}
}

void make_union(int i, int j) {
	int p = find_set(i);
	int l = find_set(j);
	if (p == l)
		return;
	if (nod[p].stare > nod[l].stare)
		swap(p, l);
	nod[l].parinte = p;
	nod[p].dist += nod[l].dist;
	if (nod[p].stare == nod[l].stare)
		nod[p].stare += 1;
}


void kruskal(int n, int m) {
	int i;
	for (i = 1; i <= n; i++)
		make_set(i);
	sorteaza(m);
	for(i=0;i<m;i++)
		if (find_set(muchii[i].dreapta) != find_set(muchii[i].stanga)) {
			A[k].stanga = muchii[i].stanga;
			A[k].dreapta = muchii[i].dreapta;
			k++;
			make_union(muchii[i].stanga, muchii[i].dreapta);
		}
}



int main() {
	int n, m;
	citire(n, m);
	//afisare(m);
	kruskal(n, m);
	for (int i = 0; i < k; i++)
		cout << A[i].stanga << " " << A[i].dreapta << endl;
	return 0;
}