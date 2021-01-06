n=int(input("n="))
ok=0
x=n+1
while ok==0:
    prim=1
    for d in range(2,x//2):
        if x%d==0:
            prim=0
    if prim==1 and x>1:
        ok=1
    else:
        x=x+1
print (x)
