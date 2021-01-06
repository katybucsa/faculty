
from Domain.Disciplina import Disciplina, Prof
from Validare.Validator import ValidatorDisciplina


class DisciplinaService:
    '''
    Clasa responsabila pentru a manevra utilizarea operatiilor CRUD pe calsa Discipline
    '''
    def __init__(self,repo):
        '''
        repo - DisciplinaRepository
        '''
        self.__repo=repo 
        self.__validator=ValidatorDisciplina()
        
    def size(self):
        '''
        Returneaza numarul disciplinelor - int
        '''
        return len(self.__repo)
        
    def adaugaDisciplina(self,idDisciplina,nume,profesor):
        '''
        Creeaza, valideaza si adauga o disciplina
        idDisciplina - int, id-ul disciplinei 
        nume, profesor - string,numele disciplinei si numele profesorului 
        '''
        disciplina=Disciplina(idDisciplina,nume,profesor)
        self.__validator.valideazaDisciplina(disciplina)
        self.__repo.adaugaD(disciplina)
        
    def modificaDisciplina(self,idDisciplina,nume,profesor):
        '''
        Creeaza, valideaza si modifica disciplina cu id-ul introdus
        idDisciplina - int, id-ul disciplinei 
        nume, profesor - string,numele disciplinei si numele profesorului 
        Returneaza noua disciplina
        '''
        disciplina=Disciplina(idDisciplina,nume,profesor)
        self.__validator.valideazaDisciplina(disciplina)
        self.__repo.modificaD(disciplina)
        return disciplina
        
    def stergeDisciplina(self,idDisciplina):
        '''
        Creeaza, valideaza si sterge o disciplina cu id-ul introdus
        idDisciplina - int
        REturneaza disciplina care a fost stearsa
        '''
        disciplina=Disciplina(idDisciplina,'DEFAULT','DEFAULTa')
        self.__validator.valideazaDisciplina(disciplina)
        self.__repo.stergeD(disciplina)
        return disciplina
        
        
    def gasesteDisciplina(self,idDisciplina):
        '''
        Cauta o disciplina in lista disciplinelor
        idDisciplina - int, id disciplina
        Returneaza disiplina care are id-ul dat daca exista
        '''
        disciplina=Disciplina(idDisciplina,'DEFAULT','DEFAULT')
        self.__validator.valideazaDisciplina(disciplina)
        disc=self.__repo.getD(disciplina)
        return disc
        
    def cautaDisciplina(self,criteriu,lista=[],i=0):
        '''
        Cauta discipline avand criteriu numele disciplinei
        criteriu - string
        returneaza o lista de discipline unde numele contine criteriu  
        '''
        al=self.__repo.getAllD()
        if i==0:
            lista=[]
        if i<len(al):
            if criteriu in al[i].getNumeD():
                lista.append(al[i])
            i+=1
            return self.cautaDisciplina(criteriu, lista, i)
        return lista
    
    
    
    def cautaProfesor(self):
        '''
        Cauta in lista disciplinelor numele tuturor profesorilor 
        Returneaza o lista in care fiecare element contine numele unui profesor si suma id-urilor disciplinelor pe care le preda
        '''
        al=self.__repo.getAllD()
        lista=[]
        l=[]
        for dis in al:
            if dis.getProfesor() not in lista:
                lista.append(dis.getProfesor())
        for pr in lista:
            s=0
            for dis in al:
                if dis.getProfesor()==pr:
                    s+=dis.getIdDisciplina()
            p=Prof(pr,s)
            l.append(p)
        return l
                    
                    
    def getAllDiscipline(self):
        '''
        Returneaza o lista cu toate disciplinele
        '''
        return self.__repo.getAllD()
        