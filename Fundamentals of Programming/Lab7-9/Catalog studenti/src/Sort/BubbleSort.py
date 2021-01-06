from Sort.GenericSort import GenericSort


class BubbleSort(GenericSort):
    def __init__(self,lista,key,reverse):
        super().__init__(lista, key, reverse)
        
    def sort(self):
        '''
        sorteaza o lista prin metoda Bubble Sort
        '''
        sortd = False
        while not sortd:
            sortd = True #assume the list is already sorted
            for i in range(1,len(self.get_lista)):
                if not self._in_order(self.get_lista[i-1],self.get_lista[i]):
                    self.get_lista[i-1],self.get_lista[i]=self.get_lista[i],self.get_lista[i-1]
                    sortd = False #the list is not sorted yet
        return self.get_lista[:]