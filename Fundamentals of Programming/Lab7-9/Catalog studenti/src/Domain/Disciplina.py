



class Disciplina:
    '''
    Clasa Disciplina constituie o disciplina
    '''
    
    def __init__(self,idDisciplina,nume,profesor):
        '''
        Creeaza o noua disciplina 
        idDisciplina - int
        nume,profesor - string
        '''
        self.__idDisciplina = idDisciplina
        self.__nume = nume
        self.__profesor = profesor
        
    def getIdDisciplina(self):
        '''
        Getter method pentru idDisciplina
        '''
        return self.__idDisciplina
    
    def getNumeD(self):
        '''
        Getter method pentru nume disciplina
        '''
        return self.__nume
    
    def getProfesor(self):
        '''
        Getter method pentru nume profesor
        '''
        return self.__profesor
    
    def setNume(self,nume):
        self.__nume = nume
    
    def setProfesor(self,profesor):
        self.__profesor=profesor
        
    def __eq__(self,other):
        '''
        other - disciplina
        returneaza True daca other si instanta curenta reprezinta aceeasi disciplina
        '''
        if isinstance(other, self.__class__):
            return self.__idDisciplina==other.__idDisciplina
        else:
            return False
        
        
    def __str__(self):
        '''
        Returneaza un string pentru campurile din entitatea Disciplina
        '''
        
        return 'Id disciplina:'+str(self.__idDisciplina)+','+' '+'Nume disciplina:'+self.__nume+','+' '+'Nume profesor:'+self.__profesor
      
    @staticmethod
    def citesteDiscDinFisier(line):
        cuv = line.split(' ')
        return Disciplina(int(cuv[0]),cuv[1],cuv[2])
    
    @staticmethod
    def scrieDiscInFisier(disc):
        return str(disc.getIdDisciplina())+' '+disc.getNumeD()+' '+disc.getProfesor()   
    
      
        
class Prof:
    
    
    def __init__(self,profesor, suma):
        '''
        Creeaza entitatea profesor 
        profesor - string, numele profesorului
        suma - int, suma id-urilor disciplinelor pe care le preda 
        '''
        self.__profesor = profesor
        self.__suma = suma

    def get_profesor(self):
        '''
        Getter method
        '''
        return self.__profesor


    def get_suma(self):
        '''
        Getter method
        '''
        return self.__suma
    
    def __eq__(self,other):
        '''
        other - entitate Prof
        Returneaza True daca other si instanta curenta reprezinta aceeasi entitate
        '''
        if isinstance(other, self.__class__):
            return self.__profesor==other.__profesor
        else:
            return False
        
    
    def __str__(self):
        '''
        Returneaza un string pentru campurile din Prof
        '''
        return 'Nume profesor: '+self.__profesor+','+' '+'Suma: '+str(self.__suma)

    
     
        
    
    
    