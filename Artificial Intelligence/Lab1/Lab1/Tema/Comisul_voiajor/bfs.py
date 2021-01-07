'''
Created on 16 Mar 2019

@author: Katy
'''

import sys
from turtledemo import clock
import datetime



def read_from_file(file,):
    cities=[]
    with open(file,"r") as f:
        nr_cities=int(f.readline()) 
        for line in f.readlines():
            line=line.strip()
            if(len(line)>0):
                cities.append(list(map(int,line.split(","))))
    return nr_cities, cities



def make_sum(path,cities):
    sum=0
    for i in range(0,len(path)-1):
        vect=cities[path[i]-1]
        sum+=vect[path[i+1]-1]
    vect=cities[path[len(path)-1]-1]
    sum+=vect[path[0]-1]
    return sum  



def BFS(nr,distances):
    solution=[]
    sum=sys.maxsize
    queue=[]
    x=0
    queue.append([])
    while len(queue):
        bottom=queue.pop(0)# ordinea oraselor vizitate
        if len(bottom)==nr:
            part_sum=make_sum(bottom,distances)
            if part_sum<sum:
                sum=part_sum
                solution=bottom
            print('solitie')
        else:
            for i in range(1, nr+1): # i - un oras
                if i not in bottom:
                    queue.append(bottom+[i])
                print("iteratia: ",x)
                x+=1
    return solution, sum


def write_to_file(file,nr,cities,length):
    with open(file,"w") as f:
        f.write(str(nr)+'\n')
        f.write(str(cities)+'\n')
        f.write(str(length)+'\n')
        
        
        
def run():
    print("Read file:")
    f=input()
    nr,distances=read_from_file(f)
    print(datetime.datetime.now())
    cities,length=BFS(nr,distances)
    write_to_file("12_out.txt",nr,cities,length)
    print(datetime.datetime.now())
run()    