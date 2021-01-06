from Validare.Validator import Validator
from Domain.Student import Student


class RepositoryException(Exception):
        pass


class StudentRepository:
    def __init__(self,numefis):
        '''
        numefis- numele fisierului in care se memoreaza studentii
        '''
        self.__studenti=[]
        self.__numefis=numefis
        self.__valid=Validator()
        self.__citesteTotDinFisier()
        
        
    def __citesteTotDinFisier(self):
        '''
        citeste toti studentii din fisier
        '''
        with open(self.__numefis,'r') as f:
            al=[]
            for line in f.readlines():
                line=line.strip()
                if len(line):
                    cuvinte=line.split(',')
                    st=Student(cuvinte[0],cuvinte[1],int(cuvinte[2]),float(cuvinte[3]))
                    self.__valid.valideaza(st)
                    al.append(st)
            self.__studenti=al[:]
            
    def __scrieTotInFisier(self):
        '''
        scrie toti studentii in fisier
        '''
        with open(self.__numefis,'w') as f:
            for st in self.__studenti:
                line=st.get_nr_matricol()+','+st.get_nume()+','+str(st.get_nr_prezente())+','+str(st.get_nota())
                f.write(line+'\n')
                    
                    
    def add(self,st):
        '''
        adauga un student in fisier
        
        '''
        self.__citesteTotDinFisier()
        if st in self.__studenti:
            raise RepositoryException('Student existent!\n')
        self.__studenti.append(st)
        self.__scrieTotInFisier()
        
    def modifica(self):
        self.__scrieTotInFisier()
        
        
    def getAll(self):
        '''
        returneaza o lista cu toti studentii
        '''
        self.__citesteTotDinFisier()
        return self.__studenti[:]