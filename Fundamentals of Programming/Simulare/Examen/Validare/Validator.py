


class ValidatorException(Exception):
    '''
    creeaza clasa de exceptii pentru Validator
    '''
    pass


class Validator:
    
    
    def valideaza(self,student):
        '''
        Valideaza un student
        student-Student
        '''
        erori=''
        name=student.get_nume().split(' ')
        if len(name)<1:
            erori+='Nume student invalid!\n'
        if len(erori)==0:
            for nume in name:
                if len(nume)<3 and len(erori):
                    erori+='Nume student invalid!\n'
                    
        if student.get_nr_prezente()<0:
            erori+='Numar de prezente invalid!n'
        
        if student.get_nota()<1 or student.get_nota()>10:
            erori+='Nota invalida!\n'
            
        if len(erori):
            raise ValidatorException(erori)
            
