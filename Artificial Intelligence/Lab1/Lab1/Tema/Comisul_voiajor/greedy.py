'''
Created on 17 Mar 2019

@author: Katy
'''
import sys


class Node:
    def __init__(self,neigh):
        self.neigh=neigh
        self.visited=False

def read_from_file_greedy(file):
    cities=[]
    with open(file,"r") as f:
        nr_cities=int(f.readline()) 
        for line in f.readlines():
            line=line.strip()
            if(len(line)>0):
                cities.append(Node(list(map(int,line.split(",")))))
    return nr_cities, cities


def min_dist(vertex,cities):
    c=cities[vertex-1] 
    mini=sys.maxsize
    city=1   
    for i in range(0,len(c.neigh)):
        if cities[i].visited==False and c.neigh[i]<mini:
            city=i+1
            mini=c.neigh[i]
    return mini,city
    


def greedy(cities):
    path=[1]
    s=0
    cities[0].visited=True
    while len(path)<len(cities):
        c,nextt=min_dist(path[len(path)-1],cities)
        s+=c
        path.append(nextt)
        cities[nextt-1].visited=True
    last=path[len(path)-1]
    s+=cities[0].neigh[last-1]
    return s,path 


def write_to_file_greedy(nr,s,ord,file):
    with open(file,"w") as f:
        f.write(str(nr)+'\n')
        f.write(str(ord)+'\n')
        f.write(str(s)+'\n')

    
    
def run_greedy():
    print("Read file:")
    nr,cities=read_from_file_greedy(input())
    s,ord=greedy(cities)
    write_to_file_greedy(nr, s,ord, "12_out.txt")
    
    
run_greedy()
    