'''
Created on 29 Jan 2018

@author: ASUS
'''
from Service.ProduseService import ProduseService
from Repository.ProduseRepository import ProduseRepository

def testService():
    serv=ProduseService(ProduseRepository('testproduse.txt'))
    serv.adaugaProdus('23','sfs',3)
    assert serv.size()==1
    with open('testproduse.txt','w')as f:
        f.write('')