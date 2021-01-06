n=int(input("n="))
m=0
k=0
for i in range(9,-1,-1):
    c=n
    k=0
    while c:
        if c%10==i:
            k=k+1
        c=c//10
    while k:
        m=m*10+i
        k=k-1
print (m)
