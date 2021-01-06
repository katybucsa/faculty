n=int(input("n="))
l=[]
lmax=0
l1=0
p1=0
p2=0
for i in range(0,n):
    x=int(input())
    l.append(x)
for i in range(0,n):
    if l[i]%2==0:
        if l1==0:
            p1=i
        l1=l1+1
    else:
        p2=i-1
        if l1>lmax:
            lmax=l1
            a=p1
            b=p2
        l1=0
if l[n-1]%2==0:
    if l1!=0:
        if l1>lmax:
            lmax=l1
            a=p1
            b=n-1
    else:
        l1=1
        print(l[n-1])
for i in range(a,b+1):
    print (l[i])

            
