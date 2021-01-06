from repository.StudentRepository import RepositoryException

class ConsoleException(Exception):
    pass 


class Comanda:
    def __init__(self,numeCmd,functieCmd):
        self.__numeCmd=numeCmd
        self.__functieCmd=functieCmd
        
    def executa(self,params):
        self.__functieCmd(params)

class Console:
    
    def __init__(self,serv):
        self.__serv=serv 
        
    
    def __readCmd(self):
        '''
        Citeste o comanda de la tastatura
        '''
        return input('>>').split(',')
    

    def __showStudents(self,params):
        al=self.__serv.getAllStud()
        if len(al)==0:
            print('Nu exista studenti in fisier!\n')
        else:
            for st in al:
                print(st)
                
    
    
    def __findStudent(self,params):
        if len(params)!=1:
            raise ConsoleException('Numar de parametri invalid!\n')
        ids=int(params[0])
        st=self.__serv.getStudentById(ids)
        print(st)
        
    
    
    
    def __asignareLab(self,params):
        if len(params)!=3:
            raise ConsoleException('Numar de parametri invalid!\n')
        ids=int(params[0])
        labn=int(params[1])
        prnum=params[2]
        st=self.__serv.getStudentById(ids)
        self.__serv.addLab(ids,labn,prnum)
        print('Laborator asignat cu succes!\n')
        
    
    
    def __showStudentLabs(self,params):
        if len(params)!=1:
            raise ConsoleException('Numar de parametri invald!\n')
        ids=int(params[0])
        st=self.__serv.getStudentById(ids)
        labs=self.__serv.getLabsByStudentId(ids)
        if len(labs)==0:
            print('Nu exista laboratoare asignate pentru acest student!\n')
        else:
            for lab in labs:
                print(lab)
    
    
        
        
    
    
    def __showLabs(self,params):
        if len(params)!=1:
            raise ConsoleException('Numar de parametri invalid!\n')
        labnr=int(params[0])
        labs=self.__serv.getLabsByLabNumber(labnr)
        if len(labs)==0:
            print('Nu exista laboratoare cu numarul introdus!\n')
        else:
            for lab in labs:
                print(lab)
        
    
    
    def run(self):
        '''
        Start the UI
        '''
        comenzi={'vizualizare studenti':Comanda('vizualizare studenti',self.__showStudents),
                 'studentul cu id':Comanda('student cu id',self.__findStudent),
                 'asigneaza laborator':Comanda('asigneaza laborator',self.__asignareLab),
                 'afiseaza laboratoare student':Comanda('afiseaza laboratoare student',self.__showStudentLabs),
                 'vizualizare laboratoare':Comanda('vizualizare laboratoare',self.__showLabs)
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
                except ConsoleException as ce:
                    print(ce)
                except RepositoryException as re:
                    print(re)
                except ValueError:
                    print('Eroare in introducerea tipului de data!\n')
            else:
                print('Comanda invalida!\n')


