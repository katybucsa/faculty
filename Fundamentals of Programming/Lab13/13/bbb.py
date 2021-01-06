'''
Created on 18 Jan 2018

@author: ASUS
'''



def f(l):
    l.append('sda')
    print(id(l))
    l=l+[5]
    print(id(l))

l=[3,5,3,'3']
f(l)
print(id(l))