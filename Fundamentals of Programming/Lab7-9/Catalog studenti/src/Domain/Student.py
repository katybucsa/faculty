



class Student:
    '''
    Constituie un student
    '''
    
    def __init__(self,idstud,nume,varsta):
        '''
        Creeaza un student
        idstud - int, id student
        nume - string, nume student
        varsta - float, varsta student
        '''
        self.__idstud = idstud
        self.__nume = nume
        self.__varsta = varsta
     
    def getIdstud(self):
        '''
        Getter method
        '''
        return self.__idstud
    
    def getNumeSt(self):
        '''
        Getter method
        '''
        return self.__nume
    
    def getVarsta(self):
        '''
        Getter method
        '''
        return self.__varsta
    
    
    def setNume(self,nume):
        self.__nume=nume
    
    def setVarsta(self,varsta):
        self.__varsta=varsta
        
    def __lt__(self, other):
        if isinstance(other,self.__class__):
            return self.__varsta<other.__varsta
        else:
            return False
       
    def __eq__(self,other):
        '''
        other - student
        Returneaza True daca other si instanta curenta reprezinta acelasi student
        '''
        if isinstance(other, self.__class__):
            return self.__idstud==other.__idstud
        else:
            return False
        
    def __str__(self):
        '''
        Returneaza un string alcatuit din campurile entitatii Student
        '''
        return 'Id student:'+str(self.__idstud)+','+' '+'Nume student:'+self.__nume+','+' '+'Varsta student:'+str(self.__varsta)
    
    @staticmethod
    def citesteStDinFisier(line):
        cuv = line.split(' ')
        return Student(int(cuv[0]),cuv[1],int(cuv[2]))
    
    @staticmethod
    def scrieStInFisier(st):
        return str(st.getIdstud())+' '+st.getNumeSt()+' '+str(st.getVarsta())
    
    
    
    