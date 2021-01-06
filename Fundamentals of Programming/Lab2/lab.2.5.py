n=int(input("n="))
ok=0
prim=0
if n<=1:
    p1=2
else:
    p1=n+1
while prim==0:
    prim=1
    for d in range(2,p1//2+1):
        if p1%d==0:
            prim=0
    if prim==0 and p1>1:
        p1=p1+1
p2=p1+2
while ok==0:
    prim=1
    for d in range(2,p2//2+1):
        if p2%d==0:
            prim=0
    if prim==1 and p2>1:
        if p2-p1==2:
            ok=1
        else:
            p1=p2
            p2=p2+1
    else:
        p2=p2+1
print (p1," ",p2)
    
