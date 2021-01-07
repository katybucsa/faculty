f([],_,0).
f([H|T],V,P):-!,H>V,f(T,V,P1),P=P1*V.
f([_|T],V,P):-f(T,V,P).


detMax([E],E):-!.
detMax([H|T],M):-detMax(T,M),M>H,!.
detMax([H|_],H).

elimina([],_,[]):-!.
elimina([E|T],E,R):-!,elimina(T,E,R).
elimina([H|T],E,[H|R]):-elimina(T,E,R).


elMax([],[]):-!.
elMax([H|T],[H|R]):-number(H),!,elMax(T,R).
elMax([H|T],R):-detMax(H,M),elimina(H,M,R1),elMax(T,R2),R=[R1|R2].

suma(_,0,0):-!.
suma(X,N,S):-mod(X,3)=:=0,!,X1 is X+1,N1 is N-1,suma(X1,N1,S1),S is S1+X.
suma(X,N,S):-X1 is X+1,suma(X1,N,S).

sum(N,S):-suma(1,N,S).

suma1(_,0,0):-!.
suma1(X,N,S):-X1 is X+3,N1 is N-1,suma1(X1,N1,S1),S is X+S1.

sum1(N,S):-suma1(3,N,S).



s1(N,M,R,R):-M>N,!.
s1(N,I,S,R):-I1 is I+1,S1 is S+I,s1(N,I1,S1,R).

s(N,S):-s1(N,0,0,S).
