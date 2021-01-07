'''
Created on 8 Oct 2018

@author: ASUS
'''
from Lista import *


def cauta(lista2,elem):
    if primElem(lista2)==None:
        return False
    elif primElem(lista2).e==elem:
        return True
    else:
        return cauta(sublista(lista2),elem)

def transforma(lista1,lista2):
    
    if eListaVida(lista1):
        return 
    elif cauta(lista2,primElem(lista1).e)==False:
        adaugaInceput(primElem(lista1).e, lista2) 
        return transforma(sublista(lista1), lista2)
    else:
        return transforma(sublista(lista1), lista2)