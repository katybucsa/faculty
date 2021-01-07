'''
Created on 8 Oct 2018

@author: ASUS
'''
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
    return lista.prim

def sublista(lista):
    l=Lista()
    l.prim=lista.prim.urm
    return l


def adaugaInceput(elem,lista):
    nod=Nod(elem)
    nod.urm=lista.prim
    lista.prim=nod
    
    
'''
tiparirea elementelor unei liste
'''
def tipar(lista):
    tipar_rec(lista.prim)
    
def tipar_rec(nod):
    if nod != None:
        print (nod.e)
        tipar_rec(nod.urm)
        
