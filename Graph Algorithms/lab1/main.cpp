#include <iostream>
#include <fstream>

using namespace std;



int main()
{

    ifstream citire("in.txt"),in("ponderi.txt");
    int n,mat[101][101],i,x,y,j,m=1,matinc[101][101],suma,suma1,ok=1,k=0,matpond[101][101],c,matdist[101][101],v[101],q[101],el,cp,ok1=1;
    int a = 10000;

    cout<<"1."<<endl<<endl;
    //citire muchii din fisier si formarea matricei de adiacenta
    citire>>n;
    for(i=1;i<=n;i++)
        for(j=1;j<=n;j++)
            mat[i][j]=0;
    while(citire>>x>>y)
        mat[x][y]=mat[y][x]=1;

    citire.close();




    // afisare matrice de adiacenta

    cout<<"Matricea de adiacenta:"<<endl<<endl;
    for(i=1;i<=n;i++)
    {
        for(j=1;j<=n;j++)
            cout<<"    "<<mat[i][j];
        cout<<endl;
    }





    //determina daca un graf este conex sau nu
    for(i=1;i<=n;i++)
        v[i]=0;
    c=0;
    q[1]=1;
    c++;
    v[1]=1;
    while(c){
        el=q[c];
        c--;
        for(i=1;i<=n;i++)
            if(mat[el][i]==1)
                if(v[i]==0)
                {
                    v[i]=1;
                    c++;
                    q[c]=i;
                }
    }
    cp=n;




    // construire si afisare matrice de incidenta
    cout<<endl;
    for(i=1;i<=n;i++)
        for(j=i+1;j<=n;j++)
            if(mat[i][j]==1)
            {
                matinc[i][m]=matinc[j][m]=1;
                m++;
            }
    m--;
    cout<<"Matricea de incidenta:"<<endl<<endl;
    for(i=1;i<=n;i++)
    {
        for(j=1;j<=m;j++)
            cout<<"    "<<matinc[i][j];
        cout<<endl;
    }




    //determinarea nodurilor izolate si verificare daca graful este regular
    cout<<endl<<endl<<"2.a) ";
    for(i=1;i<=n;i++)
    {
        suma=0;
        for(j=1;j<=n;j++)
            if(mat[i][j]==1)
                suma+=1;
        if(i!=1 && suma1!=suma)
            ok=0;
        if(suma==0)
        {
            k++;
            if(k==1)
                cout<<"Noduri izolate:";
            cout<<i<<" ";
        }

        suma1=suma;
    }




    //afisare mesaj corespunzator pentru graf regular sau nu
    cout<<endl<<endl<<"2.b) ";
    if(k==0)
        cout<<"Nu exista noduri izolate"<<endl<<endl;
    if(ok==1)
        cout<<"Graful este regular"<<endl<<endl;
    else
        cout<<"Graful nu este regular"<<endl<<endl;




    //formarea si afisarea matricei ponderilor
    cout<<endl<<"2.c) "<<"Matricea ponderilor:"<<endl<<endl;
    in>>n;
    for(i=1;i<=n;i++)
        for(j=1;j<=n;j++)
            matpond[i][j]=a;
    while(in>>x>>y>>c)
        matpond[x][y]=matpond[y][x]=c;
    in.close();
    for(i=1;i<=n;i++)
        matpond[i][i]=0;

    for(i=1;i<=n;i++)
    {
        for(j=1;j<=n;j++)
            if(matpond[i][j]==a)
                cout<<" inf ";
            else
                cout<<"     "<<matpond[i][j];
        cout<<endl;
    }
    cout<<endl;

     //afiseaza un mesaj corespunzator referitor la conexitatea unui graf
     cout<<endl<<"2.d) ";
    for(i=1;i<=cp;i++)
        if(v[i]==0)
        {
            cout<<"Graful nu este conex";
            ok1=0;
            break;
        }
    if(ok1)
        cout<<"Graful este conex";
    cout<<endl;

    //determinarea si afisarea matricei distantelor
    cout<<endl<<"2.e) "<<"Matricea distantelor:"<<endl<<endl;
    for(i=1;i<=n;i++)
        for(j=1;j<=n;j++)
            matdist[i][j]=matpond[i][j];

    for(k=1;k<=n;k++)
        for(i=1;i<=n;i++)
            for(j=1;j<=n;j++)
                if(matdist[i][k]+matdist[k][j]<matdist[i][j] && matdist[i][j])
                    matdist[i][j]=matdist[i][k]+matdist[k][j];

    for(i=1;i<=n;i++){
        for(j=1;j<=n;j++)
            cout<<"    "<<matdist[i][j];
        cout<<endl;
    }
    cout<<endl;
    return 0;
}
