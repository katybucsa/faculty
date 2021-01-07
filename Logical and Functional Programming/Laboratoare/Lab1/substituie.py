

def citeste():
    elem=int(input())
    lista=creareLista()
    return elem,lista 
        

def substituie(lista,elem,lista1,newL):
    if lista.prim==elem and newL.prim==None:
        return substituie(lista.prim.urm,elem,lista1,lista1.prim)
    else:
        return()
        '''x=lista.prim.urm;
        lista.prim=lista1.prim
        y=lista1.prim
        while y.urm!=None:
            y=y.urm
        y.urm=x;'''
        
        
def elemPozN(prim,n):
    if n==0:
        return prim.e
    return elemPozN(prim.urm, n-1)    

'''
program pentru test
'''
        

    
    
    
    
    