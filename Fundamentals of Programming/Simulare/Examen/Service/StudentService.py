from Domain.Student import Student 
from Validare.Validator import Validator
from audioop import reverse

class StudentService:
    def __init__(self,repo):
        '''
        repo- Student Repository
        '''
        self.__repo=repo
        self.__valid=Validator()
    
    
    def adaugaStudent(self,nr_mat,nume,nr_prezente,nota):
        '''
        nr_mat,string,numar matricol
        nume-string,nume student
        nr_prezente-int
        nota-float
        creeaza,valideazasi adauga un student
        '''
        st=Student(nr_mat,nume,nr_prezente,nota)
        self.__valid.valideaza(st)
        self.__repo.add(st)
        
    def adaugaBonus(self,nr_prez,nr_pct):
        '''
        nr_prez-int
        nr_pct-float
        adauga nr_pct la nota pentru studentii care au numarul de prezente>=nr_prez
        '''
        al=self.__repo.getAll()
        for st in al:
            if st.get_nr_prezente()>=nr_prez:
                nota=st.get_nota()+nr_pct 
                if nota>10:
                    nota=10
                st.set_nota(nota)
        self.__repo.modifica()
        
    def sorteaza(self):
        '''
        sorteaza lista studentilor crescator după raportul nr prezențe / notă
        '''
        al=self.__repo.getAll()
        al.sort(key=lambda x:x.get_nr_prezente()/x.get_nota(),reverse=False)
        return al

