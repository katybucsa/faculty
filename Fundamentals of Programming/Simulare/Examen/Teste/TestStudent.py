'''
Created on 12 Dec 2017

@author: ASUS
'''
from Domain.Student import Student

def TesteazaStudent():
    '''
    Functie de testare pentru creare student
    '''
    st=Student('103','Mihai Andrei',13,9)
    assert st.get_nr_matricol()=='103'
    assert st.get_nume()=='Mihai Andrei'
    assert st.get_nr_prezente()==13
    assert st.get_nota()==9
    st.set_nume('Alex Bogdan')
    st.set_nr_prezente(8)
    st.set_nota(7)
    assert st.get_nume()=='Alex Bogdan'
    assert st.get_nr_prezente()==8
    assert st.get_nota()==7
    
    st1=Student('103','Alex Bogdan',12,7)
    assert st1==st