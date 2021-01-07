putere(_,0,1):-!.
putere(X,N,R):-N1 is N-1,putere(X,N1,R1),R is X*R1.

p_aux(_,N,N,R,R):-!.
p_aux(X,N,I,C,R):-I1 is I+1,C1 is C*X,p_aux(X,N,I1,C1,R).


p(_0,1):-!.
p(X,N,R):-p_aux(X,N,0,1,R).


inserare(E,L,[E|L]).
inserare(E,[H|T],[H|L]):-inserare(E,T,L).


aranjS([E|_],1,E,[E]).
aranjS([_|T],K,S,R):-aranjS(T,K,S,R).
aranjS([H|T],K,S,R):-K>1,K1 is K-1,S>H,S1 is S-H,aranjS(T,K1,S1,R1),inserare(H,R1,R).
toateAS(L,K,S,R):-findall(A,aranjS(L,K,S,A),R).
