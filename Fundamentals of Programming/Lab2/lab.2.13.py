n=int(input("n="))
x=1
k=0
while k<n:
    copie_x=x
    prim=1
    for d in range(2,copie_x//2+1):
        if copie_x%d==0:
            prim=0
    if prim==1 or copie_x<1:
        k=k+1
        if k==n:
            print(copie_x)
    else:
        for d in range(2,copie_x+1):
            if copie_x%d==0:
                while copie_x%d==0:
                    copie_x//=d
                for j in range(0,d):
                    k=k+1
                    if k==n:
                        print (d)
    x=x+1
