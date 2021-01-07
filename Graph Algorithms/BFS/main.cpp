#include <iostream>
#include <fstream>

using namespace std;

ifstream in("bfs.txt");


void  citire(int &n,int a[][50]){
    int x,y;
    in>>n;
    while(in>>x>>y)
        a[x][y]=a[y][x]=1;
}

void afisare(int n, int a[][50]){
    for(int i=1;i<=n;i++)
    {
        for(int j=1;j<=n;j++)
            cout<<a[i][j]<<" ";
        cout<<endl;
    }
    cout<<endl;
}


void parcurgere_latime(int ns,int n,int a[][50],int v[50] ){
    for(int i=1;i<=n;i++)
        v[i]=0;
    int c[50],prim,ultim;
    v[ns]=1;
    prim=ultim=1;
    c[ultim]=ns;
    while(prim<=ultim){
        for(int i=1;i<=n;i++)
            if(a[c[prim]][i]==1)
                if(v[i]==0)
                {
                    ultim++;
                    c[ultim]=i;
                    v[i]=1;
                }
        prim++;
    }
}

void nod_vizitat(int v[50],int n){
    int i;
    for(i=1;i<=n;i++)
        if(v[i]==0)
            break;
    if(i<=n)
        cout<<"Graful nu e conex!"<<endl;
    else
        cout<<"Graful e conex!"<<endl;

}

int main()
{
    int n,i,a[50][50],x,y,v[50];
    citire(n,a);
    afisare(n,a);
    parcurgere_latime(1,n,a,v);
    nod_vizitat(v,n);

    return 0;
}
