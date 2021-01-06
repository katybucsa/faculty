n=int(input("n="))
m=0
k=0
for i in [1,0,2,3,4,5,6,7,8,9]:
    copie_n=n
    while copie_n:
        if copie_n%10==i:
            k=k+1
        copie_n//=10
    while k:
        m=m*10+i
        k=k-1
print (m)
    
