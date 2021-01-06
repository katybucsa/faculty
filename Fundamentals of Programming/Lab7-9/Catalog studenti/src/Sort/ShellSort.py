from Sort.GenericSort import GenericSort

class ShellSort(GenericSort):
    def __init__(self,lista,key,reverse):
        super().__init__(lista, key, reverse)
        
        
    def sort(self):
        '''
        sorteaza o lista prin metoda Shell Sort
        '''
        gap=len(self.get_lista)//2
        while gap:
            for i in range(len(self.get_lista)):
                el = self.get_lista[i]
                j = i 
                while j >= gap and not self._in_order(self.get_lista[j-gap], el):
                    self.get_lista[j] = self.get_lista[j-gap]
                    j-= gap
                self.get_lista[j] = el 
            gap//= 2 
        return self.get_lista[:]
            