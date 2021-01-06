from Sort.Algorithm import Algorithm

class Sorting(object):
    
    @staticmethod
    def sort(lista,*, key=None, reverse=False , algorithm=Algorithm.BUBBLE_SORT):
        l1=lista[:]
        sorting_alg = algorithm.value(l1,key,reverse)
        return sorting_alg.sort()