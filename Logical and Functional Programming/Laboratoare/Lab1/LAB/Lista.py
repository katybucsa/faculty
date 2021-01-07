class Nod:
    def __init__(self, e):
        self.e = e
        self.urm = None
    
class Lista:
    def __init__(self):
        self.prim = None
        

'''
crearea unei liste din valori citite pana la 0
'''
def creareLista():
    lista = Lista()
    lista.prim = creareLista_rec()
    return lista

def creareLista_rec():
    x = int(input("x="))
    if x == 0:
        return None
    else:
        nod = Nod(x)
        nod.urm = creareLista_rec()
        return nod
    
    
def eListaVida(lista):
    return lista.prim==None 


def primElem(lista):
    return lista.prim.e

def sublista(lista):
    l=Lista()
    l.prim=lista.prim.urm
    return l


def adaugaInceput(elem,lista):
    nod=Nod(elem)
    nod.urm=lista.prim
    lista.prim=nod
    return lista

def tipar(lista):
    tipar_rec(lista.prim)
    
def tipar_rec(nod):
    if nod != None:
        print (nod.e)
        tipar_rec(nod.urm)
        
def inverseaza(lista1,lista2):
    
    if eListaVida(lista1):
        return lista2
    return inverseaza(sublista(lista1),adaugaInceput(primElem(lista1), lista2))
    
    
def maxim(lista,maxx,i):
    
    if eListaVida(lista)==False and i==0:
        return maxim(sublista(lista),primElem(lista),i+1)
    elif eListaVida(lista) and i==0:
        return -999999999
    elif eListaVida(lista) and i!=0:
        return maxx
    elif primElem(lista)>maxx:
        return maxim(sublista(lista),primElem(lista),i+1)
    else:
        return maxim(sublista(lista),maxx,i)
    
         
def main():
    '''
    4.a)
    '''
    lista = creareLista()
    i=0
    maxx=-999999999 
    rez=maxim(lista,maxx,i)
    if rez!=maxx:
        print("Elementul maxim din lista este: ",rez,'\n')
    else:
        print("Lista este vida!\n\n\n")
        
    
    '''
    4.b)
    '''
    lista1=Lista()
    lista2=inverseaza(lista, lista1)
    tipar(lista2)
    
main() 
