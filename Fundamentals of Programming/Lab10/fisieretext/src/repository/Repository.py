class Repository:
    
    
    def __init__(self):
        self._elems = []

    
    def size(self):
        return len(self._elems)

    
    def add(self,elem):
        if elem in self._elems:
            raise RepoException("elem exista deja!")
        self._elems.append(elem)

    
    def find(self,elem):
        if elem not in self._elems:
            raise RepoException("elem nu exista!")
        idx = self._elems.index(elem)
        return self._elems[idx]

    
    def upd(self,elem):
        if elem not in self._elems:
            raise RepoException("elem nu exista!")
        idx = self._elems.index(elem)
        self._elems[idx]=elem

    
    def getAll(self):
        return self._elems[:]

    
    def rem(self,elem):
        if elem not in self._elems:
            raise RepoException("elem nu exista!")
        self._elems.remove(elem)
    
    
    
    

    
    
    
    
    
    
    
    



class RepoException(Exception):
    pass


class FileRepository(Repository):
    
    
    def __citesteTotDinFisier(self):
        with open(self._filename,"r") as f:
            for line in f.readlines():
                line = line.strip()
                if len(line)>0:
                    o = self.__citesteObjDinStr(line)
                    self._elems.append(o)
    
    
    def __init__(self, _filename,_citesteObjDinStr,_scrieObjInStr):
        self.__citesteObjDinStr = _citesteObjDinStr
        self.__scrieObjInStr = _scrieObjInStr
        Repository.__init__(self)
        self._filename = _filename
        self.__citesteTotDinFisier()
    

    def __scrieTotInFisier(self):
        with open(self._filename,"w") as f:
            for o in self._elems:
                line = self.__scrieObjInStr(o)
                f.write(line+"\n")
                
    
    
    def upd(self, elem):
        Repository.upd(self, elem)
        self.__scrieTotInFisier()


    def rem(self, elem):
        Repository.rem(self, elem)
        self.__scrieTotInFisier()


    def __appendElemInFisier(self,elem):
        with open(self._filename,"a") as f:
            line = self.__scrieObjInStr(elem)
            f.write(line+"\n")
            
    
    
    def add(self, elem):
        Repository.add(self, elem)
        self.__appendElemInFisier(elem)