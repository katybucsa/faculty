from Domain.Jucator import Jucator

class JucatorService:
    def __init__(self,repo):
        self.__repo = repo
        
    def addJucator(self,nume,prenume,inaltime,post):
        jucator=Jucator(nume,prenume,inaltime,post)
        self.__repo.add(jucator)
        
    def modificaInaltime(self,nume,prenume,inaltime):
        jucator=Jucator(nume,prenume,inaltime,'extrema')
        self.__repo.update(jucator)
        
    def echipa(self):
        al=self.__repo.getAll()
        fundasi=list(filter(lambda x:x.get_post().lower()=='fundas',al))
        fundasi.sort(key=lambda x:x.get_inaltime(), reverse=True)
        extreme=list(filter(lambda x:x.get_post().lower()=='extrema',al))
        extreme.sort(key=lambda x:x.get_inaltime(), reverse=True)  
        pivoti=list(filter(lambda x:x.get_post().lower()=='pivot',al))
        pivoti.sort(key=lambda x:x.get_inaltime(), reverse=True)
        rez=[]
        if len(fundasi)>=2 and len(pivoti)>=1 and len(extreme)>=2:
            rez.append(fundasi[0])
            rez.append(fundasi[1])
            rez.append(pivoti[0])
            rez.append(extreme[0])
            rez.append(extreme[1])
        return rez[:]
    
    def importaJucatori(self,fisier):
        return self.__repo.importa(fisier)


