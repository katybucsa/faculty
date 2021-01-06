'''
Created on 29 Dec 2017

@author: ASUS
'''


def bun(x,dim):
    '''
    x - lista de indici
    i-int
    Returneaza True daca in x nu exista elemente duplicate si False in caz contrar
    ''' 
    a=x[dim-1]
    for i in range(0,dim-1):
        if x[i]==a:
            return False
    return True


def solutie(lista,x,dim):
    '''
    lista - lista elementelor pentru care se genereaza permutarile
    i -int
    x - lista indicilor 
    dim - dimensiunea listei
    Returneaza True daca permuterea elementelor din lista are aspect de munte si False in caz contrar
    '''
    i=0
    while lista[x[i]]<lista[x[i+1]] and i<dim-1:
        i+=1
        if i==dim-1:
            break
    if i==0 or i==dim-1:
        return False
    while lista[x[i]]>lista[x[i+1]] and i<dim-1:
        i+=1
        if i==dim-1:
            return True
    return False



def create(lista,x):
    '''
    lista - lista pentru care trebuie generate permutarile
    x - lista de indici 
    returneaza o permutare
    '''
    l=[]
    for i in range(0,len(lista)):
        l.append(lista[x[i]])
    return l[:]

    
    
def bktiter(lista):
    '''
    backtracking iterativ
    '''
    if len(lista)==1 or len(lista)==0:
        return []
    l=[]
    s=[]
    k=0
    for i in range(len(lista)):
        s.append(-1)
    while k>-1:
        if s[k]<len(lista)-1:
            s[k]+=1
            if bun(s,k+1):
                if k==len(lista)-1:
                    if solutie(lista, s, k+1):
                        l.append(create(lista, s))
                else:
                    k+=1        
        else:
            s[k]=-1
            k-=1
    return l
            
    
def bktrec(lista,x,dim,l=[]):
    '''
    backtracking recursiv
    '''
    if dim==1 or dim==0:
        return []
    if len(x)==dim:
        if solutie(lista,x,dim):
            l.append(create(lista, x))
    if len(x)>dim:
        return #stop recursion
    x.append(0)
    for i in range(0,dim):
        x[-1] = i
        if bun(x,len(x)):
            #continue only if x can conduct to a solution
            bktrec(lista,x,dim,l)
    x.pop()
    return l[:]
            
                    
def run():
    '''
    n - int, numarul de elemente ale listei
    lista - lista elementelor pentru care se genereaza permutarile
    '''
    n=int(input('Citeste numarul de elemente din lista:'))
    lista=[]
    s=[]
    print('Introduceti elementele listei:')
    for i in range(n):
        lista.append(int(input()))
    permutari=bktiter(lista)
    for el in permutari:
        print(el)
    print()
    al=bktrec(lista,[],n)
    for el in al:
        print(el)
    
def testBktIterativ():
    '''
    functie de testare pentru functia bktiter(backtracking iterativ)
    '''
    l=[1,2,3]
    assert bktiter(l)==[[1,3,2],[2,3,1]]
    l=[1,2]
    assert bktiter(l)==[]
    l=[1]
    assert bktiter(l)==[]
    l=[]
    assert bktiter(l)==[]
    

def testBktRecursiv():
    '''
    functie de testare pentru functia bktrec(backtracking recursiv)
    '''
    l=[0,1]
    assert bktrec(l,[],2)==[]
    l=[0,1,2]
    assert bktrec(l,[],0)==[]
    
    

def runAllTests():
    testBktIterativ()
    testBktRecursiv()

runAllTests()   
run()