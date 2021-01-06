n=int(input("n="))
x=1
k=0
while k<n:
    copie_x=x
    if copie_x<2:
        k=k+1
    for d in range(2,copie_x+1):
        if copie_x%d==0:
            while copie_x%d==0:
                copie_x//=d
            k=k+1
            if k==n:
                print (d)
    x=x+1
