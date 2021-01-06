'''
Created on 29 Jan 2018

@author: ASUS
'''
from Teste.TestProdus import testProdus
from Teste.TestProdusRepo import testRepo
from Teste.testService import testService

def runAll():
    testProdus()
    testRepo()
    testService()