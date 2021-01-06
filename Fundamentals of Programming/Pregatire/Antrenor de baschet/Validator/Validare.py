from Domain.Jucator import Jucator

class ValidatorException(Exception):
    pass


class ValideazaJucator:
    
    def __init__(self):
        self.__erori=[]
        
    def valideaza(self,jucator):
        
        erori=''
        if jucator.get_prenume()=='':
            erori+='Prenume jucator nu poate fi vid!\n'
        if jucator.get_nume()=='':
            erori+='Nume jucator nu poate fi vid!\n'
        if jucator.get_inaltime()<=0:
            erori+='Inaltime jucator invalida!\n'
        if jucator.get_post().lower() not in('fundas','pivot','extrema'):
            erori+='Post jucator invalid!\n'
        if len(erori):
            raise ValidatorException(erori)
        


