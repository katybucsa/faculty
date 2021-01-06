
from Domain.Nota import Nota, NotaStudent, Medie,MedieDisc
from Repository.Repository import StudentRepository
from Validare.Validator import ValidatorNota, ValidatorStudent
from Sort.Algorithm import Algorithm
from Sort.Sorting import Sorting

class NotaService:
    def __init__(self,nrepo):
        '''
        Initializare
        nrepo - NotaRepository
        '''
        self.__nrepo = nrepo
        self.__nvalid = ValidatorNota()
        self.__svalid=ValidatorStudent()
        self.__strepo = StudentRepository()
        
    def size(self):
        '''
        Returneaza numarul notelor asignate - int
        '''
        return len(self.__nrepo)
        
    
    def asignareNota(self,st,disc,nota):
        '''
        Asigneaza o nota pentru un student la o disciplina data
        st - student 
        disc - disciplina
        nota - float, nota
        '''
        
        n=Nota(st,disc,nota)
        self.__nvalid.valideazaNota(n)
        self.__nrepo.adaugaN(n)
        
    
    def modificaStNota(self,st):
        '''
        Creeaza si modifica nota pentru studentul precizat
        st - student
        '''
        al=self.__nrepo.getAllGr()
        for n in al:
            if n.getStud()==st:
                nota=Nota(st, n.getDisc(), n.getNota())
                self.__nrepo.modificaNota(nota)
    
    def modificaDiscNota(self,disc):
        '''
        Creeaza si modifica notele pentru disciplina precizata
        disc - disciplina
        '''
        al=self.__nrepo.getAllGr()
        for n in al:
            if n.getDisc()==disc:
                nota=Nota(n.getStud(),disc,n.getNota())
                self.__nrepo.modificaNota(nota)
                
        
    def stergeStNota(self,st):
        '''
        Creeaza si sterge nota pentru studentul precizat
        st - student
        '''
        al=self.__nrepo.getAllGr()
        for n in al:
            if n.getStud()==st:
                nota=Nota(st, n.getDisc(), n.getNota())
                self.__nrepo.stergeNota(nota)  
                
    def stergeDiscNota(self,disc):
        '''
        Creeaza si sterge nota pentru diciplina precizata
        disc - disciplina
        ''' 
        al=self.__nrepo.getAllGr()
        for n in al:
            if n.getDisc()==disc:
                nota=Nota(n.getStud(),disc,n.getNota())
                self.__nrepo.stergeNota(nota)
        
    def getNoteOrd(self,disc,k):
        '''
        Returneaza o lista cu numele studentilor si notele lor la o disciplina ordonata alfabetic dupa nume sau nota
        disc - string, diciplina
        k - int, daca are valoarea 1 atunci lista va fi ordonata crescator dupa nume studenti, altfel va fi ordonata ascendent dupa nota
        '''
        l=[]
        al=self.__nrepo.getAllGr()
        for n in al:
            if n.getDisc().getNumeD()==disc:
                el=NotaStudent(n.getStud().getNumeSt(),n.getNota())
                l.append(el)
        if k:
            l1=Sorting.sort(l,key=lambda x: x.get_stnume(),reverse=False)
        else:
            l1=Sorting.sort(l, key=lambda x: x.get_nota(), reverse=False) 
        return l1  
    
    def topStud(self): 
        '''
        Returneaza o lista cu primii 20% din studenti ordonata descrescator dupa media la toate disciplinele
        '''
        lista=[]
        l=[]
        al=self.__nrepo.getAllGr()
        for n in al:
            if n.getStud() not in lista:
                lista.append(n.getStud())
        for st in lista:
            s=0
            nr=0
            for n in al:
                if n.getStud()==st:
                    s+=n.getNota()
                    nr+=1
            medie=float(s/nr)
            el=Medie(st.getNumeSt(),medie)
            l.append(el)
        l1=Sorting.sort(l , key=lambda x:x.get_medie(), reverse=True)
        if (20*len(l1))//100==0:
            return l1[:1]
        else:
            return l1[:((20*len(l1))//100)]
    
    
    def getMediaClasei(self):
        '''
        Returneaza o lista cu media clasei la fiecare disciplina, ordonata descrescator dupa medie
        '''
        lista=[]
        l=[]
        al=self.__nrepo.getAllGr()
        for n in al:
            if n.getDisc() not in lista:
                lista.append(n.getDisc())
        for disc in lista:
            s=0
            nr=0
            for n in al:
                if n.getDisc()==disc:
                    s+=n.getNota()
                    nr+=1
            medie=float(s/nr)
            m=MedieDisc(disc.getNumeD(),medie)
            l.append(m)
        l1=Sorting.sort(l , key=lambda x:x.getMedia(), reverse=True)
        return l1
                
        
    def getAllGrades(self):
        '''
        Returneaza o lista cu toate notele asignate
        '''
        return self.__nrepo.getAllGr()  
        
        
        
        
        
        
        
        
