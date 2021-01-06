from domain.Lab import Lab
from repository.StudentRepository import RepositoryException
class LabRepository:
    
    def __init__(self,numefis):
        self.__labs=[]
        self.__numefis=numefis
        self.__citesteTotDinFisier()
        
    
    def __citesteTotDinFisier(self):
        with open(self.__numefis,'r') as f:
            al=[]
            for line in f.readlines():
                line=line.strip()
                if len(line):
                    cuvinte=line.split(',')
                    lab=Lab(int(cuvinte[0]),int(cuvinte[1]),cuvinte[2])
                    al.append(lab)
            self.__labs=al[:]
            
    def __scrieTotInFisier(self):
        with open(self.__numefis,'w') as f:
            for lab in self.__labs:
                line=str(lab.get_student_id())+','+str(lab.get_lab_number())+','+lab.get_problem_number()
                f.write(line+'\n')
                
    def add(self,lab):
        self.__citesteTotDinFisier()
        if lab in self.__labs:
            raise RepositoryException('Laborator deja asignat!\n')
        self.__labs.append(lab)
        self.__scrieTotInFisier()
    
    def getAllLabs(self):
        self.__citesteTotDinFisier()
        return self.__labs[:]


