'''
Created on Nov 29, 2017

@author: Andrei
'''

class Nota:
    '''
    classdocs
    '''


    def __init__(self, student,materie,valoare):
        '''
        Constructor
        '''
        self.__student = student
        self.__materie = materie
        self.__valoare = valoare

    def get_student(self):
        return self.__student


    def get_materie(self):
        return self.__materie


    def get_valoare(self):
        return self.__valoare


    def set_valoare(self, value):
        self.__valoare = value

    def __eq__(self,other):
        return isinstance(other,self.__class__) and self.__student == other.__student and self.__materie==other.__materie

        