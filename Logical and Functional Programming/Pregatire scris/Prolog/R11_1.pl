inserare(E,[],[E]).
inserare(E,[H|T],[E,H|T]):-S is E-H,abs(S)=<3.
inserare(E,[H|T],R):-inserare(E,T,[H1|R1]),S is H-H1,abs(S)=<3,R=[H,H1|R1].


permutare([],[]).
permutare([H|T],R):-permutare(T,R1),inserare(H,R1,R).


permutari(L,R):-findall(X,permutare(L,X),R).
