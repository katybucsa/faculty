from Domain.Student import Student
from Domain.Disciplina import Disciplina

class Nota:
    '''
    Constituie o nota
    '''
    def __init__(self,st,disc,nota):
        '''
        Creeaza o noua nota
        st-entitate, student
        disc - string, disciplina
        nota-float, nota
        '''
        self.__st = st
        self.__disc = disc
        self.__nota = nota

    def getStud(self):
        '''
        Getter method
        '''
        return self.__st


    def getDisc(self):
        '''
        Getter method
        '''
        return self.__disc


    def getNota(self):
        '''
        Getter method
        '''
        return self.__nota


    def setStud(self, student):
        self.__st = student


    def setDisc(self, disc):
        self.__disc = disc


    def setNota(self, nota):
        self.__nota = nota
        
    def __eq__(self,other):
        '''
        other - nota
        Returneaza True daca other si instanta curenta reprezinta aceeasi nota
        '''
        if isinstance(other, self.__class__):
            return self.__st.getIdstud()==other.__st.getIdstud() and self.__disc.getIdDisciplina()==other.__disc.getIdDisciplina()
        else:
            return False
    def __str__(self):
        '''
        Returneaza un string reprezantand campurile entitatii Nota
        '''
        return 'Nume student: '+self.__st.getNumeSt()+','+' '+'Disciplina: '+self.__disc.getNumeD()+','+' '+'Nota: '+str(self.__nota)

    
    @staticmethod
    def citesteNotaDinFisier(line):
        cuv = line.split(' ')
        return Nota(Student(int(cuv[0]),'def',13),Disciplina(int(cuv[1]),'def','def'),float(cuv[2]))
    
    @staticmethod
    def scrieNotaInFisier(nota):
        return str(nota.getStud().getIdstud())+' '+str(nota.getDisc().getIdDisciplina())+' '+str(nota.getNota())
    

class NotaStudent:
    '''
    Constituie nota unui student la o anumita disciplina 
    '''
    def __init__(self,stnume,nota):
        '''
        Creeaza o noua entitate care contine numele unui student si nota la o disciplina
        stnume - string, nume student 
        nota - float, nota student
        '''
        self.__stnume = stnume
        self.__nota = nota

    def get_stnume(self):
        '''
        Getter method
        '''
        return self.__stnume


    def get_nota(self):
        '''
        Getter method
        '''
        return self.__nota
    
    def __eq__(self,other):
        '''
        other - nota la o disciplina
        Returneaza True daca other si instanta curenta reprezinta aceeasi entitate
        '''
        if isinstance(other, self.__class__):
            return self.__stnume==other.__stnume and self.__nota==other.__nota
        else:
            return False


    def __str__(self):
        '''
        Returneaza un string reprezantand campurile entitatii NotaStudent
        '''
        return 'Nume student: '+self.__stnume+','+' '+'Nota: '+str(self.__nota)
    
    

        
    
class Medie:
    '''
    Constituie media unui student
    '''
    def __init__(self,nume,medie):
        '''
        Creeaza o noua medie
        nume - string, nume student
        medie - float, medie student
        '''
        self.__nume = nume
        self.__medie = medie

    def get_nume(self):
        '''
        Getter method
        '''
        return self.__nume


    def get_medie(self):
        '''
        Getter method
        '''
        return self.__medie
    
    def __eq__(self,other):
        '''
        other - medie
        Returneaza True daca other si instanta curenta reprezinta aceeasi entitate
        '''
        if isinstance(other, self.__class__):
            return self.__nume==other.__nume and self.__medie==other.__medie
        else:
            return False
    
    def __str__(self):
        '''
        Returneaza un string ce contine campurile entitatii Medie
        '''
        return 'Nume student: '+self.__nume+','+' '+'Medie: '+str(self.__medie)
    
    
class MedieDisc:
    '''
    Constituie media clasei la o disciplina
    '''
    def __init__(self,numeDisc,media):
        self.__numeDisc = numeDisc
        self.__media = media

    def getNumeDisc(self):
        '''
        Getter method
        '''
        return self.__numeDisc


    def getMedia(self):
        '''
        Getter method
        '''
        return self.__media
    
    def __eq__(self,other):
        '''
        other - medie
        Returneaza True daca other si instanta curenta reprezinta aceeasi entitate
        '''
        if isinstance(other, self.__class__):
            return self.__numeDisc==other.__numeDisc and self.__media==other.__media
        else:
            return False


    def __str__(self):
        '''
        Returneaza un string ce contine campurile entitatii MedieDisc
        '''
        return 'Disciplina: '+self.__numeDisc+','+' '+'Media: '+str(self.__media)
    
    
    
    
    
    
    
    
    
    
    
        