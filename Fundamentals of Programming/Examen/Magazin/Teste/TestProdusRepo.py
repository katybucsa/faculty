'''
Created on 29 Jan 2018

@author: ASUS
'''
from Repository.ProduseRepository import ProduseRepository, RepositoryException
from Domain.Produs import Produs

def testRepo():
    repo=ProduseRepository('testproduse.txt')
    produs=Produs('1a','ciocolata',2)
    produs1=Produs('1b','suc',7)
    assert repo.size()==0
    repo.adauga(produs)
    assert repo.size()==1
    repo.adauga(produs1)
    assert repo.size()==2
    try:
        repo.adauga(produs)
        assert False
    except RepositoryException:
        assert True
    repo.sterge(produs)
    with open('testproduse.txt','w')as f:
        f.write('')
    
    
    
testRepo()
