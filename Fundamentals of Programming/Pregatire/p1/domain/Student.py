class Student:
    def __init__(self,ids,name):
        self.__ids=ids 
        self.__name=name

    def get_id(self):
        return self.__ids


    def get_name(self):
        return self.__name


    def set_name(self, name):
        self.__name = name

    def __eq__(self,other):
        if isinstance(other,__class__):
            return self.__ids==other.__ids
    
    def __str__(self):
        return 'Id student: '+str(self.__ids)+' '+'Nume student: '+self.__name

