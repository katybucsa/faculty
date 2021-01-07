'''
Created on 8 Oct 2018

@author: ASUS
'''
from Lista import *
from P2 import cauta

def substituie(lista,i,elem):
    
    if eListaVida(lista):
        return 
    elif i==1:
        primElem(lista).e=elem
    else:
        return substituie(sublista(lista), i-1, elem)
    
    
def diferenta(lista1,lista2,lista3):
    
    if eListaVida(lista1):
        return 
    elif cauta(lista2, primElem(lista1).e)==False:
        adaugaInceput(primElem(lista1).e, lista3)
        return diferenta(sublista(lista1), lista2, lista3)
    else:
        diferenta(sublista(lista1), lista2, lista3)
        
        
        