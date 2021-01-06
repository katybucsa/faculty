'''
Created on Nov 29, 2017

@author: Andrei
'''

class MaterieFainaDTO:
    '''
    classdocs
    '''


    def __init__(self, nume_materie,medie_materie):
        '''
        Constructor
        '''
        self.__nume_materie = nume_materie
        self.__medie_materie = medie_materie

    def get_nume_materie(self):
        return self.__nume_materie


    def get_medie_materie(self):
        return self.__medie_materie

    def __str__(self):
        return "Materia "+self.__nume_materie+" cu media "+str(self.__medie_materie)