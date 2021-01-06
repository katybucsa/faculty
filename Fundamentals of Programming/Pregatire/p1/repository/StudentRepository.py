from domain.Student import Student

class RepositoryException(Exception):
    pass 

class StudentRepository:
    
    def __init__(self,numefis):
        
        self.__studenti=[]
        self.__numefis=numefis 
        self.__citesteTotDinFisier()
        
        
    def __citesteTotDinFisier(self):
        
        with open(self.__numefis,'r') as f:
            al=[]
            for line in f.readlines():
                line=line.strip()
                if len(line):
                    cuvinte=line.split(',')
                    st=Student(int(cuvinte[0]),cuvinte[1])
                    al.append(st)
            self.__studenti=al[:]
            
    
    def __scrieTotInFisier(self):
        
        with open(self.__numefis,'w') as f:
            for st in self.__studenti:
                line=str(st.get_id())+','+st.get_name()
                f.write(line+'\n')
                
    def getSt(self,student):
        self.__citesteTotDinFisier()
        if student not in self.__studenti:
            raise RepositoryException('Student inexistent!\n')
        i=self.__studenti.index(student)
        return self.__studenti[i]
    
                
    
    def getAllSt(self):        
        self.__citesteTotDinFisier()
        return self.__studenti[:]

