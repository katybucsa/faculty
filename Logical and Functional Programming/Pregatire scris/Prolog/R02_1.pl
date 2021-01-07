detMin([H],H):-!.
detMin([H|T],M):-detMin(T,M),M<H,!.
detMin([H|_],H).

elim(_,[],[]):-!.
elim(E,[E|T],R):-elim(E,T,R),!.
elim(E,[H|T],[H|R]):-elim(E,T,R).

multime([],[]):-!.
multime(L,[M|R]):-detMin(L,M),elim(M,L,L1),multime(L1,R).


inserare(E,L,[E|L]).
inserare(E,[H|T],[H|L]):-inserare(E,T,L).


aranjP([H|_],1,H,[H]).
aranjP([_|T],K,P,A):-aranjP(T,K,P,A).
aranjP([H|T],K,P,A):-K>1,K1 is K-1,mod(P,H)=:=0, P1 is P/H, aranjP(T,K1,P1,A1),inserare(H,A1,A).

toateA(L,K,P,A):-findall(R,aranjP(L,K,P,R),A).
