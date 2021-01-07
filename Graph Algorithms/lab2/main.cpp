#include <iostream>
#include <fstream>
#include <limits>
#include <cstring>
#include <malloc.h>
#include <queue>
#include <iomanip>


using namespace std;

ifstream in("graf.txt"), f("garf1.txt"),l1("labirint.txt");
int imax = std::numeric_limits<int>::max();

void moore(int mat[][100],int n,int u,int l[],int p[]){
    l[u]=0;
    int v;
    std::queue<int>coada;
    coada.push(u);
    for(v=1;v<=n;v++)
        if(v!=u)
            l[v]=imax;
    for(v=1;v<=n;v++)
        p[v]=0;
    while(!coada.empty()){
        u=coada.front();
        coada.pop();
        for(v=1;v<=n;v++)
            if(mat[u][v]==1)
                if(l[v]==imax && p[v] == 0){
                    p[v]=u;
                    l[v]=l[u]+1;
                    coada.push(v);
                }
    }f
}


void moore_drum(int l[],int p[],int v,int drum[]){
    int k;
    k=l[v];
    drum[k]=v;
    while(k){
        drum[k-1]=p[drum[k]];
        k--;
    }
}




int main()
{
    int n,x,y,mat[100][100],u,l[100],p[100],drum[100],v,mat1[100][100],a[100][100];

    //algoritmul lui Moore

    in>>n;
    while(in>>x>>y)
        mat[x][y]=1;
    in.close();
    cout<<"Introduceti nodul:";
    cin>>u;
    cout<<endl<<endl;
    moore(mat,n,u,l,p);
    cout<<"Lungimile drumurilor:"<<endl;
    for(v=1;v<=n;v++)
        if(l[v]!=imax)
            cout<<l[v]<<"  ";
        else
            cout<<"inf";
    cout<<endl<<endl;
    cout<<"Parintele nodului:"<<endl;
    for(v=1;v<=n;v++)
        cout<<p[v]<<"  ";
    cout<<endl<<endl;
    for(v=1;v<=n;v++)
        if(l[v]!=imax){

            moore_drum(l,p,v,drum);
            for(int i=0;i<=l[v];i++)
                cout<<drum[i]<<"  ";
            cout<<endl;
        }
        else
            cout<<"Nu exista drum intre "<<u<<" si "<<v<<endl;
        cout<<endl<<endl<<endl;




    //inchidere tranzitiva intr-un graf orientat

    f>>n;
    while(f>>x>>y)
        mat1[x][y]=1;
    f.close();
    /*for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++)
            cout<<mat1[i][j]<<" ";
        cout<<endl;
    }
    cout<<endl;*/
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            moore(mat,n,i,l,p);
            if(l[j]!=imax){
                mat[i][j]=1;
            }
        }

    }
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            cout<<setw(4)<<mat[i][j]<<" ";
        }
        cout<<endl;
    }
    return 0;
}




