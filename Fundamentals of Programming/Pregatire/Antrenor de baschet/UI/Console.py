from Repository.JucatorRepository import RepositoryException
from Validator.Validare import ValidatorException

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
    

    def __adaugaJucator(self,params):
        if len(params)!=4:
            raise ConsoleException('Numar de parametri invalid!\n')
        nume=params[0].strip()
        prenume=params[1].strip()
        inaltime=float(params[2].strip())
        post=params[3].strip()
        self.__service.addJucator(nume,prenume,inaltime,post)
        print('Jucator adaugat cu succes!\n')
        
    
    
    def __modificaInaltime(self,params):
        if len(params)!=3:
            raise ConsoleException('Numar de parametri invalid!\n')
        nume=params[0].strip()
        prenume=params[1].strip()
        inaltime=float(params[2].strip())
        self.__service.modificaInaltime(nume,prenume,inaltime)
        print('Inaltime jucator modificata cu succes!\n')
    
    
    def __construiesteEchipa(self,params):
        if len(params)!=0:
            raise ConsoleException('Comanda invalida!\n')
        echipa=self.__service.echipa()
        print('    Prenume    '+'    Nume    '+'    Post    '+'    Inaltime    ')
        print('_________________________________________________________________')
        for jucator in echipa:
            print(jucator)
            
        
    
    
    def __importaJucatori(self,params):
        if len(params)!=1:
            raise ConsoleException('Numar de parametri invalid!\n')
        fisier=params[0].strip()
        nr=self.__service.importaJucatori(fisier)
        print(nr)
        
        
    
    
    def run(self):
        
        comenzi={'adauga jucator':Comanda('adauga jucator',self.__adaugaJucator),
                 'modifica inaltime':Comanda('modifica inaltime',self.__modificaInaltime),
                 'echipa':Comanda('echipa',self.__construiesteEchipa),
                 'importa':Comanda('importa jucatori',self.__importaJucatori)
            }
        
        while True:
            cmd=self.__readCmd()
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
                except ValidatorException as ve:
                    print(ve)
                except ConsoleException as ce:
                    print(ce)
                except ValueError:
                    print('Eroare in introducerea tipului de data!\n')
            else:
                print('Comanda invalida!\n')
