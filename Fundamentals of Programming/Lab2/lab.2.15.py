n=int(input("n="))
x=n-1
prim=0
while prim==0 and x>1:
    prim=1
    for d in range(2,x//2+1):
        if x%d==0:
            prim=0
    if prim==1:
        print(x)
    x=x-1
if prim==0:
    print("Nu exista numere prime mai mici decat n")
