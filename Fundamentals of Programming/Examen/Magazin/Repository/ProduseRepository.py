from Domain.Produs import Produs
from cgi import escape


class RepositoryException(Exception):
    pass

class ProduseRepository:
    '''
    Clasa raspunzatoare cu citirea si salvarea datelor in fisier
    numefis-numele fisierului din care se face citirea, respectiv salvarea datelor
    '''
    def __init__(self,numefis):
        self.__produse=[]
        self.__undo=[]
        self.__numefis=numefis
        self.__citesteProduseDinFisier()
        self.__scrieProduseInFisier()
        
        
        
    def __citesteProduseDinFisier(self):
        '''
        citeste din fisier datele, le incarca in memorie si actualizeaza lista de produse
        '''
        with open(self.__numefis,'r') as f:
            al=[]
            for line in f.readlines():
                line=line.strip()
                if len(line):
                    cuvinte=line.split(',')
                    produs=Produs(cuvinte[0],cuvinte[1],float(cuvinte[2]))
                    if produs not in al:
                        al.append(produs)
        self.__produse[:]=al[:]
        
    
    def __scrieProduseInFisier(self):
        '''
        salveaza datele din memorie in fisier
        '''
        with open(self.__numefis,'w') as f:
            for produs in self.__produse:
                line=produs.get_idp()+','+produs.get_denumire()+','+str(produs.get_pret())
                f.write(line+'\n')
                
                
    def adauga(self,produs):
        '''
        produs- o instanta a clasei Produs
        raise RepositoryException daca produsul exista deja in fisier, altfel il adauga
        '''
        
        self.__citesteProduseDinFisier()
        if produs in self.__produse:
            raise RepositoryException('Produs existent!\n')
        self.__produse.append(produs)
        self.__scrieProduseInFisier()
        
    def sterge(self,produs):
        '''
        produs- instanta a clasei Produs
        sterge produs din fisier
        '''
        
        self.__citesteProduseDinFisier()
        self.__produse.remove(produs)
        self.__scrieProduseInFisier()
        
    def saveLista(self):
        '''
        inainte de fiecare stergere, salveaza lista produselor pentru a putea fi reactualizata la undo
        '''
        self.__citesteProduseDinFisier()
        self.__undo.append(self.__produse[:])
        
    def undoLista(self):
        '''
        reface ultima operatie de stergere
        '''
        self.__produse[:]=self.__undo[-1][:]
        self.__scrieProduseInFisier()
        
    def getAll(self):
        '''
        returneaza o lista cu toate produsele din fisier
        '''
        self.__citesteProduseDinFisier()
        return self.__produse
    
    def size(self):
        self.__citesteProduseDinFisier()
        return len(self.__produse[:])





