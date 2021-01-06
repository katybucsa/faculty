class Produs:
    '''
    Clasa care defineste un produs
    idp-id produs,char
    denumire-char
    pret-float
    '''
    def __init__(self,idp,denumire,pret):
        self.__idp = idp
        self.__denumire = denumire
        self.__pret = pret

    def get_idp(self):
        '''
        Getter method pentru id produs
        '''
        return self.__idp


    def get_denumire(self):
        '''
        Getter method pentru denumire produs
        '''
        return self.__denumire


    def get_pret(self):
        '''
        Getter method pentru pret 
        '''
        return self.__pret

    def __eq__(self,other):
        '''
        Returneaza True daca other si instanta curenta reprezinta aceeasi instanta si False in caz contrar
        '''
        if isinstance(other, __class__):
            return self.__idp==other.__idp
        
    def __str__(self):
        return 'Id produs: '+self.__idp+' '+'Denumire: '+self.__denumire+' '+'Pret: '+str(self.__pret)
    
    
    


