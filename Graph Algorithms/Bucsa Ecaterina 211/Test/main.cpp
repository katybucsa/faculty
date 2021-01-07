#include <iostream>
#include <fstream>
#include <vector>

#define INF 99999
using namespace std;

struct {
	int parinte;
	int dist;
}Nod[10000];


ifstream in("graf.txt");
ofstream out("out.txt");

int a[10000][10000], mat[10000][10000],h[10000];
int dr[10000];
int k = 1;

void citire(int&n, int&m) {
	in >> n >> m;
	int x, y, z;
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= n; j++)
			a[i][j] = INF;
	for (int i = 1; i <= m; i++) {
		in >> x >> y >> z;
		a[x][y] = z;
	}
}



void initializare(int s, int n) {
	int i;
	for (i = 1; i <= n; i++) {
		Nod[i].dist = INF;
		Nod[i].parinte = 0;
	}
	Nod[s].dist = 0;
}


int extract_min(vector<int> &v) {
	int m = Nod[v[0]].dist;
	int nd = v[0], poz = 0;
	for(int i=0;i<v.size();i++)
		if (Nod[v[i]].dist < m) {
			m = Nod[i].dist;
			poz = i;
			nd = v[i];
		}
	v[poz] = v[v.size() - 1];
	v.pop_back();
	return nd;
}

void relax(int a, int b, int pondere) {
	if (Nod[b].dist > Nod[a].dist + pondere) {
		Nod[b].dist = Nod[a].dist + pondere;
		Nod[b].parinte = a;
	}
}


void Dijkstra(int s, int n) {
	initializare(s, n);
	vector<int> vector;
	for (int i = 1; i <= n; i++)
		vector.push_back(i);
	while (!vector.empty()) {
		int u = extract_min(vector);
		for (int v = 1; v <= n; v++)
			if (a[u][v] != INF) {
				relax(u, v, a[u][v]);
			}
	}
}


bool Bellman_Ford(int s, int n) {
	int i, j, k;
	initializare(s, n);
	for (k = 1; k <= n; k++)
		for (i = 1; i <= n; i++)
			for (j = 1; j <= n; j++)
				if (a[i][j] != INF)
					relax(i, j, a[i][j]);
	for (i = 1; i <= n; i++)
		for (j = 1; j <= n; j++)
			if (a[i][j] != INF)
				if (Nod[j].dist > Nod[i].dist + a[i][j])
					return false;
	return true;
}



void drum(int dest,int s) {
	dr[1] = dest;
	k++;
	while (Nod[dest].parinte != s) {
		dest = Nod[dest].parinte;
		dr[k] = dest;
		k++;
	}
	dr[k] = s;
}

void Johnson(int n) {
	int s = n + 1,i,j;
	for (i = 1; i <= n; i++)
		for (j = 1; j <= n; j++)
			mat[i][j] = a[i][j];
	for (i = 1; i <= s; i++) {
		mat[s][i] = 0;
		mat[i][s] = INF;
	}
	
	if (Bellman_Ford(s, s) == false)
		cout << "Graful contine ciclu negativ!\n";
	else {
		for (i = 1; i <= s; i++)
			h[i] = Nod[i].dist;
		for (i = 1; i <= s; i++)
			for (j = 1; j <= s; j++)
				if (mat[i][j] != INF)
					mat[i][j] += h[i] - h[j];
		for (i = 1; i <= n; i++) {
			Dijkstra(i, n);
			for (j = 1; j <= n; j++) 
				if (Nod[j].dist != INF && i!=j) {
					k = 1;
					drum(j, i);
					for (int m = k; m >= 1; m--)
						out << dr[m] << "  ";
					out << endl;
				}
						
				//cout << Nod[j].dist + h[j] - h[i] << "  ";
		}
	}
}


int main() {
	int n, m, sursa, dest;
	citire(n, m);
	cout << "Introduceti sursa: ";
	cin >> sursa;
	cout << endl;
	cout << "Introduceti destinatia: ";
	cin >> dest;
	//Bellman_Ford(sursa, n);
	/*for (int i = 1; i <= n; i++)
		cout << Nod[i].parinte << "  ";
	cout << endl;*/
	/*drum(dest,sursa);
	for(int i=k;i>=1;i--)
		cout << dr[i] << " ";*/
	Johnson(n);
	return 0;
}