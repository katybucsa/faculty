pereche(A,[B|_],[A,B]):-A<B.
pereche(A,[_|T],P):-pereche(A,T,P).


perechi([H|T],R):-pereche(H,T,R).
perechi([_|T],R):-perechi(T,R).


per(L,R):-findall(R1,perechi(L,R1),R).
