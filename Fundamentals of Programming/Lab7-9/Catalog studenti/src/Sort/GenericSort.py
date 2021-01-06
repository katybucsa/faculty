from abc import ABCMeta



class GenericSort(metaclass=ABCMeta):
    
    
    def __init__(self, lista, key, reverse):
        self.__lista = lista
        self.__key = key
        self.__reverse = reverse
        
    @property
    def get_lista(self):
        return self.__lista

    @property
    def get_key(self):
        return self.__key

    @property
    def get_reverse(self):
        return self.__reverse


    def set_key(self, value):
        self.__key = value
        
    
        
    def _in_order(self,e1,e2,):
        if self.__key is None:
            self.__key = lambda x:x 
        if self.__key(e1) == self.__key(e2):
            return True
        if not self.__reverse:
            return self.__key(e1) < self.__key(e2)
        return self.__key(e1) > self.__key(e2)
        
        