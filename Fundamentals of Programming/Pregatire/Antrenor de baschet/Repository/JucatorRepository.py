from Validator.Validare import ValideazaJucator
from Domain.Jucator import Jucator
from random import choice,randint

class RepositoryException(Exception):
    pass


class JucatorRepository:
    
    def __init__(self,numefis):
        self.__jucatori=[]
        self.__numefis=numefis 
        self.__valid=ValideazaJucator()
        self.__citesteJucatoriDinFisier()
        self.__scrieJucatoriInFisier()
        
    
    def __citesteJucatoriDinFisier(self):
        with open(self.__numefis,'r') as f:
            al=[]
            for line in f.readlines():
                line=line.strip()
                if len(line):
                    cuvinte=line.split(',')
                    jucator=Jucator(cuvinte[0],cuvinte[1],float(cuvinte[2]),cuvinte[3])
                    self.__valid.valideaza(jucator)
                    al.append(jucator)
            self.__jucatori=al[:]
            
    def __scrieJucatoriInFisier(self):
        with open(self.__numefis,'w') as f:
            for jucator in self.__jucatori:
                line=jucator.get_nume()+','+jucator.get_prenume()+','+str(jucator.get_inaltime())+','+jucator.get_post()
                f.write(line+'\n')
                
                    
    def add(self,jucator):
        self.__citesteJucatoriDinFisier()
        self.__valid.valideaza(jucator)
        if jucator in self.__jucatori:
            raise RepositoryException('Jucator existent!\n')
        self.__jucatori.append(jucator)
        self.__scrieJucatoriInFisier()
        
    def update(self,jucator):
        self.__citesteJucatoriDinFisier()
        self.__valid.valideaza(jucator)
        if jucator not in self.__jucatori:
            raise RepositoryException('Jucator inexistent!\n')
        i=self.__jucatori.index(jucator)
        self.__jucatori[i].set_inaltime(jucator.get_inaltime())
        self.__scrieJucatoriInFisier()
        
    def importa(self,fisier):
        self.__citesteJucatoriDinFisier()
        nr=0
        with open(fisier,'r') as f:
            for line in f.readlines():
                line=line.strip()
                if len(line):
                    cuvinte=line.split(' ')
                    jucator=Jucator(cuvinte[0],cuvinte[1],randint(130,240),choice(['fundas','pivot','extrema']))
                    self.__valid.valideaza(jucator)
                    if jucator not in self.__jucatori:
                        self.__jucatori.append(jucator)
                        nr+=1
        self.__scrieJucatoriInFisier()
        return nr
        
        
    def getAll(self):
        self.__citesteJucatoriDinFisier()
        return self.__jucatori
        