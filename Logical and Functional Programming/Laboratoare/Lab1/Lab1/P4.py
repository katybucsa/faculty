'''
Created on 8 Oct 2018

@author: ASUS
'''

from Lista import *

def invers(lista1,lista2):
    
    if eListaVida(lista1):
        return
    else:
        adaugaInceput(primElem(lista1).e,lista2)
        return invers(sublista(lista1),lista2)
    

def maxim(lista,max,i):
    
    if eListaVida(lista) and i==0:
        return
    elif (eListaVida(lista) and i!=0):
        return max
    elif  primElem(lista).e>max:
        return maxim(sublista(lista),primElem(lista).e,i+1)
    else:
        return maxim(sublista(lista),max,i)
  
    
    