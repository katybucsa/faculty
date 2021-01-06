'''
Created on 12 Dec 2017

@author: ASUS
'''
from Repository.StudentRepository import StudentRepository
from Service.StudentService import StudentService
from Ui.Console import Console
from Teste.AllTests import runAll

if __name__ == '__main__':
    repo=StudentRepository('studenti.txt')
    serv=StudentService(repo)
    console=Console(serv)
    runAll()
    console.run()