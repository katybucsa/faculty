

class ValidatorException(Exception):
    '''
    Creeaza clasa ValidatorException
    '''
    def __init__(self,*args,**kwargs):
        Exception.__init__(self,*args,**kwargs)
    
 

class ValidatorStudent:
    '''
    Clasa responsabila pentru validarea studentilor
    '''
    
    def __init__(self):
        self.__eroriS=[]
        
    
    
    def __len__(self):
        '''
        Returneaza o lista de erori
        '''
        return len(self.__eroriS)
    
    
    def valideazaStudent(self,student):
        '''
        Valideaza student
        raise ValidatorException(erori) daca id, nume sau varsta nu sunt corecte
        '''
        erori=''
        if student.getNumeSt()=='':
                erori+='Nume student invalid!\n'
        elif type(student.getNumeSt()) in [int,float]:
            erori+='Numele studentului nu poate fi un numar!\n'
        elif type(student.getNumeSt()) is str:
            if any(i.isdigit() for i in student.getNumeSt()):
                erori+='Numele studentului nu poate contine cifre!\n'
        if student.getIdstud()<0:
            erori+='Id student invalid!\n'
        if student.getVarsta()<17 or student.getVarsta()>100:
            erori+='Varsta student invalida!\n'
        if len(erori):
            self.__eroriS.append(erori)
            raise ValidatorException(erori)
        
            
class ValidatorDisciplina:
    
    def __init__(self):
        self.__eroriD=[]
            
        
    def __len__(self):
        '''
        Returneaza o lista de erori
        '''
        return len(self.__eroriD)    
        
        
    def valideazaDisciplina(self,disciplina):
        '''
        Valideaza disciplina
        raise ValidatorException(erori) daca id, nume sau profesor nu sunt corecte
        '''
        erori=''
        if type(disciplina.getNumeD()) is str:
            if any(i.isdigit() for i in disciplina.getNumeD()):
                erori+='Numele disciplinei nu poate contine cifre!\n'
        else:
            erori+='Numele disciplinei nu poate fi un numar!\n'
        if type(disciplina.getProfesor()) is str:
            if any(i.isdigit() for i in disciplina.getProfesor()):
                erori+='Numele profesorului nu poate contine cifre!\n'
        else:
            erori+='Numele profesorului nu poate fi un numar!\n'
        if len(erori):
            self.__eroriD.append(erori)
            raise ValidatorException(erori)
        else:
            if disciplina.getIdDisciplina()<0:
                erori+='Id disciplina invalid!\n'
            if disciplina.getNumeD()=='':
                erori+='Numele disciplinei este invalid!\n'
            if disciplina.getProfesor()=='':
                erori+='Numele profesorului este invalid!\n'
            if len(erori):
                self.__eroriD.append(erori)
                raise ValidatorException(erori)
        
        
class ValidatorNota:
    '''
    Valideaza note
    '''
    
    def __init__(self):
        self.__eroriN=[]
        
        
    def __len__(self):
        return len(self.__eroriN) 
    
    def valideazaNota(self,nota):
        '''
        Validare nota
        nota - nota student
        raise ValidatorException daca nota nu e cuprinsa intre 0 si 10 
        '''
        if nota.getNota()<=0 or nota.getNota()>10:
            self.__eroriN.append('Nota invalida!\n')
            raise ValidatorException('Nota invalida!\n')
    
    