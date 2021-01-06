class Student:
    '''
    Constuie un student
    '''
    def __init__(self,nr_matricol,nume,nr_prezente,nota):
        '''
        Creeaza un nou student 
        nr-matricol, string numarul matricol
        nume-string,nume student
        nr_prezente-int,numar de prezente
        nota-float,nota student
        '''
        self.__nr_matricol = nr_matricol
        self.__nume = nume
        self.__nr_prezente = nr_prezente
        self.__nota = nota

    def get_nr_matricol(self):
        return self.__nr_matricol


    def get_nume(self):
        return self.__nume


    def get_nr_prezente(self):
        return self.__nr_prezente


    def get_nota(self):
        return self.__nota


    def set_nume(self, value):
        self.__nume = value


    def set_nr_prezente(self, value):
        self.__nr_prezente = value


    def set_nota(self, value):
        self.__nota = value

    def __eq__(self,other):
        if isinstance(other, __class__):
            return self.__nr_matricol==other.__nr_matricol
        
        
    def __str__(self):
        return 'Nr matricol: '+self.__nr_matricol+' '+'Nume: '+self.__nume+' '+'Numar prezente: '+str(self.__nr_prezente)+' '+'Nota: '+str(self.__nota)
