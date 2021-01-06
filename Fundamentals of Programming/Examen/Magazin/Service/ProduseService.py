from Domain.Produs import Produs

class ProduseService:
    '''
    clasa responsabila cu efectuarea operatiilor de adaugare, stergere, filtrare pentru produse
    '''
    def __init__(self,repo):
        self.__repo = repo
        
    
    def adaugaProdus(self,idp,denumire,pret):
        '''
        idp-char 
        denumire-char
        pret-float
        adauga un produs
        '''
        produs=Produs(idp,denumire,pret)
        self.__repo.adauga(produs)
        
    
    def stergeProduse(self,cifra):
        '''
        cifra-int
        sterge produsele care contin cifra in id
        '''
        self.__repo.saveLista()
        al=self.__repo.getAll()
        nr=0
        a=False
        while not a:
            ok=1
            for produs in al:
                if str(cifra) in produs.get_idp():
                    self.__repo.sterge(produs)
                    nr+=1
                    ok=0
            if ok==1:
                a=True
                    
        return nr
    
    def filtreazaProduse(self,den,pr):
        '''
        filtreaza lista de produse dipa cele doua filtre introduse de la tastatura
        den- char,denumire produs
        pr-float, pret produs
        daca p=-1, lista nu se filtreaza dupa pret, iar daca denumie e string vin, nu se filtreaza dupa denumire
        '''
        al=self.__repo.getAll()
        if den!='':
            lista=list(filter(lambda x:x.get_denumire()==den,al))
        if pr!=-1 and den!='':
            l=list(filter(lambda x:x.get_pret()==pr,lista))
            return l 
        if den!='' and pr==-1:
            return lista 
        if pr!=-1 and den=='':
            l=list(filter(lambda x:x.get_pret()==pr,al))
            return l
        return al 
    
    def undo(self):
        '''
        reface ultima operatie de stergere
        '''
        self.__repo.undoLista()
    
    def size(self):
        return len(self.__repo.getAll())
        
        
        


