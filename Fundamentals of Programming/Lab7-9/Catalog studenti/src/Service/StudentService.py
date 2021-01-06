from Domain.Student import Student
from Validare.Validator import ValidatorStudent
from Sort.Algorithm import Algorithm
from Sort.Sorting import Sorting


class StudentService:
    '''
    Clasa responsabila pentru a manevra utilizarea operatiilor CRUD pe clasa Studenti
    '''
    
    def __init__(self,reposit):
        '''
        reposit - StudentRepository
        '''
        self.__reposit=reposit
        self.__validator=ValidatorStudent()
        
        
    def size(self):
        '''
        Returneaza numarul studentilor - int
        '''
        return len(self.__reposit)
        
    def adaugaStudent(self,idstud,nume,varsta):
        '''
        Creeaza, valideaza si adauga un student
        idstud - int, id-ul studentului
        nume - string,numele studentului
        varsta-float,media studentului
        '''
        student=Student(idstud,nume,varsta)
        self.__validator.valideazaStudent(student)
        self.__reposit.adaugaSt(student)
        
    def modificaStudent(self,idstud,nume,varsta):
        '''
        Creeaza, valideaza si modifica studentul cu id-ul introdus
        idstud - int, id-ul studentului
        nume - string,numele studentului
        varsta-float,media studentului
        Returneaza noul student
        '''
        student=Student(idstud,nume,varsta)
        self.__validator.valideazaStudent(student)
        self.__reposit.modificaSt(student)
        return student
        
    def stergeStudent(self,idstud):
        '''
        Creeaza, valideaza si sterge un student cu id-ul introdus
        idstud - int
        Returneaza studentul care a fost sters
        '''
        student=Student(idstud,'DEFAULT',17)
        self.__validator.valideazaStudent(student)
        self.__reposit.stergeSt(student)
        return student
        
    def gasesteStudent(self,idstud):
        '''
        Cauta un student in lista studentilor
        idstud - int, id student
        Returneaza studentul care are id-ul introdus daca exista
        '''
        student=Student(idstud,'DEFAULT',17)
        self.__validator.valideazaStudent(student)
        st=self.__reposit.getSt(student)
        return st
        
        
    def cautaStudent(self,criteriu,lista=[],i=0):
        '''
        Cauta studenti avand criteriu numele studentului
        criteriu - string
        returneaza o lista de studenti unde numele contine criteriu  
        '''
        al=self.__reposit.getAllSt()
        if i==0:
            lista=[]
        if i<len(al):
            if criteriu in al[i].getNumeSt():
                lista.append(al[i])
            i+=1
            return self.cautaStudent(criteriu,lista,i)
        return lista
        
    def getOrdonatVarsta(self):
        '''
        Returneaza o lista de studenti sortati descrescator dupa varsta
        '''
        al=self.__reposit.getAllSt()
        al1=Sorting.sort(al, key=lambda x:x.getVarsta(),reverse=True)
        return al1
    
    def getAllWithName(self,nume):
        '''
        Returneaza o lista de studenti care au numele introdus de la tastatura
        '''
        al=self.__reposit.getAllSt()
        return list(filter(lambda x:x.getNumeSt()==nume,al))
        
    def getAllStudents(self):
        '''
        Returneaza o lista cu toti studentii
        '''
        return self.__reposit.getAllSt()
    
    