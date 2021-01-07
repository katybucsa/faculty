//Algoritmul lui Prim - graf neorientat

#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#define oo 9999

using namespace std;

ifstream in("prim.txt");

struct{
    int key;
    int p;
}nod[10000];

int a[10000][10000],dr[10000];

void citire(int &n){
    in>>n;
    int x,y,z;
    for(x=1;x<=n;x++)
        for(y=1;y<=n;y++)
            a[x][y]=oo;
    while(in>>x>>y>>z)
        a[x][y]=a[y][x]=z;
}

void afisare(int &n){
    int i,j;
    for(i=1;i<=n;i++){
        for(j=1;j<=n;j++)
            cout<<a[i][j]<<" ";
        cout<<endl;
    }
    cout<<endl<<endl;
}


int extract_min(vector<int>&q){
    int m=nod[q[0]].key;
    int nd=q[0],poz=0;
    for(int i=1;i<q.size();i++){
        if(nod[q[i]].key<m){
            m=nod[q[i]].key;
            nd=q[i];
            poz=i;
        }
    }
    q[poz]=q[q.size()-1];
    q.pop_back();
    return nd;
}

void Prim(int r,int n){
    int i,u,v;
    for(i=1;i<=n;i++){
        nod[i].key=oo;
        nod[i].p=0;
    }
    nod[r].key=0;
    vector<int> q;
    for(i=1;i<=n;i++)
        q.push_back(i);
    while(!q.empty()){
        u=extract_min(q);
        for(v=1;v<=n;v++)
            if(a[u][v]!=oo)
                if(find(q.begin(),q.end(),v)!=q.end() && a[u][v]<nod[v].key){
                    nod[v].p=u;
                    nod[v].key=a[u][v];
                }
    }
}


int main()
{
    int n,r,cost=0,i,k;
    citire(n);
    afisare(n);
    cout<<"Introduceti nodul: ";
    cin>>r;
    Prim(r,n);
    for(i=1;i<=n;i++)
        cost+=nod[i].key;
    for(i=1;i<=n;i++){
        if(nod[i].p!=0)
            cout<<i<<" "<<nod[i].p<<endl;
    }

    cout<<endl<<cost<<endl;
    return 0;
}
