'''
Created on 27 Jan 2018

@author: ASUS
'''
from Repository.JucatorRepository import JucatorRepository
from Service.JucatorService import JucatorService
from UI.Console import Console

def f(a, b, c):
    a = a + 1
    b.append(3)
    c +=[3]
    
a = 7
b = [1, 2]
c = [1, 2]
f(a, b, c)
print(a, b, c)


if __name__ == '__main__':
    repo=JucatorRepository('jucatori.txt')
    service=JucatorService(repo)
    console=Console(service)
    console.run()
    
