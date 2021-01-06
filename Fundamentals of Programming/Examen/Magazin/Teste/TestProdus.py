'''
Created on 29 Jan 2018

@author: ASUS
'''

from Domain.Produs import Produs

def testProdus():
    produs=Produs('1a','ciocolata',2)
    assert produs.get_idp()=='1a'
    assert produs.get_denumire()=='ciocolata'
    assert produs.get_pret()==2
    produs1=Produs('1a','suc',7)
    assert produs1==produs 

