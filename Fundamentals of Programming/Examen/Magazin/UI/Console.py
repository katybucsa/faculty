from Repository.ProduseRepository import RepositoryException

class ConsoleException(Exception):
    pass

class Comanda:

    def __init__(self,numeCmd,functieCmd):
        self.__numeCmd = numeCmd
        self.__functieCmd = functieCmd
        
    def executa(self,params):
        self.__functieCmd(params)


class Console:
    
    def __init__(self,service):
        self.__service = service
    
    def __readCmd(self):
        return input('>>').split(',')
    

    def __adaugaProdus(self,params):
        '''
        primeste un id,denumire si un pret si incearca sa il adauge in fisier
        '''
        if len(params)!=3:
            raise ConsoleException('Numar de parametri invalid!\n')
        idp=params[0].strip()
        denumire=params[1].strip()
        pret=float(params[2].strip())
        self.__service.adaugaProdus(idp,denumire,pret)
        print('Produs adaugat cu succes!\n')
        
    
    
    def __stergeProduse(self,params):
        '''
        afiseaza numarul de produse sterse care au in continut cifra citita de la tastatura
        '''
        if len(params)!=1:
            raise ConsoleException('Numar de parametri invalid!\n')
        cifra=int(params[0].strip())
        nr=self.__service.stergeProduse(cifra)
        print('Au fost sterse',nr,'produse')
        

    
    
    
    def __filtreazaProduse(self,params):
        '''
        afiseaza lista de produse filtrata dupa o denumire si un pret introdus de la tastatura
        '''
        if len(params)!=2:
            raise ConsoleException('Numar de parametri invalid!\n')
        den=params[0].strip()
        pr=float(params[1].strip())
        lista=self.__service.filtreazaProduse(den,pr)
        for produs in lista:
            print(produs)
        
        
    
    
    def __undo(self,params):
        '''
        '''
        if len(params)!=0:
            raise ConsoleException('Comanda invalida!\n')
        self.__service.undo()
        
    
    
    def run(self):
        '''
        Start UI
        '''
        
        comenzi={'adauga produs':Comanda('adauga produs',self.__adaugaProdus),
                 'sterge produse':Comanda('sterge produse',self.__stergeProduse),
                 'filtreaza':Comanda('filtreaza',self.__filtreazaProduse),
                 'undo':Comanda('undo',self.__undo)
            }
    
        p1=''
        p2='-1'
        k=0
        m=0
        while True:
            if  k==1 or m==0:
                self.__filtreazaProduse([p1,p2])
                m=1
            cmd=self.__readCmd()
            k=1
            if len(cmd)==0:
                continue
            if len(cmd[0])==0:
                continue
            if cmd[0]=='exit':
                return
            if cmd[0] in comenzi:
                try:
                    comenzi[cmd[0]].executa(cmd[1:])
                except RepositoryException as re:
                    print(re)
                except ConsoleException as ce:
                    print(ce)
                except ValueError:
                    print('Eroare in introducerea tipului de data!\n')
                if cmd[0]=='filtreaza' and len(cmd)==3:
                    p1=cmd[1]
                    p2=cmd[2]
                    k=0
            else:
                print('Comanda invalida!\n')

