n=int(input("n="))
p=1
for d in range(2,n):
    print (d," ")
    if n%d==0:
        p=p*d
print(p)
