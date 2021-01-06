def verificare_data(ziua,luna,anul):
    '''
    Functia verifica daca data adaugata este valida.
    Primeste ca parametrii variabilele ziua, luna, anul, variabile de tip intreg si returneaza True daca aceste date sunt valide si False in caz contrar.
    '''
    if ziua<1 or ziua>31 or luna<1 or luna>12 or anul<1 or anul>2017:
        return False
    '''Data introdusa nu este corecta'''
    if (luna==4 or luna==6 or luna==9 or luna==11) and ziua>30:
        return False
    '''Lunile aprilie,iunie,septembrie,noiembrie nu au mai mult de 30 de zile'''
    if luna==2 and anul%4!=0 and ziua>28:
        return False
    '''Luna februarie are doar 28 de zile cand anul nu este bisect'''
    if luna==2 and anul%4==0 and ziua>29:
        return False
    '''Luna februarie are 29 de zile cand anuul este bisect'''
    return True
    
    

    
def verificare_suma(suma):
    '''
    Functia verifica daca suma introdusa este valida.
    Primeste ca parametru variabila suma de tip intreg si returneaza True daca valoarea atribuita acestei variabile este valida si False in caz contrar.
    '''
    if suma<1:
        return False
    '''Suma introdusa nu poate fi nula sau un numar negativ'''
    return True
    
    

    
def verificare_tip(tipul):
    '''
    Functia verifica daca tipul tranzactiei este valid.
    Primeste ca parametru variabila tipul si returneaza True daca se afla in lista celor precizate si False in caz contrar.
    '''
    if tipul!='intrare' and tipul!='iesire':
        return False
    '''Tipul tranzactiei este invalid'''
    return True



def introducere_data():
    '''
    Functia citeste variabilele intregi ziua,luna,anul, verifica validitatea lor si in caz afirmativ le returneaza.
    '''
    while True:
        try:
            ziua=int(input('Introduceti ziua:'))
            luna=int(input('Introduceti luna:'))
            anul=int(input('Introduceti anul:'))
            if verificare_data(ziua,luna,anul):
                return ziua,luna,anul
            else:
                print('Data introdusa pentru tranzactie este invalida')
        except ValueError:
            print('Introduceti valori intregi pentru data')



def introducere_suma():
    '''
    Functia citeste variabile suma de tip intreg, ii verifica validitatea si in caz afirmativ o returneaza.
    '''
    while True:
        try:
            suma=int(input('Introduceti suma:'))
            if verificare_suma(suma):
                return suma
            else:
                print('Suma introdusa pentru tranzactie este invalida')
        except ValueError:
            print('Introduceti valori intregi pentru suma')



def introducere_tip():
    '''
    Functia citeste variabila tipul, ii verifica validitatea si in caz afirmativ o returneaza
    '''
    while True:
        tipul=input('Introduceti tipul:')
        if verificare_tip(tipul):
            return tipul
        else:
            print('Tipul tranzactiei este invalid')
    




def adauga_tranzactie_noua(cont):
    '''
    Functia primeste ca parametru lista cont si o actualizeaza, adaugand o noua tranzactie.
    Variabile utilizate:z,l,a-variabile de tip intreg.
    '''
    x=[]
    z,l,a=introducere_data()
    x.append(z)
    x.append(l)
    x.append(a)
    x.append(introducere_suma())
    x.append(introducere_tip())
    cont.append(x)
    print('Tranzactia dumneavoastra a fost adaugata cu succes')



    
def actualizare_tranzactie(cont):
    '''
    Functia primeste ca parametru lista cont si actualizeaza o tranzactie pentru care se da numarul de ordine.
    Variabile utilizate:n,z,l,a-variabile de tip intreg.
    '''
    while True:
        try:
            n=intr_cmd('Introduceti numarul de ordine al tranzactiei pe care doriti sa o actualizati:')
            if n<0 or n>len(cont)-1:
                raise ValueError
            z,l,a=introducere_data()
            cont[n][0]=z
            cont[n][1]=l
            cont[n][2]=a
            cont[n][3]=introducere_suma()
            cont[n][4]=introducere_tip()
            print('Tranzactia dumneavoastra a fost actualizata cu succes')
            return
        except ValueError:
            print('Tranzactia pe care doriti sa o actualizati nu exista')



            
def adaugare_tranzactie(cont):
    '''
    Functia tipareste interfata cu utilizatorul pentru meniul comenzii 1.
    '''
    while True:
        print('Selectati tranzactia:')
        print('a pentru a adauga o tranzactie')
        print('b pentru a actualiza o tranzactie')
        print('0 pentru a reveni la meniul principal')
        i=input()
        if i=='a':
            adauga_tranzactie_noua(cont)
        elif i=='b':
            actualizare_tranzactie(cont)
        elif i=='0':
            return
        else:
            print('Comanda invalda')
            



def sterge_tranz_ziua(cont):
    ziua,luna,anul=introducere_data()
    i=0
    while i<len(cont):
        if(cont[i][0]==ziua and cont[i][1]==luna and cont[i][2]==anul):
            cont.pop(i)
        else:
            i=i+1
    print(cont)



def sterge_tranz_perioada(cont):
    while True:
        print('Introduceti data de inceput')
        ziua_i,luna_i,anul_i=introducere_data()
        print('Introduceti data de sfarsit')
        ziua_s,luna_s,anul_s=introducere_data()
        for i in range (0,len(cont)):
            if cont[i][2]>anul_i and cont[i][2]<anul_s:
                cont.pop(i)
                i=i-1
            elif cont[i][2]==anul_i:
                if cont[i][1]>luna_i:
                    cont.pop(i)
                    i=i-1
                elif cont[i][1]==luna_i:
                    if cont[i][0]>ziua_i:
                        cont.pop(i)
                        i=i-1
            elif cont[i][2]==anul_s:
                if cont[i][1]<luna_s:
                    cont.pop(i)
                    i=i-1
                elif cont[i][1]==luna_s:
                    if cont[i][0]<ziua_s:
                        cont.pop(i)
                        i=i-1
        print(cont)
        return




def sterge_tranz_tip(cont):
    tipul=introducere_tip()
    i=0
    while i<len(cont):
        if cont[i][4]==tipul:
            cont.pop(i)
        else:
            i=i+1
    print(cont)



    
def stergere_tranzactii(cont):
    while True:
        print('Selectati tipul de stergere:')
        print('a pentru a sterge toate tranzacțiile de la ziua specificată')
        print('b pentru a sterge tranzacțiile dintr-o perioadă dată (se dă ziua de început și sfârșit)')
        print('c pentru a sterge toate tranzacțiile de un anumit tip')
        print('0 pentru a reveni la meniul principal')
        i=input()
        if i=='a':
            sterge_tranz_ziua(cont)
        elif i=='b':
            sterge_tranz_perioada(cont)
        elif i=='c':
            sterge_tranz_tip(cont)
        elif i=='0':
            return
        else:
            print('Comanda invalida')



def tipareste_tranz_suma(cont):
    '''
    Functia primeste ca parametru lista cont si afiseaza pe ecran tranzactiile cu proprietatea de la comanda 3.a.
    Variabile utilizate:suma,i-variabile de tip intreg.
    '''
    suma=introducere_suma()
    for i in range(0,len(cont)):
        if(cont[i][3]>suma):
            print(cont[i])





def tipareste_tranz_zi_suma(cont):
    '''
    Functia primeste ca parametru lista cont si afiseaza pe ecran tranzactiile cu proprietatea de la comanda 3.b.
    Variabile utilizate:suma,ziua,luna,anul,i-variabile de tip intreg.
    '''
    ziua,luna,anul=introducere_data()
    suma=introducere_suma()
    for i in range(0,len(cont)):
        if cont[i][3]>suma:
            if cont[i][2]<anul:
                print(cont[i])
            elif cont[i][2]==anul:
                if cont[i][1]<luna:
                    print(cont[i])
                elif cont[i][1]==luna:
                    if cont[i][0]<ziua:
                        print(cont[i])






def tipareste_tranz_tip(cont):
    '''
    Functia primeste ca parametru lista cont si afiseaza pe ecran tranzactiile cu proprietatea de la comanda 3.c.
    Variabile utilizate:i-variabila de tip intreg,tip-variabila de tip sir de caractere.
    '''
    tip=introducere_tip()
    for i in range(0,len(cont)):
        if cont[i][4]==tip:
            print(cont[i])
    



def cautari(cont):
    '''
    Functia tipareste interfata cu utilizatorul corespunzatoare comenzii 3.
    '''
    while True:
        print('a pentru a tipări tranzacțiile cu sume mai mari decât o sumă dată')
        print('b pentru a tipări  toate tranzacțiile efectuate înainte de o zi și mai mari decât o sumă (se dă suma și ziua)')
        print('c pentru a tipări tranzacțiile de un anumit tip')
        print('0 pentru a reveni la meniul principal')
        i=input()
        if i=='a':
            tipareste_tranz_suma(cont)
        elif i=='b':
            tipareste_tranz_zi_suma(cont)
        elif i=='c':
            tipareste_tranz_tip(cont)
        elif i=='0':
            return
        else:
            print('Comanda invalida')



            
def intr_cmd(text):
    '''
    Functia verifica daca comanda introdusa este de tip intreg, returnand-o in acest caz
    '''
    while True:
        try:
            x=int(input(text))
            return x
        except ValueError:
            print('Valoare intreaga invalida')



            
def printMenu(comenzi):
    '''
    Functia tipareste meniul principal pentru aplicatoa Cont bancar.
    '''
    print('Bine ati venit')
    print('Optiuni')
    print('0.Iesire')
    for i in (comenzi):
       print(comenzi[i][1])



       
def run():
    '''
    Functia implementeaza interfata cu utilizatorul.
    '''
    cont=[]
    comenzi={1:[adaugare_tranzactie,"1.Adăugare de noi tranzacții"], 2:[stergere_tranzactii,'2.Stergere'], 3:[cautari,'3.Cautari']}
    while True:
        printMenu(comenzi)
        cmd=intr_cmd('Introduceti comanda:')
        if cmd==0:
            print('Multumim ca ati apelat la serviciile noastre')
            return
        if cmd in comenzi:
            comenzi[cmd][0](cont)
        else:
            print('Comanda invalida')

              
#functii de test




def test_verificare_data():
    '''
    Functia de test pentru functia verificare_data
    '''
    assert verificare_data(31,12,2004)==True
    assert verificare_data(31,11,1994)==False
    assert verificare_data(2,13,2000)==False
    assert verificare_data(29,2,2016)==True
    assert verificare_data(-1,0,0000)==False
    assert verificare_data(1,9,2001)==True



def test_verificare_suma():
    '''
    Functia de test pentru functia verificare_suma
    '''
    assert verificare_suma(0)==False
    assert verificare_suma(2000)==True
    assert verificare_suma(-111)==False
    assert verificare_suma(-0)==False
    assert verificare_suma(99999999)==True
    assert verificare_suma(-1.1)==False
    assert verificare_suma(55.643)==True




def test_verificare_tip():
    '''
    Functia de test pentru functia verificare_tip
    '''
    assert verificare_tip('intrare')==True
    assert verificare_tip('itnrare')==False
    assert verificare_tip('43748')==False
    assert verificare_tip('iesire')==True
    assert verificare_tip(';[;]')==False


#apelarea functii de test

test_verificare_data()
test_verificare_suma()
test_verificare_tip()



run()     
