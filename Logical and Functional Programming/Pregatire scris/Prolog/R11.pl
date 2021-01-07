f([],0).
f([H|T],S):-!, H mod 2 =:= 0, f(T,S1), S is S1*H.
f([_|T],S):-f(T,S1), S is S1.

produs(_,0,1):-!.
produs(E,N,R):-E1 is E+2,N1 is N-1,produs(E1,N1,R1),R is E*R1.


pro(N,R):-produs(2,N,R).

produs1(_,N,N,R,R):-!.
produs1(E,N,I,C,R):-I1 is I+1,C1 is C*E,E1 is E+2,produs1(E1,N,I1,C1,R).

pro1(N,R):-produs1(2,N,0,1,R).


combS([E|_],1,[E]).
combS([_|T],K,R):-combS(T,K,R).
combS([H|T],K,[H|R]):-K>1,K1 is K-1,combS(T,K1,R).


suma([],0):-!.
suma([H|T],R):-suma(T,R1),R is R1+H.

combSP(L,K,R):-combS(L,K,R),suma(R,S),mod(S,2)=:=0.

combSPara(L,K,R):-findall(X,combSP(L,K,X),R).
