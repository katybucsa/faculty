#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

ifstream in("graf.txt");
ofstream out("gr.txt");


void initializare(int n,int m, vector<vector<int> > a){
    for(int i=0;i<n;i++)
        for(int j=0;j<m;j++)
            a[i][j]=0;

}

void citire(int &n, int &m, vector<vector<int> > a){
    int x,y,z;
    in>>n>>m;
    for(int i=0;i<m;i++){
        in>>x>>y>>z;
        a[x][i]=a[y][i]=1;

    }
    in.close();
}

void transformare(int n, int m,vector<vector<int> > a,vector<vector<int> > b){
    int nr=0,k,p,j;

    for(j=0;j<m;j++)
    {
        int l=1;
        nr=0;
        while(nr<2){
            if(a[l][j]==1){
                nr++;
                if(nr==1)
                    k=l;
                else
                    p=l;
            }
        l++;
        }
        b[k][p]=b[p][k]=1;
    }
}


void afisare(int n, int m, vector<vector<int> > a){
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++)
            out<<a[i][j]<<" ";
        out<<endl;
    }
    out<<endl;
}
int main()
{
    vector<vector<int> > a(5000,vector<int>(5000)), b(5000,vector<int>(5000));
    int n,m,L;
    initializare(5000,5000,a);
    citire(n,m,a);
    afisare(n,m,a);
    initializare(5000,5000,b);
    transformare(n,m,a,b);
    //afisare(n,n,b);
    return 0;
}
