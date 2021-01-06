from Domain.Student import Student
from Domain.Disciplina import Disciplina
from Domain.Nota import Nota


class RepositoryException(Exception):
    '''
    Clasa exceptiilor din Repository
    '''
    
    def __init__(self, *args,**kwargs):
        Exception.__init__(self,*args,**kwargs)
        

class StudentRepository:
    '''
    Clasa responsabila pentru a manavra o lista de studenti
    ''' 
    
    def __init__(self):
        self._studenti = []
        
    def __len__(self):
        '''
        Returneaza numarul studentilor din Repository
        '''
        return len(self._studenti)
        
    def adaugaSt(self,student):
        '''
        Adauga un student in Repository
        raise RepositoryException('Student existent!') pentu id student existent
        '''
        if student in self._studenti:
            raise RepositoryException('Student existent!')
        self._studenti.append(student)
        
    def stergeSt(self,student):
        '''
        Sterge un student din Repository
        raise RepositoryException('Student inexistent!') daca id-ul introdus nu corespunde pentru niciun student
        ''' 
        if student not in self._studenti:
            raise RepositoryException('Student inexistent!')
        self._studenti.remove(student)
            
            
    def modificaSt(self,student):
        '''
        Modifica un student din Repository
        raise RepositoryException('Student inexistent!') daca nu exista student cu id-ulintrodus
        i - int,pozitia studentului cu id-ul introdus pentru a fi modificat
        '''
        if student not in self._studenti:
            raise RepositoryException('Student inexistent!')
        i=self._studenti.index(student)
        self._studenti[i]=student
    
    def getSt(self,student):
        '''
        Returneaza datele despre un student cu un id dat sau 
        raise RepositoryException('Student inexistent!') daca nu exista student care are id introdus
        '''
        #caz favorabil: elementul cautat se afla pe prima pozitie - T(n)=1 ∈  θ(1)
        #caz defavorabil: elementul se afla pe ultima pozitie sau nu se gaseste in lista - T(n)=n ∈  θ(n)
        #caz mediu: T(n)=(1+2+...+n)/n=(n+1)/2→ 𝑇(𝑛) ∈ θ(n)
        #complexitate: O(n)
        
        
        for i in range (len(self._studenti)):
            if self._studenti[i]==student:
                return self._studenti[i]
        raise RepositoryException('Student inexistent!')
    
    
    def getAllSt(self):
        '''
        Returneaza o lista cu toti studentii din Repository
        '''
        return self._studenti[:]
    


class FileStudentRepository(StudentRepository):
    '''
    Clasa responsabila pentru memorarea studentilor in fisiere
    '''
              
    def __init__(self,numefis):
        self.__citesteStDinFis = Student.citesteStDinFisier
        self.__scrieStInFis = Student.scrieStInFisier
        StudentRepository.__init__(self)
        self.__numefis = numefis
        self.__citesteStudsDinFisier()
        self.__scrieStudsInFisier()
    
    def __citesteStudsDinFisier(self):
        with open(self.__numefis,'r') as f:
            al=[]
            for line in f.readlines():
                line = line.strip()
                if len(line)>0:
                    st = self.__citesteStDinFis(line)
                    al.append(st)
            self._studenti=al[:]
                    
    
    def __scrieStudsInFisier(self):
        with open(self.__numefis, 'w') as f:
            for st in  self._studenti:
                line=self.__scrieStInFis(st)
                f.write(line+'\n')
    
    def __len__(self):
        return StudentRepository.__len__(self)
    
                
    def __appendStInFis(self,student):
        with open(self.__numefis,'a') as f:
            line=self.__scrieStInFis(student)
            f.write(line+'\n')
            
    def adaugaSt(self, student):
        self.__citesteStudsDinFisier()
        StudentRepository.adaugaSt(self, student)
        self.__appendStInFis(student)
            
                
    
    def modificaSt(self, student):
        self.__citesteStudsDinFisier()
        StudentRepository.modificaSt(self, student)
        self.__scrieStudsInFisier()
    
    def stergeSt(self, student):
        self.__citesteStudsDinFisier()
        StudentRepository.stergeSt(self, student)
        self.__scrieStudsInFisier()
    
    def getSt(self, student):
        self.__citesteStudsDinFisier()
        return StudentRepository.getSt(self, student)
    
    def getAllSt(self):
        self.__citesteStudsDinFisier()
        return StudentRepository.getAllSt(self)
        
    
    

class DisciplinaRepository:
    '''
    Clasa responsabila pentru a manevra o lista de discipline 
    '''
    
    def __init__(self):
        self._discipline=[]
        
    def adaugaD(self,disciplina):
        '''
        Adauga o disciplina in Repository
        raise RepositoryException('Disciplina existenta!') pentu id disciplina existent
        '''
        if disciplina in self._discipline:
            raise RepositoryException('Disciplina existenta!')
        self._discipline.append(disciplina)
        
    def stergeD(self,disciplina):
        '''
        Sterge o disciplina din Repository
        raise RepositoryException('Disciplina inexistenta!') daca id-ul introdus nu corespunde pentru nicio disciplina.
        ''' 
        if disciplina not in self._discipline:
            raise RepositoryException('Disciplina inexistenta!')
        self._discipline.remove(disciplina)
    
    def modificaD(self,disciplina):
        '''
        Modifica o disciplina din Repository
        raise RepositoryException('Disciplina inexistenta!') daca nu exista disciplina cu id-ulintrodus
        i - int,pozitia diciplinei cu id-ul introdus pentru a fi modificata
        '''
        if disciplina not in self._discipline:
            raise RepositoryException('Disciplina inexistenta!')
        i=self._discipline.index(disciplina)
        self._discipline[i]=disciplina
    
    def getD(self,disciplina):
        '''
        Returneaza datele despre o disciplina cu un id dat sau 
        raise RepositoryException('Disciplina inexistenta!') daca nu exista diciplina care are id introdus
        '''
        if disciplina not in self._discipline:
            raise RepositoryException('Disciplina inexistenta!')
        i=self._discipline.index(disciplina)
        return self._discipline[i]
    
    def __len__(self):
        '''
        Returneaza numarul disciplinelor din Repository
        '''
        return len(self._discipline)
    
    def getAllD(self):
        '''
        Returneaza o lista cu toate disciplinele din Repository
        '''
        return self._discipline[:]
    
    
class FileDisciplinaRepository(DisciplinaRepository):
    
    
    def __init__(self, numefis):
        self.__citesteDiscDinFis = Disciplina.citesteDiscDinFisier
        self.__scrieDiscInFis = Disciplina.scrieDiscInFisier
        DisciplinaRepository.__init__(self)
        self.__numefis = numefis
        self.__citesteDiscsDinFisier()
        self.__scrieDiscsInFisier()
        
    def __citesteDiscsDinFisier(self):
        with open(self.__numefis,'r') as f:
            al=[]
            for line in f.readlines():
                line=line.strip()
                if len(line)>0:
                    disc=self.__citesteDiscDinFis(line)
                    al.append(disc)
            self._discipline=al[:]
                    
    def __len__(self):
        return DisciplinaRepository.__len__(self)
                    
    
    def __scrieDiscsInFisier(self):
        with open(self.__numefis, 'w') as f:
            for disc in  self._discipline:
                line=self.__scrieDiscInFis(disc)
                f.write(line+'\n')
                
    def __appendDiscInFis(self,disciplina):
        with open(self.__numefis,'a') as f:
            line=self.__scrieDiscInFis(disciplina)
            f.write(line+'\n')
            
    def adaugaD(self, disciplina):
        self.__citesteDiscsDinFisier()
        DisciplinaRepository.adaugaD(self, disciplina)
        self.__appendDiscInFis(disciplina)
            
    
    def modificaD(self, disciplina):
        self.__citesteDiscsDinFisier()
        DisciplinaRepository.modificaD(self, disciplina)
        self.__scrieDiscsInFisier()
        
    def stergeD(self, disciplina):
        self.__citesteDiscsDinFisier()
        DisciplinaRepository.stergeD(self, disciplina)
        self.__scrieDiscsInFisier()
        
    
    def getD(self, disciplina):
        self.__citesteDiscsDinFisier()
        return DisciplinaRepository.getD(self, disciplina)
    
    def getAllD(self):
        self.__citesteDiscsDinFisier()
        return DisciplinaRepository.getAllD(self)
    
    
    
    
  
  
class NotaRepository:
    '''
    Clasa responsabila pentru a manevra o lista de note
    '''
    
    def __init__(self):
        self._note=[]
        
    def adaugaN(self,nota):
        '''
        Adauga o nota in Repository
        raise RepositoryException('Nota deja asignata!') pentu nota la un student si disciplina asignata
        '''
        if nota in self._note:
            raise RepositoryException('Nota deja asignata!')
        self._note.append(nota)
        
        
    def modificaNota(self,nota):
        '''
        Modifica o nota din Repository
        i - int, pozitia notei in lista pentru studentul sau disciplina precizata
        '''
        if nota in self._note:
            i=self._note.index(nota)
            self._note[i]=nota
            
    def stergeNota(self,nota):
        '''
        Sterge o nota din Repository
        '''
        if nota in self._note:
            self._note.remove(nota)
        
        
    def __len__(self):
        '''
        Returneaza numarul notelor asignate din Repository
        '''
        return len(self._note)
    
        
    def getAllGr(self):
        '''
        Returneaza o lista cu toate notele din Repository
        '''
        return self._note[:] 
            
        
        
class FileNoteRepository(NotaRepository):
    
    def __init__(self, numefis):
        self.__citesteNotaDinFis = Nota.citesteNotaDinFisier
        self.__scrieNotaInFis = Nota.scrieNotaInFisier
        NotaRepository.__init__(self)
        self.__numefis = numefis
        self.__strepo=FileStudentRepository('studenti.txt')
        self.__drepo=FileDisciplinaRepository('discipline.txt')
        self.__citesteNoteDinFisier()
        
        
    def __citesteNoteDinFisier(self):
        with open(self.__numefis,'r') as f:
            al=[]
            for line in f.readlines():
                line=line.strip()
                if len(line)>0:
                    nota=self.__citesteNotaDinFis(line)
                    if nota.getStud() in self.__strepo.getAllSt():
                        nota.setStud(self.__strepo.getSt(nota.getStud()))
                    if nota.getDisc() in self.__drepo.getAllD():
                        nota.setDisc(self.__drepo.getD(nota.getDisc()))
                    al.append(nota)
        self._note=al[:]
                    
    
    def __scrieNoteInFisier(self):
        with open(self.__numefis, 'w') as f:
            for nota in  self._note:
                line=self.__scrieNotaInFis(nota)
                f.write(line+'\n')
                
    def __len__(self):
        return NotaRepository.__len__(self)
                
    def __appendNotaInFis(self,nota):
        with open(self.__numefis,'a') as f:
            line=self.__scrieNotaInFis(nota)
            f.write(line+'\n')
            
    def adaugaN(self, nota):
        self.__citesteNoteDinFisier()
        NotaRepository.adaugaN(self, nota)
        self.__appendNotaInFis(nota)
        
            
    
    def modificaNota(self, nota):
        self.__citesteNoteDinFisier()
        NotaRepository.modificaNota(self, nota)
        self.__scrieNoteInFisier()
        
    def stergeNota(self, nota):
        self.__citesteNoteDinFisier()
        NotaRepository.stergeNota(self, nota)
        self.__scrieNoteInFisier()
        
    def getAllGr(self):
        self.__citesteNoteDinFisier()
        return NotaRepository.getAllGr(self)
               
    