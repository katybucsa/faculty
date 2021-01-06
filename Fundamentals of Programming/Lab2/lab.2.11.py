n1=int(input("n1="))
n2=int(input("n2="))
k=1
while n1:
    copie_n2=n2
    ok=0
    while copie_n2:
        if n1%10==copie_n2%10:
            ok=1
        copie_n2//=10
    if ok==0:
        k=0
    n1//=10
if k==1:
    print ("n1 si n2 au proprietatea P")
else:
    print ("n1 si n2 nu au proprietatea P")
