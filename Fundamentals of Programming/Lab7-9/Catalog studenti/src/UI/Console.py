from Repository.Repository import RepositoryException
from Validare.Validator import ValidatorException




class ConsoleException(Exception):
    '''
    Creeaza clasa ConsoleException pentru Consola
    '''
    def __init__(self,*args,**kwargs):
        Exception.__init__(self,*args,**kwargs)
        
        

class Comanda:
    def __init__(self,numeCmd,functieCmd):
        self.__numeCmd=numeCmd
        self.__functieCmd=functieCmd
        
    def executa(self,params):
        self.__functieCmd(params)
        
        
class Console:
    '''
    Clasa responsabila cu interfata cu utilizatorul
    Se foloseste Service pentru a efectua operatiile de citire si scriere
    '''
    
    def __init__(self,servs,servd,servn):
        '''
        Initializare UI
        servs StudentService
        servd DisciplinaService
        servn NotaService
        '''
        self.__servs=servs
        self.__servd=servd
        self.__servn=servn
        
    def __citesteCmd(self):
        '''
        Citeste comanda
        return a list
        '''
        return input('>>').split()
    
    def __uiAfisare(self,lista):
        '''
        Realizeaza afisarea unei liste
        '''
        for elem in lista:
            print(elem)
        print()
    
    def __uiAdaugaStudent(self,params):
        '''
        Citeste un student si il adauga
        '''
        if len(params)!=3:
            raise ConsoleException('Numar de parametrii invalid!\n')
        idstud=int(params[0])
        nume=params[1]
        varsta=int(params[2])
        self.__servs.adaugaStudent(idstud,nume,varsta)
        print('Student adaugat cu succes!\n')
        
        
    def __uiModificaStudent(self,params):
        '''
        Citeste un student si modifica studentul existent la id-ul introdus
        '''
        if len(params)!=3:
            raise ConsoleException('Numar de parametrii invalid!\n')
        idstud=int(params[0])
        nume=params[1]
        varsta=int(params[2])
        st=self.__servs.modificaStudent(idstud,nume,varsta)
        self.__servn.modificaStNota(st)
        print('Student modificat cu succes!\n')
        
    def __uiStergeStudent(self,params):
        '''
        Citeste un id student si sterge studentul cu id-ul introdus
        '''
        if len(params)!=1:
            raise ConsoleException('Numar de parametrii invalid!\n')
        idstud=int(params[0])
        st=self.__servs.stergeStudent(idstud)
        self.__servn.stergeStNota(st)
        print('Student sters cu succes!\n')
        
        
    def __uiPrintStudentiOrd(self,params):
        '''
        Afiseaza o lista cu studenti ordonati descrescator dupa varsta
        '''
        al=self.__servs.getOrdonatVarsta()
        self.__uiAfisare(al)
        
    def __uiPrintStudentiByName(self,params):
        '''
        Citeste un nume si afiseaza o lista cu studentii care au numele introdus
        '''
        if len(params)!=1:
            raise ConsoleException('Numar de parametrii invalid!\n')
        nume=params[0]
        al=self.__servs.getAllWithName(nume)
        if len(al)==0:
            print('Nu exista studenti cu numele',nume,'!\n')
        else:
            self.__uiAfisare(al)
        
    def __uiCautaStudent(self,params):
        '''
        Citeste un string si afiseaza o lista cu studentii a caror nume contin acel string
        '''
        if len(params)!=1:
            raise ConsoleException('Numar de parametrii invalid!\n')
        criteriu=params[0]
        al=self.__servs.cautaStudent(criteriu)
        if len(al)==0:
            print('Nu exista student al carui nume sa contina',criteriu,'!\n')
        else:
            self.__uiAfisare(al)
        
         
    def __uiPrintStudenti(self,params):
        '''
        Afiseaza o lista cu toti studentii
        '''
        al=self.__servs.getAllStudents()
        self.__uiAfisare(al)
        
        
        
        
        
        
    def __uiAdaugaDisciplina(self,params):
        '''
        Citeste o disciplina si o adauga
        '''
        if len(params)!=3:
            raise ConsoleException('Numar de parametrii invalid!\n')
        idDisciplina=int(params[0])
        nume=params[1]
        profesor=params[2]
        self.__servd.adaugaDisciplina(idDisciplina,nume,profesor)
        print('Disciplina adaugata cu succes!\n')
        
    def __uiModificaDisciplina(self,params):
        '''
        Citeste o disciplina si modifica disciplina care are id-ul introdus
        '''
        if len(params)!=3:
            raise ConsoleException('Numar de parametrii invalid!\n')
        idDisciplina=int(params[0])
        nume=params[1]
        profesor=params[2]
        disc=self.__servd.modificaDisciplina(idDisciplina,nume,profesor)
        self.__servn.modificaDiscNota(disc)
        print('Disciplina modificata cu succes!\n')
        
        
    def __uiStergeDisciplina(self,params):
        '''
        Citeste un id si sterge disciplina care are id-ul introdus
        '''
        if len(params)!=1:
            raise ConsoleException('Numar de parametrii invalid!\n')
        idDisciplina=int(params[0])
        disc=self.__servd.stergeDisciplina(idDisciplina)
        self.__servn.stergeDiscNota(disc)
        print('Disciplina stearsa cu succes!\n')
            
    def __uiPrintDiscipline(self,params):
        '''
        Afiseaza o lista cu toate disciplinele
        '''
        al=self.__servd.getAllDiscipline()
        self.__uiAfisare(al)
        
    def __uiCautaDisciplina(self,params):
        '''
        Citeste  un string si afiseaza o lista cu disciplinela a caror nume contin string-ul introdus
        '''
        if len(params)!=1:
            raise ConsoleException('Numar de parametrii invalid!\n')
        crit=str(params[0])
        al=self.__servd.cautaDisciplina(crit)
        if len(al)==0:
            print('Nu exista disciplina a carei nume sa contina',crit,'!\n')
        else:
            self.__uiAfisare(al)
    



         
    def __uiAsignareNota(self,params):
        '''
        Citeste  o nota si o asigneaza unui student la o disciplina
        '''
        if len(params)!=3:
            raise ConsoleException('Numar de parametrii invalid!\n')
        idstud=int(params[0])
        iddisc=int(params[1])
        nota=float(params[2])
        st=self.__servs.gasesteStudent(idstud)
        disc=self.__servd.gasesteDisciplina(iddisc)
        self.__servn.asignareNota(st,disc,nota)    
        print('Nota asignata cu succes!\n')
             
    def __uiPrintNote(self,params):
        '''
        Afiseaza o lista cu toate notele asignate studentilor
        '''
        al=self.__servn.getAllGrades()
        self.__uiAfisare(al) 
        
        
        
    def __uiPrintSumId(self,params):
        '''
        Afiseaza o lista cu numele tuturor profesorilor si suma corespunzatoare pentru toate disciplinele pe care le preda
        '''
        al=self.__servd.cautaProfesor()
        self.__uiAfisare(al)
        
        
    def __uiNoteAlfabetic(self,params):
        '''
        Citeste numele unei discipline
        Afiseaza lista studentilor si notele acestora la disciplina precizata ordonte alfabetic dupa nume student
        '''
        if len(params)!=1:
            raise ConsoleException('Numar de parametri invalid!\n')
        disc=params[0]
        k=1
        al=self.__servn.getNoteOrd(disc,k)
        if len(al)==0:
            print('Nu exista note la disciplina',disc,'!\n')
        else:
            self.__uiAfisare(al)
        
    def __uiNoteAsc(self,params):
        '''
        Primeste ca parametru numele unei discipline 
        Afiseaza lista studentilor si notele acestora la disciplina precizata ordonte ascendent dupa nota
        '''
        if len(params)!=1:
            raise ConsoleException('Numar de parametri invalid!\n')
        disc=params[0]
        k=0
        al=self.__servn.getNoteOrd(disc,k)
        if len(al)==0:
            print('Nu exista note la disciplina',disc,'!\n')
        else:
            self.__uiAfisare(al)
    
    def __uiTopStud(self,params):
        '''
        Afiseaza o lista cu primii 20% dintre studenti ordonati descrescator dupa media la toate disciplinele
        '''
        al=self.__servn.topStud()
        self.__uiAfisare(al)
        
    def __uiMediaClasei(self,params):
        '''
        Afiseaza o lista cu media clasei la fiecare disciplina, ordonata descrescator dupa medie
        '''
        al=self.__servn.getMediaClasei()
        self.__uiAfisare(al)
        
         
    def run(self):
        '''
        Start UI
        '''
        
        comenzi={'adaugaStudent':Comanda('adaugaStudent', self.__uiAdaugaStudent),
                 'modificaStudent':Comanda('modificaStudent',self.__uiModificaStudent),
                 'stergeStudent':Comanda('stergeStudent',self.__uiStergeStudent),
                 'adaugaDisciplina':Comanda('adaugaDisciplina',self.__uiAdaugaDisciplina),
                 'modificaDisciplina':Comanda('modificaDisciplina',self.__uiModificaDisciplina),
                 'stergeDisciplina':Comanda('stergeDisciplina',self.__uiStergeDisciplina),
                 'afiseazaStudenti':Comanda('afiseazaStudenti',self.__uiPrintStudenti),
                 'afiseazaDiscipline':Comanda('afiseazaDiscipline',self.__uiPrintDiscipline),
                 'afiseazaStudentiDupaVarsta':Comanda('afiseazaStudentiOrdonatiDescrescatorDupaVarsta',self.__uiPrintStudentiOrd),
                 'afiseazaStudentiCuNumele':Comanda('afiseazaStudentiCuNumele',self.__uiPrintStudentiByName),
                 'cautaStudent':Comanda('cautaStudent',self.__uiCautaStudent),
                 'cautaDisciplina':Comanda('cautaDisciplina',self.__uiCautaDisciplina),
                 'asigneazaNota':Comanda('asigneazaNota',self.__uiAsignareNota),
                 'afiseazaNote':Comanda('afiseazaNote',self.__uiPrintNote),
                 'afiseazaSumaId':Comanda('afiseazaSumaId',self.__uiPrintSumId),
                 'noteLaDisc1':Comanda('noteLaDisciplina1',self.__uiNoteAlfabetic),
                 'noteLaDisc2':Comanda('notaLaDisciplina2',self.__uiNoteAsc),
                 'topStudenti':Comanda('topStudenti',self.__uiTopStud),
                 'afiseazaMediaClasei':Comanda('afiseazaMediaClasei',self.__uiMediaClasei)
            }
        while True:
            cmd=self.__citesteCmd()
            if len(cmd)==0:
                continue
            if len(cmd[0])==0:
                continue
            if cmd[0]=='exit':
                print('Bye!')
                return
            if cmd[0] in comenzi:
                try:
                    comenzi[cmd[0]].executa(cmd[1:])
                except RepositoryException as re:
                    print(str(re))
                except ValidatorException as ve:
                    print(str(ve))
                except ConsoleException as ce:
                    print(str(ce))
                except ValueError:
                    print('Eroare in introducerea tipului de data!\n')
            else:
                print('Comanda invalida!\n')
            
        
        
        
        
            