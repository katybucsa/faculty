def Pornire():
    lista=[]
    comenzi={1:[Creare_lista,"1.Creare lista"],2:[Afisare_lista,"2.Afisarea secventei de lungime maxima cu termeni consecutivi de semne contrare"],3:[Afisare_lista2,"3.Afisarea secventei de lungime maxima care are suma elementelor egala cu 5"],4:[Afisare_lista3,"4.Afisarea secventei de lungime maxima cu proprietatea ca apartin intervalului [1,10]"]   }
    while True:
        printStart(comenzi)
        cmd=Citire_numere("Introduceti comanda: ")
        if cmd==0:
            print("By!")
            return 
        if cmd in comenzi:
            comenzi[cmd][0](lista)
        else:
            print("Comanda invalida")
def printStart(comenzi):
    print("Welcome")
    print("Optiuni")
    print("0.Iesire")
    for x in (comenzi):
        print (comenzi[x][1])
def Citire_numere(text):
    while True:
        try:
            x=int(input(text))
            return x
        except ValueError:
            print("Valoare intreaga nevalida")
def Creare_lista(lista):
    n=Citire_numere("Introduceti numarul de elemente din lista: ")
    for i in range(0,n):
        lista.append(Citire_numere("Introduceti al "+str(i+1)+"-lea element: "))
def Verificare(a,b):
    '''Functia verifica daca doua elemente consecutive sunt de semne contrare.
       Date de intrare: a,b numere intregi.
       Date de iesire: True daca a si b au semne contrare si False in caz contrar
    '''
    if((a>=0 and b>=0) or (a<0 and b<0)):
        return False
    return True
def Determinare_secventa(lista):
    '''
    Functia determina cea mai lunga secventa de elemante consecutive care au semne contrare
    '''
    l1=0
    lmax=0
    p1=0
    p2=0
    pi=0
    pf=0
    rez=[]
    for i in range(0,len(lista)-1):
        if Verificare(lista[i],lista[i+1]):
            if l1==0:
                pi=i
            l1=l1+1
        else:
            pf=i
            if l1>lmax:
                lmax=l1
                p1=pi
                p2=pf
            l1=0
    if Verificare(lista[len(lista)-1],lista[len(lista)-2]):
        if l1!=0:
            pf=i+1
            if l1>lmax:
                lmax=l1
                p1=pi
                p2=pf
    if p1!=p2:
        for i in range(p1,p2+1):
            rez.append(lista[i])
    return rez
def Afisare_lista(lista):
    print(Determinare_secventa(lista))
def suma(lista):
    lmax=0
    p1=0
    p2=0
    pi=0
    pf=0
    rez1=[]
    for i in range(0, len(lista)):
        j=i
        l=0
        pi=j
        s=0
        while(s<5 and j<len(lista)):
            s=s+lista[j]
            j=j+1
            l=l+1
            pf=j-1
        if s==5:
            if l>lmax:
                lmax=l
                p1=pi
                p2=pf
    if p1!=p2:
        for i in range(p1,p2+1):
            rez1.append(lista[i])
    return rez1
def Afisare_lista2(lista):
    print(suma(lista))
def Numere_din_interval(lista):
    l=0
    lmax=0
    p1=0
    p2=0
    pi=0
    pf=0
    rez2=[]
    for i in range(0, len(lista)):
        if(lista[i]>=0 and lista[i]<=10):
            if l==0:
                pi=i
            l=l+1
        else:
            pf=i-1
            if l>lmax:
                lmax=l
                p1=pi
                p2=pf
            l=0
    if lista[len(lista)-1]>=0 and lista[len(lista)-1]<=10:
        if l!=0:
            pf=i
            if l>lmax:
                lmax=l
                p1=pi
                p2=pf
    print(p1,p2)
    if p1!=p2:
        for i in range(p1,p2+1):
            rez2.append(lista[i])
    return rez2
def Afisare_lista3(lista):
    print(Numere_din_interval(lista))
Pornire()
