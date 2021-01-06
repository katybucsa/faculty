n=int(input("n="))
x=n-1
perfect=0
while perfect==0 and x>0:
    Suma_divizori=0
    for d in range(1,x//2+1):
        if x%d==0:
            Suma_divizori=Suma_divizori+d
    if Suma_divizori==x:
        perfect=1
        print(x)
    x=x-1
if perfect==0:
    print("Nu exista numere perfecte mai mici decat n")
