from domain.Student import Student
from domain.Lab import Lab


class LabService:
    
    def __init__(self,repost,repol):
        self.__repost=repost
        self.__repol=repol
        
    def getStudentById(self,studentId):
        st=Student(studentId,'def')
        student=self.__repost.getSt(st)
        return student
    
    def addLab(self,studentId,labNumber,problemNumber):
        lab=Lab(studentId,labNumber,problemNumber)
        self.__repol.add(lab)
        
    def getLabsByStudentId(self,studentId):
        l=[]
        al=self.__repol.getAllLabs()
        for lab in al:
            if lab.get_student_id()==studentId:
                l.append(lab)
        return l 
    
    def getLabsByLabNumber(self,labNumber):
        l=[]
        al=self.__repol.getAllLabs()
        for lab in al:
            if lab.get_lab_number()==labNumber:
                l.append(lab)
        return l 
                
        
        
    
    def getAllStud(self):
        return self.__repost.getAllSt()
    


