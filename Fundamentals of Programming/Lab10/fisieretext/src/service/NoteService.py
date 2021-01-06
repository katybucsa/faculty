from domain.Materie import Materie
from domain.MaterieFainaDTO import MaterieFainaDTO
class NoteService:
    
    
    def __init__(self, _repo, _noterepo, _materierepo):
        self.__repo = _repo
        self.__noterepo = _noterepo
        self.__materierepo = _materierepo

    
    def celeMaiFaineMaterii(self):
        note = self.__noterepo.getAll()
        materii = {}
        for nota in note:
            key = nota.get_materie()
            if key not in materii:
                materii[key] =[0,0]
            materii[key][0]+=nota.get_valoare()
            materii[key][1]+=1
        rez=[]
        for key in materii:
            nume_materie = self.__materierepo.find(Materie(key,"")).get_nume()
            medie_materie = materii[key][0]/materii[key][1]
            rez.append(MaterieFainaDTO(nume_materie, medie_materie))
        rez.sort(key=lambda x: x.get_medie_materie(), reverse=True)
        return rez
    
    



