adauga([],_,_,_,[]):-!.
adauga([H|T],E,P,P,[H,E|R]):-!,Pi is P*2-1,Pc is P+1,adauga(T,E,Pi,Pc,R).
adauga([H|T],E,Pi,Pc,[H|R]):-Pc1 is Pc+1,adauga(T,E,Pi,Pc1,R).

add(L,E,R):-adauga(L,E,3,1,R).

gen(N,M,[]):-M>2*N-1,!.
gen(N,M,[M|R]):-M1 is M+1,gen(N,M1,R).

inserare(E,[],[E]).
inserare(E,[H|T],[E,H|T]):- X is E-H,abs(X)=<2.
inserare(E,[H|T],L):-inserare(E,T,[H1|L1]),X is H-H1,abs(X)=<2,L=[H,H1|L1].



permut([],[]).
permut([H|T],R):-permut(T,R1),inserare(H,R1,R).

permutari(N,R):-gen(N,N,R1),findall(P,permut(R1,P),R).
