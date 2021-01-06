'''
Created on 23 Jan 2018

@author: ASUS
'''

class Lab:
    
    def __init__(self,studentId,labNumber,problemNumber):
        self.__studentId = studentId
        self.__labNumber = labNumber
        self.__problemNumber = problemNumber

    def get_student_id(self):
        return self.__studentId


    def get_lab_number(self):
        return self.__labNumber


    def get_problem_number(self):
        return self.__problemNumber

    def __eq__(self,other):
        if isinstance(other, __class__):
            return self.__labNumber==other.__labNumber and self.__studentId==other.__studentId
        
    def __str__(self):
        return 'Id student: '+str(self.__studentId)+' '+'Numar laborator: '+str(self.__labNumber)+' '+'Numar problema: '+self.__problemNumber
    
    
    
        