

#include <iostream>
#include <vector>
#include <fstream>
using namespace std;

#define INF 0x3f3f3f3f

ifstream in("mat.txt");
ifstream f("bf");

struct{
    int p;
    int dist;
}v[50];

int dr[1000];

void initializare(int n,int s){

    for(int i=0;i<n;i++)
    {
        v[i].dist=INF;
        v[i].p=-1;
    }
    v[s].dist=0;
}



int extract_min(vector<int> q,int n){
    int m=v[q[0]].dist;
    int poz=0;
    for(int i=0;i<n;i++)
        if(v[q[i]].dist<m)
            {
                m=v[q[i]].dist;
                poz=i;
            }
    return poz;
}

void relax(int a,int b,int pondere){
    if(v[b].dist>v[a].dist+pondere)
    {
        v[b].dist=v[a].dist+pondere;
        v[b].p=a;
    }
}


void Dijkstra(int mat[][100], int s, int n){
    initializare(n,s);
    int i,poz,u;
    vector <int> q;
    for(i=0;i<n;i++)
        q.push_back(i);
    while(q.size()){
        poz=extract_min(q,q.size());
        u=q[poz];
        q.erase(q.begin()+poz);
        for(i=0;i<n;i++)
            if(mat[u][i]!=INF)
                relax(u,i,mat[u][i]);
    }
}




bool BELLMAN_FORD(int mat[][100],int s, int n){
    initializare(n,s);
    int i,u,j;
    for(i=0;i<n-1;i++)
        for(u=0;u<n;u++)
            for(j=0;j<n;j++)
                if(mat[u][j]!=INF)
                    relax(u,j,mat[u][j]);
    for(u=0;u<n;u++)
        for(i=0;i<n;i++)
            if(mat[u][i]!=INF)
                if(v[i].dist > v[u].dist+mat[u][i])
                    return false;
    return true;
}


void JOHNSON(int mat[][100],int n){
    int h[50],s=n+1;
    for(int i=1;i<=s;i++){
        mat[s][i]=0;
        mat[i][s]=INF;
    }
    if(BELLMAN_FORD(mat,s,s)==false)
        cout<<"Graful contine ciclu negativ"<<endl;
    else{
        for(int i=1;i<=s;i++)
            h[i]=v[i].dist;
        for(int i=1;i<=s;i++)
            for(int j=1;j<=s;j++)
                if(mat[i][j]!=INF)
                    mat[i][j]=mat[i][j]+h[i]-h[j];
        for(int i=1;i<=n;i++){
            Dijkstra(mat,i,n);
            for(int j=1;j<=n;j++)
                cout<<v[j].dist+h[j]-h[i]<<"  ";
            cout<<endl;
        }
    }
}

void drum(int dest,int &k) {
	k = 0;
	dr[k] = dest;
	k++;
	while (v[dest].p!=-1) {
        dest=v[dest].p;
		dr[k] =dest;
		k++;
	}
}

int main()
{
    int sursa,x,y,z,mat[100][100],n,bf[100][100],m;
    cout<<"Introduceti nodul:";
    cin>>sursa;
    in>>n;
    for(int i=0;i<100;i++)
        for(int j=0;j<100;j++)
            mat[i][j]=bf[i][j]=INF;

    while(in>>x>>y>>z){
        mat[x][y]=z;

    }
     in.close();


    f>>m;
    while(f>>x>>y>>z)
        bf[x][y]=z;
    f.close();


    cout<<endl<<"=====================DIJKSTRA========================="<<endl<<endl;
    Dijkstra(mat,sursa,n);
    for(int i=1;i<=n;i++)
        cout<<v[i].dist<<"   ";



    cout<<"\n\n"<<"=========================BELLMAN-FORD====================="<<"\n\n";
    if(BELLMAN_FORD(mat,sursa,m)==false)
        cout<<"Graful contine cicluri negative";
    else
        {for(int i=0;i<m;i++)
            cout<<v[i].dist<<"   ";
            cout<<endl;

    for (int i = 0; i <m; i++)
			if (v[i].dist != INF) {
                int k=0;
				drum(i,k);
				for (int j = k-1; j >=0; j--)
					cout << dr[j] << " ";
				cout << endl;
			}
			else
				cout << "Nu exista drum intre " << sursa << " si " << i << endl;

        }
    cout<<"\n\n"<<"=========================JOHNSON====================="<<"\n\n";
    JOHNSON(mat,m);
    return 0;
}
