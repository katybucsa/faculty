from Validare.Validator import ValidatorException
from Repository.StudentRepository import RepositoryException

class Comanda:
    
    def __init__(self, numeCmd,functieCmd):
        self.__numeCmd=numeCmd
        self.__functieCmd=functieCmd
        
    def executa(self,params):
        self.__functieCmd(params)


class ConsoleException(Exception):
    pass



class Console:
    def __init__(self,serv):
        '''
        serv -StudentService
        '''
        self.__serv=serv
        
        
    def __readCmd(self):
        '''
        citeste comanda
        '''
        return input('>>').split('  ')
    
    
    def __adaugaStudent(self,params):
        '''
        citeste de la tastatura datele despre un student
        '''
        if len(params)!=4:
            raise ConsoleException('Numar de parametri invalid!\n')
        nr_mat=params[0]
        nume=params[1]
        nr_prezente=int(params[2])
        nota=float(params[3])
        self.__serv.adaugaStudent(nr_mat,nume,nr_prezente,nota)
        print('Student adaugat cu succes!\n')
        
    
    
    def __adaugaBonus(self,params):
        '''
        citeste de la tastatura un numar intreg, reprezentand numar prezente si un nr real reprezentand un nr de pucte
        '''
        if len(params)!=2:
            raise ConsoleException('Numar deparametrii invalid!\n')
        nr_prez=int(params[0])
        nr_pct=float(params[1])
        self.__serv.adaugaBonus(nr_prez,nr_pct)
        print('Bonus adaugat cu succes!\n')
        
    
    
    def __sorteaza(self,params):
        '''
        afiseza o lista cu studenti ordonati crescator după raportul nr prezențe / notă
        '''
        al=self.__serv.sorteaza()
        if len(al)==0:
            print('Nu exista studenti in fisier!\n')
        else:
            for st in al:
                print(st)
    
    
    def run(self):
        '''
        Start UI
        '''
        comenzi={'adaugaStudent':Comanda('adaugaStudent',self.__adaugaStudent),
                 'acordareBonus':Comanda('adaugaBonus',self.__adaugaBonus),
                 'sorteaza':Comanda('sorteaza',self.__sorteaza)
                 
            }
        while True:
            cmd=self.__readCmd()
            if len(cmd)==0:
                continue
            elif len(cmd[0])==0:
                continue
            elif cmd[0]=='exit':
                return
            elif cmd[0] in comenzi:
                try:
                    comenzi[cmd[0]].executa(cmd[1:])
                except ValidatorException as ve:
                    print(ve)
                except RepositoryException as re:
                    print(re)
                except ValueError as ve:
                    print(ve)
                except ConsoleException as ce:
                    print(ce)
            else:
                print('Comanda invalida!\n')

