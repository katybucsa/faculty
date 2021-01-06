'''
Created on 29 Jan 2018

@author: ASUS
'''
from Repository.ProduseRepository import ProduseRepository
from Service.ProduseService import ProduseService
from UI.Console import Console
from Teste.allTests import runAll

if __name__ == '__main__':
    repo=ProduseRepository('produse.txt')
    service=ProduseService(repo)
    console=Console(service)
    runAll()
    console.run()